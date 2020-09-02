package day03;

public class ClassNotes {
}   //Response path de herhangi bir method kullnamdan butun data extracting yani tek tek alabiliriz
/*  //ama Json path de method kullanabiliyoruz getinit, getString vs gibi
Day 8 :
ResyAssured library
    method chaining
    path variables|paramters
    query paramters
    asserting the body , jsonPath . matchers
    give()   --- RequestSpecification
         query param , path variable , header
         auth , authorization ....
         log request info
    when()
            HTTP Method  GET POST PUT DELETE   ---  Response
    then()   ---  ValidatabaleResponse
            assertion start here
    You may set the baseURI , basePath , optionally set the Port
            RestAssured.baseURI = "YOUR BASE URL HERE"
            baseURI = " something here" ; //since we already did static import
            RestAssured.basePath = " some path here"
            Optionally you may also set the port
            RestAssured.Port = 8000
    usually if you are testing one app in your test ,
    its better to set it under @BeforeAll section of yours
    so all the tests can use it and your request URL will be much shorter
        get("/spartans")
        what would be your full request URL
            baseURI + basePath + "/spartans"
        path variables
            given()
                .pathParam("spartanID",123)
            when()
                .get("/spartans/{spartanID}")
            when()
                .get("/spartans/{spartanID}" , 123)
        query parameters
            given()
                .queryParam("gender","Female")
                .queryParam("nameContain","a")
            when()
                .get("/spartans/search")
        // full request URL will be
                baseURI+basePath +"/spartans/search?gender=Female&nameContain=a"
        // open openmovie db
            http://www.omdbapi.com/?t=Boss&plot=full&apikey=26aa5b74
            baseURI : http://www.omdbapi.com/
            query parameters :  t=Boss  plot=full apikey=26aa5b74
            given()
                .queryParam("t","Boss")
                .queryParam("plot","full")
                .queryParam("apikey","26aa5b74")
            when()
                .get()
            baseURI + basePath + "some endpoint here" + query params
            http://www.omdbapi.com/?t=Boss&plot=full&apikey=26aa5b74

    // getting the data out of response body

        getting single data can be done with path("path to the value here")
        method  for example
        {
            "Title": "The Boss Baby",
            "Year": "2017",
            "Director": "Tom McGrath",
            "Actors": "Alec Baldwin, Steve Buscemi, Jimmy Kimmel, Lisa Kudrow",
            "Awards": "Nominated for 1 Oscar. Another 4 wins & 20 nominations.",
            "Poster": "https://m.media-amazon.com/images/M/MV5BMTg5MzUxNzgxNV5BMl5BanBnXkFtZTgwMTM2NzQ3MjI@._V1_SX300.jpg",
            "Ratings": [
                {
                    "Source": "Internet Movie Database",
                    "Value": "6.3/10"
                },
                {
                    "Source": "Rotten Tomatoes",
                    "Value": "53%"
                },
                {
                    "Source": "Metacritic",
                    "Value": "50/100"
                }
            ],
            "Metascore": "50",
            "imdbRating": "6.3",
            "imdbVotes": "104,398",
            "imdbID": "tt3874544",
            "Type": "movie",
            "DVD": "25 Jul 2017",
            "BoxOffice": "$174,996,080",
            "Production": "DreamWorks Animation",
            "Website": "N/A",
            "Response": "True"
Please add new class called SpartanSearchExtractData
        Add all the imports
        add @BeforeAll to initilize the baseURI, basePath
        Add a test
        send get request to /spartan/search
        query paramters gender = Female
        save the response Object into response variable
        call jsonPath method to get JsonPath object from respone
        JsonPath jp = response.jsonPath() ;
        // get the list of all IDs store it into List
        // get the list of all names store it into List of String
        // get the mumberOfElements field value

 */