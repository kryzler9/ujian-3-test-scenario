package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TestAdd1Product {
    WebDriver driver;

    @BeforeClass
    public void setup(){
        System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver(); // Perbaiki ini untuk menginisialisasi driver instance
        driver.manage().window().maximize();
        String url = "https://www.saucedemo.com/";
        driver.get(url);
        login();
    }

    public void login() {

        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("secret_sauce");


        WebElement loginClick = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginClick.click();

    }

    @Test
    public void testAddProduct(){

        delay(2);
        WebElement addProductBike = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']"));
        addProductBike.click();

        delay(2);

        WebElement cartBadge = driver.findElement(By.xpath("//*[@id=\"shopping_cart_container\"]/a/span"));

        // Assert
        if (cartBadge.isDisplayed()) {
            Assert.assertEquals(cartBadge.getText(), "1");
        } else {
            Assert.fail("Product was not added to the cart!");
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