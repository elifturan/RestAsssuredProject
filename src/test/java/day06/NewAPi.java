package day06;

import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Pojo.Locations;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;

public class NewAPi {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="https://newsapi.org/";
        RestAssured.basePath="/v2";
    }
    @DisplayName("Testing the newsApi endpoint")
    @Test
    public void authorsWithIdInfo(){

        Response response=
               given()
                        .log().all()
                .accept(ContentType.JSON)
                .queryParam("apikey",
                        "9930181c16e1402a88b69bda817302df")
                .queryParam("country","us")
                .when()
                .get("/top-headlines")
                .prettyPeek()
                //.then()
                //.log().all()
              //.body("status",is("ok"))
                //.body("totalResults",is(3));
               ;

        JsonPath jp= response.jsonPath();
        //System.out.println("response.jsonPath().getList() = " + response.jsonPath().getList());
        System.out.println("All author names: "+ jp.getList("articles.findAll{it.source.id != null && it.author != null}.author"));

        List<String> nameList=response.jsonPath().getList("articles.source.name");
        System.out.println("All names from ApiNew: "+ nameList);

        List<Integer> idList=response.jsonPath().getList("articles.source.id");
        System.out.println("All lists: " + idList);


/*
All author names: [Sam Byford, John Fritze, Maureen Groppe, David Jackson, Dennis Romero, Ian Talley,
 Al Jazeera, Review by Brian Lowry, CNN, Associated Press, Brandon Ambrosino, Ryan Browne and Barbara Starr, CNN]

All names from ApiNew: [The Verge, ESPN, USA Today, Fox Business, NBC News, Investor's Business Daily, Los Angeles Times, P

All lists: [the-verge, null, usa-today, null, nbc-news, null, null, null, the-wall-street-journal, al-jazeera-english, null, null, cnn, null, politico, null, politico, null, cnn, null]

 */
    }
}
