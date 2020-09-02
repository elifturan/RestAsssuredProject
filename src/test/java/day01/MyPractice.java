package day01;

import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import io.restassured.RestAssured;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;

import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class MyPractice {

    @BeforeAll
    public static void setUp(){
        System.out.println("Starting to test");
    }
    @BeforeEach
    public void beforeEachTest(){
        System.out.println("Checking test and start to running");
    }

    @Test
    public void Test1(){ //https://www.breakingbadapi.com/api/characters/:character_id?limit=10&offset=10

        Response response = get("https://www.breakingbadapi.com/api/characters");
        System.out.println("Status code of this response " +response.statusCode());
        System.out.println("other way to get the status code "+ response.getStatusCode());
        System.out.println("Getting status line of this response "+ response.statusLine());
        System.out.println("Getting the value of date header "+ response.header("Date"));
        System.out.println("Other way to getting the value of date header " + response.getHeader("Date"));
        System.out.println("Getting the value of Content-Type header " + response.header("Content-Type"));
        System.out.println("Getting the value of Content-Length header "+ response.header("Content-Length"));
        System.out.println("Getting the value of Connection header " + response.header("Connection"));
        System.out.println("Getting the Content-type " + response.contentType());
        System.out.println("Other way to getting the content type "+response.getContentType());
    }

    @DisplayName("Testing the BreakinBad endpoint")
    @Test
    public void Test2(){
        Response response = get("https://www.breakingbadapi.com/api/characters");

        assertEquals(200, response.statusCode());
        assertEquals(200,response.getStatusCode());
        assertEquals("HTTP/1.1 200 OK", response.statusLine());
        assertEquals("application/json; charset=utf-8", response.header("Content-Type"));
        assertEquals("keep-alive",response.header("Connection"));
        assertEquals("application/json; charset=utf-8",response.contentType());
        assertEquals("application/json; charset=utf-8",response.getContentType());



    }

    @DisplayName("Testing the BreakingBad endpoint")
    @Test
    public void Test3(){
        Response response =get ("https://www.breakingbadapi.com/api/characters");
        System.out.println(response.asString());
        System.out.println(response.body().asString());

        String BreakinBadBody= response.asString();
        assertEquals("",BreakinBadBody);
        assertEquals(100,BreakinBadBody.length());
    }

    @DisplayName("Printing the response body using method")
    @Test
    public void printBody(){
        Response response = get("https://www.breakingbadapi.com/api/characters");
         response.prettyPrint(); //only json format
        //response.prettyPrint().toLowerCase();
         //response.prettyPeek(); //asString format and than json format
         //response.body().prettyPeek();
        System.out.println("==========");

        int statusCode = response.prettyPeek().statusCode();
        System.out.println("Printing the status code "+ statusCode);
    }

    @Test
    public void Test4() {
      int firstN= 100;
      int secondN=200;
      assertThat(firstN+secondN, is(300));
      assertThat(firstN+secondN, equalTo(300));
      assertThat(firstN+secondN,is(equalTo(300)));
      assertThat(firstN+secondN, not(400));
      assertThat(firstN+secondN, is(not(400)));

      String fname="Suheyla";
      String mname=" Nihal";
      assertThat(fname+mname,is("Suheyla Nihal"));
        assertThat(fname+mname,equalTo("Suheyla Nihal"));
        assertThat(fname+mname,is(equalTo("Suheyla Nihal")));

        assertThat(mname,equalToCompressingWhiteSpace("Nihal"));
        assertThat(fname+mname,is(equalToIgnoringCase("suheyla nihal")));
    }


    @DisplayName("Support for Collection")
    @Test
    public void Test5(){

        List<Integer> numList = Arrays.asList(1,2,3,4,5,6,7,8);
        assertThat(numList,hasSize(8));
        assertThat(numList,hasItem(5));
        assertThat(numList,contains(1,2,3,4,5,6,7,8));

    }

    @DisplayName("First Attempt for chaining")
    @Test
    public void Test6(){

        when().
                get("https://www.breakingbadapi.com/api/characters").
                prettyPeek().
                then().
                statusCode(200).
                //header("Connection", "keep-alive");
                header("Connection",equalToIgnoringCase("keep-alive"));

    }


        @AfterEach
    public void afterEachTest(){
        System.out.println("Everything is flowing smoothly ");
    }
    @AfterAll
    public static void AfterAllCheck(){
        System.out.println("Test is done!!");
    }

}
