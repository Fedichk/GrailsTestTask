package com.grails.task

import grails.test.mixin.Mock
import org.codehaus.groovy.runtime.InvokerInvocationException
import spock.lang.Specification

import static groovyx.gpars.GParsPool.withPool

@Mock([MyData, Counter])
class YahooServiceSpec extends Specification {

    YahooService yahooService = new YahooService()

    void "test Yahoo connection"() {

        given:
        URL correctURL = new URL('http://download.finance.yahoo.com/d/quotes.csv?s=AAPL+GOOG+MSFT&f=nab')
        URL notCorrectURL = new URL('http://download.finance.yahoo.net/d/quotes.csv?s=AAPL+GOOG+MSFT&f=nab')

        when:
        ArrayList<String> result = yahooService.getData(correctURL)

        then:
        result.size() == 3

        when:
        yahooService.getData(notCorrectURL)

        then:
        thrown UnknownHostException

    }

    void "test saving"() {

        given:
        List<String> dataList = ["\"Apple Inc.\",149.20,149.01",
                                 "\"Alphabet Inc.\",956.79,956.11",
                                 "\"Microsoft Corporation\",72.80,72.71"]

        when:
        withPool(10) {
            50.times {
                yahooService.saveData(dataList)
            }
        }

        then:
        List<MyData> allData = MyData.findAll()
        allData.size() == 150
        Set<MyData> sortedData = allData
        sortedData.size() == 150
    }
}
