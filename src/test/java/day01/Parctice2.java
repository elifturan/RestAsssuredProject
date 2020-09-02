package day01;

import org.junit.jupiter.api.Test;
import static org.hamcrest.Matchers.*;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import io.restassured.RestAssured.*;

import java.util.Arrays;
import java.util.List;

import static io.restassured.matcher.RestAssuredMatchers.* ;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;


// Hamcrest library is a assetion library
// to aim make the test more human readable
// using lots of human readable machers like something is(somethingelse)
// Most importantly restassured use hamcrest matcher
//when we chain multiple rest assured methods

public class Parctice2 {

    @Test
    public void test1(){
        int num1=5;
        int num2=4;
        // we need these two import for this to work
       //import static org.hamcrest.MatcherAssert.assertThat;
       //import static org.hamcrest.Matchers.*;

        // Hamcrest already come with RestAssured dependency
        // hamcrest library use the assertThat method for all assertions
        // hamcrest use built in matchers to do assertion
        // couple common ones are :
        //  is( some value )
        // equalTo( some value)
        //  or optionally   is ( equalTo(some value) )

        assertThat(num1+num2, is(9));
        assertThat(num1+num2, equalTo(9));
        assertThat(num1+num2, is(equalTo(9)));

        //not (value)
        //is (not  (some value ) )
        //not (equalTo (11) )
        assertThat(num1+num2, not(11));
        assertThat(num1+num2, is(not(11)));

        //save your first name and last name into 2 variables
        //test the concatenation result using hamcrest matcher

        String firstName="Oznur "; //there is a space in the last character here
        String lastName="Gunes";

        assertThat(firstName+lastName, is("Oznur Gunes"));
        assertThat(firstName+lastName, equalTo("Oznur Gunes"));
        assertThat(firstName+lastName, is(equalTo("Oznur Gunes")));

        //how to check in caseInsenstive manner
        assertThat(firstName, equalToIgnoringCase("oznur"));
        //how to ignore all whitespaces
        assertThat(firstName, equalToCompressingWhiteSpace("Oznur"));

    }
    @DisplayName("Support for collection")
    @Test
    public void test2(){

        List<Integer> numlist = Arrays.asList(11,44,3,55,88,5);
        //checking the list size is 6
        assertThat(numlist,hasSize(6));
        //checking the list contains 11
        assertThat(numlist,hasItem(11));

        //chehcking the list contains more than one items : 11, 44,55
        assertThat(numlist, contains(11,44,3,55,88,5));
        assertThat(numlist, containsInAnyOrder(11,44,3));



    }
}
