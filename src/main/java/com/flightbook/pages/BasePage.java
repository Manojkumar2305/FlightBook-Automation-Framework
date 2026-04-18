package com.flightbook.pages;

import com.flightbook.config.ConfigReader;
import com.flightbook.utils.DriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * BasePage – shared wait utilities for all Page classes.
 * Zero Thread.sleep() — all waits via WebDriverWait / FluentWait.
 */
public abstract class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage() {
        this.driver = DriverManager.getDriver();
        this.wait   = new WebDriverWait(driver,
                Duration.ofSeconds(ConfigReader.getInstance().getTimeout()));
    }

    /** Wait until visible, return element */
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Wait until clickable, click */
    protected void waitAndClick(By locator) {
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

    /** Wait until visible, clear and type */
    protected void waitAndType(By locator, String text) {
        WebElement el = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        el.clear();
        el.sendKeys(text);
    }

    /** Select dropdown by visible text */
    protected void selectByText(By locator, String text) {
        new Select(waitForElement(locator)).selectByVisibleText(text);
    }

    /** Select dropdown by value attribute */
    protected void selectByValue(By locator, String value) {
        new Select(waitForElement(locator)).selectByValue(value);
    }

    /** FluentWait – polls every 500ms for slow-loading elements */
    protected WebElement fluentWait(By locator) {
        return new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(ConfigReader.getInstance().getTimeout()))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(org.openqa.selenium.NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** JS click – bypass any overlay issues */
    protected void jsClick(By locator) {
        WebElement el = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
    }

    public String getPageTitle()  { return driver.getTitle(); }
    public String getCurrentUrl() { return driver.getCurrentUrl(); }
}
