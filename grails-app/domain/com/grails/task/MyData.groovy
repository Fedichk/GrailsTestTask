package com.grails.task

class MyData implements Serializable {
    Integer id
    String data
    Date recordCreatedDate

    static constraints = {
        recordCreatedDate nullable: true
    }

    static mapping = {
        version false
    }

    def beforeInsert(){
        recordCreatedDate = new Date()
    }
}
