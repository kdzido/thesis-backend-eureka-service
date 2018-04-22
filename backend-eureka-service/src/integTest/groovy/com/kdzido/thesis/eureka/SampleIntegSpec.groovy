package com.kdzido.thesis.eureka

import spock.lang.Specification

import static io.restassured.RestAssured.*
import static io.restassured.matcher.RestAssuredMatchers.*
import static org.hamcrest.Matchers.*

class SampleIntegSpec extends Specification {

    void setup() {
//        RestAssured.port = 8761
//        RestAssured.baseURI = "http://${System.getenv('EUREKAPEER1')}"
//        RestAssured.basePath = "/eureka"
    }

    def "should pass"() {
        expect:
        given().when().get("http://www.google.com").then().statusCode(200)
    }
}
