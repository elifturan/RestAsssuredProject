package day05;

import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Pojo.Spartan;
import static io.restassured.RestAssured.*;

public class MySecureSpartanTest {

    @BeforeAll
    public static void SetUP(){

        RestAssured.baseURI="http://23.23.75.140";
        RestAssured.port=8000;
        RestAssured.basePath="/api";
    }
    @DisplayName("Test GET /spartan/{id} Endpoint with No Credentials")
    @Test
    public void testGetSingleSpartanSecured(){

        given()
                .log().all()
                .pathParam("id",31)
                .when()
                .get("/spartans/{id}")
                .then()
                .log().all()
                .statusCode(401);
    }
    @DisplayName("Test GET /spartan/{id} Endpoint with Credentials")
    @Test
    public void testGetSpartanWithCredentials(){
         int newID=createRandomSpartan();
         given()
                 .log().all()
                 .auth().basic("admin","admin")
                 .pathParam("id",newID)
                 .when()
                 .get("/spartans/{id}")
                 .then()
                 .log().all()
                 .statusCode(200);

    }
    public static int createRandomSpartan(){
        Faker faker =new Faker();
        String name= faker.name().firstName();
        String gender=faker.demographic().sex();
        long phone= faker.number().numberBetween(1000000000l,9999999999l);

        Spartan spartan= new Spartan(name,gender,phone);

        Response response=
                given()
                .log().all()
                .auth().basic("admin","admin")
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/spartans")
                .prettyPeek();
        return response.jsonPath().getInt("data.id");



    }

}
