package day07;

public class ClassNotes {
}
/*
Day 12 :
Warm up task :
Make a request to GOT all characters endpoint :
GET https://api.got.show/api/book/characters
then store all the character name whose house value is House Stark
Hint :
The response is top level json array so you will not need to provide json path
The method will look like this getList(" findAll { condition here }.theFieldNameHere")  (edited)
Assert the list size is 76
------------------
Complete before class :

    * Add a package called utility
    * Add ConfigurationReader.java
          DB_Utility.java from your jdbc class
    * Add configuration.properties under your root project folder
    * Add dependency for Oracle Driver and MySQL driver
        <dependency>
            <groupId>com.oracle.ojdbc</groupId>
            <artifactId>ojdbc8</artifactId>
            <version>19.3.0.0</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.21</version>
        </dependency>
----------------------------
        Backend team has already designed the Rest API with all endpoints
        and yet it has not been implemented yet
        UI team need to use these endpoints to display the data on front end
        since the API is not ready but the design is already there
        They decided to have a mock api -
            --- a fake result that similar to real result but with dummy data
        /api/sparatans  --->> supposed to return list of json array that match database
         and now it just return some hard coded dummy data until the api impl is ready
         when the backend team is done with implememtation
         all they have to do is switch the url and point to the real api
         Mock api endpoints might look like this
         someFakeDomain.comORSomeIP/api/spartans
         Real implemented API after finish might look like
         theRealDomain.com/api/spartans
    --------------------------
    We Created a Test class for Validating Library app data from Databse and API
    Any web application usually has at least 3 tier/layer
                UI, Presentation tier
                API, Business Logic tier
                DB , Data tier
        In this particular test ,
             * First we ran the query to get our expected data from dabatase
                     - book count
                     - borrowed count
                     - users count
                by using 3 different sql queries and stored the result as expected result.
        We have a endpont /Dashboard_status
                    to get those information in json format
           So our test send a request to the endpoint get the response
           and validate the json body agains the expected data we got from the database
        --------------
        We wanted to make our code robust so it does not have hard coded values
            we used properties file to get urls , username password for db , api
            we generated the token dynamically so we can fresh token each time
            we added all the set up needed for database and api under @Before All section
            We have 2 utilities , one for making connection to the database
            Another for API , to get latest token by providing username password
            and we also added the @AfterAll section
            to clearn up our connection using destroy method in DB_utility
            and clean up the RestAssured static fields value
            like baseURI , basePath, port using reset() method
        ------------
        in the test
            we ran 3 query to get our expected data using the DB_Utility Method
            the sent a request to get the json body and validate the result
            against the expected data we got from the database
            right now no expected data is hardcoded
            so if the actual book acount , user count , borrowed book count change ,
            it will not affect the test result
            because we are getting the expected result dynamically from the database
        -------
        RestAssured.baseURI = ConfigurationReader.getProperty("library1.base_url");
        RestAssured.basePath = "/rest/v1";
        //added a utility class that contains below method
        // Setting up DB connection by using the utility method
        DB_Utility.createConnection("library1");
        // We want to return this token out of the method so the next class can use it
        LibraryUtil.loginAndGetToken(ConfigurationReader.getProperty("library1.librarian_username")
                                            , ConfigurationReader.getProperty("library1.librarian_password"));
        if we want to run the test in libary2 then we will have to change
            all libarry1 to library2 in many places
        so we decided to move it out and make a method out of it
        so we just pass environment value and set up DB and API for that env
        and return the token
     // this method does exactly that
   public static String setUpRestAssuredAndDB_forEnv(String env){
        RestAssured.baseURI = ConfigurationReader.getProperty( env + ".base_url");
        RestAssured.basePath = "/rest/v1";
        //added a utility class that contains below method
        // Setting up DB connection by using the utility method
        DB_Utility.createConnection(env);
        // We want to return this token out of the method so the next class can use it
          return      LibraryUtil.loginAndGetToken(ConfigurationReader.getProperty(env + ".librarian_username")
                                            , ConfigurationReader.getProperty(env + ".librarian_password"));
    }
    // now our before all method will be much cleaner
    @BeforeAll
    public static void init(){
        libraryToken = LibraryUtil.setUpRestAssuredAndDB_forEnv("library1") ;
    }
     // in order to run in different env we just need to change "library1" -> "library2"
     // after adding library2 into configuration file we have
    but we still have hardcoded data in @before all
    eventually we decided to get the active environment from properties file
    so we added  active_env=libarry1 into configuration file
    and changed the @BeforeAll method to
    @BeforeAll
    public static void init(){
        String active_env = ConfigurationReader.getProperty("active_env");
        libraryToken = LibraryUtil.setUpRestAssuredAndDB_forEnv(active_env) ;
    }
    now same test will run for different env by just changing configuration file
    active_env=libary2

 */