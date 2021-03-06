package com.grails.task

class YahooController {

    def yahooService

    def yahooFlow = {

        initialize {
            action {}
            on("success").to "getData"
        }

        getData {
            action {
                yahooService.save()
            }
            on("success").to "load"
            on(Exception).to "error"
            on("error").to "error"
        }

        load {
            action {
                def all = yahooService.loadAll()
                [all: all]
            }
            on("success").to "result"
            on(Exception).to "error"
            on("error").to "error"
        }

        result {
            on("endFlow").to "endFlow"
        }

        error()

        endFlow()
    }

    def index = {
        redirect(action: "yahoo")
    }
}
