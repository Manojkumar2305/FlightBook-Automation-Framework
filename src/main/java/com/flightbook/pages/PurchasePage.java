package com.flightbook.pages;

import org.openqa.selenium.By;

/**
 * PurchasePage – /purchase.php
 * Passenger and payment detail form.
 */
public class PurchasePage extends BasePage {

    // Flight info (pre-populated)
    private final By flightDescription = By.id("flight_info");
    private final By priceDisplay      = By.cssSelector(".price_total, p.price_total, h3");

    // Passenger fields
    private final By firstNameInput    = By.id("inputFirstName");
    private final By lastNameInput     = By.id("inputLastName");
    private final By addressInput      = By.id("inputAddress");
    private final By cityInput         = By.id("inputCity");
    private final By stateInput        = By.id("inputState");
    private final By zipInput          = By.id("inputZip");
    private final By cardTypeDropdown  = By.id("cardType");
    private final By creditCardInput   = By.id("creditCardNumber");
    private final By nameOnCardInput   = By.id("nameOnCard");
    private final By rememberMeChk    = By.id("rememberMe");
    private final By purchaseButton    = By.cssSelector("input[type='submit']");
    private final By pageHeading       = By.cssSelector("h2, h3");

    // ── Pre-populated info checks ─────────────────────────────────────────────

    public boolean isFlightDescriptionShown() {
        try {
            String text = waitForElement(flightDescription).getText();
            return text != null && !text.trim().isEmpty();
        } catch (Exception e) { return false; }
    }

    public boolean isPriceShown() {
        try {
            // Price appears in the form header area
            return driver.getPageSource().contains("$");
        } catch (Exception e) { return false; }
    }

    // ── Form filling ──────────────────────────────────────────────────────────

    public void fillPassengerDetails(String firstName, String lastName,
                                      String address, String city,
                                      String state, String zip) {
        waitAndType(firstNameInput, firstName);
        waitAndType(lastNameInput,  lastName);
        waitAndType(addressInput,   address);
        waitAndType(cityInput,      city);
        waitAndType(stateInput,     state);
        waitAndType(zipInput,       zip);
    }

    public void fillPaymentDetails(String cardType, String cardNumber, String nameOnCard) {
        selectByText(cardTypeDropdown, cardType);
        waitAndType(creditCardInput,  cardNumber);
        waitAndType(nameOnCardInput,  nameOnCard);
    }

    public void clickPurchaseFlight() {
        waitAndClick(purchaseButton);
    }

    /** Full form fill + submit */
    public void completePurchase(String firstName, String lastName,
                                  String address, String city, String state, String zip,
                                  String cardType, String cardNumber, String nameOnCard) {
        fillPassengerDetails(firstName, lastName, address, city, state, zip);
        fillPaymentDetails(cardType, cardNumber, nameOnCard);
        clickPurchaseFlight();
    }

    public boolean isOnPurchasePage() {
        return getCurrentUrl().contains("purchase");
    }

    public String getHeading() {
        return waitForElement(pageHeading).getText();
    }

    // ── Validation helpers ────────────────────────────────────────────────────

    /** Submit with empty first name to trigger validation */
    public void submitWithEmptyFirstName(String lastName, String address,
                                          String city, String state, String zip,
                                          String cardNumber, String nameOnCard) {
        waitAndType(lastNameInput,   lastName);
        waitAndType(addressInput,    address);
        waitAndType(cityInput,       city);
        waitAndType(stateInput,      state);
        waitAndType(zipInput,        zip);
        waitAndType(creditCardInput, cardNumber);
        waitAndType(nameOnCardInput, nameOnCard);
        clickPurchaseFlight();
    }
}
