package com.mycompany.pages;

import org.openqa.selenium.WebDriver;

public class HomePage extends PageBase{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public TrelloMethodPage handleTrello() {
        return new TrelloMethodPage(driver);
    }

}
