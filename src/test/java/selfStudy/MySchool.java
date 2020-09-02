package selfStudy;
import Pojo.Address;
import Pojo.Company;
import Pojo.Contact;
import io.restassured.internal.path.json.mapping.JsonPathJackson1ObjectDeserializer;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class MySchool {
    @BeforeAll
    public static void setUp(){
        RestAssured.baseURI= ConfigurationReader.getProperty("mySchoolBaseURL");
    }
    @DisplayName("Retrieve All Students")
    @Test
    public void test1(){

        Response response=
                given()
                        //.log().all()
                        .accept(ContentType.JSON)
                        .contentType(ContentType.JSON)
                .when()
                        .get("/student/all");
                        //.prettyPeek();
        response.then().statusCode(200)
                .statusLine(containsString("200"));
                //.body("Date", containsString("Wed, 02 Sep 2020 16:24:07 GMT"))
                //.body("Time", lessThan(200));

        JsonPath jsonPath=response.jsonPath();
        List<Map<String, ?>> students= jsonPath.getList("students");
        System.out.println("students = " + students.size()); //119
        /*
        for(Map<String,?> eachStudent: students){
            System.out.println("Lets see eachStudent = " + eachStudent);
        }
         */
        String firstname= jsonPath.getString("students[2].firstName");
        System.out.println("Third person firstname = " + firstname); //Vera

        int batch= jsonPath.getInt("students[0].batch");
        System.out.println("first person batch = " + batch);

        int contactId= jsonPath.getInt("students[1].contact.contactId");
        System.out.println("second person contactId's = " + contactId); //15645

        String street= jsonPath.getString("students[0].company.address.street");
        System.out.println("First Person's street = " + street); //string

        List<String> names= jsonPath.getList("students.firstName");
        /*for(String each:names){
            System.out.println("each name = " + each);
        }
         */
        List<Integer> studentID= jsonPath.getList("students.studentId");
        /*for(Integer eachID: studentID){
            System.out.println("Students's eachID = " + eachID);
        }

         */
//There are json format
        Map<String,?> contacts= jsonPath.getMap("students[0].contact");
        //System.out.println("contacts = " + contacts);//contacts = {contactId=15644, phone=string, emailAddress=string, premanentAddress=string}

        Map<String,?> company= jsonPath.getMap("students[0].company");
       // System.out.println("company = " + company);//company = {companyId=15564, companyName=string, title=string, startDate=01/01/2018, address={addressId=15564, street=string, city=string, state=string, zipCode=0}}


        Contact contactPojo= jsonPath.getObject("students[3].contact", Contact.class);
        System.out.println("contactPojo = " + contactPojo);
//contactPojo = Contact{contactId=15662, phone='7738557985', emailAddress='aaa@gmail.com', premanentAddress='123 main str'}
        System.out.println(contactPojo.getContactId()); // 15662
        System.out.println(contactPojo.getEmailAddress()); //aaa@gmail.com

        Company companyPojo= jsonPath.getObject("students[2].company", Company.class);
        System.out.println("companyPojo = " + companyPojo);//companyPojo = Company{companyId=15581, companyName='Cybertek', title='string', startDate='string'}
        System.out.println(companyPojo.getCompanyId());
        System.out.println(companyPojo.getTitle());



    }
}
