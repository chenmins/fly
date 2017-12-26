package tv.xza.fly

class PostController {

    CommonService commonService

    def index() {

    }

    def add(Topic topic) {
        //println topic
        topic.user = session.user
        topic.save()
        flash.title = '提示信息'
        flash.message = '发贴成功，即将返回'
        //flash.back = true
        flash.redirect = "/html/index/${topic.id}"
        redirect(controller: 'tip', action: 'index')
    }

    def reply(){
        def id = params.id
        def body = params.body
        def topic = Topic.get(id)
        def post = params.post
        def re = new TopicReply()

        re.topic = topic
        re.user = session.user
        re.body = body
        re.save()

        commonService.incTopicReplyCount(topic.id)

        flash.title = '提示信息'
        flash.message = '回帖成功，即将返回'
        flash.redirect = post
        redirect(controller: 'tip', action: 'index')
    }
}
