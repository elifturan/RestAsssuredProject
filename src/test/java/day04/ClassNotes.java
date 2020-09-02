package day04;

public class ClassNotes {
}
/*
Day 09
Day 04 of RestAssured
    Post Request
         need body , and need to tell server what kind of data we are sending
     We learned how to pass body using String
     we can also pass body by external file or Map object or custom class object
        in spartan app we should get 201 status code
        and new data should be generated with success
    We want to get the name part random by using java faker dependency

        <dependency>
            <groupId>com.github.javafaker</groupId>
            <artifactId>javafaker</artifactId>
            <version>1.0.2</version>
        </dependency>6
     We can also provide the body as external File
     File f1 = new File("path/to/your/file.json") ;
     given()
            .contentType(ContentType.json)
            .body(f1)
     when()
            .post("/spartans")......
     We can also provide Java objects like Map , or custom type we created(coming later)
     conversion between Java object and JSON need external handler
     popular options are :
                Jackson library from com.fasterxml.jackson.core
                Gson library from google
     we just need to provide one of these in the classpath (dependency)
     and restassured will take care of the rest
        <dependency>
            <groupId>com.fasterxml.jackson.core</groupId>
            <artifactId>jackson-databind</artifactId>
            <version>2.11.2</version>
        </dependency>
       so behind the scene
       its taking the map object and turning it into json when we pass map object to the body
        Map<String, Object> bodyMap = new HashMap<>();
        bodyMap.put("name","Vincent");
        bodyMap.put("gender","Male");
        bodyMap.put("phone",3476346789l);
        given()
                .contentType(ContentType.JSON)
                .body(bodyMap).
        when()
                .post("/spartans").
        jackson-databind is turning the bodyMap object into below json
        {
            "name": "Vincent",
            "gender": "Male",
            "phone": 3476346789
        }
        turning your java object to json (or text files) known as serialization
        you can use it for any request that need such transformation
        like PUT , PATCH
        {
            "name": "Vincent",
            "gender": "Male",
            "phone": 3476346789
        }
        --->> custom java type
        public class Spartan {
            private String name ;
            private String gender ;
            private long phone ;
            // public getters and setters
            // optionally constructors
        }
        // this type of class that has private fields
        // and public getters and setters , optionally constructors
        // also known as pojo (PLAIN OLD JAVA OBJECT)
        // to represent data
        please create a package called pojo
        add class Spartan with 3 encapsulate fields name, gender , phone
        add 2 constructors
                empty no arg constructor
                constructors with 3 arguments to set all the field values
        --------- back to Junit test from here
        continue with your post request in the junit test
        tinstead of providing body
            as string or file or map object ,
        this time we will provide the body as spartan object
        and let jackson to take care of the rest of transformation to json

 */
