package day02;

public class ClassNotes {
}
/*
JUnit 5 : latest version of Junit , with new feature support for later java versions need minimun jdk 8 to work
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.7.0-M1</version>
        </dependency>

         @BeforeAll  @BeforeEach  @Test @AfterEach  @AfterAll
         @DisplayName("Your preferred name here")
         @Disable // ignorining certain test
         import static org.junit.jupiter.api.Assertions.*;
         assertEquals(  ,    )
         assertTrue(     )
----------------------------------
        Hamcrest Assertion Library
        import static org.hamcrest.Matchers.*;
        for Readable assertions using Hamcrest matchers
            assertThat(5+4 , is(10) ) ;
            is( 10 ) equalTo( 10 )  is( equalTo( 10 ) )
            not( 10 )  is ( not(10) )
--------------------------------------------------------
----  RestAssuerd ---
            a java client library for testing restapi in fluent way
            with given when then style
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>4.3.1</version>
            <scope>test</scope>
        </dependency>
        RestAssuerd Library use Hamcrest matchers for its assertions in most cases
        Hamcrest Library is included in RestAssuerd dependency already
        so we do not need to add extra dependency in pom file
        if you want to use it without RestAssuerd you may add dependency separately
        import static io.restassured.RestAssured.* ;
        import static io.restassured.matcher.RestAssuredMatchers.* ;
        import static org.hamcrest.Matchers.*;
        RestAssuerd.somemethodsHere

  ---------- >>>>   or since we directly did static import , we can access all the static method directly
        get("some requestURL")  --->> Response object
        delete("somerequest URL") --->>Response object
        // do some validation separately using that Response object
        Or we can do method chaining  given when then style

        given()  // ResquestSpecification
             .   // query parameter , headers , authentication ,
                 // path variables
                 // log
        when()
             .   // HTTP Methods  GET POST PUT DELETE   ---Response
             .   // optionally print the m out to see the result  , log
        then().  // ValidatableResponse
                // ValidatableResponse
                // Assestions starts here ,
                //you may add as many assertions as you want
                // if any assertion fail the whole chain fail
        2 way of printing the response
       *** prettyPrint -->> print the body and return String
                -- no more chaining method of Response Object if this used

       *** prettyPeek --->> print the entire respons object and Return same Response Object
        including status code , header , body
        since it returns same response object
        we can keep chaining the methods after prettyPeek

you may set the baseURI AND basePath in @BeforeAll method for reuse
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI = "http://54.174.216.245:8000" ;
        RestAssured.basePath = "/api" ;
    }

 you can log the request and response separately using the log methods
                given()
                .log().differentOptions here
                .log().all() and so on for request
              then()
                .log().differentOptions here
                .log().all() and so on for response
    RestAssuerd support path variable and query parameters
        it will be under given section
        path variable
            given()
                .log().all()
                .pathParam("id",971).
            when()
                .get("spartans/{id}").  ...
            or
            given()
                .log().all().
            when()
                .get("spartans/{id}" , 971 ).
        query parameters
            given()
                .log().all()
                .queryParam("apikey","value here")
            // it will automatically add ?apikey=something  at the end of url

        Please open up new class called ZipCode Test
        Add baseURI as http://api.zippopotam.us
            basePath as /us
        under @BeforeAll
        add
            path variable {zipcode} in given section
        send Get request
        then  check the status code 200
        check the contentype header is json
             body : post code -- the zipcode you entered
                    country  United States
                    longitude  -- the expected value
                    state    --  the expected value
        if you are done with this
            do the same for below endpoint
            api.zippopotam.us/us/:state/:city
/// Please create a new class called OpenMovieDB_Test
        add @BeforeAll
          add your baseURI as http://www.omdbapi.com
        // make a request
          by adding few query parameters like
          apikey =  your APIKEY
          t =  the movie you want to search
          plot =  full
          then status code 200
                contentype is json
                body
                    title is what you are searching for
                    year is as you expected
                    first rating value
                    last rating value
Practice what we learned for Open Weather API
Get free API Key from here by signing up
https://openweathermap.org/guide#how
Read the doc here for available endpoints for Current Weather Data
https://openweathermap.org/current
it use query parameters to filter the data you need.
api.openweathermap.org/data/2.5/weather?q={city name}&appid={your api key}
api.openweathermap.org/data/2.5/weather?q={city name},{state code}&appid={your api key}
api.openweathermap.org/data/2.5/weather?q={city name},{state code},{country code}&appid={your api key}

 */
