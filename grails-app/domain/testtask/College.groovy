package testtask

class College {

    int id
    String name

    static hasMany = [students: Student]

    static constraints = {
        name nullable: false
    }

    static mapping = {
        version false
        id column: 'id'
        name column: 'name'
    }

    @Override
    public String toString() {
        return name
    }
}
