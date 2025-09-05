package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import com.insider.utils.ConfigReader;
import com.insider.utils.WebDriverUtils;
import java.util.List;

public class QAJobsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By seeAllQAJobsLocator = By.xpath("//a[contains(text(), 'See all QA jobs')]");
    private By locationFilter = By.id("select2-filter-by-location-container");
    private By departmentFilter = By.id("select2-filter-by-department-container");
    private By jobList = By.id("jobs-list");
    private By jobPosition = By.cssSelector("#jobs-list .position-list-item .position-title");
    private By jobDepartment = By.cssSelector("#jobs-list .position-list-item .position-department");
    private By jobLocation = By.cssSelector("#jobs-list .position-list-item .position-location");
    private By viewRoleButton = By.xpath("//a[normalize-space()='View Role']");
    
    // Dropdown and option locators
    private By select2Dropdown = By.className("select2-dropdown");
    private By departmentOptionClass = By.cssSelector(".job-team");
    private By jobLocationClass = By.cssSelector(".job-location");
    private By anyLinkInJob = By.xpath(".//a[contains(@href, 'jobs.lever.co/useinsider')]");

    // ================================
    // CONSTRUCTOR
    // ================================
    
    /**
     * Constructor for QAJobsPage
     * @param driver - WebDriver instance
     * @param wait - WebDriverWait instance
     */
    public QAJobsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }

    // ================================
    // WEBELEMENT RETURN METHODS
    // ================================
    
    /**
     * Find department option in dropdown
     * @param department - Department name to find
     * @return WebElement - Found department option
     */
    private WebElement findDepartmentOption(String department) {
        return WebDriverUtils.findDropdownOption(wait, department);
    }
    
    /**
     * Find location option in dropdown
     * @param location - Location name to find
     * @return WebElement - Found location option
     */
    private WebElement findLocationOption(String location) {
        return WebDriverUtils.findDropdownOption(wait, location);
    }
    
    /**
     * Get all job elements from the job list
     * @return List<WebElement> - List of job elements
     */
    public List<WebElement> getJobElements() {
        scrollToJobList();
        return driver.findElements(jobList);
    }

    // ================================
    // ACTION METHODS
    // ================================
    
    /**
     * Navigate to the QA jobs page
     */
    public void navigateToQAJobsPage() {
        driver.get("https://useinsider.com/careers/quality-assurance/");
    }

    /**
     * Click on the "See all QA jobs" button
     */
    public void clickSeeAllQAJobs() {
        // Close any overlays that might block the click
        WebDriverUtils.closeOverlaysWithAcceptButton(driver);
        
        WebElement seeAllQAJobsElement = wait.until(ExpectedConditions.elementToBeClickable(seeAllQAJobsLocator));
        seeAllQAJobsElement.click();
    }
    
    /**
     * Apply location filter to the job list
     */
    public void filterByLocation() {
        try {
            String location = ConfigReader.getLocationFilter();
            System.out.println("Applying location filter: " + location);
            
            // close Overlay
            WebDriverUtils.closeOverlaysWithAcceptButton(driver);
            
            // Wait for the page and filters to load
            waitForPageAndFiltersToLoad();
            
            // Click on Location filter
            clickLocationFilter();
            
            //Wait for the dropdown to open
            wait.until(ExpectedConditions.presenceOfElementLocated(select2Dropdown));
            
            //Wait for the options to load and select
            waitForLocationOptionsAndSelect(location);
            
            System.out.println("Location filter applied successfully: " + location);
            
            // Wait for job positions to load after filtering
            waitForJobPositionsToLoad();
            
        } catch (Exception e) {
            System.out.println("Location filter failed: " + e.getMessage());
            throw new RuntimeException("Failed to apply location filter", e);
        }
    }
    
    /**
     * Apply department filter to the job list
     */
    public void filterByDepartment() {
        try {
            String department = ConfigReader.getDepartmentFilter();
            System.out.println("Applying department filter: " + department);
            
            // Close any overlays that might block interaction
            WebDriverUtils.closeOverlaysWithAcceptButton(driver);
            
            // Wait for page and filters to load
            waitForPageAndFiltersToLoad();
            
            // Click on Department filter
            clickDepartmentFilter();
            
            // Wait for the dropdown to open
            wait.until(ExpectedConditions.presenceOfElementLocated(select2Dropdown));
            
            // Wait for options to load and select
            waitForDepartmentOptionsAndSelect(department);
            
            System.out.println("Department filter applied successfully: " + department);
            
            // Wait for job positions to load after filtering
            waitForJobPositionsToLoad();
            
        } catch (Exception e) {
            System.out.println("Department filter failed: " + e.getMessage());
            throw new RuntimeException("Failed to apply department filter", e);
        }
    }
    
    /**
     * Scroll down to make the job list visible
     */
    public void scrollToJobList() {
        // Scroll down to make job list visible
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 500);");
        
        // Wait for job list to be visible instead of hard sleep
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(jobList));
        } catch (Exception e) {
            System.out.println("Job list not found after scrolling: " + e.getMessage());
        }
    }
    
    /**
     * Click on the View Role button for a specific job element
     * @param jobElement - The job element containing the View Role button
     */
    public void clickViewRoleButton(WebElement jobElement) {
        try {
            // 1st Attempt: Look for specific "View Role" button
            WebElement viewRoleBtn = jobElement.findElement(viewRoleButton);
            WebDriverUtils.clickElementWithFallback(driver, viewRoleBtn, "View Role button");
        } catch (Exception e) {
            try {
                // 2nd Attempt: Look for any link within the job element
                WebElement anyLink = jobElement.findElement(anyLinkInJob);
                WebDriverUtils.clickElementWithFallback(driver, anyLink, "Any link in job");
            } catch (Exception e2) {
                // 3rd Attempt: Click the entire job element itself
                WebDriverUtils.clickElementWithFallback(driver, jobElement, "Job element");
            }
        }
    }

    // ================================
    // VERIFICATION METHODS
    // ================================
    
    /**
     * Check if the QA jobs page is opened successfully
     * @return boolean - true if QA jobs page is opened, false otherwise
     */
    public boolean isQAJobsPageOpened() {
        try {
            String currentUrl = driver.getCurrentUrl().toLowerCase();
            return currentUrl.contains("qualityassurance") || 
                   currentUrl.contains("qa") ||
                   currentUrl.contains("careers") ||
                   currentUrl.contains("jobs");
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if the jobs list is present on the page
     * @return boolean - true if jobs list is present, false otherwise
     */
    public boolean isJobsListPresent() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(jobList));
            List<WebElement> jobs = driver.findElements(jobList);
            return !jobs.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Validate job details against expected criteria
     * @param jobElement - The job element to validate
     * @param expectedPosition - Expected position text
     * @param expectedDepartment - Expected department text
     * @param expectedLocation - Expected location text
     * @return boolean - true if all criteria match, false otherwise
     */
    public boolean validateJobDetails(WebElement jobElement, String expectedPosition, String expectedDepartment, String expectedLocation) {
        try {
            // Get job text content
            String jobText = jobElement.getText().toLowerCase();
            
            // Initialize validation flags
            boolean hasPosition = true; // Default to true if not checking position
            boolean hasDepartment = true; // Default to true if not checking department
            boolean hasLocation = true; // Default to true if not checking location
            
            // Check position if expectedPosition is not empty
            if (!expectedPosition.isEmpty()) {
                hasPosition = jobText.contains(expectedPosition.toLowerCase());
                
                // If not found in overall text, try to find specific position element
                if (!hasPosition) {
                    try {
                        String position = jobElement.findElement(jobPosition).getText().toLowerCase();
                        hasPosition = position.contains(expectedPosition.toLowerCase());
                    } catch (Exception e) {
                        // Position element not found, use text content check result
                    }
                }
            }
            
            // Check department if expectedDepartment is not empty
            if (!expectedDepartment.isEmpty()) {
                hasDepartment = jobText.contains(expectedDepartment.toLowerCase());
                
                // If not found in overall text, try to find specific department element
                if (!hasDepartment) {
                    try {
                        String department = jobElement.findElement(jobDepartment).getText().toLowerCase();
                        hasDepartment = department.contains(expectedDepartment.toLowerCase());
                    } catch (Exception e) {
                        // Department element not found, use text content check result
                    }
                }
            }
            
            // Check location if expectedLocation is not empty
            if (!expectedLocation.isEmpty()) {
                hasLocation = jobText.contains(expectedLocation.toLowerCase());
                
                // If not found in overall text, try to find specific location element
                if (!hasLocation) {
                    try {
                        String location = jobElement.findElement(jobLocation).getText().toLowerCase();
                        hasLocation = location.contains(expectedLocation.toLowerCase());
                    } catch (Exception e) {
                        // Location element not found, use text content check result
                    }
                }
            }
            
            return hasPosition && hasDepartment && hasLocation;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if redirected to Lever application form
     * @return boolean - true if redirected to Lever domain, false otherwise
     */
    public boolean isRedirectedToLeverApplication() {
        try {
            // Check if we're on a Lever domain
            String currentUrl = driver.getCurrentUrl();
            boolean isLeverDomain = currentUrl.contains("jobs.lever.co");
            
            if (!isLeverDomain) {
                System.out.println("Not on Lever domain. Current URL: " + currentUrl);
                return false;
            } else {
                // We're on Lever domain - that's sufficient proof of successful redirect
                System.out.println("Successfully redirected to Lever domain: " + currentUrl);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error checking Lever application redirect: " + e.getMessage());
            return false;
        }
    }

    // ================================
    // PRIVATE HELPER METHODS
    // ================================
    
    /**
     * Wait for page and filters to load completely
     */
    private void waitForPageAndFiltersToLoad() {
        try {
            System.out.println("Waiting for page and filters to load...");
            
            // Wait for document ready state
            WebDriverUtils.waitForDocumentReady(driver);
            
            // Wait for filter elements to be present and clickable
            wait.until(ExpectedConditions.presenceOfElementLocated(locationFilter));
            wait.until(ExpectedConditions.elementToBeClickable(locationFilter));
            wait.until(ExpectedConditions.presenceOfElementLocated(departmentFilter));
            wait.until(ExpectedConditions.elementToBeClickable(departmentFilter));
            
            // Initialize select2 components
            ((JavascriptExecutor) driver).executeScript(
                "if (typeof $ !== 'undefined' && $.fn.select2) { " +
                "  $('#filter-by-location').select2('destroy').select2(); " +
                "  $('#filter-by-department').select2('destroy').select2(); " +
                "}"
            );
            
            // Wait for select2 to be initialized
            wait.until(ExpectedConditions.presenceOfElementLocated(select2Dropdown));
            
            System.out.println("Page and filters loaded successfully");
            
        } catch (Exception e) {
            System.out.println("Element-based wait failed, using fallback: " + e.getMessage());
            // Fallback to shorter sleep if element-based waiting fails
            try {
                Thread.sleep(3000);
            } catch (InterruptedException ie) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted while waiting for page to load", ie);
            }
        }
    }
    
    /**
     * Click on the location filter dropdown
     */
    private void clickLocationFilter() {
        WebDriverUtils.scrollToElementAndClick(driver, wait, locationFilter, "Location filter");
    }
    
    /**
     * Wait for location options to load and select the specified location
     * @param location - Location to select
     */
    private void waitForLocationOptionsAndSelect(String location) {
        try {
            System.out.println("Waiting for location options to load...");
            
            // Wait for options to load using WebDriverWait
            WebDriverUtils.waitForDropdownOptions(wait, jobLocationClass, "Location");
            
            // Location option to select
            WebElement locationOption = findLocationOption(location);
            if (locationOption != null) {
                locationOption.click();
                System.out.println("Location option selected: " + location);
            } else {
                throw new RuntimeException("Could not find location option: " + location);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load location options: " + e.getMessage(), e);
        }
    }
    
    /**
     * Click on the department filter dropdown
     */
    private void clickDepartmentFilter() {
        WebDriverUtils.scrollToElementAndClick(driver, wait, departmentFilter, "Department filter");
    }
    
    /**
     * Wait for department options to load and select the specified department
     * @param department - Department to select
     */
    private void waitForDepartmentOptionsAndSelect(String department) {
        try {
            System.out.println("Waiting for department options to load...");
            
            // Wait for options to load using WebDriverWait
            WebDriverUtils.waitForDropdownOptions(wait, departmentOptionClass, "Department");
            
            // Select the department option
            WebElement departmentOption = findDepartmentOption(department);
            if (departmentOption != null) {
                departmentOption.click();
                System.out.println("Department option selected: " + department);
            } else {
                throw new RuntimeException("Could not find department option: " + department);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to load department options: " + e.getMessage(), e);
        }
    }
    
    /**
     * Wait for job positions to load after applying filters
     */
    private void waitForJobPositionsToLoad() {
        try {
            System.out.println("Waiting for job positions to load after filtering...");
            
            // Wait for job positions to be present and visible
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".job-list, .position-item, [class*='job'], [class*='position']")));
            
            // Additional wait to ensure all positions are loaded
            Thread.sleep(2000);
            
            System.out.println("Job positions loaded successfully");
            
        } catch (Exception e) {
            System.out.println("Warning: Job positions may not have loaded completely: " + e.getMessage());
            // Don't throw exception, just log warning as this might be expected in some cases
        }
    }
}