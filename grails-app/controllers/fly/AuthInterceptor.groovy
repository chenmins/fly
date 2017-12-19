package fly


class AuthInterceptor {

    AuthInterceptor() {
        match(controller:"user") // using strings
        //match(controller: ~/(author|publisher)/) // using regex
        //matchAll().excludes(controller:"user")
    }

    boolean isLogin(){
        return session.user!=null;
    }

    boolean isAdmin(){
        return session.user.role=='admin';
    }

    boolean before() {
//        return true
        if(isLogin()&&isAdmin())
            return true
        else
            return false
    }

    boolean after() { true }

    void afterView() {
        render '错误啦'
    }
}
