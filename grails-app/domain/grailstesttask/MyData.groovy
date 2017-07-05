package grailstesttask

class MyData {
    Integer id
    String data
    Date recordCreatedDate = new Date()

    static constraints = {
    }

    static mapping = {
        version false
    }
}
