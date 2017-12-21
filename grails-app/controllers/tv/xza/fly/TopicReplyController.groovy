package tv.xza.fly

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TopicReplyController {

    TopicReplyService topicReplyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond topicReplyService.list(params), model:[topicReplyCount: topicReplyService.count()]
    }

    def show(Long id) {
        respond topicReplyService.get(id)
    }

    def create() {
        respond new TopicReply(params)
    }

    def save(TopicReply topicReply) {
        if (topicReply == null) {
            notFound()
            return
        }

        try {
            topicReplyService.save(topicReply)
        } catch (ValidationException e) {
            respond topicReply.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'topicReply.label', default: 'TopicReply'), topicReply.id])
                redirect topicReply
            }
            '*' { respond topicReply, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond topicReplyService.get(id)
    }

    def update(TopicReply topicReply) {
        if (topicReply == null) {
            notFound()
            return
        }

        try {
            topicReplyService.save(topicReply)
        } catch (ValidationException e) {
            respond topicReply.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'topicReply.label', default: 'TopicReply'), topicReply.id])
                redirect topicReply
            }
            '*'{ respond topicReply, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        topicReplyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'topicReply.label', default: 'TopicReply'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'topicReply.label', default: 'TopicReply'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
