package com.insider.tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.insider.utils.ExtentReportUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static ExtentReports extent;
    protected ExtentTest test;
    
    @BeforeSuite
    public void setUpSuite() {
        // Initialize ExtentReports
        extent = ExtentReportUtils.initializeExtentReport("Insider Test Automation Report");
    }
    
    @BeforeMethod
    public void setUp() {
        // Setup ChromeDriver
        WebDriverManager.chromedriver().setup();
        
        // Configure Chrome options
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        
        // Initialize WebDriver
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @AfterSuite
    public void tearDownSuite() {
        // Flush ExtentReports
        ExtentReportUtils.flushReport();
    }
    
    // ================================
    // COMMON EXTENTREPORTS METHODS
    // ================================
    
    /**
     * Start a new test with ExtentReports
     * @param testName - Name of the test
     * @param testDescription - Description of the test
     * @param category - Category of the test (e.g., "Smoke Test", "Regression Test")
     */
    protected void startTest(String testName, String testDescription, String category) {
        test = ExtentReportUtils.createTest(testName, testDescription, category);
    }
    
    /**
     * Start a new test with ExtentReports (without category)
     * @param testName - Name of the test
     * @param testDescription - Description of the test
     */
    protected void startTest(String testName, String testDescription) {
        test = ExtentReportUtils.createTest(testName, testDescription);
    }
    
    /**
     * Log an info step
     * @param stepName - Name of the step
     */
    protected void logInfo(String stepName) {
        ExtentReportUtils.logInfo(stepName);
    }
    
    /**
     * Log a pass step
     * @param stepName - Name of the step
     */
    protected void logPass(String stepName) {
        ExtentReportUtils.logPass(stepName);
    }
    
    /**
     * Log a fail step
     * @param stepName - Name of the step
     */
    protected void logFail(String stepName) {
        ExtentReportUtils.logFail(stepName);
    }
    
    /**
     * Take screenshot and attach to report
     * @param screenshotName - Name for the screenshot
     */
    protected void takeScreenshot(String screenshotName) {
        ExtentReportUtils.takeScreenshot(driver, screenshotName);
    }
    
    /**
     * Take screenshot and attach to report with step
     * @param stepName - Name of the step
     * @param screenshotName - Name for the screenshot
     */
    protected void takeScreenshotWithStep(String stepName, String screenshotName) {
        ExtentReportUtils.takeScreenshotWithStep(driver, stepName, screenshotName);
    }
    
    /**
     * Add URL to report
     * @param urlName - Name for the URL
     */
    protected void addUrl(String urlName) {
        ExtentReportUtils.addUrl(driver, urlName);
    }
    
    /**
     * Add exception details to report
     * @param exception - Exception to add
     */
    protected void addException(Throwable exception) {
        ExtentReportUtils.addException(exception);
    }
    
    
    /**
     * Execute a test step with automatic logging and screenshot
     * @param stepName - Name of the step
     * @param stepAction - Action to perform
     */
    protected void executeStep(String stepName, Runnable stepAction) {
        try {
            logInfo(stepName);
            stepAction.run();
            logPass(stepName + " completed successfully");
        } catch (Exception e) {
            logFail(stepName + " failed: " + e.getMessage());
            addException(e);
            takeScreenshot("Step failure: " + stepName);
            throw e;
        }
    }
    
    /**
     * Execute a test step with automatic logging, screenshot, and assertion
     * @param stepName - Name of the step
     * @param assertion - Assertion to perform
     * @param errorMessage - Error message if assertion fails
     */
    protected void executeAssertion(String stepName, Runnable assertion, String errorMessage) {
        try {
            logInfo(stepName);
            assertion.run();
            logPass(stepName + " assertion passed");
        } catch (AssertionError e) {
            logFail(stepName + " assertion failed: " + errorMessage);
            takeScreenshot("Assertion failure: " + stepName);
            throw e;
        } catch (Exception e) {
            logFail(stepName + " failed: " + e.getMessage());
            addException(e);
            takeScreenshot("Step failure: " + stepName);
            throw e;
        }
    }
}
