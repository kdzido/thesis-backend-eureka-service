package com.kdzido.thesis.eureka

import io.restassured.http.ContentType
import spock.lang.Requires
import spock.lang.Specification
import spock.lang.Timeout

import java.util.concurrent.TimeUnit

import static io.restassured.RestAssured.*
import static io.restassured.matcher.RestAssuredMatchers.*
import static org.hamcrest.Matchers.*

/**
 * @author krzysztof.dzido@gmail.com
 */
@Requires({env['EUREKASERVICE_URI_1'] && env['EUREKASERVICE_URI_2']})
class PeerAwareEurekaClusterIntegSpec extends Specification {

    @Timeout(unit=TimeUnit.MINUTES, value=5)
    def "should create eureka cluster out of 2 peers"() {
        given:
        def peer1 = System.getenv("EUREKASERVICE_URI_1")
        def peer2 = System.getenv("EUREKASERVICE_URI_2")
        // TODO pass as quickly as possible
        // TODO pass as quickly as possible
        // TODO pass as quickly as possible
//        TimeUnit.SECONDS.sleep(90)

        expect:
        given().when()
                    .accept(ContentType.JSON)
                    .get("$peer1/apps")
                .then()
                    .statusCode(200)
                    .body("applications.application.name", hasItem("EUREKASERVICE"))
                    .body("applications.application.instance.app", hasItems(["EUREKASERVICE", "EUREKASERVICE"]))

//        and:
//        given().when()
//                    .accept(ContentType.JSON)
//                    .get("$peer2/apps")
//                .then()
//                    .statusCode(200)
//                    .body("applications.application.name", hasItem("EUREKASERVICE"))
//                    .body("applications.application.instance.app", hasItems(["EUREKASERVICE", "EUREKASERVICE"]))
    }

}
