package day07;

import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class GOT_Homework {

    // GET https://api.got.show/api/book/characters
    // get all the character names in the list if the character field house = house stark

    @DisplayName("Getting All Members of House Stark")  //getList(" findAll { condition here }.theFieldNameHere")
    @Test
    public void testingGOT() {

        Response response = given()
                .baseUri("https://api.got.show")
                .basePath("/api/book").
                        when()
                .get("/characters");
        List<String> houseStarkList =
                response.jsonPath().getList(" findAll {it.house=='House Stark'}.name ");
        System.out.println("houseStarkList = " + houseStarkList);
        // you list should have size 76 ;
        //houseStarkList = [Alebelly, Eddard Stark, Edderion Stark, Edrick Stark, Edwyle Stark,
        // Edwyn Stark, Wyl (guard), Lew (guard), Rickard Stark, Rickon Stark, Robb Stark, Eyron Stark,
        // Farlen, Gage, Gariss, Lyanna Stark, Alyn, Rodrik Stark, Rodrik Stark (son of Beron), Rodwell Stark,
        // Artos Stark, Arya Stark, Bandy, Barth (brewer), Barthogan Stark, Sansa Stark, Mikken, Benjen Stark,
        // Benjen Stark (Bitter), Benjen Stark (Sweet), Beron Stark, Murch (Winterfell), Brandon Stark, Brandon Stark (son of Cregan), Bran Stark, Brandon Stark (Bad), Brandon Stark (Burner), Brandon Stark (the daughterless), Brandon Stark (Shipwright), Calon, Cayn, Shyra, Skittrick, Osha, Cregan Stark, Harwin, Hayhead, Heward, Hodor, Palla, Porther, Poxy Tym, Syrio Forel, Theon Stark, TomToo, Tomard, Desmond, Quent, Hullen, Jacks, Donnis, Donnor Stark, Dorren Stark, Torrhen Stark, Turnip, Jon Snow, Jonnel Stark, Jonos Stark,
        // Jorah Stark, Joseth, Varly, Walton Stark, Karlon Stark, Kyra, Wayn (guard), Willam Stark]

        assertThat(houseStarkList, hasSize(76));
        // check the list has item Eddard Stark
        assertThat(houseStarkList, hasItem("Eddard Stark"));
        // check the list has items  Robb Stark , Lyanna Stark , Arya Stark
        assertThat(houseStarkList, hasItems("Robb Stark", "Lyanna Stark", "Arya Stark"));


    }

}
