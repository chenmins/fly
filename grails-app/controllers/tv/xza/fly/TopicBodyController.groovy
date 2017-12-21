package tv.xza.fly

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class TopicBodyController {

    TopicBodyService topicBodyService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond topicBodyService.list(params), model:[topicBodyCount: topicBodyService.count()]
    }

    def show(Long id) {
        respond topicBodyService.get(id)
    }

    def create() {
        respond new TopicBody(params)
    }

    def save(TopicBody topicBody) {
        if (topicBody == null) {
            notFound()
            return
        }

        try {
            topicBodyService.save(topicBody)
        } catch (ValidationException e) {
            respond topicBody.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'topicBody.label', default: 'TopicBody'), topicBody.id])
                redirect topicBody
            }
            '*' { respond topicBody, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond topicBodyService.get(id)
    }

    def update(TopicBody topicBody) {
        if (topicBody == null) {
            notFound()
            return
        }

        try {
            topicBodyService.save(topicBody)
        } catch (ValidationException e) {
            respond topicBody.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'topicBody.label', default: 'TopicBody'), topicBody.id])
                redirect topicBody
            }
            '*'{ respond topicBody, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        topicBodyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'topicBody.label', default: 'TopicBody'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'topicBody.label', default: 'TopicBody'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
