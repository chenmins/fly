package fly

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

}
