package com.grails.task

import grails.transaction.Transactional

class YahooService {

    public static final URL yahooURL = new URL('http://download.finance.yahoo.com/d/quotes.csv?s=AAPL+GOOG+MSFT&f=nab')

    def serviceSaveMethod() {
        def listOfResults = concatResult(getData(yahooURL))
        saveData(listOfResults)
    }

    String getData(URL url) {
        return url.getText()
    }

    List<String> concatResult(String data) {
        return data.split('\\r?\\n')
    }

    def saveData(List<String> dataList) {
        dataList.each {
            def lastValue = Counter.last('value').getValue()
            def newValue = lastValue + 1
            Counter counter = new Counter(name: 'QN_ID_' + newValue, value: newValue)
            if (counter.validate()) {
                counter.save()
            } else {
                println(counter.errors.hasErrors())
            }
            MyData myData = new MyData(id: newValue, data: it)
            if (myData.validate()) {
                myData.save()
            } else {
                println(myData.errors.hasErrors())
            }
        }
    }

    @Transactional(readOnly = true)
    List<MyData> loadAll() {
        return MyData.list()
    }
}
