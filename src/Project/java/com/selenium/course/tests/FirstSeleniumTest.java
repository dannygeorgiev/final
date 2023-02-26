package com.selenium.course.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FirstSeleniumTest {
    public WebDriver driver;
    @BeforeTest
    public void setUp(){
        WebDriverManager.chromedriver().setup(); // Automatically downloads and prepare webdriver for the version of the brwoser
        driver = new ChromeDriver(); // uses the downloaded driver version


    }
    @AfterTest
    public void tearDown(){

    }
    @Test
    public void successfulLoginSauceDemo(){
        driver.get("https://www.saucedemo.com/");

        //elements
        WebElement usernameInput = driver.findElement(By.cssSelector("[data-test=\"username\"]"));
        WebElement passwordInput = driver.findElement(By.id("password"));
        WebElement loginBtn = driver.findElement(By.className("submit-button btn_action"));
        WebElement profileMenu = driver.findElement(By.id("react-burger-menu-btn"));
        WebElement logoutLink = driver.findElement(By.id("logout_sidebar_link"));

        //actions
        usernameInput.click();
        usernameInput.clear();
        usernameInput.sendKeys("standard_user");
        passwordInput.click();
        passwordInput.clear();
        passwordInput.sendKeys("secret_sauce");
        loginBtn.click();

        profileMenu.click();

        //assert
        Assert.assertTrue(logoutLink.isDisplayed());


    }

}
