package com.mycompany.pages;

import static io.restassured.RestAssured.given;
import io.restassured.response.Response;
import org.openqa.selenium.WebDriver;
import io.restassured.http.ContentType;

public class TrelloMethodPage extends PageBase{

    String myKey = "MyKEY";
    String myToken = "MyTOKEN";
    String boardName = "ThisIsMyTestBoardRestAssured";

    public TrelloMethodPage(WebDriver driver) {
        super(driver);
    }

    public TrelloMethodPage callTrello() throws InterruptedException {

//		1) Trello üzerinde bir board oluşturalim

        String baseURI="https://api.trello.com/1/boards?token=" + myToken +"&key=" + myKey +"&name=" + boardName;

        Response response = given().contentType(ContentType.JSON).when().post(baseURI);

        String responseBody=response.getBody().asString();
        System.out.println("Response Body: " + responseBody);

        String boardId=response.body().jsonPath().getString("id");
        System.out.println("Board ID: " + boardId);
        System.out.println("Board 1'i basari ile olusturduk");
        Thread.sleep(5000);

//		2) Oluşturduğumuz board’a iki tane kart oluşturalim.

//		1. Liste olustur. 2. Kartlari ekle.
        String createList = "https://api.trello.com/1/lists/?token=" + myToken + "&key=" + myKey + "&idBoard=" + boardId +"&name=List 1";

        response = given().contentType(ContentType.JSON).when().post(createList);
        responseBody=response.getBody().asString();
        String listId=response.body().jsonPath().getString("id");
        System.out.println("List ID: " + listId);
        System.out.println("Listeyi basari ile olusturduk");
        Thread.sleep(3000);

        // Card 1

        String createCard = "https://api.trello.com/1/cards/?idList=" + listId +"&token=" + myToken + "&key=" + myKey + "&name=Card Name";

        response = given().contentType(ContentType.JSON).when().post(createCard);
        responseBody=response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        String cardId1=response.body().jsonPath().getString("id");
        System.out.println("1. Card ID: " + cardId1);
        System.out.println("1. Carti basari ile olusturduk");
        Thread.sleep(10000);

        // Card 2

        response = given().contentType(ContentType.JSON).when().post(createCard);
        responseBody=response.getBody().asString();
        String cardId2=response.body().jsonPath().getString("id");
        System.out.println("2. Card ID: " + cardId2);
        System.out.println("2. Carti basari ile olusturduk");
        Thread.sleep(10000);

//		3) Oluşturduğumuz bu iki karttan random olacak sekilde bir tanesini güncelleyelim.

        String updateCard = "https://api.trello.com/1/cards/" + cardId2 + "?desc=This is an update.&name=Changed Name&token=" + myToken + "&key=" + myKey;

        response = given().contentType(ContentType.JSON).when().put(updateCard);
        responseBody=response.getBody().asString();
        System.out.println("Response Body: " + responseBody);
        System.out.println("2. Carti basari ile guncelledik.");
        Thread.sleep(5000);

//      4) Oluşturduğunuz kartları siliniz

        String deleteCard1 = "https://api.trello.com/1/cards/" + cardId1 + "?token=" + myToken + "&key=" + myKey;
        given().contentType(ContentType.JSON).when().delete(deleteCard1);
        System.out.println("1. Carti basari ile sildik.");
        Thread.sleep(5000);

        String deleteCard2 = "https://api.trello.com/1/cards/" + cardId2 + "?token=" + myToken + "&key=" + myKey;
        given().contentType(ContentType.JSON).when().delete(deleteCard2);
        System.out.println("2. Carti basari ile sildik.");
        Thread.sleep(5000);

//      5) Oluşturduğunuz board'u siliniz
        
        String deleteURI = "https://api.trello.com/1/boards/" + boardId + "?token=" + myToken + "&key=" + myKey;
        given().contentType(ContentType.JSON).when().delete(deleteURI);

        Thread.sleep(3000);

        System.out.println("Board 1'i basari ile sildik");

        return new TrelloMethodPage(driver);

    }

}
