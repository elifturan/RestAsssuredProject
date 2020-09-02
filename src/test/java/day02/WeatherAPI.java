package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class WeatherAPI {

    @BeforeAll
    public static void setUp(){
        //https://openweathermap.org/api/one-call-api
        RestAssured.baseURI= "https://openweathermap.org";
        RestAssured.basePath="/data/2.5/weather";
    }

    @DisplayName("Getting Weather Data")
    @Test
    public void weatherData(){


        given()
                .log().all()
                .queryParam("lat", "40.12")
                .queryParam("lon","-96.66")
                .queryParam("timezone", "America/Chicago")
                .queryParam("timezone_offset","-18000").
        when()
                .get().
        then()
                .log().all()
                .statusCode(200)
               // .body("current[0].dt", is("1595243443L"))
              //  .body("current[0].sunrise", is("1595243663L"))
                .body("current.humidity", is("100"))
                .body("current.clouds",is("90"))
                ;
    }

    @DisplayName("City Weather Test")
    @Test
    public void cityWeatherTest() {
        given()
                .log().all()
                .queryParam("q","Wethersfield")
                .queryParam("appid","aae7f472e5476eed4c69c8e1fc98dc")
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(200)
                .body("weather[0].id", is(800))
                .body("weather[0].main", is ("Clear"))
                .body("weather[0].description",is ("clear sky"))
                .body("main.humidity",is (82));
    }
    @DisplayName("Weather")
    @Test
    public void weather(){
        given()
                .log().all().
                queryParam("q", "San Bruno")
                .queryParam("appid" , "7f48034ac843890e526ed608af57ea85" )
                .when()
                .get()
                .then()
                .log().all()
                .statusCode(is(200))
                .body("weather[0].id" , is(803))
                .body("name" , is("San Bruno"))
                .body("timezone", is(-25200))
                .body("cod" , is(200))
        ;
    }
    @Test
    public void OpenWeatherTest() {
        given()
                .and().accept(ContentType.JSON)
                .queryParam("id", "745044")
                .queryParam("APPID", "597963c38d319d9d7536c4582b4dc22a")
                .when().get()
                .prettyPeek()
                .then()
                .body("weather[0].description", is("broken clouds"))
                .body("sys.country", is("TR"))
                .header("Content-Type", "application/json; charset=utf-8");
    }



}
