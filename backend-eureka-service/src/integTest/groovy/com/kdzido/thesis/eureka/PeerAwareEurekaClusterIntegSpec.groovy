package com.kdzido.thesis.eureka

import io.restassured.http.ContentType
import spock.lang.Ignore
import spock.lang.Requires
import spock.lang.Specification
import spock.lang.Stepwise
import spock.lang.Timeout
import spock.lang.Unroll

import java.util.concurrent.TimeUnit

import static io.restassured.RestAssured.*
import static io.restassured.matcher.RestAssuredMatchers.*
import static org.hamcrest.Matchers.*
import static org.awaitility.Awaitility.*

/**
 * @author krzysztof.dzido@gmail.com
 */
@Requires({ env['EUREKASERVICE_URI_1'] && env['EUREKASERVICE_URI_2'] })
@Stepwise
class PeerAwareEurekaClusterIntegSpec extends Specification {

    @Unroll
    @Timeout(unit = TimeUnit.MINUTES, value = 4)
    def "that eureka peers are up: #peer1, #peer2"() { // readable fail
        expect:
            await().atMost(2, TimeUnit.MINUTES).until({ is200(peer1) })
            await().atMost(2, TimeUnit.MINUTES).until({ is200(peer2) })

        where:
        peer1                                | peer2
        System.getenv("EUREKASERVICE_URI_1") | System.getenv("EUREKASERVICE_URI_2")
    }


    @Ignore
    @Unroll
    @Timeout(unit = TimeUnit.MINUTES, value = 5)
    def "that eureka peers are aware of each other: #peer1, #peer2"() { // readable fail
        expect:
        // TODO pass as quickly as possible
        // TODO pass as quickly as possible
        // TODO pass as quickly as possible
//        TimeUnit.SECONDS.sleep(80)

        given()
                .when()
                .accept(ContentType.JSON)
                .get("${peer1.trim()}/apps")
                .then()
                .statusCode(200)
                .body("applications.application.name", hasItem("EUREKASERVICE"))
                .body("applications.application.instance.app", hasItems(["EUREKASERVICE", "EUREKASERVICE"]))

        and:
        given()
                .when()
                .accept(ContentType.JSON)
                .get("${peer2.trim()}/apps")
                .then()
                .statusCode(200)
                .body("applications.application.name", hasItem("EUREKASERVICE"))
                .body("applications.application.instance.app", hasItems(["EUREKASERVICE", "EUREKASERVICE"]))


        where:
        peer1                                | peer2
        System.getenv("EUREKASERVICE_URI_1") | System.getenv("EUREKASERVICE_URI_2")
    }

    static getRegisteredApps(eurekaBaseUri) {
        def response = when()
                .get("$eurekaBaseUri/apps")
                .then()
                .extract().response()
        return response
    }

    static is200(eurekaBaseUri) {
        def response = getRegisteredApps(eurekaBaseUri)
        return response.statusCode() == 200
    }


    static isServiceRegistered(eurekaBaseUri) {
        when()
                .get("$eurekaBaseUri/apps")
                .then()
        .statusCode(200)
                .body("applications.application.name", hasItem("EUREKASERVICE"))
                .body("applications.application.instance.app", hasItems(["EUREKASERVICE", "EUREKASERVICE"]))
    }



}
