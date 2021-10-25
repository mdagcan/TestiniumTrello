package com.mycompny.tests;

import com.mycompany.pages.HomePage;
import org.openqa.selenium.WebDriver;

public class TestBase {

    WebDriver driver;
    HomePage homePage = new HomePage(driver);

}
