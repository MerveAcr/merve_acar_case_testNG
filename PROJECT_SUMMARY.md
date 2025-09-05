# Insider Website Test Automation Project

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
  - Apply location filter: "Istanbul, Turkiye"
  - Apply department filter: "Quality Assurance"
  - Validate all jobs match filter criteria
  - Test View Role button redirect to Lever application form
- **Validation**: Each job must contain:
  - Position: "Quality Assurance"
  - Department: "Quality Assurance" 
  - Location: "Istanbul, Turkiye"
  - Successful redirect to jobs.lever.co domain

## 🏗️ Project Architecture

### Page Object Model (POM)
- **HomePage**: Handles homepage navigation and basic page verification
- **CareersPage**: Manages careers page validation and section checks
- **QAJobsPage**: Handles QA jobs filtering, validation, and job details verification
- **GlobalNavigationBar**: Manages navigation between different sections and sub-menus

### Base Classes
- **BaseTest**: WebDriver setup, configuration, and common test utilities with ExtentReports integration

### Utility Classes
- **WebDriverUtils**: Main utility facade for all WebDriver operations
- **ElementUtils**: Common element operations (click, scroll, hover, find with fallback)
- **PageUtils**: Page-level operations (wait for load, document ready, AJAX completion)
- **OverlayUtils**: Overlay and popup handling operations
- **DropdownUtils**: Dropdown and select2 component operations
- **ConfigReader**: Configuration properties management
- **ExtentReportUtils**: ExtentReports integration and report generation

### Configuration
- **test.properties**: Centralized configuration for URLs, timeouts, and test data
- **testng.xml**: TestNG suite configuration for test execution

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
│   │   │   ├── HomePage.java
│   │   │   ├── CareersPage.java
│   │   │   ├── QAJobsPage.java
│   │   │   └── GlobalNavigationBar.java
│   │   ├── tests/                   # Base test class
│   │   │   └── BaseTest.java
│   │   └── utils/                   # Utility classes
│   │       ├── WebDriverUtils.java
│   │       ├── ElementUtils.java
│   │       ├── PageUtils.java
│   │       ├── OverlayUtils.java
│   │       ├── DropdownUtils.java
│   │       ├── ConfigReader.java
│   │       └── ExtentReportUtils.java
│   └── main/resources/
│       └── test.properties          # Test configuration
└── src/test/java/com/insider/tests/
    └── InsiderWebsiteTest.java      # Main test class
```

## 🔧 Technical Implementation Details

### Framework Architecture
- **Selenium WebDriver 4.15.0**: Latest stable version with enhanced features
- **TestNG 7.8.0**: Advanced test framework with parallel execution support
- **Maven**: Dependency management and build automation
- **ExtentReports 5.0.9**: Professional HTML reporting with screenshots

### Design Patterns
- **Page Object Model**: Clean separation of page logic and test logic
- **Facade Pattern**: WebDriverUtils as main interface for all operations
- **Utility Pattern**: Specialized utility classes for different operations
- **Configuration Pattern**: Centralized configuration management

### Quality Assurance
- **Error Handling**: Basic exception handling and fallback mechanisms
- **Wait Strategies**: Explicit waits with 10-second timeout
- **Browser Support**: Chrome browser (primary)
- **Code Quality**: Documented and modular structure

## 📈 Business Value
- **Test Automation**: Automated validation of specified user journeys
- **Quality Assurance**: Helps identify issues in careers functionality
- **Regression Testing**: Can be used for basic regression testing
- **Documentation**: Provides test documentation for careers flow
- **Efficiency**: Reduces manual testing for covered scenarios

## 🎉 Project Status
✅ **COMPLETED** - All specified test scenarios implemented and working

The project successfully validates the Insider careers functionality as per the given requirements and can be integrated into CI/CD pipelines.