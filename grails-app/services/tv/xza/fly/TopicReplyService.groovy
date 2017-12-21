package tv.xza.fly

import grails.gorm.services.Service

@Service(TopicReply)
interface TopicReplyService {

    TopicReply get(Serializable id)

    List<TopicReply> list(Map args)

    Long count()

    void delete(Serializable id)

    TopicReply save(TopicReply topicReply)

}