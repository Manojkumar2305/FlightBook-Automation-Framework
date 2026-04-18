package com.flightbook.tests;

import com.flightbook.pages.FlightListPage;
import com.flightbook.pages.HomePage;
import com.flightbook.pages.PurchasePage;
import com.flightbook.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Test Module 2 – Flight Selection
 *  TC_SEL_01 : Choose This Flight → navigates to purchase form
 *  TC_SEL_02 : Purchase form shows pre-populated flight description
 *  TC_SEL_03 : Purchase form shows price of selected flight
 */
public class FlightSelectionTest extends BaseTest {

    // ── TC_SEL_01 ─────────────────────────────────────────────────────────────
    @Test(description = "Choose This Flight navigates to the purchase form",
          retryAnalyzer = RetryAnalyzer.class)
    public void testChooseFlightNavigatesToPurchase() {
        HomePage       home     = new HomePage();
        FlightListPage listPage = new FlightListPage();

        home.searchFlight("Boston", "London");
        Assert.assertTrue(listPage.hasFlights(), "Pre-condition: flights must be listed");

        listPage.chooseFirstFlight();

        PurchasePage purchasePage = new PurchasePage();
        Assert.assertTrue(purchasePage.isOnPurchasePage(),
                "After clicking Choose This Flight, should navigate to purchase page");
    }

   
    // ── TC_SEL_03 ─────────────────────────────────────────────────────────────
    @Test(description = "Purchase form shows the price of the selected flight",
          retryAnalyzer = RetryAnalyzer.class)
    public void testPurchaseFormShowsPrice() {
        HomePage       home     = new HomePage();
        FlightListPage listPage = new FlightListPage();

        home.searchFlight("Portland", "Berlin");
        listPage.chooseFirstFlight();

        PurchasePage purchasePage = new PurchasePage();
        Assert.assertTrue(purchasePage.isPriceShown(),
                "Purchase page must show a price ($) for the selected flight");
    }
}
