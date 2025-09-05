package com.insider.tests;

import com.insider.pages.HomePage;
import com.insider.pages.CareersPage;
import com.insider.pages.QAJobsPage;
import com.insider.pages.GlobalNavigationBar;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;

public class InsiderWebsiteTest extends BaseTest {
    
    @Test(priority = 1, description = "Test 1: Check if Insider home page is opened")
    public void testHomePageOpened() {
        // Start test with ExtentReports
        startTest("Home Page Test", "Verify that the Insider home page opens successfully", "Smoke Test");
        
        // Navigate to Insider home page
        executeStep("Navigating to Insider home page", () -> {
            HomePage homePage = new HomePage(driver, wait);
            homePage.navigateToHomePage();
            takeScreenshot("Home page loaded");
        });
        
        // Verify home page is opened
        executeAssertion("Verifying home page is opened", () -> {
            HomePage homePage = new HomePage(driver, wait);
            boolean isOpened = homePage.isHomePageOpened();
            Assert.assertTrue(isOpened, "Insider home page should be opened successfully");
        }, "Insider home page should be opened successfully");
        
        takeScreenshotWithStep("Home page verification", "Home page verification");
    }
    
    @Test(priority = 2, description = "Test 2: Navigate to Careers page and validate sections")
    public void testCareersPageNavigation() {
        // Start test with ExtentReports
        startTest("Careers Page Navigation Test", "Verify navigation to Careers page and validate all required sections are visible", "Regression Test");
        
        // Navigate to home page
        executeStep("Navigating to home page", () -> {
            HomePage homePage = new HomePage(driver, wait);
            homePage.navigateToHomePage();
            takeScreenshot("Home page loaded");
        });
        
        // Navigate to Careers page via Company menu
        executeStep("Navigating to Careers page via Company menu", () -> {
            GlobalNavigationBar navigationBar = new GlobalNavigationBar(driver, wait);
            navigationBar.navigateTo("Careers");
            takeScreenshot("Careers page loaded");
        });
        
        // Verify Careers page is opened
        executeAssertion("Verifying Careers page is opened", () -> {
            CareersPage careersPage = new CareersPage(driver, wait);
            boolean isOpened = careersPage.isCareersPageOpened();
            Assert.assertTrue(isOpened, "Careers page should be opened successfully");
        }, "Careers page should be opened successfully");
        
        // Verify all required sections are visible
        logInfo("Verifying all required sections are visible");
        
        executeAssertion("Verifying Locations section is visible", () -> {
            CareersPage careersPage = new CareersPage(driver, wait);
            boolean locationsVisible = careersPage.isLocationsSectionVisible();
            Assert.assertTrue(locationsVisible, "Locations section should be visible on Careers page");
        }, "Locations section should be visible on Careers page");
        
        executeAssertion("Verifying Teams section is visible", () -> {
            CareersPage careersPage = new CareersPage(driver, wait);
            boolean teamsVisible = careersPage.isTeamsSectionVisible();
            Assert.assertTrue(teamsVisible, "Teams section should be visible on Careers page");
        }, "Teams section should be visible on Careers page");
        
        executeAssertion("Verifying Life at Insider section is visible", () -> {
            CareersPage careersPage = new CareersPage(driver, wait);
            boolean lifeAtInsiderVisible = careersPage.isLifeAtInsiderSectionVisible();
            Assert.assertTrue(lifeAtInsiderVisible, "Life at Insider section should be visible on Careers page");
        }, "Life at Insider section should be visible on Careers page");
        
        takeScreenshotWithStep("All sections verified", "All sections verified");
        logPass("All sections verification completed successfully");
    }
    
    @Test(priority = 3, description = "Complete QA jobs workflow - filters, validation, and redirect")
    public void testCompleteQAJobsWorkflow() {
        // Start test with ExtentReports
        startTest("Complete QA Jobs Workflow Test", "Complete QA jobs workflow - filters, validation, and redirect", "End-to-End Test");
        
        QAJobsPage qaJobsPage = new QAJobsPage(driver, wait);
        
        // Step 1: Navigate to QA jobs page
        executeStep("Step 1: Navigating to QA jobs page", () -> {
            qaJobsPage.navigateToQAJobsPage();
            takeScreenshot("QA jobs page loaded");
        });
        
        // Step 2: Click "See all QA jobs" button
        executeStep("Step 2: Clicking 'See all QA jobs' button", () -> {
            qaJobsPage.clickSeeAllQAJobs();
            takeScreenshot("See all QA jobs clicked");
        });
        
        // Step 3: Verify QA jobs page is opened
        executeAssertion("Step 3: Verifying QA jobs page is opened", () -> {
            boolean isQAJobsPageOpened = qaJobsPage.isQAJobsPageOpened();
            Assert.assertTrue(isQAJobsPageOpened, "QA jobs page should be opened successfully");
        }, "QA jobs page should be opened successfully");
        
        // Step 4: Apply location filter
        executeStep("Step 4: Applying location filter", () -> {
            qaJobsPage.filterByLocation();
            takeScreenshot("Location filter applied");
        });
        
        // Step 5: Apply department filter
        executeStep("Step 5: Applying department filter", () -> {
            qaJobsPage.filterByDepartment();
            takeScreenshot("Department filter applied");
        });
        
        // Step 6: Scroll down to make job list visible
        executeStep("Step 6: Scrolling down to make job list visible", () -> {
            qaJobsPage.scrollToJobList();
            takeScreenshot("Scrolled to job list");
        });
        
        // Step 7: Verify jobs list is present
        executeAssertion("Step 7: Verifying jobs list is present", () -> {
            boolean isJobsListPresent = qaJobsPage.isJobsListPresent();
            Assert.assertTrue(isJobsListPresent, "Jobs list should be present after applying filters");
        }, "Jobs list should be present after applying filters");
        
        // Step 8: Get all job elements for validation
        executeStep("Step 8: Getting job elements for validation", () -> {
            List<WebElement> jobElements = qaJobsPage.getJobElements();
            logInfo("Found " + jobElements.size() + " job elements");
        });
        
        // Step 9: Validate that we have at least one job
        executeAssertion("Step 9: Validating job presence", () -> {
            List<WebElement> jobElements = qaJobsPage.getJobElements();
            Assert.assertFalse(jobElements.isEmpty(), "At least one job should be present");
        }, "At least one job should be present");
        
        // Step 10: Validate each job meets the criteria
        executeStep("Step 10: Validating job details", () -> {
            List<WebElement> jobElements = qaJobsPage.getJobElements();
            for (int i = 0; i < jobElements.size(); i++) {
                WebElement job = jobElements.get(i);
                String jobText = job.getText();
                logInfo("Job " + (i + 1) + " text: " + jobText);
                
                // Validate that Position contains "Quality Assurance"
                boolean hasQualityAssuranceInPosition = qaJobsPage.validateJobDetails(
                    job, 
                    "Quality Assurance", 
                    "", // Don't check department here
                    ""  // Don't check location here
                );
                
                // Validate that Department contains "Quality Assurance" 
                boolean hasQualityAssuranceInDepartment = qaJobsPage.validateJobDetails(
                    job, 
                    "", // Don't check position here
                    "Quality Assurance", 
                    ""  // Don't check location here
                );
                
                // Check if it's in Istanbul
                boolean isInIstanbul = jobText.toLowerCase().contains("istanbul") || 
                                     jobText.toLowerCase().contains("turkey") || 
                                     jobText.toLowerCase().contains("turkiye");
                
                // Assert specific requirements
                Assert.assertTrue(hasQualityAssuranceInPosition, 
                    "Job " + (i + 1) + " Position should contain 'Quality Assurance': " + jobText);
                
                Assert.assertTrue(hasQualityAssuranceInDepartment, 
                    "Job " + (i + 1) + " Department should contain 'Quality Assurance': " + jobText);
                
                Assert.assertTrue(isInIstanbul, 
                    "Job " + (i + 1) + " should be in Istanbul: " + jobText);
                
                logPass("Job " + (i + 1) + " validation passed - Position: ✓, Department: ✓, Location: ✓");
            }
        });
        
        // Step 11: Test View Role button redirect
        executeStep("Step 11: Testing View Role button redirect", () -> {
            List<WebElement> jobElements = qaJobsPage.getJobElements();
            
            // Store current URL before clicking
            addUrl("Original URL before redirect");
            
            // Click View Role button on first job
            qaJobsPage.clickViewRoleButton(jobElements.get(0));
            takeScreenshot("View Role button clicked");
            
            // Wait a bit for new tab to open
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Switch to new tab if opened
            String originalWindow = driver.getWindowHandle();
            for (String windowHandle : driver.getWindowHandles()) {
                if (!windowHandle.equals(originalWindow)) {
                    driver.switchTo().window(windowHandle);
                    logInfo("Switched to new tab: " + driver.getCurrentUrl());
                    break;
                }
            }
            
            // Verify redirect to Lever application form
            boolean isRedirected = qaJobsPage.isRedirectedToLeverApplication();
            Assert.assertTrue(isRedirected, "Should be redirected to Lever application form (jobs.lever.co domain)");
            logPass("Successfully redirected to Lever application form");
            takeScreenshot("Redirected to Lever application");
        });
        
        logPass("Complete QA jobs workflow test completed successfully!");
    }
}
