package grails.async.demo

import groovy.util.logging.Slf4j

import static grails.async.Promises.task

@Slf4j
class SiteController {

    SiteService siteService

    def index() {
        task {
            render model: siteService.getModel(), view: 'index'
        }
    }

    def home() {
        task {
            redirect action: 'index'
        }
    }

    def error() {
        task {
            if (params.boolean('causeError')) {
                log.info('I am going to throw an error now.')
                siteService.throwError()
            } else {
                log.info('No error will be thrown')
            }
            return
        }.onError {
            redirect action: 'index'
            return
        }.onComplete {
            render model: siteService.getModel(), view: 'index'
            return
        }
    }

    def error2(){
        task {
            siteService.throwError()
        }
    }

    def caught(){
        task {
            try {
                siteService.throwError()
            } catch (RuntimeException e) {
                log.error("Caught exception!", e)
                render view: 'index'
            }
        }
    }
}
