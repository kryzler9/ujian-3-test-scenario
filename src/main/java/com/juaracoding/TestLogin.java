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


public class TestLogin {
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
    public void testLoginSuccess() {

        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        username.sendKeys("standard_user");

        delay(1);

        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("secret_sauce");

        delay(2);

        WebElement loginClick = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginClick.click();

        // Assert
        if (driver.getCurrentUrl().contains("inventory.html")) {
            Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        } else {
            Assert.fail("Login failed!");
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