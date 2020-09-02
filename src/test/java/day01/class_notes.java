package day01;

public class class_notes {
}
/*
Day 6 :
    Authentication : telling the system who you are
                by providing credentials
    Authorization :  actions you can take accoridng to the who you are
    I have worked with different type of Authorization method
    like
   --- Basic Auth
        one of my internal app use basic auth
        and I need to provide username and password
        for each request I make
    Token based :
       ---- API Key
        another project I worked on use API-KEY
        I need to provide the API-key in custom header(x-library-token)
        for each and every request
        -----    Bearer token
        another project I worked on use Bearer token
        I need to provide the Bearer token under the Authorization header
        The value of header always start with
                Bearer(space) Long JWT token goes here

<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.cybertek</groupId>
    <artifactId>RestAssuredProject</artifactId>
    <version>1.0-SNAPSHOT</version>
<!--    This is for instructing to always build with Java 8 anywhere-->
    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
    </properties>
    <!--    This is for instructing to always build with Java 8 anywhere-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.8.1</version>
                <configuration>
                    <source>8</source>
                    <target>8</target>
                </configuration>
            </plugin>
        </plugins>
    </build>
<!--    add junit 5 dependency - does not matter which version-->
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.7.0-M1</version>
        </dependency>

        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.7.0-M1</version>
        </dependency>

    </dependencies>
</project>

https://junit.org/junit5/docs/current/user-guide/
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Calculator Test")
public class SampleTest {
    @DisplayName("Addition test for 5+4")
    @Test
=    public void calculatorTest(){
        System.out.println("Hello World");
        // assert 4 + 5  is 9
        assertEquals(9,4+5);
        // how do we add error message if the assertion fail
        assertEquals(10, 5+4 , "Hey wrong result!!");
    }
}
----------
TestNG  ---
     @beforeClass @beforeMethod @test  @afterMethod @afterClass
     @ignore

Junit 4 ---
     @before @beforeMethod  @test  @afterMethod @after
     @ignore

JUnint 5
     @BeforeAll  @BeforeEach  @test @AfterEach @AfterAll
     @Disabled (same as ignore)
     @DisplayName

------
RestAssured is a library for testing restful API programmatically
it use a library called Hamcrest for asserting the response
So we will learn simple Hamcrest way of assertion along the way
--- The dependency for rest assure is this
--- the static import we need to make to directly work with it's methods
are below
import static io.restassured.RestAssured.* ;
import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
  // we need these two import for this to work
        // Hamcrest already come with RestAssured dependency
        // hamcrest library use the assertThat method for all assertions
        // hamcrest use built in matchers to do assertion
        // couple common ones are :
            //  is( some value )
            // equalTo( some value)
            //  or optionally   is ( equalTo(some value) )
            // not
        assertThat( num1 + num2 ,  is(9)   );
        assertThat( num1 + num2 ,  equalTo(9)   );
        assertThat(num1+num2,    is (equalTo(9) ) ) ;
        // not( value )
        // is( not (some value ) )
        // not( equalTo(11) )
        assertThat(num1+num2 , not(11) );
        assertThat(num1+num2, is( not(11) ) );
        // save your first name and last name into 2 variables
        // test the concatenation result using hamcrest matcher

        // String matchers
        // equalToIgnoringCase
        // equalToCompressingWhiteSpace
        //containsString, endsWith, startsWith - test string matching
        // how to check in caseInsenstive manner
        assertThat(firstName , equalToIgnoringCase("kaisar ") );
        // how to ignore all whitespaces
        assertThat(firstName, equalToCompressingWhiteSpace("Kaisar"));
        List<Integer> numList = Arrays.asList(11,44,3,55,88,5) ;
        // checking the list size is 6
        assertThat(numList, hasSize(6)  );
        // checking the list contains 11
        assertThat(numList, hasItem(11) );
        // has items is used to check multiple items : 11, 3, 5
        assertThat(numList, hasItems(11,3,5));
        // contains method works like equals here
        // checking the list contains all the items in exact order : 11,44,3,55,88,5
        assertThat(numList, contains(11,44,3,55,88,5 )   );

Rest Assure use method chaining to achive readable test
//       given().
            // some more information you want to provide other than request url
            // like header , query param , path variable , body
//       when()
            // you send the request GET POST PUT PATCH DELETE
//        then()
            // VALIDATE SOMETHING HERE
            // given part -- RequestSpecification
                        // you can add information like header, query param, path var, body
                        // if this request need authentication , it also goes to give section
            // when part --- Send Request(GET POST PUT DELETE)
        //                  --Get response
            // then part -- ValidatableResponse
                        // this is where assertions start
                        // you can chain multiple assertions
                        // if any assertions fail , whole test fail.
            given()  // add all your request specific information like header, query param, path var, body
                    .header("Accept","application/xml").
            when()
                    .get("http://54.174.216.245:8000/api/spartans").
                    prettyPeek().
            then()
                    .statusCode( is(200) ) ;

 */