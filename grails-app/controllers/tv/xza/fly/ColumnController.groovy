package tv.xza.fly

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ColumnController {

    ColumnService columnService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond columnService.list(params), model:[columnCount: columnService.count()]
    }

    def show(Long id) {
        respond columnService.get(id)
    }

    def create() {
        respond new Column(params)
    }

    def save(Column column) {
        if (column == null) {
            notFound()
            return
        }

        try {
            columnService.save(column)
        } catch (ValidationException e) {
            respond column.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'column.label', default: 'Column'), column.id])
                redirect column
            }
            '*' { respond column, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond columnService.get(id)
    }

    def update(Column column) {
        if (column == null) {
            notFound()
            return
        }

        try {
            columnService.save(column)
        } catch (ValidationException e) {
            respond column.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'column.label', default: 'Column'), column.id])
                redirect column
            }
            '*'{ respond column, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        columnService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'column.label', default: 'Column'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'column.label', default: 'Column'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
