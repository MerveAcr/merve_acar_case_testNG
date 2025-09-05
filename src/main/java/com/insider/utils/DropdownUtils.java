package com.insider.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Utility class for dropdown operations
 */
public class DropdownUtils {
    
    /**
     * Find a dropdown option by text
     * @param wait - WebDriverWait instance
     * @param optionText - Text of the option to find
     * @return WebElement - Found option element
     */
    public static WebElement findDropdownOption(WebDriverWait wait, String optionText) {
        return wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[normalize-space(text())='" + optionText + "']")));
    }
    
    /**
     * Wait for dropdown options to load
     * @param wait - WebDriverWait instance
     * @param optionLocator - Locator for the options
     * @param optionName - Name of the options for logging
     */
    public static void waitForDropdownOptions(WebDriverWait wait, By optionLocator, String optionName) {
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(optionLocator));
            System.out.println(optionName + " options loaded successfully");
        } catch (Exception e) {
            System.out.println(optionName + " options not found, trying alternative approach...");
            wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//li[contains(@class, 'select2-results__option')]")));
        }
    }
}
