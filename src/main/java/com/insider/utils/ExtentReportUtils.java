package com.insider.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Utility class for ExtentReports functionality
 */
public class ExtentReportUtils {
    
    private static ExtentReports extent;
    private static ExtentTest test;
    private static String reportPath;
    
    /**
     * Initialize ExtentReports
     * @param reportName - Name for the report
     * @return ExtentReports instance
     */
    public static ExtentReports initializeExtentReport(String reportName) {
        if (extent == null) {
            // Clean old reports before creating new one
            cleanOldReports();
            
            // Use fixed filename to keep only recent result
            reportPath = "test-output/ExtentReports/" + reportName + ".html";
            
            // Create ExtentSparkReporter
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
            
            // Configure the report
            sparkReporter.config().setDocumentTitle("Insider Test Automation Report");
            sparkReporter.config().setReportName(reportName);
            sparkReporter.config().setTheme(Theme.STANDARD);
            sparkReporter.config().setTimeStampFormat("MMM dd, yyyy HH:mm:ss");
            
            // Create ExtentReports instance
            extent = new ExtentReports();
            extent.attachReporter(sparkReporter);
            
            // Set system information
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("User", System.getProperty("user.name"));
            extent.setSystemInfo("Project", "Insider Test Automation");
        }
        return extent;
    }
    
    /**
     * Clean old ExtentReports files to keep only recent results
     */
    private static void cleanOldReports() {
        try {
            File reportsDir = new File("test-output/ExtentReports/");
            if (reportsDir.exists() && reportsDir.isDirectory()) {
                File[] files = reportsDir.listFiles((dir, name) -> name.endsWith(".html"));
                if (files != null) {
                    for (File file : files) {
                        if (file.delete()) {
                            System.out.println("Deleted old report: " + file.getName());
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Warning: Could not clean old reports: " + e.getMessage());
        }
    }
    
    /**
     * Create a new test
     * @param testName - Name of the test
     * @param testDescription - Description of the test
     * @return ExtentTest instance
     */
    public static ExtentTest createTest(String testName, String testDescription) {
        test = extent.createTest(testName, testDescription);
        return test;
    }
    
    /**
     * Create a new test with category
     * @param testName - Name of the test
     * @param testDescription - Description of the test
     * @param category - Category of the test
     * @return ExtentTest instance
     */
    public static ExtentTest createTest(String testName, String testDescription, String category) {
        test = extent.createTest(testName, testDescription).assignCategory(category);
        return test;
    }
    
    
    /**
     * Log an info step
     * @param stepName - Name of the step
     */
    public static void logInfo(String stepName) {
        if (test != null) {
            test.log(Status.INFO, stepName);
        }
    }
    
    /**
     * Log a pass step
     * @param stepName - Name of the step
     */
    public static void logPass(String stepName) {
        if (test != null) {
            test.log(Status.PASS, stepName);
        }
    }
    
    /**
     * Log a fail step
     * @param stepName - Name of the step
     */
    public static void logFail(String stepName) {
        if (test != null) {
            test.log(Status.FAIL, stepName);
        }
    }
    
    
    /**
     * Take screenshot and attach to report
     * @param driver - WebDriver instance
     * @param screenshotName - Name for the screenshot
     */
    public static void takeScreenshot(WebDriver driver, String screenshotName) {
        if (test != null && driver != null) {
            try {
                TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
                String screenshotPath = takesScreenshot.getScreenshotAs(OutputType.BASE64);
                test.addScreenCaptureFromBase64String(screenshotPath, screenshotName);
            } catch (Exception e) {
                if (test != null) {
                    test.log(Status.WARNING, "Failed to take screenshot: " + e.getMessage());
                }
            }
        }
    }
    
    /**
     * Take screenshot and attach to report with step
     * @param driver - WebDriver instance
     * @param stepName - Name of the step
     * @param screenshotName - Name for the screenshot
     */
    public static void takeScreenshotWithStep(WebDriver driver, String stepName, String screenshotName) {
        logInfo(stepName);
        takeScreenshot(driver, screenshotName);
    }
    
    
    /**
     * Add URL to report
     * @param driver - WebDriver instance
     * @param urlName - Name for the URL
     */
    public static void addUrl(WebDriver driver, String urlName) {
        if (test != null && driver != null) {
            try {
                String currentUrl = driver.getCurrentUrl();
                test.info(MarkupHelper.createLabel("Current URL: " + currentUrl, ExtentColor.BLUE));
            } catch (Exception e) {
                if (test != null) {
                    test.log(Status.WARNING, "Failed to add URL: " + e.getMessage());
                }
            }
        }
    }
    
    
    /**
     * Add exception details to report
     * @param exception - Exception to add
     */
    public static void addException(Throwable exception) {
        if (test != null) {
            test.fail(exception);
        }
    }
    
    /**
     * Flush the report
     */
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }
    
    /**
     * Manually clean all old ExtentReports files
     * Call this method if you want to clean reports manually
     */
    public static void cleanAllReports() {
        cleanOldReports();
        System.out.println("All old ExtentReports have been cleaned.");
    }
}
