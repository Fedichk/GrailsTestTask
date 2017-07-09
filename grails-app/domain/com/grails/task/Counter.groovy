package com.grails.task

class Counter {
    String name
    Integer value = 0

    static constraints = {
        name blank: false, size: 1..20
    }

    static mapping = {
        version false
        id generator: 'assigned', name: 'name'
        value defaultValue: "0"
    }
}
