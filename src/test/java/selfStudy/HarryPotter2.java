package selfStudy;

import io.restassured.internal.path.json.mapping.JsonPathJackson1ObjectDeserializer;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class HarryPotter2 {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= ConfigurationReader.getProperty("harryPotterApiBaseURL");
    }
    @DisplayName("Verify sorting hat")
    @Test
    public void test1(){
        /*
                given()
                .log().all()
                .contentType(ContentType.JSON)
                .when()
                .get("/sortingHat")
                .prettyPeek()
                .then()
                .assertThat()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body(containsString("Gryffindor"))
                        .body(containsString("Hufflepuff"))
                           .body(containsString("Ravenclaw"))
                               .body(containsString("Slytherin"));
         */
         //Verify soring hat
        //1. Send a get request to /sortingHat. Request includes:
        //2.Verify status code 200, content type application/json;charset=utf-8
        //3.Verify that response body contains one of the following houses:
        //  "Gryffindor", "Ravenclaw", "Slytherin", "Hufflepuff"

        List<String> expected =new ArrayList<>(Arrays.asList("\"Gryffindor\"","\"Ravenclaw\"", "\"Slytherin\"", "\"Hufflepuff\""));
        Response response=
                get("/sortingHat")
                        .prettyPeek();
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json; charset=utf-8",response.contentType());
        Assertions.assertTrue(expected.contains(response.body().asString()));


    }
    @DisplayName("Verify bad key")
    @Test
    public void test2(){
        //1. Send a get request to /characters. Request includes:
        //    . Header Accept with value application/json
        //    . Query param key with value invalid
        //2. Verify status code 401, content type application/json; charset=utf-8
        //3.Verify response status line include message Unauthorized
        //4.Verify that response body says "error": "API Key Not Found"

        String expected = "{\"error\":\"API Key Not Found\"}";

        given()
                .accept(ContentType.JSON)
                .queryParam("key","invalid")
                .when()
                .get("/characters")
                .then()
                .assertThat()
                .statusCode(401)
                .contentType("application/json; charset=utf-8")
                .statusLine(containsString("Unauthorized"))
                .body(containsString(expected));
    }

    @DisplayName("Verify no key")
    @Test
    public void test3(){
        // 1. Send a get request to /characters. Request includes:
        //  .Header Accept with value application/json
        //2. Verify status code 409, content type application/json; charset=utf-8
        //3. Verify response status line include message Conflict
        //4. Verify that response body says "error": "Must pass API key for request"
 String expected= "{\"error\":\"Must pass API key for request\"}" ;


         given()
                 .accept(ContentType.JSON)
                 .when()
                 .get("/characters")
                 .then()
                 .statusCode(409)
                 .contentType("application/json; charset=utf-8")
                 .statusLine(containsString("Conflict"))
                 .body(containsString(expected));

    }
    @DisplayName("Verify number of characters")
    @Test
    public void test4(){

        //1. Send a get request to /characters. Request includes:
        // .Header Accept with value application/json
        //   .Query param key with value {{apiKey}}
        //2. Verify status code 200, content type application/json; charset=utf-8
        //3. Verify response contains 194 characters
        // apikey = $2a$10$YF7KAP5SE5rmCdDDXtJqxOikgr8w0pPWO712cZdU131boDXfSt8De

        given()
                .log().all()
                .accept(ContentType.JSON)
                //.queryParam("key",ConfigurationReader.getProperty("harryPotterApiKey"))
                .queryParam("key","$2a$10$YF7KAP5SE5rmCdDDXtJqxOikgr8w0pPWO712cZdU131boDXfSt8De")
                .when()
                .get("/characters")
                .prettyPeek()
                .then()
                .statusCode(200)
                .contentType("application/json; charset=utf-8")
                .body("",hasSize(195));

    }
}
