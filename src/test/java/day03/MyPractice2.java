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
public class MyPractice2 {

    @BeforeAll
    public static void setUP(){
     RestAssured.baseURI="http://52.72.23.155";
     RestAssured.port=8000;
     RestAssured.basePath="/api";  //"/api/spartans"
    }

    @DisplayName("Adding the data to Spartans")
    @Test
            public void Test1Post() {
       String newData= "{\n" +
               "  \"name\" : \"Cigdem\",\n" +
               "  \"gender\" : \"Female\",\n" +
               "  \"phone\" : 234567890\n" +
               "}" ;
        System.out.println("My new body data : " + newData);

        given()
                .contentType(ContentType.JSON)
                .body(newData)
                .log().all().

                when()
                .post("/spartans").

                then()
                .log().all()
                .statusCode( is(201))
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Cigdem"))
                .body("data.gender",is("Female"))
                ;
    }
    @DisplayName("Practice")
    @Test
    public void postRequest(){
        String newData = "{\n" +
                "  \"name\"  : \"Mariana\",\n" +
                "  \"gender\": \"Female\",\n" +
                "  \"phone\": 1244567890\n" +
                "}" ;

        Response response=
                given()
                .contentType(ContentType.JSON)
                .body(newData)
                .log().all().
                        when()
                .post("/spartans")
                .prettyPeek();
        System.out.println("This is id: "+ response.path("data.id"));
        System.out.println("This is name: "+ response.path("data.name"));
        System.out.println("This is gender: " + response.path("data.gender"));
        System.out.println("This is phone: " + response.path("data.phone"));


    }
    @DisplayName("Updating existing Data")
    @Test
    public void updateSpartan() {
        String updateBody = "{\n" +
                "   \"id\": 167,\n" +
                "   \"name\": \"Marianan Mary\",\n" +
                "   \"gender\": \"Female\",\n" +
                "   \"phone\": 1244567890\n" +
               "}";

        given()
                .contentType(ContentType.JSON)
                .body(updateBody)
                .log().all()

                .when()
                .put("/spartans/{id}",167)

                .then()
                .log().all()
                .statusCode(204);
        }
        @DisplayName("Practice Put")
        @Test
        public void newUpdate(){
            String updateBody = "{\n" +
                    "   \"id\": 166,\n" +
                    "   \"name\": \"Nur Light\",\n" +
                    "   \"gender\": \"Female\",\n" +
                    "   \"phone\": 1244567890\n" +
                    "}";
            given()
                    .contentType(ContentType.JSON)
                    .body(updateBody)
                    .log().all()
                    .when()
                    .put("/spartans/{id}", 166)
                    .then()
                    .log().all()
                    .statusCode(204)
                    ;
        }
        @DisplayName("Deleting the data")
        @Test
        public void deleteData(){
            when()
                    .delete("/spartans/{id}",161).
                    then()
                    .statusCode(204)
            ;
        }
        @DisplayName("Data deleting")
        @Test
        public void dataDeleting(){

        when()
                .delete("/spartans/{id}",162)
        .then()
                .statusCode(204);
        }


    }


