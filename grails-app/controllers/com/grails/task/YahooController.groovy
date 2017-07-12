package com.grails.task

class YahooController {

    def yahooService

    def yahooFlow = {

        initialize {
            action {}
            on("success").to "getData"
        }

        getData {
            on("getData") {
                yahooService.serviceMethod()
            }.to "load"
        }

        load {
            action {
                def all = yahooService.loadAll()
                [all: all]
            }
            on("success").to "result"
        }

        result {
            on("endFlow").to "endFlow"
        }

        endFlow()
    }

    def index = {
        redirect(action: "yahoo")
    }
}
