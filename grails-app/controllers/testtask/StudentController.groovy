package testtask

import org.springframework.web.multipart.MultipartHttpServletRequest

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class StudentController {
    def mailService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Student.list(params), model: [studentInstanceCount: Student.count()]
    }

    def upload() {

    }

    def saveCSV() {
        def multiRequest = (MultipartHttpServletRequest) request
        def files = multiRequest.getFiles('studentCSV')
        files.each { f ->
            def bin = new BufferedReader(new InputStreamReader(f.getInputStream()))
            bin.lines().forEach({ String line ->
                def array = line.split(';|,') // we assume here that our CSV file has a bit reliable format
                try {
                    if (array.length < 5) throw new ParseException("Line is malformed, too few entries")
                    def studentMail = array[0].trim()
                    if (!studentMail.empty && !(studentMail ==~ /[\w\-\_\.]+@[\w]+\.[\w]{2,3}/)) throw new ParseException("Invalid email")
                    def firstName = array[1].trim()
                    def lastName = array[2].trim()
                    if (firstName.empty || lastName.empty) throw new ParseException("Name is not provided")
                    def docID = array[3].trim()
                    def college = College.findByName(array[4].trim())
                    if (college == null) throw new ParseException("College with that name not found")
                    new Student(
                            email: studentMail,
                            firstName: firstName,
                            lastName: lastName,
                            documentID: docID,
                            college: college
                    ).save()
                    // I did not enable this plugin and try to send mail because
                    // I probably have broken environment or whatever so I can't install the damn plugin
                    // and this shouldn't be a problem on a working environment
                    /*mailService.sendMail { // TODO install mail plugin and send that mail
                        from "testtask@gmail.com"
                        to studentMail
                        subject "Hello"
                        html "Hello ${firstName}"
                    }*/
                }
                catch (ParseException e) {
                    new LogEntry(
                            message: e.message,
                            line: line
                    ).save()
                }
            })
        }
        flash.message = message(code: 'student.uploaded.success')
        redirect(action: 'index', params: '')
    }

    def show(Student studentInstance) {
        respond studentInstance
    }

    def create() {
        respond new Student(params)
    }

    @Transactional
    def save(Student studentInstance) {
        if (studentInstance == null) {
            notFound()
            return
        }

        if (studentInstance.hasErrors()) {
            respond studentInstance.errors, view: 'create'
            return
        }

        studentInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), studentInstance.id])
                redirect studentInstance
            }
            '*' { respond studentInstance, [status: CREATED] }
        }
    }

    def edit(Student studentInstance) {
        respond studentInstance
    }

    @Transactional
    def update(Student studentInstance) {
        if (studentInstance == null) {
            notFound()
            return
        }

        if (studentInstance.hasErrors()) {
            respond studentInstance.errors, view: 'edit'
            return
        }

        studentInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Student.label', default: 'Student'), studentInstance.id])
                redirect studentInstance
            }
            '*' { respond studentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Student studentInstance) {

        if (studentInstance == null) {
            notFound()
            return
        }

        studentInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Student.label', default: 'Student'), studentInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
