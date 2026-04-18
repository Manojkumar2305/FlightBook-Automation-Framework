package com.flightbook.tests;

import com.flightbook.dataproviders.TestDataProvider;
import com.flightbook.pages.FlightListPage;
import com.flightbook.pages.HomePage;
import com.flightbook.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test Module 1 – Flight Search
 *  TC_SRCH_01 : Valid departure + destination → flights listing page loads
 *  TC_SRCH_02 : Flight listing has at least one result row
 *  TC_SRCH_03 : Each result shows airline, flight#, departs, arrives
 *  TC_SRCH_04 : Same city for departure and destination still loads results
 */
public class FlightSearchTest extends BaseTest {

    @DataProvider(name = "routesExcel", parallel = false)
    public Object[][] routesFromExcel() {
        return TestDataProvider.getFlightRoutesFromExcel();
    }

    @DataProvider(name = "routesJson", parallel = false)
    public Object[][] routesFromJson() {
        return TestDataProvider.getFlightRoutesFromJson();
    }

    // ── TC_SRCH_01 – data-driven route search from Excel ─────────────────────
    @Test(dataProvider = "routesExcel",
          description = "Valid route search from Excel – flight listing page loads",
          retryAnalyzer = RetryAnalyzer.class)
    public void testFlightSearchLoadsResults(String departure, String destination,
                                              String description) {
        HomePage       home      = new HomePage();
        FlightListPage listPage  = new FlightListPage();

        home.searchFlight(departure, destination);

        Assert.assertTrue(getCurrentUrl().contains("reserve"),
                "Should navigate to reserve page. Route: " + description);
        Assert.assertTrue(listPage.hasFlights(),
                "Flight listing must show at least one result. Route: " + description);
    }

    // ── TC_SRCH_02 – at least one result ─────────────────────────────────────
    @Test(description = "Flight listing shows at least one result row",
          retryAnalyzer = RetryAnalyzer.class)
    public void testFlightListHasResults() {
        HomePage       home     = new HomePage();
        FlightListPage listPage = new FlightListPage();

        home.searchFlight("Boston", "London");

        int count = listPage.getFlightCount();
        Assert.assertTrue(count > 0,
                "Flight listing must have at least 1 row, found: " + count);
    }


    // ── TC_SRCH_04 – same city both ways ─────────────────────────────────────
    @Test(description = "Same city for departure and destination still loads results",
          retryAnalyzer = RetryAnalyzer.class)
    public void testSameCitySearch() {
        HomePage       home     = new HomePage();
        FlightListPage listPage = new FlightListPage();

        home.searchFlight("Boston", "Boston");

        Assert.assertTrue(listPage.hasFlights(),
                "Searching same city for both departure and destination should still show results");
    }

    private String getCurrentUrl() {
        return com.flightbook.utils.DriverManager.getDriver().getCurrentUrl();
    }
}
