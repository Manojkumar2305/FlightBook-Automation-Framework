package com.flightbook.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

/**
 * FlightListPage – /reserve.php
 * Shows available flights table. Each row has airline, flight#, departs, arrives, price.
 */
public class FlightListPage extends BasePage {

    private final By flightRows        = By.cssSelector("table tbody tr");
    private final By chooseButtons     = By.cssSelector("input[type='submit']");
    private final By tableHeaders      = By.cssSelector("table thead th");
    private final By airlineColumn     = By.cssSelector("table tbody tr td:nth-child(2)");
    private final By flightNumColumn   = By.cssSelector("table tbody tr td:nth-child(3)");
    private final By departsColumn     = By.cssSelector("table tbody tr td:nth-child(4)");
    private final By arrivesColumn     = By.cssSelector("table tbody tr td:nth-child(5)");
    private final By pageHeading       = By.cssSelector("h3");

    /** Wait for flight results using FluentWait (slow network) */
    public boolean hasFlights() {
        try {
            fluentWait(By.cssSelector("table tbody tr"));
            return !driver.findElements(flightRows).isEmpty();
        } catch (Exception e) { return false; }
    }

    public int getFlightCount() {
        return driver.findElements(flightRows).size();
    }

    /** Verify airline column is not empty for first row */
    public boolean isAirlineShown() {
        List<WebElement> airlines = driver.findElements(airlineColumn);
        return !airlines.isEmpty() && !airlines.get(0).getText().trim().isEmpty();
    }

    public boolean isFlightNumberShown() {
        List<WebElement> nums = driver.findElements(flightNumColumn);
        return !nums.isEmpty() && !nums.get(0).getText().trim().isEmpty();
    }

    public boolean isDepartureTimeShown() {
        List<WebElement> dep = driver.findElements(departsColumn);
        return !dep.isEmpty() && !dep.get(0).getText().trim().isEmpty();
    }

    public boolean isArrivalTimeShown() {
        List<WebElement> arr = driver.findElements(arrivesColumn);
        return !arr.isEmpty() && !arr.get(0).getText().trim().isEmpty();
    }

    /** Click the first Choose This Flight button */
    public void chooseFirstFlight() {
        List<WebElement> btns = driver.findElements(chooseButtons);
        if (!btns.isEmpty()) {
            wait.until(org.openqa.selenium.support.ui.ExpectedConditions
                    .elementToBeClickable(btns.get(0))).click();
        }
    }

    public String getPageHeading() {
        return waitForElement(pageHeading).getText();
    }

    public String getPageTitle() {
        return driver.getTitle();
    }
}
