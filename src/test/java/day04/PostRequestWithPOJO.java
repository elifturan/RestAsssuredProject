package day04;

import Pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class PostRequestWithPOJO {

    @BeforeAll
    public static void init() {

        RestAssured.baseURI = "http://54.158.178.13";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";

    }
    @Test
    public void testPostBodyWithPojo() {   // this is posting data with pojo's java class to respond
                                          //as a json format so
        //Turning POJO(Java Object) to String|Byte[] in our case Json is known as Serialization

        Spartan sp1 = new Spartan("Irina Li", "Female",1231231231 ) ;

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(sp1).
                when()
                .post("/spartans").
                then()
                .log().all()
                .statusCode(201) ;
        // BACK ON 4:01 PM EST



    }
}