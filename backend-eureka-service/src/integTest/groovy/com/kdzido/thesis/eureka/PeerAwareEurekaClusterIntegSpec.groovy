package com.kdzido.thesis.eureka

import io.restassured.http.ContentType
import spock.lang.Ignore
import spock.lang.Requires
import spock.lang.Specification
import spock.lang.Timeout

import java.util.concurrent.TimeUnit

import static io.restassured.RestAssured.*
import static io.restassured.matcher.RestAssuredMatchers.*
import static org.hamcrest.Matchers.*

//@Requires(env['EUREKASERVICE_URI_1'])
//@Requires(env['EUREKASERVICE_URI_2'])
class PeerAwareEurekaClusterIntegSpec extends Specification {

    @Ignore
    def "should get peers from env"() {
        given:
        def peer1 = System.getenv("EUREKASERVICE_URI_1")
        def peer2 = System.getenv("EUREKASERVICE_URI_2")

        expect:
        peer1 == 'http://192.168.99.103:8761/eureka'
        peer2 == 'http://192.168.99.103:8762/eureka'

    }

    @Timeout(unit=TimeUnit.MINUTES, value=5)
    def "should create eureka cluster out of 2 peers"() {
        given:
        def peer1 = System.getenv("EUREKASERVICE_URI_1")
        def peer2 = System.getenv("EUREKASERVICE_URI_2")

        peer1 = 'http://192.168.99.103:8761/eureka'
        peer2 = 'http://192.168.99.103:8762/eureka'
//        println "peer1: " + peer1
//        println "peer2: " + peer2

        expect:
        TimeUnit.SECONDS.sleep(60)

        println peer1
        println peer2

        given().when()
                    .accept(ContentType.JSON)
                    .get("$peer1/apps")
                .then()
                    .statusCode(200)
                    .body("applications.application.name", hasItem("EUREKASERVICE"))
                    .body("applications.application.instance.app", hasItems(["EUREKASERVICE", "EUREKASERVICE"]))
        and:
        given().when()
                .accept(ContentType.JSON)
                .get("$peer2/apps")
                .then()
                .statusCode(200)
                .body("applications.application.name", hasItem("EUREKASERVICE"))
                .body("applications.application.instance.app", hasItems(["EUREKASERVICE", "EUREKASERVICE"]))

    }
}
