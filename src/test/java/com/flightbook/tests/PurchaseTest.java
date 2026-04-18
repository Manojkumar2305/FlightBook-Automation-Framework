package com.flightbook.tests;

import com.flightbook.dataproviders.TestDataProvider;
import com.flightbook.pages.ConfirmationPage;
import com.flightbook.pages.FlightListPage;
import com.flightbook.pages.HomePage;
import com.flightbook.pages.PurchasePage;
import com.flightbook.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test Module 3 – Purchase Form
 *  TC_PURCH_01 : Fill valid passenger + payment → confirmation page loads
 *  TC_PURCH_02 : Confirmation page shows a booking ID / order reference
 *  TC_PURCH_03 : Thank-you message is present on confirmation page
 */
public class PurchaseTest extends BaseTest {

    @DataProvider(name = "passengerDataExcel", parallel = false)
    public Object[][] passengerData() {
        return TestDataProvider.getPassengerDataFromExcel();
    }

    /** Helper: search + choose first flight, land on purchase page */
    private void navigateToPurchasePage() {
        HomePage       home     = new HomePage();
        FlightListPage listPage = new FlightListPage();
        home.searchFlight("Boston", "London");
        listPage.chooseFirstFlight();
    }

    // ── TC_PURCH_01 – data-driven from Excel ──────────────────────────────────
    @Test(dataProvider = "passengerDataExcel",
          description = "Valid passenger data from Excel – confirmation page loads",
          retryAnalyzer = RetryAnalyzer.class)
    public void testPurchaseWithValidData(String firstName, String lastName,
                                          String address,   String city,
                                          String state,     String zip,
                                          String creditCard, String nameOnCard) {
        navigateToPurchasePage();

        PurchasePage purchasePage = new PurchasePage();
        purchasePage.completePurchase(
                firstName, lastName, address, city, state, zip,
                "Visa", creditCard, nameOnCard);

        ConfirmationPage confirmPage = new ConfirmationPage();
        Assert.assertTrue(confirmPage.isConfirmationPageLoaded(),
                "Confirmation page should load after successful purchase. Passenger: "
                + firstName + " " + lastName);
    }

    // ── TC_PURCH_02 – booking ID ───────────────────────────────────────────────
    @Test(description = "Confirmation page shows a booking ID or order reference",
          retryAnalyzer = RetryAnalyzer.class)
    public void testConfirmationShowsBookingId() {
        navigateToPurchasePage();

        new PurchasePage().completePurchase(
                "QA", "Tester", "123 Test St", "Boston",
                "MA", "02101", "Visa",
                "4111111111111111", "QA Tester");

        ConfirmationPage confirmPage = new ConfirmationPage();
        Assert.assertTrue(confirmPage.isConfirmationPageLoaded(),
                "Must be on confirmation page");
        Assert.assertTrue(confirmPage.isBookingIdPresent(),
                "Confirmation page must show a booking ID / order reference");
    }

    // ── TC_PURCH_03 – thank-you message ───────────────────────────────────────
    @Test(description = "Thank-you message is present on confirmation page",
          retryAnalyzer = RetryAnalyzer.class)
    public void testConfirmationThankYouMessage() {
        navigateToPurchasePage();

        new PurchasePage().completePurchase(
                "QA", "Tester", "123 Test St", "Boston",
                "MA", "02101", "Visa",
                "4111111111111111", "QA Tester");

        ConfirmationPage confirmPage = new ConfirmationPage();
        Assert.assertTrue(confirmPage.isThankYouMessagePresent(),
                "Confirmation heading must contain 'Thank you' or 'Confirmed'");
    }
}
