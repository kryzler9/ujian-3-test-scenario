package com.juaracoding;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class AddProduct {
    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver","D:\\chromedriver-win64\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        driver.manage().window().maximize();
        String url = "https://www.saucedemo.com/";
        driver.get(url);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Open Browser URL");

        //Brand
        delay(3);
        WebElement logoBrand = driver.findElement(By.xpath("//div[@class='login_logo']"));
        System.out.println(logoBrand.isDisplayed());

        WebElement username = driver.findElement(By.xpath("//input[@id='user-name']"));
        username.sendKeys("standard_user");

        WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
        password.sendKeys("secret_sauce");

        WebElement loginClick = driver.findElement(By.xpath("//input[@id='login-button']"));
        loginClick.click();

        delay(2);
        String txtTitle = driver.findElement(By.xpath("//span[@class='title']")).getText();
        System.out.println("Page :"+txtTitle);
        String expectedTitle = "Products";
        customAssertEquals(txtTitle, expectedTitle);

        WebElement addProductClick = driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']"));
        addProductClick.click();

        WebElement checkCart = driver.findElement(By.xpath("//a[@class='shopping_cart_link']"));
        checkCart.click();

        delay(2);
        String productName = driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
        System.out.println("Product Added :"+productName);
        String expectedProductName = "Sauce Labs Bike Light";
        customAssertEquals(productName, expectedProductName);


        delay(5);

        driver.quit();
        System.out.println("Exit Browser");
    }

    public static void delay(long detik) {
        try {
            Thread.sleep(detik*1000);
        } catch (InterruptedException e){
            throw new RuntimeException();
        }
    }

    public static void customAssertEquals(String actual, String expected){
        System.out.print("Test Result: ");
        if(actual.equals(expected)){
            System.out.println("Passed");
        } else {
            System.out.println("Failed");
        }
    }
}

