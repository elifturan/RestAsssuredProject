package day08;

import static org.junit.jupiter.api.Assertions.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import utility.DB_Utility;
import java.util.List;
import static io.restassured.RestAssured.*;

public class SpartanApiDB_Practice {

    /**
     * The dev just implemented the search endpoint
     * and want it to be tested to make sure it's actually
     * returning the correct result from the database
     *
     *    GET /spartans/search?gender=Female&nameContains=a
     *  we want to test the count of result is correct
     *  for numberOfElements field.
     *
     *  The Database query to get the count is :
     *  // all the females that have a in their name , case insensitive
     *   -- This is how we get the data with case insensitive manner
     *      SELECT * FROM SPARTANS
     *       WHERE LOWER(gender) = 'female'
     *       and LOWER(name) LIKE '%a%' ;
     *
     *
     *      // Get the list of all IDs from your response
     *      // and validate the list against all the IDs from the database
     *
     */
    @BeforeAll
    public static void init(){

        RestAssured.baseURI = "http://100.25.192.231" ;
        RestAssured.port = 8000;
        RestAssured.basePath = "/api" ;

        DB_Utility.createConnection(ConfigurationReader.getProperty("spartan1.database.url") ,
                ConfigurationReader.getProperty("spartan1.database.username") ,
                ConfigurationReader.getProperty("spartan1.database.password")
        );
       // DB_Utility.createConnection("spartan1");

    }

    @DisplayName("Testing out my DB Connection ")
    @Test
    public void testDB(){

        //DB_Utility.runQuery("SELECT * FROM SPARTANS") ;
        //DB_Utility.displayAllData();

        // run this query so we can use it for expected result
        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery( query ) ;
        //DB_Utility.displayAllData();
        //  if you do , get the row count so we can see how many data returned
        // and use it for expected result in next test
        int expectedResult =  DB_Utility.getRowCount();
        System.out.println("expectedResult = " + expectedResult); //expectedResult = 70
    }

    @DisplayName("Testing /spartans/search Endpoint and Validate against DB")
    @Test
    public void testSearch(){

        // make a request to GET /spartans/search
        // using query parameter gender Female  nameContains a

        Response response = given()
                .log().all()
                .queryParam("gender","Female")
                .queryParam("nameContains", "a").
                        when()
                .get("/spartans/search")
                .prettyPeek();

        int resultCount =  response.path("numberOfElements") ;
        //int resultCount =  response.jsonPath().getInt("numberOfElements") ;
        System.out.println("resultCount = " + resultCount); //resultCount = 70
        // try at home , parameterize what you search for gender and name
        // in both query param and this db query
        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery(query);
        int expectedResult = DB_Utility.getRowCount() ;
        // this is using junit assertion , you can use hamcrest if you want.
        assertEquals(expectedResult,resultCount);
    }
    @DisplayName("Testing /spartans/search Endpoint and Validate against DB for all IDs")
    @Test
    public void testSearchVerifyAllID(){

        // make a request to GET /spartans/search
        // using query parameter gender Female  nameContains a

        Response response = given()
                .log().all()
                .queryParam("gender","Female")
                .queryParam("nameContains", "a").
                        when()
                .get("/spartans/search")
                .prettyPeek();

        // We want to store the id list as List<String> rather than List of Integer so we can compare easily
        // with the List<String> we got from DB Response , and no parsing needed
        // so we used the getList method that accept 2 parameters
        // the jsonpath to get the list and the Data type of the List you want ! -->> String.class
        List<String> idListFromResponse = response.jsonPath().getList("content.id" , String.class) ;
        System.out.println("idListFromResponse= " + idListFromResponse.size());
        for(String each: idListFromResponse){
            System.out.println(each);
        }

        String query = "SELECT * FROM SPARTANS     " +
                " WHERE LOWER(gender) = 'female'  " +
                " and LOWER(name) LIKE '%a%' ";
        DB_Utility.runQuery(query);

        List<String> idListFromDB =  DB_Utility.getColumnDataAsList(1) ;
        System.out.println("idListFromDB = " + idListFromDB);
        //idListFromDB = [146, 149, 150, 193, 199, 201, 217, 220, 34, 122, 123, 127, 49, 47, 156, 159, 160, 162, 165, 166, 167, 170, 171, 52, 53, 197, 3, 4, 6, 9, 10, 13, 18, 51, 27, 28, 29, 50, 174, 177, 178, 179, 182, 183, 209, 212, 213, 215, 218, 236, 46, 131, 134, 135, 136, 139, 140, 141, 144, 145, 187, 189, 104, 32, 33, 126, 125, 151, 154, 155]
        assertEquals( idListFromResponse.size() , idListFromDB.size() ) ;
          //          from postman and api  ,    from sql than will check

        // how to assert 2 list have same content
        assertEquals(idListFromDB, idListFromResponse);

//int expectedResult = DB_Utility.getRowCount()
// this is using junit assertion , you can use hamcrest if you want.
//assertEquals(expectedResult,resultCount);

    }
    @AfterAll
    public static void destroy(){
        RestAssured.reset();
        DB_Utility.destroy();

    }
}
