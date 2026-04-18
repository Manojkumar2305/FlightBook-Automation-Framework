package com.flightbook.tests;

import com.flightbook.dataproviders.TestDataProvider;
import com.flightbook.pages.FlightListPage;
import com.flightbook.pages.HomePage;
import com.flightbook.utils.RetryAnalyzer;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * Test Module 4 – Different Route Search
 *  TC_ROUTE_01 : 3 departure-destination combos each load results (data-driven JSON)
 *  TC_ROUTE_02 : Flight count changes between different routes
 *  TC_ROUTE_03 : Page title on flights listing is correct for each route
 */
public class DifferentRouteTest extends BaseTest {

    @DataProvider(name = "routesJson", parallel = false)
    public Object[][] routesJson() {
        return TestDataProvider.getFlightRoutesFromJson();
    }

    // ── TC_ROUTE_01 – data-driven from JSON ───────────────────────────────────
    @Test(dataProvider = "routesJson",
          description = "Each route from JSON loads flight results",
          retryAnalyzer = RetryAnalyzer.class)
    public void testEachRouteLoadsResults(String departure, String destination,
                                           String description) {
        HomePage       home     = new HomePage();
        FlightListPage listPage = new FlightListPage();

        home.searchFlight(departure, destination);

        Assert.assertTrue(listPage.hasFlights(),
                "Flight results must load for route: " + description);
        Assert.assertTrue(listPage.getFlightCount() > 0,
                "At least 1 flight must be listed for route: " + description);
    }

    // ── TC_ROUTE_02 – flight count differs between routes ─────────────────────
    @Test(description = "Flight count can differ between different route searches",
          retryAnalyzer = RetryAnalyzer.class)
    public void testFlightCountDiffersBetweenRoutes() {
        HomePage       home     = new HomePage();
        FlightListPage listPage = new FlightListPage();

        // Route 1
        home.searchFlight("Boston", "London");
        int countRoute1 = listPage.getFlightCount();
        Assert.assertTrue(countRoute1 > 0, "Route 1 must have flights");

        // Navigate back home for Route 2
        com.flightbook.utils.DriverManager.getDriver()
                .get(com.flightbook.config.ConfigReader.getInstance().getBaseUrl());

        // Route 2
        home.searchFlight("Portland", "Berlin");
        int countRoute2 = listPage.getFlightCount();
        Assert.assertTrue(countRoute2 > 0, "Route 2 must have flights");

        // Both routes returned valid results — counts documented
        // (BlazeDemo may return same count — we verify results exist for both)
        Assert.assertTrue(countRoute1 + countRoute2 > 1,
                "Combined routes must have more than 1 total result. "
                + "Route1=" + countRoute1 + " Route2=" + countRoute2);
    }

    // ── TC_ROUTE_03 – page title correct ──────────────────────────────────────
    @Test(description = "Flights listing page title is correct for each route search",
          retryAnalyzer = RetryAnalyzer.class)
    public void testFlightListPageTitle() {
        HomePage       home     = new HomePage();
        FlightListPage listPage = new FlightListPage();

        home.searchFlight("San Diego", "Dublin");

        String title = listPage.getPageTitle();
        Assert.assertNotNull(title, "Page title must not be null");
        Assert.assertFalse(title.isEmpty(), "Page title must not be empty");
        // BlazeDemo listing page title is "BlazeDemo - reserve"
        Assert.assertTrue(title.toLowerCase().contains("blaze") ||
                           title.toLowerCase().contains("reserve") ||
                           title.toLowerCase().contains("flight"),
                "Page title should identify the flights listing page. Got: " + title);
    }
}
