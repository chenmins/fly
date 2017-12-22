package tv.xza.fly
//主题
class Topic {
    //用户
    User user
    //标题
    String title
    //分类
    Column column
    //飞吻
    int feiwen = 0
    //正文
    TopicBody topicBody
    //回复数量
    int replyCount = 0
    //阅读数量
    int readCount = 0
    //置顶
    boolean top = false
    //精华
    boolean star = false
    //已审核
    boolean verify = false
    //已经结贴
    boolean close = false
    //热门
    boolean hot = false
    //发布时间
    Date releaseDate = new Date()
    //評論時間
    Date replyDate = new Date()

    static hasMany = [topicReply: TopicReply]

    static constraints = {
    }
}
