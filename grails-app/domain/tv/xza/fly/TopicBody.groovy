package tv.xza.fly

class TopicBody {

    String body

    static belongsTo = [topic: Topic]

    static constraints = {
    }
}
