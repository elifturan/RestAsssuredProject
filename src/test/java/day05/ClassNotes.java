package day05;

public class ClassNotes {
}
/*
Day 10 :
    More jackson --
    Authentication / Authorization using restAssured
    more jsonPath practice
    ----- Providing basic authentication in the request
    --- we put the authentication and Authorization under given section
      if its a basic auth we would do
            given().auth().basic("username","password")
        everything else is exactly the same
     ------ JSON TO JAVA OBJECT
        -- store the result of GET /spartan/{id} into POJO object
            {
                "id": 231,
                "name": "Jenell",
                "gender": "Male",
                "phone": 3356824450
            }
            ---->> Java Object
        Since our existing Spartan object does not have id field
        so we can not save the id field
        so for easy config , we will just create another Spartan2 POJO
        with id , name , gender , phone fields to start with
        getters and setters
        no arg constructors , 4 arg constructors
        add toString method so we can print it out
-------  Please create a POJO class called Region
-----            encapsulated fields : region_id , region_name
          add toString method so we can print out with actual content
            Create a new class called HR_ORDS_Test
            add @BeforeAll and add baseURI, basePath, port
          Here is the get request we need to make
          http://54.174.216.245:1000/ords/hr/regions/1
          baseURI = http://54.174.216.245
          port = 1000  // DO NOT HAVE TO REMEMEBER , DEV WILL TELL YOU , OR YOU DONT HAVE IT AT ALL
          basePath = ords/hr
          Create a test called testRegion()
          request :  GET /regions/{region_id}
          PRINT OUT THE RESULT
------   we will learn how to ignore certain fields from json
          save the response and we will save the json into Region POJO
          and we will ignore the extra fields we do not need like links from response
          YOU CAN USE YOUR OWN IP , BOTH CYBERTEK INSTANCES COMES WITH IT
          Practice , Create pojo for all Employees , Departments , Locations , Countries , Jobs
        use those 2 endpoints available for each to get all resources save it it as list of pojo , get single resource and save as single POJO ,
        Also try out the open movie DB endpoints
        ignore the fields you do not want to save.
        Here is the link for OMDBAPI  collection : https://www.getpostman.com/collections/74e9200f46f22b1d8cff
        Here is the link for ORDS API  collection : https://www.getpostman.com/collections/39d74311be7e979d3bcd
        try out the Serialization : pojo to json  and json to pojo  for Library App endpoints
        your request will start like this :
        given().header("x-library-token","your long token here" ) (edited)

        Turning POJO(Java Object) to String|Byte[] in our case Json is known as Serialization
        Turning jSON to Java Object (POJO) known as De-serialization

 */