package day06;
import Pojo.Countries;
import Pojo.Departments;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HR_Departments {

    @BeforeAll
    public static void init(){
        RestAssured.baseURI="http://54.174.216.245";
        RestAssured.port=1000;
        RestAssured.basePath="ords/hr";
    }
    @DisplayName("Testing the departments/")
    @Test
    public void testDepartments(){
        Response response =
                given()
                .accept(ContentType.JSON)
                .log().all()
                .when()
                .get("/departments")
                .prettyPeek();

        Departments departments =response.as(Departments.class);
        System.out.println("departments = " + departments);

        List<String> depeartmentID= response.jsonPath().getList("items.department_id");
        System.out.println("departmentID= " + depeartmentID.size());
        String departmentName= response.jsonPath().getString("items.find{it.department_id == '100'}.department_name");
        System.out.println("departmentID = " + departmentName);

        int getDepartment_Id= response.jsonPath().getInt("items.find{it.location_id == 1700}.department_id");
        System.out.println("getDepartment_Id = " + getDepartment_Id);

        int managerId= response.jsonPath().getInt("items.find{it.department_name=='Marketing'}.manager_id");
        System.out.println("managerId = " + managerId);

        List<String> manager_id = 
                response.jsonPath()
                        .getList( "items.findAll{it.department_id == 30 || it.department_id == 40}.department_name");
        System.out.println("manager_id = " + manager_id);

        List<String> manager_id2 =
                response.jsonPath()
                        .getList( "items.findAll{it.department_id == 50 && it.department_id == 60}.department_name");
        System.out.println("manager_id = " + manager_id);

    }
}
