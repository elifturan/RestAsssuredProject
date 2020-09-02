package day08;

import Pojo.Spartan;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import utility.ConfigurationReader;
import utility.DB_Utility;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomeWork {

    static int newID ;
    Spartan spartan = new Spartan("Oznur Gunes", "Female", 2552442552l);

    @BeforeAll
    public static void setUP(){
      RestAssured.baseURI= "http://100.25.192.231";
      RestAssured.port=8000;
      RestAssured.basePath="/api";

        DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url"),
        ConfigurationReader.getProperty("spartan1.database.username"),
        ConfigurationReader.getProperty("spartan1.database.password"));
    }
    @Order(1)
    @Test
    public void testAddData(){
         Response response=
                 given()
                      .log().all()
                      .contentType(ContentType.JSON)
                      .body(spartan)
                 .when()
                      .post("spartans")
                      .prettyPeek();

         newID=response.jsonPath().getInt("data.id");
        System.out.println("New Id: "+ newID);
    }

    @Order(2)
    @Test
    public void testReadData(){
              given()
                      .log().all()
                      .contentType(ContentType.JSON)
              .when()
                      .get("/spartans/{id}",newID)
              .then()
                      .log().all()
                      .statusCode(200)
                      //.statusLine(containsString("OK"))
                      .body("name", is("Oznur Gunes"));
    }

    @Order(3)
    @Test
    public void testUpdateData(){
        String randomName = new Faker().name().firstName();

        Map<String, Object> updatedBody = new LinkedHashMap<>();
        updatedBody.put("name", randomName);
        updatedBody.put("gender", "Female");
        updatedBody.put("phone", 8745124312L);

                given()
                .log().all()
                .contentType(ContentType.JSON)
                        .body(updatedBody)
                .when()
                .put("/spartans/{id}", newID)
                .then()
                .statusCode(204);

    }
    @Order(4)
    @Test
    public void testDeleteData(){
        given()
                .log().all()
                .when()
                .delete("/spartans/{id}", newID)
                .then()
                .log().all()
                .statusCode(204);

    }
}
