package com.kdzido.thesis.eureka

import io.restassured.http.ContentType
import spock.lang.Specification

import static io.restassured.RestAssured.*
import static io.restassured.matcher.RestAssuredMatchers.*
import static org.hamcrest.Matchers.*

class PeerAwareEurekaClusterIntegSpec extends Specification {

    void setup() {
//        RestAssured.port = 8761
//        RestAssured.baseURI = "http://${System.getenv('EUREKAPEER1')}"
//        RestAssured.basePath = "/eureka"
    }

    def "should create eureka cluster out of 2 peers"() {
        expect:
        given().when()
                .accept(ContentType.JSON)
                .get("http://192.168.99.103:8761/eureka/apps")
                .then()
                    .body("")
                .statusCode(200)
    }
}
