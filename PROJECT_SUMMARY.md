# Insider Website Test Automation - Project Summary

## 🎯 Project Overview
This project implements comprehensive automated testing for the Insider website using Selenium WebDriver and TestNG framework. The test suite covers all the requested scenarios for testing the Insider careers functionality.

## ✅ Test Scenarios Implemented

### 1. Homepage Verification
- **Test**: `testHomePageOpened()`
- **Purpose**: Verify that https://useinsider.com/ opens correctly
- **Validation**: Checks URL, page title, and presence of Insider logo

### 2. Careers Navigation
- **Test**: `testCareersPageNavigation()`
- **Purpose**: Navigate Company > Careers and validate page sections
- **Validation**: Verifies presence of Locations, Teams, and Life at Insider sections

### 3. Complete QA Jobs Workflow
- **Test**: `testCompleteQAJobsWorkflow()`
- **Purpose**: Complete end-to-end QA jobs workflow including navigation, filtering, validation, and redirect
- **Steps**:
  - Navigate to QA jobs page
  - Click "See all QA jobs" button
  - Apply location filter: "Istanbul, Turkey"
  - Apply department filter: "Quality Assurance"
  - Validate all jobs match filter criteria
  - Test View Role button redirect to Lever application form
- **Validation**: Each job must contain:
  - Position: "Quality Assurance"
  - Department: "Quality Assurance" 
  - Location: "Istanbul, Turkey"
  - Successful redirect to jobs.lever.co domain

## 🏗️ Project Architecture

### Page Object Model (POM)
- **HomePage**: Handles homepage navigation and Company menu interactions
- **CareersPage**: Manages careers page validation and section checks
- **QAJobsPage**: Handles QA jobs filtering and job details validation

### Base Classes
- **BaseTest**: WebDriver setup, configuration, and common test utilities
- **WebDriverUtils**: Utility methods for common WebDriver operations

### Configuration
- **test.properties**: Centralized configuration for URLs, timeouts, and test data
- **testng.xml**: TestNG suite configuration for test execution

## 🚀 How to Run Tests

### Prerequisites
- Java 11+ (Java 17 recommended)
- Maven 3.6+
- Chrome browser

### Execution Methods

#### Method 1: Using Maven
```bash
mvn test
```

#### Method 2: Using TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

#### Method 3: Using Scripts
- **Windows**: `run-tests-with-extent.bat` or `setup-and-run.bat`
- **Linux/Mac**: `./run-tests-with-extent.sh`

#### Method 4: Run Specific Test
```bash
mvn test -Dtest=InsiderWebsiteTest#testHomePageOpened
```

## 📁 Project Structure
```
insider-test-automation/
├── pom.xml                          # Maven configuration
├── testng.xml                       # TestNG suite configuration
├── README.md                        # Project documentation
├── run-tests-with-extent.bat/.sh   # Execution scripts
├── setup-and-run.bat               # Setup and execution script
├── clean-reports.bat/.sh           # Report cleanup scripts
├── src/
│   ├── main/java/com/insider/
│   │   ├── pages/                   # Page Object classes
│   │   ├── tests/                   # Base test class
│   │   └── utils/                   # Utility classes
│   └── main/resources/
│       └── test.properties          # Test configuration
└── src/test/java/com/insider/tests/
    └── InsiderWebsiteTest.java      # Main test class
```

## 🔧 Technical Features

### WebDriver Management
- Automatic ChromeDriver setup using WebDriverManager
- Chrome browser configuration with optimal settings
- Implicit and explicit wait strategies

### Test Configuration
- Priority-based test execution (1-5)
- Configurable timeouts and browser settings
- Centralized test data management

### Error Handling
- Robust exception handling in all page methods
- Graceful fallbacks for element interactions
- Comprehensive assertion messages

### Maintainability
- Clean separation of concerns
- Reusable page object methods
- Centralized locator management

## 📊 Test Results
Tests generate detailed reports in multiple locations:
- **ExtentReports**: `test-output/ExtentReports/Insider Test Automation Report.html` - Interactive HTML report with screenshots and detailed steps
- **Surefire Reports**: `target/surefire-reports/` - Standard TestNG reports including:
  - Test execution summary
  - Pass/fail status for each test
  - Detailed error messages and stack traces
  - Execution timing information

## 🎉 Project Status
✅ **COMPLETED** - All requested test scenarios implemented and ready for execution

The project is fully functional and ready to run the complete test suite for the Insider website careers functionality.
