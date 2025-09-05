# Insider Website Test Automation

This project contains automated tests for the Insider website using Selenium WebDriver and TestNG framework.

## Test Scenarios

The test suite covers the following scenarios:

1. **Homepage Verification**: Check if Insider home page opens correctly
2. **Careers Navigation**: Navigate to Company > Careers and validate page sections
3. **Complete QA Jobs Workflow**: End-to-end workflow including:
   - Navigate to QA jobs page
   - Apply location filter (Istanbul, Turkey) and department filter (Quality Assurance)
   - Validate all jobs match the filter criteria
   - Test View Role button redirect to Lever application form

## Prerequisites

- Java 11 or higher (Java 17 recommended)
- Maven 3.6 or higher
- Chrome browser installed

## Project Structure

```
src/
├── main/java/com/insider/
│   ├── pages/          # Page Object Model classes
│   ├── tests/          # Base test class
│   └── utils/          # Utility classes
└── test/java/com/insider/tests/
    └── InsiderWebsiteTest.java  # Main test class
```

## Setup and Execution

### 1. Clone the repository
```bash
git clone <repository-url>
cd insider-test-automation
```

### 2. Install dependencies
```bash
mvn clean install
```

### 3. Run tests

#### Run all tests:
```bash
mvn test
```

#### Run specific test:
```bash
mvn test -Dtest=InsiderWebsiteTest#testHomePageOpened
```

#### Run using TestNG XML:
```bash
mvn test -DsuiteXmlFile=testng.xml
```

#### Run using provided scripts:
```bash
# Windows
run-tests-with-extent.bat
# or
setup-and-run.bat

# Linux/Mac
./run-tests-with-extent.sh
```

## Test Configuration

The tests are configured to:
- Use Chrome browser (automatically managed by WebDriverManager)
- Run in maximized window mode
- Use 10-second timeouts for element waits
- Execute tests in priority order (1-3)
- Generate detailed ExtentReports with screenshots
- Support both ExtentReports and Surefire reporting

## Page Object Model

The project follows Page Object Model pattern for better maintainability:

- **HomePage**: Handles homepage navigation and Company menu interactions
- **CareersPage**: Manages careers page validation and section checks
- **QAJobsPage**: Handles QA jobs filtering and job details validation

## Troubleshooting

### Common Issues:

1. **ChromeDriver issues**: WebDriverManager automatically handles driver setup
2. **Element not found**: Check if selectors need updating due to website changes
3. **Timeout issues**: Increase wait time in BaseTest class if needed

### Debug Mode:
Add `-Dmaven.surefire.debug` to run tests in debug mode:
```bash
mvn test -Dmaven.surefire.debug
```

## Test Reports

Test results are generated in multiple locations after execution:

- **ExtentReports**: `test-output/ExtentReports/Insider Test Automation Report.html` - Interactive HTML report with screenshots and detailed test steps
- **Surefire Reports**: `target/surefire-reports/` - Standard TestNG reports with execution summary and detailed logs

### Opening Reports
- **Windows**: Run `run-tests-with-extent.bat` to automatically open the ExtentReports
- **Linux/Mac**: Run `./run-tests-with-extent.sh` to automatically open the ExtentReports

## Contributing

1. Follow the existing code structure and naming conventions
2. Add proper JavaDoc comments for new methods
3. Update this README for any new test scenarios
4. Ensure all tests pass before submitting changes
