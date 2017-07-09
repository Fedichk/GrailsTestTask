import com.grails.task.Counter
import com.grails.task.MyData

class BootStrap {

    def init = { servletContext ->
        if (Counter.findByName("QN_ID") == null) {
            Counter counter = new Counter(name: "QN_ID")
            if (counter.validate()) {
                counter.save()
            } else {
                println(counter.errors.hasErrors())
            }
        }
    }
    def destroy = {
    }
}
