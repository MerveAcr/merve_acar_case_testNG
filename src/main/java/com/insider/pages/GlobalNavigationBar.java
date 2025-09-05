package com.insider.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.insider.utils.WebDriverUtils;
import com.insider.utils.ConfigReader;

public class GlobalNavigationBar {
    private WebDriver driver;
    private WebDriverWait wait;
    
    /**
     * Constructor for GlobalNavigationBar
     * @param driver - WebDriver instance
     * @param wait - WebDriverWait instance
     */
    public GlobalNavigationBar(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    
    // ================================
    // GENERIC NAVIGATION METHODS
    // ================================
    
    
    /**
     * Hovers over any main navigation tab to open dropdown
     * @param tabName - Name of the main tab
     */
    public void hoverOverMainTab(String tabName) {
        try {
            WebElement tabElement = findElementWithFallback(tabName, "main tab");
            WebDriverUtils.hoverOverElement(driver, tabElement);
            System.out.println("Successfully hovered over " + tabName + " tab");
        } catch (Exception e) {
            System.out.println("Failed to hover over " + tabName + " tab: " + e.getMessage());
            throw new RuntimeException("Failed to hover over " + tabName + " tab", e);
        }
    }
    
    /**
     * Clicks on any sub-menu item from any main tab
     * @param mainTabName - Name of the main tab (e.g., "Company")
     * @param subItemName - Name of the sub-menu item (e.g., "Careers", "About Us")
     */
    public void clickSubMenuItem(String mainTabName, String subItemName) {
        try {
            // First hover over the main tab to open dropdown
            hoverOverMainTab(mainTabName);
            
            // Wait for dropdown to appear
            wait.until(ExpectedConditions.presenceOfElementLocated(getSubMenuItemLocator(subItemName)));
            
            // Click the sub-menu item
            WebElement subItemElement = findElementWithFallback(subItemName, "sub-menu item");
            WebDriverUtils.clickElementWithFallback(driver, subItemElement, subItemName + " sub-menu item");
            
            System.out.println("Successfully clicked " + subItemName + " from " + mainTabName + " menu");
        } catch (Exception e) {
            System.out.println("Failed to click " + subItemName + " from " + mainTabName + " menu: " + e.getMessage());
            throw new RuntimeException("Failed to click " + subItemName + " from " + mainTabName + " menu", e);
        }
    }
    
    // ================================
    // CONVENIENCE METHODS
    // ================================
    
    /**
     * Navigate to any page using main tab and sub-menu item
     * @param mainTab - Main tab name (optional, if null will search all tabs)
     * @param subItem - Sub-menu item name
     */
    public void navigateTo(String mainTab, String subItem) {
        clickSubMenuItem(mainTab, subItem);
    }
    
    /**
     * Navigate to any page using just sub-menu item
     * This method will try to find the sub-item under any main tab
     * @param subItem - Sub-menu item name
     */
    public void navigateTo(String subItem) {
        // Try to find the sub-item under any main tab
        String[] mainTabs = getAvailableMainTabs();
        for (String mainTab : mainTabs) {
            try {
                clickSubMenuItem(mainTab, subItem);
                System.out.println("Successfully found " + subItem + " under " + mainTab + " tab");
                return;
            } catch (Exception e) {
                // Continue to next tab if this one doesn't have the sub-item
                System.out.println("Sub-item " + subItem + " not found under " + mainTab + " tab, trying next...");
            }
        }
        throw new RuntimeException("Sub-item '" + subItem + "' not found under any main tab");
    }
    
    /**
     * Navigate to any page using main tab and sub-menu item
     * This is the most flexible method - can handle any main tab and sub-item combination
     * @param mainTab - Main tab name
     * @param subItem - Sub-menu item name
     */
    public void navigateToSubItem(String mainTab, String subItem) {
        clickSubMenuItem(mainTab, subItem);
    }
    
    // ================================
    // HELPER METHODS
    // ================================
    
    /**
     * Generic method to find elements with fallback locators
     * @param elementName - Name of the element to find
     * @param elementType - Type of element (for logging)
     * @return WebElement found
     */
    private WebElement findElementWithFallback(String elementName, String elementType) {
        By primaryLocator = getPrimaryLocator(elementName);
        By textLocator = getTextLocator(elementName);
        By hrefLocator = getHrefLocator(elementName);
        
        return WebDriverUtils.findElementWithFallback(wait, primaryLocator, textLocator, hrefLocator, elementName + " " + elementType);
    }
    
    /**
     * Get primary locator using normalize-space()
     * @param elementName - Name of the element
     * @return By locator
     */
    private By getPrimaryLocator(String elementName) {
        return By.xpath("//a[normalize-space()='" + elementName + "']");
    }
    
    /**
     * Get text-based fallback locator using contains()
     * @param elementName - Name of the element
     * @return By locator
     */
    private By getTextLocator(String elementName) {
        return By.xpath("//a[contains(text(), '" + elementName + "')]");
    }
    
    /**
     * Get href-based fallback locator using contains()
     * @param elementName - Name of the element
     * @return By locator
     */
    private By getHrefLocator(String elementName) {
        String hrefValue = elementName.toLowerCase().replace(" ", "-");
        return By.xpath("//a[contains(@href, '" + hrefValue + "')]");
    }
    
    /**
     * Get locator for sub-menu items (used for waiting)
     * @param subItemName - Name of the sub-menu item
     * @return By locator
     */
    private By getSubMenuItemLocator(String subItemName) {
        return getPrimaryLocator(subItemName); // Use the same primary locator
    }
    
    // ================================
    // VALIDATION METHODS
    // ================================
    
    /**
     * Check if a main tab exists
     * @param tabName - Name of the tab to check
     * @return boolean - true if tab exists
     */
    public boolean isMainTabPresent(String tabName) {
        try {
            findElementWithFallback(tabName, "main tab");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Check if a sub-menu item exists
     * @param subItemName - Name of the sub-menu item to check
     * @return boolean - true if sub-menu item exists
     */
    public boolean isSubMenuItemPresent(String subItemName) {
        try {
            findElementWithFallback(subItemName, "sub-menu item");
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    
    /**
     * Get all available main tabs from properties
     * @return String array of main tab names
     */
    public String[] getAvailableMainTabs() {
        String tabs = ConfigReader.getProperty("navigation.main.tabs");
        return tabs.split(",");
    }
    
    /**
     * Get all available company sub-menu items from properties
     * @return String array of sub-menu item names
     */
    public String[] getAvailableSubMenuItems() {
        String items = ConfigReader.getProperty("navigation.company.sub.tabs");
        return items.split(",");
    }
}