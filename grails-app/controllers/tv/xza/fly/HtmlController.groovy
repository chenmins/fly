package tv.xza.fly

class HtmlController {

    TopicService topicService

    def index() {
        if(topicService.get(params.id)==null){
            flash.title = '提示信息'
            flash.message = '页面不存在，即将返回'
            //flash.back = true
            flash.redirect = "/"
            redirect(controller: 'tip', action: 'index')
            return
        }
    }
}
