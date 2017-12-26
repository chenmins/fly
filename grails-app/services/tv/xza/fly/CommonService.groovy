package tv.xza.fly

import grails.gorm.transactions.Transactional

@Transactional
class CommonService {

    /**
     * 增加阅读数
     * @param id
     */
    void incTopicReadCount(long id) {
        Topic.executeUpdate("update Topic c set c.readCount =  c.readCount+1 where c.id = :id",
                [id: id])
    }

    /**
     * 增加评论数
     * @param id
     */
    void incTopicReplyCount(long id) {
        Topic.executeUpdate("update Topic c set c.replyCount =  c.replyCount+1 , c.replyDate = :replyDate where c.id = :id",
                [id: id,replyDate: new Date()])
    }

    /**
     * 减少评论数
     * @param id
     */
    void descTopicReplyCount(long id) {
        Topic.executeUpdate("update Topic c set c.replyCount =  c.replyCount-1 where c.id = :id",
                [id: id ])
    }
}
