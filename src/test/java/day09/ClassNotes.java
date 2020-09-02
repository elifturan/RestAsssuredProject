package day09;

public class ClassNotes {
}
/*
Day 14 :
    Quick review :
      *   We reviewed API_DB combo validation
        send a api request get the response and validated the response body
        against the expected result we got from the database
      * Test Execution Order
            By default , your tests in Test class will be exececuted
            by alphabetical order
            We can change that behaviour by adding annotation at class level
            and at method method
            at class level , order by @Order annotation number
            @TestMethodOrder(MethodOrderer.OrderAnnotation.class)
            at method level
            @Order(1) @Order(2) @Order(3)
            if we want it to be ordered by display name
            @TestMethodOrder(MethodOrderer.DisplayName.class)
            at method level
            just add regular @DisplayName with the name you specified

    ParameterizedTest
            In Junit5 it's easy to write Parameterized Test
            just by adding annotation and provide the source of data
            We looked one of the source called CsvFileSource
            it will read from the csv file under src/test/resources
            and run your test for each row according to your row count
    //if you have csv file with this content
    10, 12
    2, 4
    5, 6
    @ParameterizedTest
    @CsvFileSource(resources = "/yourCsvFile.csv")
    public void test(int a, int b){
        // inside the test a and b will be replaced by the actual value in your csv file
    }
    // what if we have a header line in csv file
    num1 , num2
    10, 12
    2, 4
    5, 6
    @ParameterizedTest
    @CsvFileSource(resources = "/yourCsvFile.csv", numLinesToSkip = 1 )
    public void test(int a, int b){
        // inside the test a and b will be replaced by the actual value in your csv file
    }
    We also learned to change the default display name for the test
    so it can be more readable and easy to find error when test fail

    @ParameterizedTest(name = "This is where we customize")
    in the name String we can access the iteration number and actual columm data
    iteration number we can use {index}
    @ParameterizedTest(name = "This is iteration {index}")
    actual data in csv we can use {YourColumnIndexGoesHere}  {0} {1}
    @ParameterizedTest(name = "Fist column data is {0} and second column data is {1}")
    ------  Assume that you have a test class
    --- with multiple methods that share same request speciication
    --- and have similar  response assertion
    -- in this case , we can save the request specifcation and response speciication for reuse
    given  -- RequestSpecification
    when   -- Response
    then   -- ValidatableResponse


    -------  We are doing a role based access control test
     -- for the Spartan app with username password
     for the credentials  user/user
       user should not be able to delete data
       user should not be able to post data
       user should not be able to update data
       all these 3 tests share same username and password
       and we can also add accept json result back
       and we want to log all the request
        all these test can share same response status as 403
        and all tests response content type is json
        and all test has Date header not null assertion
        and we want to see the log of all request
//   Practice the requestSpec and response Spec with POST /Spartans endpoint
//   extract out the request specification for
            authentication (admin/admin)
            logging (all of them)
            contentType (json)
            randomBody (created from createRandomSpartan method)
//  extract out the responseSpec
            statusCode (201)
            Date (not null like previous class)
            body
                 "success": "A Spartan is Born!",
                 id is not null
                 name is the name we used to create the Spartan object
                 gender is the gender we used to create the Spartan object
                 phone is the phone we used to create the Spartan object

How can we reuse the parts in given section in different tests
        for the requests
        especially the auth and headers and parameters if needed
        RequestSpecification object can be used to save the reusable part of given section
        so we do not have to set for each and every test
How can we reuse the parts in then section if we have
        commmon assertions between different tests
        ReponseSpecification
        How do I get the RequestSpecification object
        getting the RequestSpecification object can be as easy as
        getting the reusable part of given section of your test
        and save it into a veriable type called RequestSpecification
        RequestSpecification reqSpec = given()
                            .auth().basic("admin","admin")
                            .contentType(ContentType.JSON)
                            .andSoOn......
        or we can also use RequestSpecBuilder class
How Do I get ReponseSpecification object
        we need to use the ResponseSpecBuilder class
            and add all the expectation for our assetion
            ResponseSpecBuilder resSpecBuilder = new ResponseSpecBuilder();
            validPostResponseSpec  =    resSpecBuilder
                                            .expectStatusCode(201)
                                            .expectHeader("Date", notNullValue(String.class) )
                                            .log(LogDetail.ALL)
                                            .expectBody("success", is("A Spartan is Born!") )
                                            .build() ;

        so eventually you can use the spec in the chain directly
        given()
            .spec(requestSpec)
            .more Stuff if you need
        when()
            .getOrPost(/toSomeEndpoint)
        then()
            .spec(responseSpec)
            .more Stuff here if you need
    If you want to share these spec in different test in same class
    you can add static field and set the spec value in the @BeforeAll Section
    IF YOU WANT TO SHARE THIS IN DIFFERENT TEST CLASSES
    You can create a API_Spec_Utility with either static fields or methods
    so you can reuse it everywhere
    // It might look something like this
    // so it can be easily shared everywhere
    public class API_Spec_Utility{
        public static RequestSpecification SUCCESS_GET_SPEC = ......
        public static RequestSpecification SUCCESS_PUT_SPEC = ......
        public static ReponseSpecification SUCCESS_GET_RESPONSE_SPEC = ......
        public static RequestSpecification SUCCESS_PUT_RESPONSE_SPEC = ......

    }
    // you can also combine more than one spec by chaining
 */