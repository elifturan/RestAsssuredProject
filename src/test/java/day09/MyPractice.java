package day09;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import Pojo.Spartan;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class MyPractice {

    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI="http://54.174.216.245";
        RestAssured.port=8000;
        RestAssured.basePath="/api";
    }
    @ParameterizedTest
    @CsvFileSource(resources = "/allSpartans.csv", numLinesToSkip = 1)
    public void testAddSpartan(String csvName, String csvGender,long csvPhone){
        Spartan spartan = new Spartan(csvName,csvGender,csvPhone);

        Response response=
                given()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/spartans")
                .prettyPeek();
        response.then()
                .statusLine(containsString("Created"))
                .statusCode(201)
                .body("success", is("A Spartan is Born!"))
                .body("data.id",is(notNullValue()))
                .body("data.name", is(containsString("Draco")))
                .body("data.gender",is(containsString("Male")))
                .body("data.phone",is((9876543210l)))
        ;
    }

}
