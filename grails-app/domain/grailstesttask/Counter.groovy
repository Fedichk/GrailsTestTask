package grailstesttask

class Counter {
    String name
    Integer value

    static constraints = {
        name blank: true, size: 1..20
    }

    static mapping = {
        version false
        id generator: 'assigned', name: 'name'
        value defaultValue: "0"
    }
}
