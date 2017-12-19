package fly

import tv.xza.fly.User

class BootStrap {

    def init = { servletContext ->
        def user = User.get(1)
        println "user get 1"
        println user
        if(user==null){
            println "user add admin"
            user = new User()
            user.username='admin'
            user.password='000000'
            user.email='admin@dpm.im'
            user.role='admin'
            user.save();
        }
    }
    def destroy = {
    }
}
