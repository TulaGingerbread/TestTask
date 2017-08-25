package testtask

class LogEntry {

    int id
    String message
    String line

    static constraints = {
        message nullable: false
        line nullable: false
    }

    static mapping = {
        version false
        id column: 'id'
        message column: 'message'
        line column: 'line'
    }
}
