package day08;

public class ClassNotes {
}
/*
Day 12 API . DAY 8 RestAssured
Practice more on API DB combo
Setting the order of the tests (like priority in TestNG)
One end 2 end happy path for Spartan App Crud Operation
Data Driven Test in Junit 5
--- Side bar trick :
How to directly write query and execute in your IntelliJ
--- Your JUNIT5 Test default execution order is
  --- Alpha-numerical order
--  What if i want to specify order of execution for my tests
   instead of the defautl one
we need two annotation one for class level , one for method level
so we can provide order number
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class YourTestClass{
    @Order (1)
    @Test
    public void test(){
    }
    .... more tests with more orders
}
// your test should run without the dependency with other tests normally
// but sometimes we want our test to be executed in the order
// that we specified
for example :
    I want to add Spartan Crud Operation Happy path as 4 tests
    in the test class
    and I want to run my test exactly in this order
        *  Create -- Add Spartan  assert --- grab the id and save it for next test
        *  Read   -- Read the data that created and assert
        *  Update -- Update the same data you just read  assert
        *  Delete -- Delete the data -- assert
--- Do this as a homework , while practcing RestAssured and Ordering
// Using CSV File as data source for the JUNIT 5 Test
// Parameter injection --
we want to create a csv file called  data.csv
under a package src/test/resources
data.csv  has below content
num1,num2
5,4
4,7
3,8
6,10
// Please add another csv file called numbers.csv
    // num1,num2, additionResult
    // 5,4,9
    // 4,7,11
    // 3,8,11
    // 6,10,16
    /// Please add a @ParameterizedTest
    /// specify the file source as numbers.csv
    ///  in the meantime add 3rd parameter to your test called int result
    /// assert that num1 + num2  = result
    @ParameterizedTest
    @CsvFileSource(resources = "/numbers.csv", numLinesToSkip = 1)
    public void testAddition(int n1, int n2, int result){
        //import static org.junit.jupiter.api.Assertions.assertEquals;
        assertEquals(result,n1+n2 ) ;
    }
    // What if I want some custom name
        // @ParameterizedTest(name = "Some custom name here")
    // How do I refer the row number in my csv file
        // you can refer row number using exact text -->>{index} in the name String
    // How do I refer the column data in my display name
        // in the name String we can add {yourColumnIndexHere}
        // {0} for first column
        // {1} for second column
        // {2} for third column
    // WHERE DO I ADD the display name to start with ? add it to @ParameterizedTest (name = " here")
    // we want display like this so we know exactly what we tested in each iteration
    // Row1 : 5+4=9
    // Row2 : 4+7=1
    //@ParameterizedTest(name = "CURRENT ITERATION {index}  | ThirdCol {2} | FirstCol {0} | SecondCol {1} |  ")
    @ParameterizedTest(name = "iteration {index} -> {0} + {1} = {2}")

    in a parameterized test with csv file
    if you want to read two columns of csv
    you will have 2 parameters in your test method
    the data type must match , if its whole number it can be int for example
    if its a String then we can have parameter data type as String
    ------
    --- Create a csv file under resources folder called credentials.csv
    -- it has 2 column , username , password
    --- copy the library1 username password I shared under codenote to create this file
    ----
    We will write a parameterized test for POST /login endpoint
    if the username and password is valid
        you should simply get 200 and the token field should not be null
----- Data Drive your POST /api/Spartans request
----  Create a csv file called allSpartans.csv under src/test/resources folder
            add 3 column name , gender , phone
            add 6 rows of valid data
            then try to send post request using these data
--- Data Drive your GET / api.zippopotam.us/us/:state/:city
    --- Create a csv file called state_city.csv
        add 3 column  state , city , numberOfZipcodes
                      VA ,  Fairfax , 9(send the request and prepare this number)
         assert the state , city
                    and number of zipcodes you got from the response

How did I write query directly in the IntelliJ and see the result
        Used a plugin called Database Navigator
        and provided the connection info
        ---- Short video on the way on how
 */