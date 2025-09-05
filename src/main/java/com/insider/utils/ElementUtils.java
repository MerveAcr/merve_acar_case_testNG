package com.insider.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Utility class for common element operations
 */
public class ElementUtils {
    
    /**
     * Scroll to an element to make it visible
     * @param driver - WebDriver instance
     * @param element - Element to scroll to
     */
    public static void scrollToElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
    
    /**
     * Click an element with fallback to JavaScript click
     * @param driver - WebDriver instance
     * @param element - Element to click
     * @param elementName - Name of the element for logging
     */
    public static void clickElementWithFallback(WebDriver driver, WebElement element, String elementName) {
        try {
            element.click();
            System.out.println(elementName + " clicked");
        } catch (Exception e) {
            clickWithJavaScript(driver, element);
            System.out.println(elementName + " clicked with JavaScript");
        }
    }
    
    /**
     * Scroll to element and click it
     * @param driver - WebDriver instance
     * @param wait - WebDriverWait instance
     * @param locator - Locator for the element
     * @param elementName - Name of the element for logging
     */
    public static void scrollToElementAndClick(WebDriver driver, WebDriverWait wait, By locator, String elementName) {
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        scrollToElement(driver, element);
        clickElementWithFallback(driver, element, elementName);
    }
    
    /**
     * Click an element using JavaScript
     * @param driver - WebDriver instance
     * @param element - Element to click
     */
    public static void clickWithJavaScript(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
    
    /**
     * Hover over an element using JavaScript
     * @param driver - WebDriver instance
     * @param element - Element to hover over
     */
    public static void hoverOverElement(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].dispatchEvent(new Event('mouseover'));", element);
    }
    
    /**
     * Find element with multiple fallback locators
     * @param wait - WebDriverWait instance
     * @param primaryLocator - Primary locator to try first
     * @param textLocator - Text-based fallback locator
     * @param hrefLocator - Href-based fallback locator
     * @param elementName - Name of the element for logging
     * @return WebElement - Found element
     */
    public static WebElement findElementWithFallback(WebDriverWait wait, By primaryLocator, By textLocator, By hrefLocator, String elementName) {
        try {
            return wait.until(ExpectedConditions.elementToBeClickable(primaryLocator));
        } catch (Exception e1) {
            try {
                System.out.println(elementName + " found with text locator");
                return wait.until(ExpectedConditions.presenceOfElementLocated(textLocator));
            } catch (Exception e2) {
                System.out.println(elementName + " found with href locator");
                return wait.until(ExpectedConditions.presenceOfElementLocated(hrefLocator));
            }
        }
    }
}
