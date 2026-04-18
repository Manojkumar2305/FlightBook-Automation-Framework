package com.flightbook.pages;

import org.openqa.selenium.By;

/**
 * ConfirmationPage – /confirmation.php
 * Booking success page with order ID and thank-you message.
 */
public class ConfirmationPage extends BasePage {

    private final By confirmHeading  = By.cssSelector("h1");
    private final By bookingIdRow    = By.xpath("//table//td[contains(text(),'Id')]" +
                                                "/following-sibling::td");
    private final By bookingIdAlt    = By.cssSelector("table tr:first-child td:last-child");
    private final By confirmTable    = By.cssSelector("table.table");

    public boolean isConfirmationPageLoaded() {
        try {
            return getCurrentUrl().contains("confirmation")
                    || waitForElement(confirmHeading).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public String getConfirmationHeading() {
        return waitForElement(confirmHeading).getText();
    }

    public boolean isThankYouMessagePresent() {
        String heading = getConfirmationHeading().toLowerCase();
        return heading.contains("thank") || heading.contains("confirmed");
    }

    public boolean isBookingIdPresent() {
        try {
            // BlazeDemo shows booking details in a table
            String pageSource = driver.getPageSource();
            return pageSource.contains("Id") && waitForElement(confirmTable).isDisplayed();
        } catch (Exception e) { return false; }
    }

    public String getBookingId() {
        try {
            return fluentWait(bookingIdRow).getText().trim();
        } catch (Exception e) {
            try { return fluentWait(bookingIdAlt).getText().trim(); }
            catch (Exception ex) { return ""; }
        }
    }
}
