package tv.xza.fly

class Topic {

    User user

    String title

    Column column

    int feiwen = 0

    TopicBody topicBody

    int replyCount = 0

    int readCount = 0

    boolean top = false

    boolean star = false

    Date releaseDate = new Date()

    static hasMany = [topicReply: TopicReply]


    static constraints = {
    }
}
