package fly


class AuthInterceptor {

    AuthInterceptor() {
        match(controller:"self")// using strings
        //match(controller: ~/(author|publisher)/) // using regex
        //matchAll().excludes(controller:"user")
    }

    boolean isLogin(){
        return session.user!=null;
    }

    boolean before() {
        if(isLogin()){
            return true
        }else{
            redirect(controller: 'auth', action: 'login')
            return false
        }
    }

    boolean after() { true }

    void afterView() {
//        render '错误啦'
    }
}
