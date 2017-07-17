package com.grails.task

import com.netflix.hystrix.Hystrix
import com.netflix.hystrix.HystrixCommand
import grails.transaction.Transactional

class YahooService {

    public static final URL YAHOO_URL = new URL('http://download.finance.yahoo.com/d/quotes.csv?s=AAPL+GOOG+MSFT&f=nab')

    def save() {
        List<String> data = getData(YAHOO_URL)
        saveData(data)
    }

//    @HystrixCommand
    List<String> getData(URL url) {
        String rawData = url.getText()
        return rawData.split('\\r?\\n') as List<String>
    }

    def saveData(List<String> dataList) {
        int requestId = getRequestId()
        dataList.each {
            createCounter(requestId)
            createMyData(requestId, it)
            requestId++
        }
    }

    private void createMyData(int requestId, String it) {
        MyData myData = new MyData(id: requestId, data: it)
        myData.save()
    }

    private void createCounter(int requestId) {
        Counter counter = new Counter(name: 'QN_ID_' + requestId, value: requestId)
        counter.save()
    }

    private int getRequestId() {
        Counter counter = Counter.last('value')
        if (counter) {
            return counter.getValue() + 1
        } else {
            return 0
        }
    }

    @Transactional(readOnly = true)
    List<MyData> loadAll() {
        return MyData.list()
    }
}
