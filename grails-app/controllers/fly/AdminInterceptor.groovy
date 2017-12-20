package fly


class AdminInterceptor {

    AdminInterceptor() {
        match(controller:"user")
    }

    boolean isAdmin(){
        return session.user.role=='admin';
    }

    boolean before() {
        if(isAdmin())
            return true
        else
            return false
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
