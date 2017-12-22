package tv.xza.fly

class AuthController {
    UserService userService
    def mailService
    def simpleCaptchaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index() {
        render 'Hello world'
    }


    def login(User user) {

    }

    def reg(User user) {

    }

    def forget(User user) {

    }

    def logout(User user) {
        session.user = null;
        return render(view: 'login')
    }

    def forgetSend(User user) {
        flash.title = "密码找回结果"
        if (user == null) {
            flash.message ="帐号秘密为空"
            return render(view: 'tip')
        }
        boolean captchaValid = simpleCaptchaService.validateCaptcha(params.vercode)
        if(!captchaValid){
            flash.message ="验证码不正确"
            return render(view: 'tip')
        }
        def u = User.findByEmail(user.email)
        if(u==null){
            flash.message ="${user.email}帐号不存在"
            return render(view: 'tip')
        }
        flash.message ="帐号${user.email}密码已经重置，请查收邮件"
        u.password=rnd()
        userService.save(u)
        mailService.sendMail {
            to user.email
            subject "New user"+flash.message
            text "新的密码：${u.password},请即时修改"
        }
        return render(view: 'tip')
    }

    def rnd(){
        int max=99999999;
        int min=10000000;
        Random random = new Random();
        random.nextInt(max)%(max-min+1) + min;
    }

    def regUser(User user) {
        flash.title = "注册结果"
        if (user == null) {
            flash.message ="帐号秘密为空"
            return render(view: 'tip')
        }
        boolean captchaValid = simpleCaptchaService.validateCaptcha(params.vercode)
        if(!captchaValid){
            flash.message ="验证码不正确"
            return render(view: 'tip')
        }
        def u = User.findByEmail(user.email)
        if(u!=null){
            flash.message ="${user.email}帐号已经注册，请尝试找回密码"
            return render(view: 'tip')
        }
        if(user.password != params.repass){
            flash.message ="两次密码不相同"
            return render(view: 'tip')
        }
        user.role="user"
        user.save()
        flash.message ="帐号${user.email}注册成功，请查收邮件"
        mailService.sendMail {
            to user.email
            subject "New user"+flash.message
            text "A new user has been created"
        }
        return render(view: 'tip')
    }


    def check(User user) {
        flash.title = "登录结果"
        if (user == null) {
            flash.message ="帐号秘密为空"
            return render(view: 'login')
        }
        boolean captchaValid = simpleCaptchaService.validateCaptcha(params.vercode)
        if(!captchaValid){
            flash.message ="验证码不正确"
            return render(view: 'login')
        }
        def u = User.findByEmail(user.email)
        if(u==null){
            flash.message ="${user.email}帐号不存在"
            return render(view: 'login')
        }
        if(user.password == u.password){
            session.user = u;
            flash.message ="密码ok"
        }else{
            flash.message ="密码error"
        }
        flash.title = '提示信息'
        if(params.real!=null){
            flash.redirect = params.real
            flash.message = '登录成功，即将返回上一个页面'
            redirect(controller: 'tip', action: 'index')
            return
        }
        flash.message = '回帖成功，即将返回首页'
        redirect(controller: 'index', action: 'index')
        return
    }
}
