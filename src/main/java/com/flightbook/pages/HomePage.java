package com.flightbook.pages;

import org.openqa.selenium.By;

/**
 * HomePage – https://blazedemo.com
 * Handles departure/destination dropdowns and Find Flights button.
 */
public class HomePage extends BasePage {

    private final By departureDropdown   = By.name("fromPort");
    private final By destinationDropdown = By.name("toPort");
    private final By findFlightsButton   = By.cssSelector("input[type='submit']");
    private final By pageHeading         = By.cssSelector("h1");

    public void selectDeparture(String city) {
        selectByText(departureDropdown, city);
    }

    public void selectDestination(String city) {
        selectByText(destinationDropdown, city);
    }

    public void clickFindFlights() {
        waitAndClick(findFlightsButton);
    }

    /** Convenience: select both cities and search */
    public void searchFlight(String departure, String destination) {
        selectDeparture(departure);
        selectDestination(destination);
        clickFindFlights();
    }

    public boolean isHomePageLoaded() {
        try {
            return waitForElement(findFlightsButton).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public String getHeadingText() {
        return waitForElement(pageHeading).getText();
    }
}
