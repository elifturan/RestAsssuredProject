package day08;


import org.junit.jupiter.api.Test;
import utility.ConfigurationReader;

public class HowDidActiveEnvWorkedInOurCode {
    
    @Test
    public void test1(){
        // print out all the library1 info if active_env = library1
        // make a method out of it
        
        String currentEnv = "library3"; //ConfigurationReader.getProperty("active_env") ;
        System.out.println("currentEnv = " + currentEnv);
        // print library1 database url

        //String dbURL = ConfigurationReader.getProperty("spartan1.database.url");
        String dbURL = ConfigurationReader.getProperty(currentEnv+".database.url");
        System.out.println("db1URL = " + dbURL);
        
        
        String firstEnv="library1";
        System.out.println("firstEnv = " + firstEnv);
        String firstEnvDB= ConfigurationReader.getProperty(firstEnv + ".database.url");
        System.out.println("firstEnvDB = " + firstEnvDB);
        
        
        String secondEnv="library2";
        System.out.println("secondEnv = " + secondEnv);
        String secondEnvDB= ConfigurationReader.getProperty(secondEnv + ".database.url");
        System.out.println("secondEnvDB = " + secondEnvDB);
    }
}
