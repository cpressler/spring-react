package com.softvision.example.springboot;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimpleAutomationTest {
    //We should add @Test annotation that JUnit will run below method
    @Test
    //Start to write our test method.
    public void titleTest() {

        //Set GeckoDriver (Marionette Driver) path as system property
        //System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        //FirefoxDriver driver = new FirefoxDriver();


        // this could be dependent on each system, mac windows, linux
        System.setProperty("webdriver.chrome.driver", "/usr/local/bin/chromedriver");
        //Step 1- Driver Instantiation: Instantiate driver object as FirefoxDriver
        WebDriver driver = new ChromeDriver();

        //Step 2- Navigation: Open a website
        driver.navigate().to("https://www.swtestacademy.com/");

        //Step 3- Assertion: Check its title is correct
        //assertEquals method Parameters: Expected Value, Actual Value, Assertion Message
        assertEquals("Software Test Academy", driver.getTitle(), "Title check failed!");

        //Step 4- Close Driver
        driver.close();

        //Step 5- Quit Driver
        driver.quit();
    }
}
