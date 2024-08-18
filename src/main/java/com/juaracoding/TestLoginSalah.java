package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class TestLoginSalah {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver(); // Perbaiki ini untuk menginisialisasi driver instance
        driver.manage().window().maximize();
        String url = "https://www.saucedemo.com/";
        driver.get(url);
    }

    @Test
    public void testLoginFailure() {

        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        username.sendKeys("new_user");

        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("tomato_sauce");

        delay(2);

        WebElement loginClick = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginClick.click();

        WebElement errorMessage = driver.findElement(By.xpath("//h3[@data-test='error']"));

        // Assert
        if (errorMessage.isDisplayed()) {
            Assert.assertTrue(errorMessage.getText().contains("Username and password do not match"));
        } else {
            Assert.fail("Error message not displayed!");
        }

        delay(5);
    }


    @AfterClass
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    public static void delay(long detik) {
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }
}