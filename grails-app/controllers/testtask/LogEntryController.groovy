package testtask


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LogEntryController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond LogEntry.list(params), model: [logEntryInstanceCount: LogEntry.count()]
    }

    def show(LogEntry logEntryInstance) {
        respond logEntryInstance
    }

    def create() {
        respond new LogEntry(params)
    }

    @Transactional
    def save(LogEntry logEntryInstance) {
        if (logEntryInstance == null) {
            notFound()
            return
        }

        if (logEntryInstance.hasErrors()) {
            respond logEntryInstance.errors, view: 'create'
            return
        }

        logEntryInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'logEntry.label', default: 'LogEntry'), logEntryInstance.id])
                redirect logEntryInstance
            }
            '*' { respond logEntryInstance, [status: CREATED] }
        }
    }

    def edit(LogEntry logEntryInstance) {
        respond logEntryInstance
    }

    @Transactional
    def update(LogEntry logEntryInstance) {
        if (logEntryInstance == null) {
            notFound()
            return
        }

        if (logEntryInstance.hasErrors()) {
            respond logEntryInstance.errors, view: 'edit'
            return
        }

        logEntryInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'LogEntry.label', default: 'LogEntry'), logEntryInstance.id])
                redirect logEntryInstance
            }
            '*' { respond logEntryInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(LogEntry logEntryInstance) {

        if (logEntryInstance == null) {
            notFound()
            return
        }

        logEntryInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'LogEntry.label', default: 'LogEntry'), logEntryInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'logEntry.label', default: 'LogEntry'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
