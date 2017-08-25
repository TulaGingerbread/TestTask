package testtask



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CollegeController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond College.list(params), model:[collegeInstanceCount: College.count()]
    }

    def show(College collegeInstance) {
        respond collegeInstance
    }

    def create() {
        respond new College(params)
    }

    @Transactional
    def save(College collegeInstance) {
        if (collegeInstance == null) {
            notFound()
            return
        }

        if (collegeInstance.hasErrors()) {
            respond collegeInstance.errors, view:'create'
            return
        }

        collegeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'college.label', default: 'College'), collegeInstance.id])
                redirect collegeInstance
            }
            '*' { respond collegeInstance, [status: CREATED] }
        }
    }

    def edit(College collegeInstance) {
        respond collegeInstance
    }

    @Transactional
    def update(College collegeInstance) {
        if (collegeInstance == null) {
            notFound()
            return
        }

        if (collegeInstance.hasErrors()) {
            respond collegeInstance.errors, view:'edit'
            return
        }

        collegeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'College.label', default: 'College'), collegeInstance.id])
                redirect collegeInstance
            }
            '*'{ respond collegeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(College collegeInstance) {

        if (collegeInstance == null) {
            notFound()
            return
        }

        collegeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'College.label', default: 'College'), collegeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'college.label', default: 'College'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
