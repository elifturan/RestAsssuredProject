package day07;
import io.restassured.internal.path.json.mapping.JsonPathJackson1ObjectDeserializer;
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
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class MyNewsApi {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="https://newsapi.org/";
                RestAssured.basePath="/v2";
    }
    @DisplayName("testing the newsApi enpoint")
    @Test
    public void title(){

        Response response=
                given()
                .log().all()
                        .accept(ContentType.JSON)
                        .queryParam("apikey","9930181c16e1402a88b69bda817302df")
                .queryParam("q","java")
                .when()
                .get("/top-headlines")
                .prettyPeek()
                ;
        JsonPath jsonPath = response.jsonPath();
        System.out.println("Total Results is: " + jsonPath.getList("totalResults"));

        List<String>  titleList= jsonPath.getList("articles.source.title");
        System.out.println("titleList = " + titleList);

        List<String> description= jsonPath.getList("articles.source.description");
        System.out.println("description = " + description);

        System.out.println("Status= " +jsonPath.getList("status") );
        assertThat("Status",is("ok"));
    }

}
