package day01;

import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Practice1 {
 //that is mine http://52.72.23.155:8000/api/hello
    // you may use your own IP
    // we are using spartan app that does not require password
    //http://54.174.216.245:8000/api/hello

    @Test
    public void test1(){

        // MAKE SURE YOUR REQUEST WORKS IN POSTMAN
        // IF ANYTHING DOES NOT WORK MANNUALY IT WILL NOT WORK IN HERE EITHER

        //RestAssured.get("URL HERE")
        // SINCE WE DID THE STATIC IMPORT
        // we can directly call the get method
        // after we send the request
        // we can save the result in to a type called Response
        // need this  import io.restassured.response.Response;
        Response response = get("http://52.72.23.155:8000/api/hello") ;
        // response object store all the information about the response we got
        // like status  , statusline , body , headers and so on
        System.out.println("status code of this response : " + response.statusCode()  ); //status code of this response : 200
        // this is another way of getting status code starts with HTTP/1.1
        System.out.println("getting status line of this response " + response.statusLine());//getting status line of this response HTTP/1.1 200

        // in restAssured there are usually 2 methods that does same action
        // one directly with the name like response.statusCode()
        // another stating with getXXX  like response.getStatusCode()
        System.out.println("status code of this response : " + response.getStatusCode()  );//status code of this response : 200
        // getting the header out of the response
        // we can use       response.header("the header name goes here )
        // or we can use    response.getHeader("the header name goes here )
        System.out.println("Getting the value of date header " + response.header("Date"));
        //Getting the value of date header Tue, 18 Aug 2020 18:57:15 GMT
        System.out.println("Getting the value of date header " + response.getHeader("Date") );
        //Getting the value of date header Tue, 18 Aug 2020 18:57:15 GMT

        //try to get Content-Type header value and Content-Length header value
        System.out.println("Getting the value of Content-Type header " + response.header("Content-Type"));
        //Getting the value of Content-Type header text/plain;charset=UTF-8
        System.out.println("Getting the value of Content-Length header " + response.header("Content-Length"));
        //Getting the value of Content-Length header 17

        //content-type is s common in pretty much all request so there is a built support for this header
        //by directly calling a method
        System.out.println(response.contentType()); //text/plain;charset=UTF-8
        System.out.println(response.getContentType()); //text/plain;charset=UTF-8
    }

    @DisplayName("Testing /hello endpoint")
    @Test
    public void testHello(){

        Response response = get("http://52.72.23.155:8000/api/hello");
        //we use sout on top so we are able to see on the console but here with assert when it is right does not see it

        // testing the status code returned correctly
        assertEquals(200, response.statusCode() );
        // testing the Content-Type header value is : text/plain;charset=UTF-8
        assertEquals("text/plain;charset=UTF-8" , response.header("Content-Type"));
        // alternatively use getHeader
        assertEquals("text/plain;charset=UTF-8" , response.getHeader("Content-Type"));
        // alternatively use response.contentType() or response.getContentType()
        assertEquals("text/plain;charset=UTF-8" , response.contentType() ) ;
        // testing the Content-length header value is : 17
        assertEquals("17" , response.header("Content-length") );

    }

    @DisplayName("Testing /hello endpint body")
    @Test
    public void testingHelloResponseBody(){
        //get the body and assert the body equal to Hello from Sparta
        Response response = get("http://52.72.23.155:8000/api/hello");

        //getting the body as String using asString method of Response object
        System.out.println(response.asString());//Hello from Sparta

        //getting the body by calling body method
        //PLEASE DONT USE TOSTRING -- IT DOES NOT GIVE YOU THE CONTENT
        System.out.println(response.body().asString() ); //Hello from Sparta

        //assert the body is Hello from Sparta, length is 17
        String helloBody= response.asString();

        assertEquals("Hello from Sparta", helloBody);
        //, length is 17
        assertEquals(17, helloBody.length());

    }
    @DisplayName("Printing the response body using method")
    @Test
    public void printBody(){
        Response response = get("http://52.72.23.155:8000/api/hello");
        //easy to print the response body and return at the same time //will show only body
        response.prettyPrint(); //Hello from Sparta //after we dont write or add code or method

        //another way to see the body quick is prettyPeek will shoe all info
        response.prettyPeek(); //after we can put code or method
        /*
        HTTP/1.1 200
Content-Type: text/plain;charset=UTF-8
Content-Length: 17
Date: Tue, 18 Aug 2020 18:39:54 GMT

Hello from Sparta

         */
        System.out.println("=================");

        int statusCode= response.prettyPeek().statusCode();
        //response.prettyPrint().toLowerCase();
        System.out.println("Printing only Status code " +statusCode);
/*
HTTP/1.1 200
Content-Type: text/plain;charset=UTF-8
Content-Length: 17
Date: Tue, 18 Aug 2020 18:44:00 GMT

Hello from Sparta
Printing only Status code 200
 */
    }
}
