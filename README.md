# FlightBook – BlazeDemo Flight Automation Framework

## AUT
**URL:** https://blazedemo.com  
**No backend setup needed** — live demo site, no registration required

## Tech Stack
| Tool | Version |
|---|---|
| Java | 11+ |
| Selenium | 4.18.1 |
| TestNG | 7.9.0 |
| WebDriverManager | 5.7.0 |
| ExtentReports | 5.1.1 |
| Apache POI | 5.2.5 (Excel) |
| Jackson | 2.16.1 (JSON) |

## Project Structure
```
FlightBook/
├── pom.xml
├── testng.xml
├── screenshots/              ← auto-created on failure
├── test-output/
│   └── FlightBookReport.html ← auto-generated after run
└── src/
    ├── main/java/com/flightbook/
    │   ├── config/ConfigReader.java
    │   ├── pages/
    │   │   ├── BasePage.java
    │   │   ├── HomePage.java
    │   │   ├── FlightListPage.java
    │   │   ├── PurchasePage.java
    │   │   └── ConfirmationPage.java
    │   ├── utils/
    │   │   ├── DriverManager.java
    │   │   ├── ScreenshotUtil.java
    │   │   ├── ExtentReportManager.java
    │   │   └── RetryAnalyzer.java
    │   └── listeners/TestListener.java
    └── test/
        ├── java/com/flightbook/
        │   ├── dataproviders/TestDataProvider.java
        │   └── tests/
        │       ├── BaseTest.java
        │       ├── FlightSearchTest.java    ← Module 1
        │       ├── FlightSelectionTest.java ← Module 2
        │       ├── PurchaseTest.java        ← Module 3
        │       ├── DifferentRouteTest.java  ← Module 4
        │       └── FormValidationTest.java  ← Module 5
        └── resources/
            ├── config.properties
            ├── flightRoutes.json
            └── testdata.xlsx (FlightRoutes + PassengerData sheets)
```

## Eclipse Setup
1. File → Import → Maven → Existing Maven Projects → browse to FlightBook
2. Right-click pom.xml → Maven → Update Project (Alt+F5)
3. Run: `mvn test`

## Test Count Breakdown
| Module | Tests | Data Source |
|---|---|---|
| 1 – Flight Search | 4+4 (Excel data-driven) | testdata.xlsx FlightRoutes |
| 2 – Flight Selection | 3 | inline |
| 3 – Purchase Form | 2+1 (Excel data-driven) | testdata.xlsx PassengerData |
| 4 – Different Routes | 4+2 (JSON data-driven) | flightRoutes.json |
| 5 – Form Validations | 1+3+1 (inline DataProvider) | inline |

## Key Design Rules
| Rule | How enforced |
|---|---|
| No Thread.sleep() | WebDriverWait + FluentWait only |
| No hardcoded values | config.properties + testdata.xlsx + flightRoutes.json |
| No WebDriver in tests | DriverManager + BaseTest |
| POM enforced | All By.* locators in Page classes only |
| Data-driven | Excel (FlightRoutes, PassengerData) + JSON (flightRoutes.json) |
| Screenshot on failure | TestListener auto-captures with timestamp |
| Parallel execution | thread-count="2" parallel="methods" |
| Retry on failure | RetryAnalyzer (1 retry per test) |
