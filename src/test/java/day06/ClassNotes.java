package day06;

public class ClassNotes {
}
/*
Day 11 :
We learned different type of body content type
in POST Request other than Json
  url encoded form data
    Content-Type=application/x-www-form-urlencoded
    and in RestAssured
        we used .contentType(ContentType.URLENC)
             for specifying this type of contentType
    then we used method
        formParam("key","value") to provide the body
 ------------
    If you see 404, before checking your data exists or not
    check the URL is correct or not
    if http://library1.cybertekschool.com/rest/v1/login url
     accidentally typed as http://library1.cybertekschool.com/rest/someNonExistingPath
     then you will also get 404 because there is no such path in the server
     any URL that does not specify port actually have hidden ports
     so any URL with http://somename.com/somepath here
           automatically have port 80 unless specified otherwise
     so any URL with https://somename.com/somepath here
           automatically have port 443 unless specified otherwise
     so our library app is starting with http actually automatically get port 80
     port 80 and 443 is so common that we do not see it in any url with domain names
     any port other than 80 AND 443 must be explicitly part of the URL
     for example  yourIP:8000 --->> spartan app
                  yourIP:1000 --->> HR ORDS rest api
     POJO -- Plain Old Java Object , usually used to represent data
     The POJO Class usually have encapsulated fields :
                optionally constructors , toString
     POJO to JSON (or string , byte array) -->> Serialization
     JSON to POJO    -->> De-Serialization
     We used Jackson Databind libray to serialize and deserialize
     There are other libarry like Gson from google , work in similar way
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.11.2</version>
        </dependency>
      most of the time we do not have to mention the jackson library at all
      it will automatically work with rest assured to make it work
      assuming we have a POJO class for the json we are working with
      response.as(YourClassName.class)  if the json is at top level
      {
        "key1" : "value1"
        "key2" : "value2"
        "key3" : "value3"
      }
      if the json is not at top level , we have to use jsonPath to get to the json object
      {
         "batch" : 18 ,
         "info"  : {
                "key1" : "value1"
                "key2" : "value2"
                "key3" : "value3"
              }
      }
      // assuming we have an object stored into a variable called response
      //
       YourPojo  obj1  = response.jsonPath().getObject("info" , YourPojo.class)
       //  if you have json array , we can store it into the list using getList method
       List<YourPojo> lst = response.jsonPath.getList("your path here", YourPojo.class)
       // Jackson data bind , will find the field that have exact same name as json field
       // to map the data ,
       // if anything does not match you will get error
       // in order to ignore the fields that you do not want to add
       // you can use jackson annotation
       @JsonIgnoreProperties(ignoreUnknown = true) on top of your pojo class
       //  it will ingore any non-matching fields
       what to do if the json field name is not the name you want to use for your pojo field
       you can use the annotation
       @JsonProperty("your json field name here")
       private int yourPojoFieldYouWantToMapHere
       for example if you have below json as response

       {
            "the name" : "Batch 18"
            "size"  : 300
       }
       and you want to map this json to a POJO class as below
       public class Batch{
            private String name ;
            private int count ;
            //  getters and setters here omitted
       }
       jackson will not know what json field to map what java field
       so we can use @JsonProperty to instruct jackson to map the field we want
       public class Batch{
            @JsonProperty("the name")
            private String name ;
            @JsonProperty("size")
            private int count ;
            //  getters and setters here omitted
       }
-----------   We also learned the provide basic auth

          give().auth().basic("username","password")
          // what if i have token
          // usually the token either go to query param or the header
            // according to the documentation
            // so we can directly add it to either location by following the doc
          in open movie DB API , it need the api key in query parameter section
          so this is what we did
          given().queryParam("apikey","your token goes here") .........
          in Library app
            we provide the token in the header called x-library-token
          given().header("x-library-token","your long token goes here")
          // IN THE REQUEST ,  given section
          .accept(ContentType.JSON) // THIS IS TELLING , I WANT JSON BACK AS RESPONSE.
          .contentType(ContentType.URLENC) // This specify what kinda of data you are sending to the server in the body

          -------
          groovy methods in jsonPath

          get single item
          yourPathToJsonArray.find{ it.yourFieldName == someValue}.someOtherFieldYouWant

          get a filtered list
          yourPathToJsonArray.findAll{ it.yourFieldName == someValue}.someOtherFieldYouWant

          get max according to criteria
          yourPathToJsonArray.MAX{ it.salary}.someOtherFieldYouWant
          get min according to criteria
          yourPathToJsonArray.MIN{ it.salary}.someOtherFieldYouWant

          interview questions
          Go to newsAPI.org and get an API key
          // each request put the key you got in Authorization header
          header name Authorization , value Bearer long_token_here

          send request to the endpoint /top-headlines?country=us
          get all the author name if the source id is not null
          print the list of author name

 */
