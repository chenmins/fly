package tv.xza.fly

class SelfController {

    UserService userService
    def mailService
    def simpleCaptchaService

    def index() {
        render 'Hello world'
    }

    def set(){

    }

    def repass(){
        def user = session.user;
        def nowpass = params.nowpass
        def pass = params.pass
        def repass = params.repass
        flash.title = "密码修改结果"
        if(user.password != nowpass){
            flash.message ="原始密码错误"
            return render(view: 'tip')
        }
        if(pass != repass){
            flash.message ="重复密码不一致"
            return render(view: 'tip')
        }
        flash.message ="帐号${user.email}密码已经修改，请查收邮件"
        user.password=repass
        userService.save(user)
        mailService.sendMail {
            to user.email
            subject "Edit password"+flash.message
            text "新的密码：${user.password},请注意"
        }
        return render(view: 'tip')
    }



}
