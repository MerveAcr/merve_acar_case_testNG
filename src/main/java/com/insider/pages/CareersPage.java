package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.util.List;

public class CareersPage {
    private WebDriver driver;
    private WebDriverWait wait;
    
    // Locators
    private By locationsSection = By.id("career-our-location");
    private By teamsSection = By.id("career-find-our-calling");
    private By lifeAtInsiderSection = By.xpath("//section//h2[normalize-space()='Life at Insider']");
    
    /**
     * Constructor for CareersPage
     * @param driver - WebDriver instance
     * @param wait - WebDriverWait instance
     */
    public CareersPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    
    /**
     * Check if the careers page is opened successfully
     * @return boolean - true if careers page is opened, false otherwise
     */
    public boolean isCareersPageOpened() {
        try {
            return driver.getCurrentUrl().contains("careers") && 
                   driver.getTitle().toLowerCase().contains("career");
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if the Locations section is visible on the careers page
     * @return boolean - true if Locations section is visible, false otherwise
     */
    public boolean isLocationsSectionVisible() {
        wait.until(ExpectedConditions.presenceOfElementLocated(locationsSection));
        return driver.findElement(locationsSection).isDisplayed();
    }
    
    /**
     * Check if the Teams section is visible on the careers page
     * @return boolean - true if Teams section is visible, false otherwise
     */
    public boolean isTeamsSectionVisible() {
        wait.until(ExpectedConditions.presenceOfElementLocated(teamsSection));
        return driver.findElement(teamsSection).isDisplayed();
    }
    
    /**
     * Check if the Life at Insider section is visible on the careers page
     * @return boolean - true if Life at Insider section is visible, false otherwise
     */
    public boolean isLifeAtInsiderSectionVisible() {
        wait.until(ExpectedConditions.presenceOfElementLocated(lifeAtInsiderSection));
        return driver.findElement(lifeAtInsiderSection).isDisplayed();
    }
}
