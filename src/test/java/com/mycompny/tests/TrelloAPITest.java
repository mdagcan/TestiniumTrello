package com.mycompny.tests;

import org.testng.annotations.Test;

public class TrelloAPITest extends TestBase {

    @Test
    public void checkTrelloBoard() throws InterruptedException {

        homePage.handleTrello()
                .callTrello();

    }
}
