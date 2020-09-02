package day03;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class MyPractice3 {
    @DisplayName("Testing movie")
    @Test
    public void test1() {


        given()
                .log().all()
                .baseUri("http://www.omdbapi.com")
                .queryParam("apikey", "26aa5b74")
                .queryParam("t", "Boss Baby").

                when()
                .get()
                .prettyPeek()
                .then()
                .statusCode(200);

    }

    @DisplayName("Testing movie")
    @Test
    public void test2() {

        Response response =
                given()
                        .log().all()
                        .baseUri("http://www.omdbapi.com")
                        .queryParam("apikey", "26aa5b74")
                        .queryParam("t", "Boss Baby").
                        when().get();
        System.out.println(response.statusCode());
        String title=response.path("Title");

        System.out.println("Title: " + title );
        String year=response.path("Year");
        System.out.println("Year: "+ year);
        String rated=response.path("Rated");
        System.out.println("Rated: "+rated);
        String released=response.path("Released");
        System.out.println("Released: "+released);
        String runtime=response.path("Runtime");
        System.out.println("Runtime: "+runtime);
        String director=response.path("Director");
        System.out.println("Director: "+ director);
        String firstRating= response.path("Ratings[0].Source");
        System.out.println("First Rating Source: "+ firstRating);
        String firstValue= response.path("Ratings[0].Value");
        System.out.println("First Rating Value: " +firstValue);

        Map<String, Object> responseMap= response.path("Director", "Rated", "Title", "Year");
        //System.out.println("Response Map: "+ responseMap);

        /*
        200
Title: The Boss Baby
Year: 2017
Rated: PG
Released: 31 Mar 2017
Runtime: 97 min
Director: Tom McGrath
First Rating Source: Internet Movie Database
First Rating Value: 6.3/10


         */

    }
    @DisplayName("Testing Movies")
    @Test
    public void Test3(){
        Response response=
                given()
                .log().all()
                        .baseUri("http://www.omdbapi.com")
                        .queryParam("apikey", "26aa5b74")
                        .queryParam("t", "Boss Baby").
                        when().get("http://www.omdbapi.com");
       // JsonPath jp = response.jsonPath()
        String title = response.jsonPath().getString("Title");
        System.out.println("Title: "+ title);

        int year=response.jsonPath().getInt("Year");
        System.out.println("Year: "+ year);

        String rated= response.jsonPath().getString("Rated");
        System.out.println("Rated: "+rated);

        String released=response.jsonPath().getString("Released");
        System.out.println("Released: "+released);

        String ratingSource= response.jsonPath().getString("Ratings[0].Source");
        System.out.println("Rating Source is: "+ ratingSource);

        Map<String, Object> responseMap= response.jsonPath().getMap("");
        System.out.println("Response Map: "+ responseMap);
        //Response Map: {Title=The Boss Baby, Year=2017, Rated=PG, Released=31 Mar 2017, Runtime=97 min, Genre=Animation, Adventure, Comedy, Family, Fantasy, Director=Tom McGrath, Writer=Michael McCullers, Marla Frazee (based on the book by), Actors=Alec Baldwin, Steve Buscemi, Jimmy Kimmel, Lisa Kudrow, Plot=A suit-wearing, briefcase-carrying baby pairs up with his 7-year old brother to stop the dastardly plot of the CEO of Puppy Co., Language=English, Spanish, Country=USA, Awards=Nominated for 1 Oscar. Another 4 wins & 20 nominations., Poster=https://m.media-amazon.com/images/M/MV5BMTg5MzUxNzgxNV5BMl5BanBnXkFtZTgwMTM2NzQ3MjI@._V1_SX300.jpg, Ratings=[{Source=Internet Movie Database, Value=6.3/10}, {Source=Rotten Tomatoes, Value=53%}, {Source=Metacritic, Value=50/100}], Metascore=50, imdbRating=6.3, imdbVotes=104,398, imdbID=tt3874544, Type=movie, DVD=25 Jul 2017,
        // BoxOffice=$174,996,080, Production=DreamWorks Animation, Website=N/A, Response=True}

        Map<String, Object> firstRatingMap=response.jsonPath().getMap("Ratings[0]");
        System.out.println("First Rating Map: "+ firstRatingMap);
        //First Rating Map: {Source=Internet Movie Database, Value=6.3/10}

        List<String> sourceList= response.jsonPath().getList("Ratings.Source");
        System.out.println("Source List: "+ sourceList);
        //Source List: [Internet Movie Database, Rotten Tomatoes, Metacritic]

/*
Title: The Boss Baby
Year: 2017
Rated: PG
Released: 31 Mar 2017
Rating Source is: Internet Movie Database
Response Map: {Title=The Boss Baby, Year=2017, Rated=PG, Released=31 Mar 2017, Runtime=97 min, Genre=Animation, Adventure, Comedy, Family, Fantasy, Director=Tom McGrath, Writer=Michael McCullers, Marla Frazee (based on the book by), Actors=Alec Baldwin, Steve Buscemi, Jimmy Kimmel, Lisa Kudrow, Plot=A suit-wearing, briefcase-carrying baby pairs up with his 7-year old brother to stop the dastardly plot of the CEO of Puppy Co., Language=English, Spanish, Country=USA, Awards=Nominated for 1 Oscar. Another 4 wins & 20 nominations., Poster=https://m.media-amazon.com/images/M/MV5BMTg5MzUxNzgxNV5BMl5BanBnXkFtZTgwMTM2NzQ3MjI@._V1_SX300.jpg, Ratings=[{Source=Internet Movie Database, Value=6.3/10}, {Source=Rotten Tomatoes, Value=53%}, {Source=Metacritic, Value=50/100}], Metascore=50, imdbRating=6.3, imdbVotes=104,398, imdbID=tt3874544, Type=movie, DVD=25 Jul 2017, BoxOffice=$174,996,080, Production=DreamWorks Animation, Website=N/A, Response=True}


 */




    }
}