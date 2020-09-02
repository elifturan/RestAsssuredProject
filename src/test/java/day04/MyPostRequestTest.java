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

public class MyPostRequestTest {

    @BeforeAll
    public static void setUp(){
       RestAssured.baseURI="http://52.72.23.155";
       RestAssured.port=8000;
       RestAssured.basePath="/api";
    }
    @DisplayName("Practice post as a String")
    @Test
    public void testPostWithString(){
        Faker faker = new Faker();
        String fakeName= faker.name().firstName();
        System.out.println("Lets see what they give us: "+fakeName);
        String bodyString= "{\n" +
                "  \"name\"  : \"" + fakeName+ "\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 6234567890\n" +
                "}";

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyString)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .body("data.name", is(fakeName))
                .body("data.gender",is("Female"))
                .body("data.phone",is(6234567890l));

    }
    @DisplayName("Posting with Map")
    @Test
    public void testPostWithMAp(){

        Map<String,Object> mapBody= new LinkedHashMap<>();
        mapBody.put("name", "Suheyla N");
        mapBody.put("gender","Female");
        mapBody.put("phone",5678976543l);

        System.out.println("MapBody: "+ mapBody);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(mapBody)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .body("data.name",is("Suheyla N"));
    }
    @DisplayName("Testing Post with file")
    @Test
    public void testPostWithFile(){

        File file =new File("spartan.json");
        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(file)
                .when()
                .post("/spartans")
                .then()
                .log().all()
                .statusCode(201)
                .body("data.name",is("From file"));
    }
    @Test
    public void testPostWithPOJO(){
        Spartan spartan =new Spartan("Asude April", "Female", 4142014666l);

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(spartan)
                .when()
                .post("/spartans")
                .then()
                .statusCode(201)
                .log().all();
    }
}
