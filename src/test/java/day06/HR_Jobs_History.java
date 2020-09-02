package day06;
import Pojo.Jobs_History;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_Jobs_History {
    @BeforeAll
    public static void init(){
        RestAssured.baseURI= "http://54.174.216.245";
        RestAssured.port=1000;
        RestAssured.basePath="ords/hr";
    }
    @DisplayName("Testing the/Jobs_History endpoint")
    @Test
    public void testJob_History(){
        //Response response= get("/job_history");
        Response response=
                given()
                        .accept(ContentType.JSON)       
                        .log().all()
                        .when()
                        .get("/job_history")
                        .prettyPeek();
        
        //Jobs_History jh = response.as(Jobs_History.class);
        //System.out.println("jh = " + jh);
        JsonPath jp= response.jsonPath();


        List<String> employeeID= jp.getList("items.employee_id");
        System.out.println("employeeID = " + employeeID.size()); //employeeID = 11

        System.out.println("All IDs : "+jp.getList("items.employee_id"));//All IDs :[101, 101, 101, 102, 114, 122, 176, 176, 200, 200, 201]
        System.out.println("All Job_id: "+ jp.getList("items.job_id"));
        //All Job_id: [AD_VP, AC_ACCOUNT, AC_MGR, IT_PROG, ST_CLERK, ST_CLERK, SA_REP, SA_MAN, AD_ASST, AC_ACCOUNT, MK_REP]
        System.out.println("First Person's Id: "+ jp.getInt("items[0].employee_id")); //First Person's Id: 101
        System.out.println("Second Person's Id: " + jp.getInt("items[6].employee_id"));  //Second Person's Id: 176
        System.out.println("Last Person's Id: "+ jp.getInt("items[-1].employee_id")); //Last Person's Id: 201

        System.out.println(jp.getString("items.find{it.employee_id == 101}.job_id")); //AD_VP
        System.out.println(jp.getString("items.find{it.department_id == 50}.start_date")); //2006-03-24T05:00:00Z












    }
}
/*
"employee_id": 101,
            "": "2005-09-21T04:00:00Z",
            "end_date": "2020-08-24T15:38:01Z",
            "job_id": "AD_VP",
            "": 90,
 */