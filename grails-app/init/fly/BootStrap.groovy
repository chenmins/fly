package fly

import tv.xza.fly.*

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
            user.role=Roles.admin
            user.save();

            user = new User()
            user.username='chenmin'
            user.password='000000'
            user.email='cnlll@qq.com'
            user.role=Roles.user
            user.save();

            new Column(columnName:'提问',role:Roles.user).save()
            new Column(columnName:'分享',role:Roles.user).save()
            new Column(columnName:'讨论',role:Roles.user).save()
            new Column(columnName:'建议',role:Roles.user).save()
            new Column(columnName:'公告',role:Roles.admin).save()
            new Column(columnName:'动态',role:Roles.admin).save()
        }
    }
    def destroy = {
    }
}
