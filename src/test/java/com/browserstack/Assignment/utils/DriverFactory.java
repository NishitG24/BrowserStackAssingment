package com.browserstack.Assignment.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
/*
 * DriverFactory.java
 * This class manages the WebDriver instance for the tests.
 * It initializes the driver based on the environment (local or remote).
 */
public class DriverFactory {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initDriver() {
        if (Boolean.getBoolean("local")) {              // mvn -Dlocal=true
            driver.set(new ChromeDriver());             // Local Chrome via Selenium‑Manager
        } else {
            driver.set(BrowserStackFactory.create());   // Remote browser
        }
        driver.get().manage().window().maximize();
    }


    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void quitDriver() {
        driver.get().quit();
        driver.remove();
    }

}
