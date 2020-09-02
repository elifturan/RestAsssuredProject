package day04;

import Pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.util.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class MyPostPutPatchRequestWithPojo {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://52.72.23.155";
        RestAssured.port=8000;
        RestAssured.basePath="/api";

    }
    @DisplayName("Posting with POJO")
    @Test
    public void testPostWithPOJO(){

       Spartan spartan =new Spartan("SourceOfLight", "Female", 4567890123l);
       given()
               .log().all()
               .contentType(ContentType.JSON)
               .body(spartan)
               .when()
               .post("/spartans")
               .then()
               .log().all()
               .statusCode(201)
               //.body("data.name",is("SourceOfLight"))
               //.body("data.gender",is("Female"))
               //.body("data.phone",is(4567890123l))
       ;

    }
    @DisplayName("Putting with POJO")
    @Test
    public void testPutWithPOJO(){
        //Spartan spartan =new Spartan("")

    }
}
