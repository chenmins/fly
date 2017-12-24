package tv.xza.fly

class TopicBody {

    String body

    static belongsTo = [topic: Topic]

    static mapping = {
        table('fly_topic_body')
    }

    static constraints = {
    }
}
