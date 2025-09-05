# Selenium TestNG Automation Framework

A comprehensive Selenium WebDriver and TestNG based automation framework for testing web applications. This framework demonstrates best practices in test automation including Page Object Model, utility classes, and professional reporting.

## 🚀 Framework Features

### Core Technologies
- **Selenium WebDriver 4.15.0** - Latest stable version with enhanced capabilities
- **TestNG 7.8.0** - Advanced testing framework with parallel execution support
- **Maven 3.6+** - Dependency management and build automation
- **Java 11+** - Modern Java features and performance optimizations

### Advanced Capabilities
- **ExtentReports 5.0.9** - Professional HTML reporting with screenshots and step-by-step logs
- **WebDriverManager** - Automatic WebDriver binary management
- **Page Object Model** - Clean, maintainable test architecture
- **Utility Pattern** - Reusable components for common operations
- **Configuration Management** - Centralized test data and settings

### Framework Architecture
```
src/
├── main/java/com/insider/
│   ├── pages/                    # Page Object Model classes
│   │   ├── HomePage.java         # Homepage interactions
│   │   ├── CareersPage.java      # Careers page operations
│   │   ├── QAJobsPage.java       # Job filtering and validation
│   │   └── GlobalNavigationBar.java # Navigation utilities
│   ├── tests/                    # Base test class
│   │   └── BaseTest.java         # Common test setup and utilities
│   └── utils/                    # Utility classes
│       ├── WebDriverUtils.java   # Main utility facade
│       ├── ElementUtils.java     # Element operations
│       ├── PageUtils.java        # Page-level operations
│       ├── OverlayUtils.java     # Overlay handling
│       ├── DropdownUtils.java    # Dropdown operations
│       ├── ConfigReader.java     # Configuration management
│       └── ExtentReportUtils.java # Reporting utilities
└── test/java/com/insider/tests/
    └── InsiderWebsiteTest.java   # Test implementation
```

## 📋 Prerequisites

- **Java 11+** (Java 17 recommended for optimal performance)
- **Maven 3.6+** (for dependency management and build)
- **Chrome Browser** (WebDriverManager handles driver setup automatically)
- **IDE** (IntelliJ IDEA, Eclipse, or VS Code recommended)

## 🏗️ Framework Design Patterns

### Page Object Model (POM)
- **Clean Separation**: Page logic separated from test logic
- **Reusability**: Page objects can be reused across multiple tests
- **Maintainability**: Easy to update when UI changes
- **Readability**: Tests are more readable and business-focused

### Utility Pattern
- **WebDriverUtils**: Main facade for all WebDriver operations
- **ElementUtils**: Common element operations (click, scroll, hover, find with fallback)
- **PageUtils**: Page-level operations (wait for load, document ready, AJAX completion)
- **OverlayUtils**: Overlay and popup handling operations
- **DropdownUtils**: Dropdown and select2 component operations
- **ConfigReader**: Configuration properties management
- **ExtentReportUtils**: ExtentReports integration and report generation

### Configuration Management
- **Centralized Settings**: All configuration in `test.properties`
- **Environment Support**: Easy switching between test environments
- **Data-Driven Testing**: Support for external test data sources

## 🛠️ Setup and Installation

### 1. Clone the Repository
```bash
git clone https://github.com/MerveAcr/merve_acar_case_testNG.git
cd merve_acar_case_testNG
```

### 2. Install Dependencies
```bash
mvn clean install
```

### 3. Verify Installation
```bash
mvn test -Dtest=InsiderWebsiteTest#testHomePageOpened
```

## 🚀 Framework Usage

### Running Tests

#### Run All Tests
```bash
mvn test
```

#### Run Specific Test
```bash
mvn test -Dtest=InsiderWebsiteTest#testHomePageOpened
```

#### Run Using TestNG XML
```bash
mvn test -DsuiteXmlFile=testng.xml
```

#### Run Using Provided Scripts
```bash
# Windows
run-tests-with-extent.bat
# or
setup-and-run.bat

# Linux/Mac
./run-tests-with-extent.sh
```

### Framework Configuration

The framework is configured to:
- Use Chrome browser (automatically managed by WebDriverManager)
- Run in maximized window mode
- Use 10-second timeouts for element waits
- Execute tests in priority order (1-3)
- Generate detailed ExtentReports with screenshots
- Support both ExtentReports and Surefire reporting

## 📊 Reporting and Results

### ExtentReports
- **Location**: `test-output/ExtentReports/Insider Test Automation Report.html`
- **Features**: Interactive HTML report with screenshots and detailed test steps
- **Auto-opening**: Scripts automatically open reports after execution

### Surefire Reports
- **Location**: `target/surefire-reports/`
- **Features**: Standard TestNG reports with execution summary and detailed logs

### Opening Reports
- **Windows**: Run `run-tests-with-extent.bat` to automatically open the ExtentReports
- **Linux/Mac**: Run `./run-tests-with-extent.sh` to automatically open the ExtentReports

## 🔧 Framework Customization

### Adding New Page Objects
1. Create new class in `src/main/java/com/insider/pages/`
2. Extend common functionality from existing pages
3. Add page-specific locators and methods
4. Update test classes to use new page objects

### Adding New Utility Methods
1. Add methods to appropriate utility class
2. Add corresponding facade method in `WebDriverUtils`
3. Update documentation and add JavaDoc comments

### Configuration Management
1. Add new properties to `test.properties`
2. Add getter methods to `ConfigReader`
3. Use in page objects and test classes

## 🐛 Troubleshooting

### Common Issues

1. **ChromeDriver Issues**: WebDriverManager automatically handles driver setup
2. **Element Not Found**: Check if selectors need updating due to website changes
3. **Timeout Issues**: Increase wait time in BaseTest class if needed
4. **Memory Issues**: Increase JVM heap size: `-Xmx2g`

### Debug Mode
Add `-Dmaven.surefire.debug` to run tests in debug mode:
```bash
mvn test -Dmaven.surefire.debug
```

### Logging
- **Console Output**: Basic test execution logs
- **ExtentReports**: Detailed step-by-step logs with screenshots
- **Surefire Reports**: Technical execution details

## 🤝 Contributing

### Code Standards
1. Follow existing code structure and naming conventions
2. Add proper JavaDoc comments for new methods
3. Update this README for any new framework features
4. Ensure all tests pass before submitting changes

### Adding New Features
1. Create feature branch from main
2. Implement changes with proper documentation
3. Add/update tests for new functionality
4. Submit pull request with detailed description

## 📚 Framework Documentation

- **API Documentation**: JavaDoc comments in all classes
- **Test Examples**: See `InsiderWebsiteTest.java` for usage examples
- **Configuration Guide**: See `test.properties` for all available settings
- **Reporting Guide**: See ExtentReports documentation for advanced features

## 🔄 Framework Maintenance

### Regular Updates
- Keep Selenium WebDriver updated to latest stable version
- Update TestNG to latest version for new features
- Review and update utility methods as needed
- Update documentation for any changes

### Performance Optimization
- Monitor test execution times
- Optimize wait strategies
- Review and clean up unused code
- Update browser configurations as needed