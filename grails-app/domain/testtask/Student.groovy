package testtask

class Student {

    int id
    String firstName
    String lastName
    String documentID
    String email
    College college

    static constraints = {
        firstName nullable: false
        lastName nullable: false
        documentID nullable: true
        email nullable: true
        college nullable: false
    }

    static mapping = {
        version false
        id column: 'id'
        firstName column: 'first_name'
        lastName column: 'last_name'
        documentID column: 'document_id'
        email column: 'email'
        college column: 'college'
    }
}
