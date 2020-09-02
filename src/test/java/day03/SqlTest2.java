package day03;
import io.restassured.http.ContentType;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.hamcrest.Matchers.*;

public class SqlTest2 {

    @BeforeAll
    public static void SetUp(){
        baseURI= "http://52.72.23.155:1000/ords/hr";
    }
    @DisplayName("SQL Test")
    @Test
    public void VerifyFirstRegion(){

        given()
                .pathParam("id",1)
                .when()
                .get("/regions/{id}").prettyPeek()
                .then().assertThat()
                .statusCode(200)
                .body("region_name", is("Europe"))
                .body("region_id",is(1))
                .time(lessThan(5L),TimeUnit.SECONDS);

}
}