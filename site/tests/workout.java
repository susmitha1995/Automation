package com.sunpower.automation.edp.api.site.tests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import lombok.SneakyThrows;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class workout {

    RequestSpecification req;

    public void setReq(){

        req = given().baseUri("http://localhost:3000/");
        RestAssured.requestSpecification = req;
    }

    @SneakyThrows
    @Test
    public void getEmployees() {

        given().when().get("employees").then().log().all();
    }

}
