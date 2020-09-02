package day05;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MyPostRequestforLibrary {

    @BeforeAll
    public static void SetUP() {
        RestAssured.baseURI = "http://library1.cybertekschool.com";
        RestAssured.basePath = "/rest/v1";
    }

    @DisplayName("Post/Login as a Librarian")
    @Test
    public void testLoginEndPoint() {

        given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email", "librarian68@library")
                .formParam("password", "hsTpIdzm")
                .when()
                .post("/login")
                .then()
                .log().all()
                .statusCode(200)
                .body("token", is(notNullValue()));

        /*
        {
    "token": "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjA4OSIsImZ1bGxfbmFtZSI6IlRlc3QgTGlicmFyaWFuIDY4IiwiZW1haWwiOiJsaWJyYXJpYW42OEBsaWJyYXJ5IiwidXNlcl9ncm91cF9pZCI6IjIifSwiaWF0IjoxNTk4NDExMjYxLCJleHAiOjE2MDEwMDMyNjF9._lGikdyI64BxFzF5zWGvKjI5Nvq3CCiSqbUQGV_6WP8",
    "redirect_uri": "//library1.cybertekschool.com/redirect.html?t=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyIjp7ImlkIjoiMjA4OSIsImZ1bGxfbmFtZSI6IlRlc3QgTGlicmFyaWFuIDY4IiwiZW1haWwiOiJsaWJyYXJpYW42OEBsaWJyYXJ5IiwidXNlcl9ncm91cF9pZCI6IjIifSwiaWF0IjoxNTk4NDExMjYxLCJleHAiOjE2MDEwMDMyNjF9._lGikdyI64BxFzF5zWGvKjI5Nvq3CCiSqbUQGV_6WP8"
}

         */
}
    @DisplayName("Testing out from utlity")
    @Test
    public void runUtilityMethod(){
        System.out.println( loginAndGetToken("librarian68@library","hsTpIdzm") );
    }
    public static String loginAndGetToken(String username, String password){
        String token="";
        Response response =
                (Response) given()
                .log().all()
                .contentType(ContentType.URLENC)
                .formParam("email",username)
                .formParam("password",password)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .log().all();

        token=response.jsonPath().getString("token");
        return token;
    }


}
