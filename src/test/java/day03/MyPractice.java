package day03;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;

public class MyPractice {
    @BeforeAll
    public static void setUP(){
       RestAssured.baseURI= "http://52.72.23.155";
       RestAssured.port=8000;
       RestAssured.basePath="/api";
// ---> path param ---> api/spartans/:id  or https://api.github.com/users/:username
// ----> query param -->  /api/spartans/search?gender=male&nameContains=ra
    }
    @DisplayName("Getting test")
    @Test
    public void Test1(){
        given()
                .log().all()
                .queryParam("gender","female or male")
                .queryParam("nameContains", "o").

                when()
                        .get("spartans")
                        .prettyPeek().

                        then().
                        statusCode(200)
        .contentType("application/json;charset=UTF-8")
        ;

    }

}

