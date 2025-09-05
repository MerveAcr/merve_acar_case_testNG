package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.insider.utils.WebDriverUtils;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By insiderLogo = By.xpath("//img[@alt='insider_logo']");
    
    /**
     * Constructor for HomePage
     * @param driver - WebDriver instance
     * @param wait - WebDriverWait instance
     */
    public HomePage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    
    /**
     * Navigate to the Insider home page
     */
    public void navigateToHomePage() {
        driver.get("https://useinsider.com/");
    }
    
    /**
     * Check if the home page is opened successfully
     * @return boolean - true if home page is opened, false otherwise
     */
    public boolean isHomePageOpened() {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(insiderLogo));
            return driver.getCurrentUrl().contains("useinsider.com") && 
                   driver.getTitle().toLowerCase().contains("insider");
        } catch (Exception e) {
            return false;
        }
    }
    
    
}
