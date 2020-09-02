package day01;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RestAssuredMethodChaining {

    @DisplayName("First Attempt for chaining")
    @Test
    public void chainingMethodsTest1(){

        //"http://52.72.23.155:8000/api/hello"
        //given().
//some more information you want to provide other than request URL
//like header, query parameter, path variable, body...
//when().
//you send the request GET POST PUT PATCH, or DELETE
//then()
//Validate something here.

        when().
                get("http://52.72.23.155:8000/api/hello").   // sending a request to this url
                then().                         // asssrtions start here
                statusCode(200).        // asserting status code is 200
                header("Content-Length", "17" ) ;  // asserting header is "17"
    }
    @DisplayName("Using Hamcrest matcher for assertion")
    @Test
    public void testingWithMatcher(){

        when().
                get("http://52.72.23.155:8000/api/spartans").   // sending a request to this url
                then().                         // asssrtions start here
                statusCode( is(200) ).        // asserting status code is 200
                header("Content-Length",  equalTo("17") ) ;  // asserting header is "17"
    }
    @DisplayName("Testing GET /api/Spartans endpoint")
    @Test
    public void testAllSpartans(){
        // given part -- RequestSpecification
        // you can add information like header, query param, path var, body
        // if this request need authentication , it also goes to give section
        // when part --- Send Request(GET POST PUT DELETE)
        //                  --Get response
        // then part -- ValidatableResponse
        // this is where assertions start
        // you can chain multiple assertions
        // if any assertions fail , whole test fail.

        given().  // add all your request specific information like header, query param, path var, body
                header("Accept","application/xml").
                when().
                get("http://52.72.23.155:8000/api/spartans").
                prettyPeek().
                //prettyPrint(). does not work because after we are not able write method
                then().
                statusCode( is(200) ) ;

    }

}
