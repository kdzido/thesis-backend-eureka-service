package com.kdzido.thesis.eureka

import groovyx.net.http.RESTClient
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Unroll

import java.util.concurrent.TimeUnit

import static org.awaitility.Awaitility.*

/**
 * @author krzysztof.dzido@gmail.com
 */
@Stepwise
class EurekaClusterIntegSpec extends Specification {

    final static EUREKASERVICE_URI_1 = System.getenv("EUREKASERVICE_URI_1")
    final static EUREKASERVICE_URI_2 = System.getenv("EUREKASERVICE_URI_2")

    def eurekapeer1Client = new RESTClient("$EUREKASERVICE_URI_1").with {
        setHeaders(Accept: 'application/json')
        it
    }
    def eurekapeer2Client = new RESTClient("$EUREKASERVICE_URI_2").with {
        setHeaders(Accept: 'application/json')
        it
    }

    @Unroll
    def "that eureka peers are up: #peer1, #peer2"() { // readable fail
        expect:
        await().atMost(2, TimeUnit.MINUTES).until({
            try {
                def resp = eurekapeer1Client.get(path: "/eureka/apps")
                resp.status == 200
            } catch (e) {
                return false
            }
        })

        and:
        await().atMost(2, TimeUnit.MINUTES).until({
            try {
                def resp = eurekapeer2Client.get(path: "/eureka/apps")
                resp.status == 200
            } catch (e) {
                return false
            }
        })

        where:
        peer1                                | peer2
        System.getenv("EUREKASERVICE_URI_1") | System.getenv("EUREKASERVICE_URI_2")
    }

    def "that eureka is registered in Eureka peers"() { // readable fail
        expect:
        await().atMost(2, TimeUnit.MINUTES).until({
            try {
                def resp = eurekapeer1Client.get(path: "/eureka/apps")
                resp.status == 200 &&
                        resp.headers.'Content-Type' == "application/json" &&
                        resp.data.applications.application.any {
                            it.name == "EUREKASERVICE" &&
                                    it.instance.findAll { it.app == "EUREKASERVICE" }.size() == 2
                        }
            } catch (e) {
                return false
            }
        })

        and:
        await().atMost(2, TimeUnit.MINUTES).until({
            try {
                def resp = eurekapeer2Client.get(path: "/eureka/apps")
                resp.status == 200 &&
                        resp.headers.'Content-Type' == "application/json" &&
                        resp.data.applications.application.any {
                            it.name == "EUREKASERVICE" &&
                                    it.instance.findAll { it.app == "EUREKASERVICE" }.size() == 2
                        }
            } catch (e) {
                return false
            }
        })
    }

}
