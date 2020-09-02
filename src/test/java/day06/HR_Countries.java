package day06;
import Pojo.Countries;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import Pojo.Locations;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HR_Countries {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://54.174.216.245";
        RestAssured.port=1000;
        RestAssured.basePath="ords/hr";
    }
    @DisplayName("Testing the countries/{country_id} endpoint")
    @Test
    public void testLocation(){
        Response response =
                given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/countries")
               .prettyPeek();

        Countries countries= response.as(Countries.class);      //deserilization
        System.out.println("countries = " + countries);

        List<String> country_id= response.jsonPath().getList("items.country_id");
        System.out.println("country_id = " + country_id.size());
        System.out.println("country_id = " + country_id);


        List<String> country_name= response.jsonPath().getList("items.country_name");
        System.out.println("country_name = " + country_name.size());
        System.out.println("country_name = " + country_name);

        List<String> region_id= response.jsonPath().getList("items.region_id");
        System.out.println("region_id = " + region_id.size());
        System.out.println("region_id = " + region_id);

 /*
 countries = Countries{country_id:nullcountry_name:nullregion_id:0'}
country_id = 25
country_id = [AR, AU, BE, BR, CA, CH, CN, DE, DK, EG, FR, IL, IN, IT, JP, KW, ML, MX, NG, NL, SG, UK, US, ZM, ZW]
country_name = 25
country_name = [Argentina, Australia, Belgium, Brazil, Canada, Switzerland, China, Germany, Denmark, Egypt, France, Israel, India, Italy, Japan, Kuwait, Malaysia, Mexico, Nigeria, Netherlands, Singapore, United Kingdom, United States of America, Zambia, Zimbabwe]
region_id = 25
region_id = [2, 3, 1, 2, 2, 1, 3, 1, 1, 4, 1, 4, 3, 1, 3, 4, 3, 2, 4, 1, 3, 1, 2, 4, 4]

  */

 //Json to Java POJO  // Turning jSON to Java Object (POJO) known as De-serialization
 List<Countries> countryList = response.jsonPath().getList("items", Countries.class);
 assertThat(country_id,hasSize(25));
 assertThat(country_name,hasSize(25));
 assertThat(region_id,hasSize(25));
 assertThat(country_name,hasItem("Brazil"));
 assertThat(country_id,hasItems("AR","AU","BE","BR","CA","CH"));

        JsonPath jsonPath =response.jsonPath();
        List<String> coName = jsonPath.getList("items[0..4].country_name");
        System.out.println(coName); //[Argentina, Australia, Belgium, Brazil, Canada]
        List<String> contains=jsonPath.getList("items.findAll{it.country_name == 'Brazil'}.country_id");
        System.out.println("contains = " + contains);

    }
}
