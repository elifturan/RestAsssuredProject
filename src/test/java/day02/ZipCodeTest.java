package day02;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ZipCodeTest {

  @BeforeAll
  public static void setUp(){

      RestAssured.baseURI = "http://api.zippopotam.us";
      RestAssured.basePath="/us/";

  }
  @DisplayName("Testing ZipCodeTest")
    @Test
    public void testZipToCity1(){

      given()
              .pathParam("zipcode",33064)
              .log().all()
     .when()
              .get("/{zipcode}")
     .then()
              .log().all()
              .statusCode(is(200))
              //  .body("JSON PATH", is("THE VALUE"))
              .contentType(ContentType.JSON)
              //.body("post code", is(33064))
              .body("'post code'",is("33064"))
              .body("country", is("United States"))
              //.body("longitude", is("-80.1157"))
              .body("places[0].state", is("Florida"))
             // .body("latitude", is("26.2785"))
      ;
  }
    @Test
    @DisplayName("Zip to City Test")
    public void testZipToCity(){


        given()
                .pathParam("zip",22030).
                log().all().
                when()
                .get("/{zip}").
                then()
                .log().all()
                .statusCode(is(200))
                .contentType(ContentType.JSON)
                // FX FOR THE SPACE IN THE KEY
                .body("'post code'", is("22030")    )
                .body("country",is("United States") )
                // get the state and check it's Virginia
                .body("places[0].state" , is("Virginia")   )
                // get the place name
                .body("places[0].'place name' " , is("Fairfax") )

        ;
        // if a field/key you are trying to get has space
        // then add ''  for example  " 'post code'



    }
  @DisplayName("Testing City to Zip")
    @Test
    public void testCitytoZip() {

      given()
              .pathParam("state", "FL")
              .pathParam("city", "Pompano Beach")
              .log().all()
              .when()
              .get("{state}/{city}")
              .then()
              .log().all()
              .statusCode(is(200))
              //  .body("JSON PATH", is("THE VALUE"))
              .contentType(ContentType.JSON);


  }
    @DisplayName("City to Zip")
    @Test
    public void testCityToZip2(){

        //api.zippopotam.us/us/:state/:city
        given()
                .pathParam("state","VA")
                .pathParam("city","Fairfax")
                .log().all().
                when()
                .get("/{state}/{city}").
//                .get("/{state}/{city}" , "VA","Fairfax" ). // second way we did with spartan
        then()
                .log().all()
                .statusCode( is(200) )
                .body("'country abbreviation'",is("US") )
                // test the latitude of first place is "38.8458"
                .body("places[0].latitude" ,is("38.8458") )
                // jsonPath hack for getting last item from jsonArray
                // -1 index indicate the last item , only works here not in postman
                .body("places[-1].latitude" , is("38.7602") )

        ;


    }
}
