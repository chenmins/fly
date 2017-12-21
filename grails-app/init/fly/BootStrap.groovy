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

            new Column(columnName:'提问',role:Roles.user,top:true).save()
            new Column(columnName:'分享',role:Roles.user,top:true,badge:true).save()
            new Column(columnName:'讨论',role:Roles.user,top:true).save()
            new Column(columnName:'建议',role:Roles.user,top:true).save()
            new Column(columnName:'公告',role:Roles.admin,top:true).save()
            new Column(columnName:'动态',role:Roles.admin,top:true).save()

            def column = Column.get(5)
            user = User.get(1)

            def topic = new Topic()
            topic.user = user
            topic.column = column
            topic.title='first 第一个帖子'
            topic.readCount = 30
            topic.replyCount = 2
            topic.feiwen = 20
            topic.top = true
            topic.hot = true
            topic.star = true
            topic.verify = true
            topic.topicBody = new TopicBody(body:"这里是正文，你可以删除")
            topic.addToTopicReply(new TopicReply(user:User.get(1),body:'回帖1'))
            topic.addToTopicReply(new TopicReply(user:User.get(2),body:'回帖2',good: true))
            topic.save();
        }
    }
    def destroy = {
    }
}
