package com.grails.task

class YahooController {

    def yahooFlow = {
        initialize {
            action {
                println("Init")
            }
            on("success").to "getData"

        }

        getData {
            on("getData") {
                println("Work!!!")
            }.to "result"
        }

        result {
            on("endFlow").to "endFlow"
        }

        endFlow ()
    }

    def index = {
        redirect(action: "yahoo")
    }
}
