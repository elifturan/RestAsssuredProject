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

public class MyPutPractice {

    @BeforeAll
    public static void init() {
        RestAssured.baseURI = "http://52.72.23.155";
        RestAssured.port = 8000;
        RestAssured.basePath = "/api";
    }

    @DisplayName("Put Request with Map")
    @Test
    public void putMap() {

        String faker = new Faker().name().firstName();

        Map<String, Object> UpdateMapPut = new LinkedHashMap<>();
        UpdateMapPut.put("name", faker);
        UpdateMapPut.put("gender", "Female");
        UpdateMapPut.put("Phone", 2343567887l);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(UpdateMapPut)
                .when()
                .put("/spartans/{id}", 160)
                .then()
                .log().all()
                .statusCode(204);
        when()
                .get("/spartans/{id}", 160)
                .then()
                .statusCode(200)
                .body("name", is(faker));
    }

    @DisplayName("Put Request body as a POJO")
    @Test
    public void testPutRequestWithPOJO() {

        String randomName = new Faker().name().firstName();

        Spartan spartan = new Spartan(randomName, "Male", 3456789012l);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .put("/spartans/{id}", 160)
                .then()
                .log().all()
                .statusCode(is(204));
    }

    @DisplayName("Patch request")
    @Test
    public void testPatchPartialUpdate() {

        String randomName = new Faker().name().firstName();
        Map<String, Object> patchBodyMap = new HashMap<>();
        patchBodyMap.put("name", randomName);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(patchBodyMap)
                .when()
                .patch("/spartans/{id}", 160)
                .then()
                .log().all()
                .statusCode(is(204));

    }
}