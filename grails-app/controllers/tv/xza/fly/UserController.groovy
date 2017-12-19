package tv.xza.fly

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class UserController {

    UserService userService
    def mailService
    def simpleCaptchaService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond userService.list(params), model:[userCount: userService.count()]
    }

    def login(User user) {

    }
    def logout(User user) {
        session.user = null;
        return render(view: 'login')
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
            from "chen.min@bupt.edu.cn"
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
        if(user.password == u.password){
            session.user = u;
            flash.message ="密码ok"
        }else{
            flash.message ="密码error"
        }
        return render(view: 'tip')
    }

    def reg(User user) {

    }

    def show(Long id) {
        respond userService.get(id)
    }

    def create() {
        respond new User(params)
    }

    def save(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*' { respond user, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond userService.get(id)
    }

    def update(User user) {
        if (user == null) {
            notFound()
            return
        }

        try {
            userService.save(user)
        } catch (ValidationException e) {
            respond user.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), user.id])
                redirect user
            }
            '*'{ respond user, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        userService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
