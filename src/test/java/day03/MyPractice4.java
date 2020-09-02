package day03;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MyPractice4 {

    @BeforeAll
    public static void SetUp() {

        RestAssured.baseURI = "http://52.72.23.155";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }
    @DisplayName("Posting Test")
    @Test
    public void PostingData(){

        String newData= "{\n" +
                "  \"name\"  : \"Fatma Ak\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 6234567890\n" +
                "}" ;
        System.out.println("My granma's name "+ newData);

        given()
                .contentType(ContentType.JSON)
                .body(newData)
                .log().all()
                .when()
                .post("/spartans")
                .then()
                .statusCode(is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name",is("Fatma Ak"))
                .body("data.gender",is("Female"))
                ;
    }
    @DisplayName("POstRequestExtractingData")
    @Test
    public void PostingNewData(){
        String newData= "{\n" +
                "  \"name\"  : \"Hatice Ak\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 6230567890\n" +
                "}" ;
        Response response=
        given()
                .contentType(ContentType.JSON)
                .body(newData)
                .log().all()
                .when()
                .post("/spartans")
                .prettyPeek();

        System.out.println("The id with normal path: "+ response.path("data.id"));
        System.out.println("The id with json path: "+ response.jsonPath().getInt("data.id") );

        System.out.println("The name with normal path: "+ response.path("data.name"));
        System.out.println("The name with json path: "+ response.jsonPath().getString("data.name"));

        System.out.println("The gender with normal path: "+response.path("data.gender"));
        System.out.println("The gender with json path: "+response.jsonPath().getString("data.gender"));

        System.out.println("The phone with normal path: "+ response.path("data.phone"));
        System.out.println("The phone with json path: "+response.jsonPath().getLong("data.phone"));

    }
    @DisplayName("Testing Put /api/spartans")
    @Test
    public void UpdatingData(){
        String updateBody = "{\n" +
                "   \"id\": 168,\n" +
                "   \"name\": \"Fatma P Ak\",\n" +
                "   \"gender\": \"Female\",\n" +
                "   \"phone\": 1000000000\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .log().all()
                .when()
                .put("/spartans/{id}",168)
                .then()
                .log().all()
                .statusCode(204);

    }
    @DisplayName("Deleting Data")
    @Test
    public void deleteData(){

        when()
                .delete("/spartans/{id}", 159)
                .then()
                .statusCode(204);
    }
}
