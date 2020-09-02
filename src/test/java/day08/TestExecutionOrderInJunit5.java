package day08;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

// Default test execution order is alphabetical
// ----->>>>>     means if we wil not use order than will execute with an alphabetic order

// if you want to change it , this is one way to do it
// This annotation indicate to run the tests according to the @Order annotation with number
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestExecutionOrderInJunit5 {
//If we dont use displayname Annotation will go order each Test's with method's name
//without order testA , testB, testK, testZ
//with order annotation will go with order number  testK , testA, testZ, testB
    //@Order(4)
    @Test
    public void testB(){

    }
    //@Order(2)
    @Test
    public void testA(){

    }
    //@Order(3)
    @Test
    public void testZ(){

    }
   // @Order(1)
    @Test
    public void testK(){

    }


}
