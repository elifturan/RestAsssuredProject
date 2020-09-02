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

public class SQLTests {

    @BeforeAll
    public static void SetUp() {

        RestAssured.baseURI = "http://52.72.23.155";
        RestAssured.port = 1000;
        RestAssured.basePath = "/ords/hr";

    }

    @DisplayName("Testing from sql getting list of all employees")
    @Test
    public void getAllEmployees() {
        Response response =
                given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get("/employees")
                        .prettyPeek();
    }

    @DisplayName("Testing from sql getting one employee")
    @Test
    public void getOneEmployee() {
        Response response =
                (Response) given()
                        .contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get("/employees/{id}", 100)
                        .prettyPeek();
        response.then().statusCode(404);
        int statusCode = response.statusCode();

    }

    @DisplayName("Testing from sql getting one employee")
    @Test
    public void getAllCountries() {
        Response response =
                (Response) given()
                        //.contentType(ContentType.JSON)
                        .log().all()
                        .when()
                        .get("/countries")
                        .then()
                        .statusCode(200);

    }
}