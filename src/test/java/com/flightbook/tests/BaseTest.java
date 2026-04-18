package com.flightbook.tests;

import com.flightbook.config.ConfigReader;
import com.flightbook.utils.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * BaseTest – browser lifecycle for all test classes.
 * No WebDriver code in any test method.
 */
public class BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        DriverManager.initDriver();
        DriverManager.getDriver().get(ConfigReader.getInstance().getBaseUrl());
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
