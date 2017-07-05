import grailstesttask.Counter

class BootStrap {

    def init = { servletContext ->
        if (Counter.findByName("QN_ID") == null) {
            new Counter(name: "QN_ID", value: 0).save()
        }
    }
    def destroy = {
    }
}
