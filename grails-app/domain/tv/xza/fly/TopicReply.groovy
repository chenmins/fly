package tv.xza.fly

class TopicReply {

    User user

    String body

    Date releaseDate = new Date()

    static belongsTo = Topic

    static constraints = {
    }
}
