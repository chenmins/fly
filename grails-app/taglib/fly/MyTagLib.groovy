package fly

import grails.util.TypeConvertingMap
import grails.web.mapping.UrlMapping
import org.springframework.web.servlet.support.RequestContextUtils

import java.text.SimpleDateFormat
import tv.xza.fly.*

class MyTagLib {
//    static defaultEncodeAs = [taglib:'html']
    static defaultEncodeAs = [taglib:'none']
    //static encodeAsForTags = [tagName: [taglib:'html'], otherTagName: [taglib:'none']]
//    static encodeAsForTags = [tagName: 'raw']
    static namespace = "my"

    boolean isLogin(){
        return session.user!=null;
    }

    boolean isAdmins(){
        if(!isLogin())
            return false;
        return session.user.role==Roles.admin;
    }

    def isLogin={attr,body->
        if(isLogin()){
            out <<  body()
        }
    }

    def notLogin={attr,body->
        if(!isLogin()){
            out <<  body()
        }
    }

    def isAdmin={attr,body->
        if(isAdmins()){
            out <<  body()
        }
    }

    def topColumn={attr, body ->
        def cols = Column.findAllByTop(true);
        cols.each {col ->
            out <<  body(col)
        }
    }

    def topTopic={attr, body ->
        def max = attr.max ?: 5
        def offset = attr.offset ?: 0
        def var = attr.var ?: "topic"
        def cols = Topic.findAllByTop(true,[max: max, offset:offset, sort: "id", order: "desc"]);
        cols.each {col ->
            out <<  body((var):col)
        }
    }

    def firstTopic={attr, body ->
        def max = attr.max ?: 15
        def offset = attr.offset ?: 0
        def var = attr.var ?: "topic"
        def cols = Topic.findAllByTop(false,[max: max, offset:offset, sort: "id", order: "desc"]);
        cols.each {col ->
            out <<  body((var):col)
        }
    }

    def hotTopic={attr, body ->
        def max = attr.max ?: 5
        def offset = attr.offset ?: 0
        def var = attr.var ?: "topic"
        def cols = Topic.findAllByHot(true,[max: max, offset:offset, sort: "id", order: "desc"]);
        cols.each {col ->
            out <<  body((var):col)
        }
    }

    def topic = { attr, body ->
        if(attr.id==null)
            return;
        def var = attr.var ?: "topic"
        def topic = Topic.get(attr.id)
        out << body((var):topic)
    }

    def isOwner = { attr, body ->
        if(attr.id==null)
            return;
        if(session.user == null)
            return
        if(attr.id != session.user.id)
            return
        out << body()
    }

    def hasAuthority = { attr, body ->
        if(attr.id==null)
            return;
        if(session.user == null)
            return
        if(session.user.role == Roles.admin){
            out << body()
            return
        }
        if(attr.id != session.user.id)
            return
        out << body()
    }

    def replyGood = { attr, body ->
        if(attr.id==null)
            return;
        def var = attr.var ?: "reply"
        def reply = TopicReply.findByTopicAndGood(Topic.get(attr.id),true)
        if(reply!=null)
            out << body((var):reply)
    }

    def replyOther = { attr, body ->
        if(attr.id==null)
            return;
        def var = attr.var ?: "reply"
        def max = attr.max ?: 5
        def offset = attr.offset ?: 0
        def reply = TopicReply.findAllByTopicAndGood(Topic.get(attr.id),false,
                [max: max, offset:offset, sort: "id", order: "desc"]
        )
        reply.each { r ->
            out << body((var):r)
        }
    }

    def user = { attr, body ->
        if(attr.id==null)
            return;
        def var = attr.var ?: "user"
        def user = User.get(attr.id)
        out << body((var):user)
    }

    def smileDate = { attr, body ->
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        def date1 =  attr.date
        def date2 = new Date()
        def date3 = date2.getTime()-date1.getTime()  //时间差的毫秒数
        //计算出相差天数
        int days=Math.floor(date3/(24*3600*1000))
        //计算出小时数
        def leave1=date3%(24*3600*1000)    //计算天数后剩余的毫秒数
        int hours=Math.floor(leave1/(3600*1000))
        //计算相差分钟数
        def leave2=leave1%(3600*1000)        //计算小时数后剩余的毫秒数
        int minutes=Math.floor(leave2/(60*1000))
        //计算相差秒数
        def leave3=leave2%(60*1000)      //计算分钟数后剩余的毫秒数
        int seconds=Math.round(leave3/1000)
//        def ass = ("a相差 "+days+"天 "+hours+"小时 "+minutes+" 分钟"+seconds+" 秒")
        def ass = null
        if(days<8  )
            ass = days+"天前"
        if(days==0  )
            ass = hours+"小时前"
        if(days==0 && hours==0 )
            ass = minutes+"分钟前"
        if(days==0 && hours==0 && minutes==0)
            ass = seconds+"秒前"
        if(ass==null)
            ass = sdf.format(date1)
        out << ass
    }

    def dateFormat={attr,body->
        if(attr.dateString){
            if(attr.dateString==~/^\d{14}$/){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
                try{
                    out<<sdf.parse(attr.dateString).format("yyyy年MM月dd日 HH:mm:ss");
                }catch(Exception e){
                    out<<attr.dateString;
                }
            }else{
                out<<attr.dateString;
            }
        }
    }


    /**
     * Creates next/previous links to support pagination for the current controller.<br/>
     *
     * &lt;g:paginate total="${Account.count()}" /&gt;<br/>
     *
     * @emptyTag
     *
     * @attr total REQUIRED The total number of results to paginate
     * @attr action the name of the action to use in the link, if not specified the default action will be linked
     * @attr controller the name of the controller to use in the link, if not specified the current controller will be linked
     * @attr id The id to use in the link
     * @attr params A map containing request parameters
     * @attr prev The text to display for the previous link (defaults to "Previous" as defined by default.paginate.prev property in I18n messages.properties)
     * @attr next The text to display for the next link (defaults to "Next" as defined by default.paginate.next property in I18n messages.properties)
     * @attr omitPrev Whether to not show the previous link (if set to true, the previous link will not be shown)
     * @attr omitNext Whether to not show the next link (if set to true, the next link will not be shown)
     * @attr omitFirst Whether to not show the first link (if set to true, the first link will not be shown)
     * @attr omitLast Whether to not show the last link (if set to true, the last link will not be shown)
     * @attr max The number of records displayed per page (defaults to 10). Used ONLY if params.max is empty
     * @attr maxsteps The number of steps displayed for pagination (defaults to 10). Used ONLY if params.maxsteps is empty
     * @attr offset Used only if params.offset is empty
     * @attr mapping The named URL mapping to use to rewrite the link
     * @attr fragment The link fragment (often called anchor tag) to use
     */
    Closure paginate2 = { Map attrsMap ->
        TypeConvertingMap attrs = (TypeConvertingMap)attrsMap
        def writer = out
        if (attrs.total == null) {
            throwTagError("Tag [paginate] is missing required attribute [total]")
        }

        def messageSource = grailsAttributes.messageSource
        def locale = RequestContextUtils.getLocale(request)

        def total = attrs.int('total') ?: 0
        def offset = params.int('offset') ?: 0
        def max = params.int('max')
        def maxsteps = (attrs.int('maxsteps') ?: 10)

        if (!offset) offset = (attrs.int('offset') ?: 0)
        if (!max) max = (attrs.int('max') ?: 10)

        Map linkParams = [:]
        if (attrs.params instanceof Map) linkParams.putAll((Map)attrs.params)
        linkParams.offset = offset - max
        linkParams.max = max
        if (params.sort) linkParams.sort = params.sort
        if (params.order) linkParams.order = params.order

        Map linkTagAttrs = [:]
        def action
        if (attrs.containsKey('mapping')) {
            linkTagAttrs.mapping = attrs.mapping
            action = attrs.action
        } else {
            action = attrs.action ?: params.action
        }
        if (action) {
            linkTagAttrs.action = action
        }
        if (attrs.controller) {
            linkTagAttrs.controller = attrs.controller
        }
        if (attrs.containsKey(UrlMapping.PLUGIN)) {
            linkTagAttrs.put(UrlMapping.PLUGIN, attrs.get(UrlMapping.PLUGIN))
        }
        if (attrs.containsKey(UrlMapping.NAMESPACE)) {
            linkTagAttrs.put(UrlMapping.NAMESPACE, attrs.get(UrlMapping.NAMESPACE))
        }
        if (attrs.id != null) {
            linkTagAttrs.id = attrs.id
        }
        if (attrs.fragment != null) {
            linkTagAttrs.fragment = attrs.fragment
        }
        linkTagAttrs.params = linkParams

        // determine paging variables
        def steps = maxsteps > 0
        int currentstep = ((offset / max) as int) + 1
        int firststep = 1
        int laststep = Math.round(Math.ceil(total / max)) as int

        // display previous link when not on firststep unless omitPrev is true
        if (currentstep > firststep && !attrs.boolean('omitPrev')) {
            linkTagAttrs.put('class', 'laypage-prev')
            linkParams.offset = offset - max
            writer << callLink((Map)linkTagAttrs.clone()) {
                (attrs.prev ?: messageSource.getMessage('paginate.prev', null, messageSource.getMessage('default.paginate.prev', null, 'Previous', locale), locale))
            }
        }

        // display steps when steps are enabled and laststep is not firststep
        if (steps && laststep > firststep) {
            linkTagAttrs.put('class', 'step')

            // determine begin and endstep paging variables
            int beginstep = currentstep - (Math.round(maxsteps / 2.0d) as int) + (maxsteps % 2)
            int endstep = currentstep + (Math.round(maxsteps / 2.0d) as int) - 1

            if (beginstep < firststep) {
                beginstep = firststep
                endstep = maxsteps
            }
            if (endstep > laststep) {
                beginstep = laststep - maxsteps + 1
                if (beginstep < firststep) {
                    beginstep = firststep
                }
                endstep = laststep
            }

            // display firststep link when beginstep is not firststep
            if (beginstep > firststep && !attrs.boolean('omitFirst')) {
                linkParams.offset = 0
                writer << callLink((Map)linkTagAttrs.clone()) {firststep.toString()}
            }
            //show a gap if beginstep isn't immediately after firststep, and if were not omitting first or rev
            if (beginstep > firststep+1 && (!attrs.boolean('omitFirst') || !attrs.boolean('omitPrev')) ) {
                writer << '<span class="step gap">..</span>'
            }

            // display paginate steps
            (beginstep..endstep).each { int i ->
                if (currentstep == i) {
                    writer << "<span class=\"laypage-curr\">${i}</span>"
                }
                else {
                    linkParams.offset = (i - 1) * max
                    writer << callLink((Map)linkTagAttrs.clone()) {i.toString()}
                }
            }

            //show a gap if beginstep isn't immediately before firststep, and if were not omitting first or rev
            if (endstep+1 < laststep && (!attrs.boolean('omitLast') || !attrs.boolean('omitNext'))) {
                writer << '<span class="step gap">..</span>'
            }
            // display laststep link when endstep is not laststep
            if (endstep < laststep && !attrs.boolean('omitLast')) {
                linkParams.offset = (laststep - 1) * max
                writer << callLink((Map)linkTagAttrs.clone()) { laststep.toString() }
            }
        }

        // display next link when not on laststep unless omitNext is true
        if (currentstep < laststep && !attrs.boolean('omitNext')) {
            linkTagAttrs.put('class', 'laypage-next')
            linkParams.offset = offset + max
            writer << callLink((Map)linkTagAttrs.clone()) {
                (attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, messageSource.getMessage('default.paginate.next', null, 'Next', locale), locale))
            }
        }
    }

    /**
     * Creates next/previous links to support pagination for the current controller.<br/>
     *
     * &lt;g:paginate total="${Account.count()}" /&gt;<br/>
     *
     * @emptyTag
     *
     * @attr total REQUIRED The total number of results to paginate
     * @attr action the name of the action to use in the link, if not specified the default action will be linked
     * @attr controller the name of the controller to use in the link, if not specified the current controller will be linked
     * @attr id The id to use in the link
     * @attr params A map containing request parameters
     * @attr prev The text to display for the previous link (defaults to "Previous" as defined by default.paginate.prev property in I18n messages.properties)
     * @attr next The text to display for the next link (defaults to "Next" as defined by default.paginate.next property in I18n messages.properties)
     * @attr omitPrev Whether to not show the previous link (if set to true, the previous link will not be shown)
     * @attr omitNext Whether to not show the next link (if set to true, the next link will not be shown)
     * @attr omitFirst Whether to not show the first link (if set to true, the first link will not be shown)
     * @attr omitLast Whether to not show the last link (if set to true, the last link will not be shown)
     * @attr max The number of records displayed per page (defaults to 10). Used ONLY if params.max is empty
     * @attr maxsteps The number of steps displayed for pagination (defaults to 10). Used ONLY if params.maxsteps is empty
     * @attr offset Used only if params.offset is empty
     * @attr mapping The named URL mapping to use to rewrite the link
     * @attr fragment The link fragment (often called anchor tag) to use
     */
    Closure paginate = { Map attrsMap ->
        TypeConvertingMap attrs = (TypeConvertingMap)attrsMap
        def writer = out
        if (attrs.total == null) {
            throwTagError("Tag [paginate] is missing required attribute [total]")
        }

        def messageSource = grailsAttributes.messageSource
        def locale = RequestContextUtils.getLocale(request)

        def total = attrs.int('total') ?: 0
        def offset = params.int('offset') ?: 0
        def max = params.int('max')
        def maxsteps = (attrs.int('maxsteps') ?: 10)

        if (!offset) offset = (attrs.int('offset') ?: 0)
        if (!max) max = (attrs.int('max') ?: 10)

        Map linkParams = [:]
        if (attrs.params instanceof Map) linkParams.putAll((Map)attrs.params)
        linkParams.offset = offset - max
        linkParams.max = max
        if (params.sort) linkParams.sort = params.sort
        if (params.order) linkParams.order = params.order

        Map linkTagAttrs = [:]
        def action
        if (attrs.containsKey('mapping')) {
            linkTagAttrs.mapping = attrs.mapping
            action = attrs.action
        } else {
            action = attrs.action ?: params.action
        }
        if (action) {
            linkTagAttrs.action = action
        }
        if (attrs.controller) {
            linkTagAttrs.controller = attrs.controller
        }
        if (attrs.containsKey(UrlMapping.PLUGIN)) {
            linkTagAttrs.put(UrlMapping.PLUGIN, attrs.get(UrlMapping.PLUGIN))
        }
        if (attrs.containsKey(UrlMapping.NAMESPACE)) {
            linkTagAttrs.put(UrlMapping.NAMESPACE, attrs.get(UrlMapping.NAMESPACE))
        }
        if (attrs.id != null) {
            linkTagAttrs.id = attrs.id
        }
        if (attrs.fragment != null) {
            linkTagAttrs.fragment = attrs.fragment
        }
        linkTagAttrs.params = linkParams

        // determine paging variables
        def steps = maxsteps > 0
        int currentstep = ((offset / max) as int) + 1
        int firststep = 1
        int laststep = Math.round(Math.ceil(total / max)) as int

        // display previous link when not on firststep unless omitPrev is true
        if (currentstep > firststep && !attrs.boolean('omitPrev')) {
            linkTagAttrs.put('class', 'prevLink')
            linkParams.offset = offset - max
            writer << callLink((Map)linkTagAttrs.clone()) {
                (attrs.prev ?: messageSource.getMessage('paginate.prev', null, messageSource.getMessage('default.paginate.prev', null, 'Previous', locale), locale))
            }
        }

        // display steps when steps are enabled and laststep is not firststep
        if (steps && laststep > firststep) {
            linkTagAttrs.put('class', 'step')

            // determine begin and endstep paging variables
            int beginstep = currentstep - (Math.round(maxsteps / 2.0d) as int) + (maxsteps % 2)
            int endstep = currentstep + (Math.round(maxsteps / 2.0d) as int) - 1

            if (beginstep < firststep) {
                beginstep = firststep
                endstep = maxsteps
            }
            if (endstep > laststep) {
                beginstep = laststep - maxsteps + 1
                if (beginstep < firststep) {
                    beginstep = firststep
                }
                endstep = laststep
            }

            // display firststep link when beginstep is not firststep
            if (beginstep > firststep && !attrs.boolean('omitFirst')) {
                linkParams.offset = 0
                writer << callLink((Map)linkTagAttrs.clone()) {firststep.toString()}
            }
            //show a gap if beginstep isn't immediately after firststep, and if were not omitting first or rev
            if (beginstep > firststep+1 && (!attrs.boolean('omitFirst') || !attrs.boolean('omitPrev')) ) {
                writer << '<span class="step gap">..</span>'
            }

            // display paginate steps
            (beginstep..endstep).each { int i ->
                if (currentstep == i) {
                    writer << "<span class=\"currentStep\">${i}</span>"
                }
                else {
                    linkParams.offset = (i - 1) * max
                    writer << callLink((Map)linkTagAttrs.clone()) {i.toString()}
                }
            }

            //show a gap if beginstep isn't immediately before firststep, and if were not omitting first or rev
            if (endstep+1 < laststep && (!attrs.boolean('omitLast') || !attrs.boolean('omitNext'))) {
                writer << '<span class="step gap">..</span>'
            }
            // display laststep link when endstep is not laststep
            if (endstep < laststep && !attrs.boolean('omitLast')) {
                linkParams.offset = (laststep - 1) * max
                writer << callLink((Map)linkTagAttrs.clone()) { laststep.toString() }
            }
        }

        // display next link when not on laststep unless omitNext is true
        if (currentstep < laststep && !attrs.boolean('omitNext')) {
            linkTagAttrs.put('class', 'nextLink')
            linkParams.offset = offset + max
            writer << callLink((Map)linkTagAttrs.clone()) {
                (attrs.next ? attrs.next : messageSource.getMessage('paginate.next', null, messageSource.getMessage('default.paginate.next', null, 'Next', locale), locale))
            }
        }
    }


}
