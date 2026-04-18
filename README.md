# FlightBook-Automation-Framework
FlightBook Automation Framework – A Selenium + Java TestNG framework built using Page Object Model (POM) to automate end-to-end testing of a flight booking web application with reporting, retry logic, and scalable design.


# ✈️ FlightBook – Selenium Test Automation Framework

## 🚀 Overview

FlightBook is a **Selenium + Java Test Automation Framework** built using **Page Object Model (POM)** and **TestNG** to automate end-to-end testing of a flight booking web application.

It follows **industry best practices** like reusable design, data-driven testing, reporting, and failure handling.

---

## 🎯 Objective

Automate complete flight booking workflow:

* Flight search
* Flight listing validation
* Flight selection
* Passenger form submission
* Booking confirmation
* Form validation scenarios

---

## 🧱 Tech Stack

* Java
* Selenium WebDriver
* TestNG
* Maven
* WebDriverManager
* ExtentReports

---

## 🏗️ Project Structure

```
FlightBookFramework/
│
├── src/main/java
│   ├── pages/              # Page classes (POM)
│   ├── utils/              # Utilities (ConfigReader, Waits)
│   ├── base/               # BasePage (common methods)
│
├── src/test/java
│   ├── tests/              # Test classes
│   ├── listeners/          # Screenshot + reporting
│   ├── dataproviders/      # Test data
│
├── src/test/resources
│   ├── config.properties   # Configurations
│
├── testng.xml              # Test suite
├── pom.xml                 # Maven dependencies
```

---

## ✨ Key Features

### ✅ Page Object Model (POM)

* Separate classes for each page:

  * HomePage
  * FlightListPage
  * PurchasePage
  * ConfirmationPage

---

### ✅ Data-Driven Testing

* Uses `@DataProvider` for multiple test inputs

---

### ✅ Configuration Management

* Centralized config using `config.properties`
* No hardcoded values

---

### ✅ Smart Wait Strategy

* Uses `WebDriverWait`
* No `Thread.sleep()`

---

### ✅ Retry Mechanism

* Failed tests are retried using `IRetryAnalyzer`

---

### ✅ Screenshot on Failure

* Automatically captured using TestNG Listener
* Stored in `/screenshots/`

---

### ✅ Test Reporting

* ExtentReports generates HTML reports
* Includes:

  * Test status
  * Logs
  * Screenshots on failure

---

## 🧪 Test Modules

### 🔹 Flight Search

* Search flights using source and destination
* Validate results and columns

### 🔹 Flight Selection

* Select flight and verify navigation
* Validate flight details

### 🔹 Purchase Flow

* Fill passenger details
* Submit form
* Verify confirmation

### 🔹 Multiple Route Testing

* Test different city combinations
* Validate dynamic results

### 🔹 Form Validation

* Empty field validation
* Invalid input handling

---

## ⚙️ Configuration

Example `config.properties`:

```
base.url=https://blazedemo.com
browser=chrome
timeout=15
headless=false
```

---

## ▶️ How to Run

### Using Maven:

```
mvn clean test
```

### Using TestNG:

Run `testng.xml`

---

## 📸 Reports & Screenshots

* Reports: `test-output/`
* Screenshots: `/screenshots/`

---

## 💡 Best Practices Followed

* Clean POM design
* No hardcoded values
* Reusable methods
* Proper waits
* Scalable framework structure

---

## 🚀 Future Enhancements

* Parallel execution
* CI/CD integration (Jenkins/GitHub Actions)
* Excel/JSON data-driven testing
* Headless execution

---

## 👨‍💻 Author

Manoj

---

## ⭐ Support

If you found this useful, give it a ⭐ and fork the repo!
