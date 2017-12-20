package fly

import tv.xza.fly.Roles


class AdminInterceptor {

    AdminInterceptor() {
        match(controller:"user")
    }

    boolean isAdmin(){
        if(session.user==null)
            return false;
        return session.user.role==Roles.admin;
    }

    boolean before() {
        if(isAdmin()){
            return true
        }else{
            flash.title='错误提示'
            flash.message='请以管理员身份访问'
            redirect(controller: 'tip', action: 'index')
            return false
        }
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
