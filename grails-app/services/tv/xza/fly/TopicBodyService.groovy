package tv.xza.fly

import grails.gorm.services.Service

@Service(TopicBody)
interface TopicBodyService {

    TopicBody get(Serializable id)

    List<TopicBody> list(Map args)

    Long count()

    void delete(Serializable id)

    TopicBody save(TopicBody topicBody)

}