package grails.async.demo

class SiteService {

    def getModel() {
        Thread.currentThread().sleep(2000)
    }

    def throwError(){
        throw new RuntimeException("Some error!")
    }
}
