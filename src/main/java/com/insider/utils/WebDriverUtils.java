package com.insider.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Main utility class that delegates to specialized utility classes
 * This serves as a facade for all WebDriver-related operations
 */
public class WebDriverUtils {
    
    // ================================
    // ELEMENT OPERATIONS
    // ================================
    
    public static void scrollToElement(WebDriver driver, WebElement element) {
        ElementUtils.scrollToElement(driver, element);
    }
    
    public static void clickElementWithFallback(WebDriver driver, WebElement element, String elementName) {
        ElementUtils.clickElementWithFallback(driver, element, elementName);
    }
    
    public static void scrollToElementAndClick(WebDriver driver, WebDriverWait wait, By locator, String elementName) {
        ElementUtils.scrollToElementAndClick(driver, wait, locator, elementName);
    }
    
    public static void clickWithJavaScript(WebDriver driver, WebElement element) {
        ElementUtils.clickWithJavaScript(driver, element);
    }
    
    public static void hoverOverElement(WebDriver driver, WebElement element) {
        ElementUtils.hoverOverElement(driver, element);
    }
    
    public static WebElement findElementWithFallback(WebDriverWait wait, By primaryLocator, By textLocator, By hrefLocator, String elementName) {
        return ElementUtils.findElementWithFallback(wait, primaryLocator, textLocator, hrefLocator, elementName);
    }
    
    // ================================
    // PAGE OPERATIONS
    // ================================
    
    public static void waitForPageLoad(WebDriver driver) {
        PageUtils.waitForPageLoad(driver);
    }
    
    public static void waitForDocumentReady(WebDriver driver) {
        PageUtils.waitForDocumentReady(driver);
    }
    
    public static void waitForAjaxToComplete(WebDriver driver) {
        PageUtils.waitForAjaxToComplete(driver);
    }
    
    // ================================
    // OVERLAY OPERATIONS
    // ================================
    
    public static void closeOverlays(WebDriver driver) {
        OverlayUtils.closeOverlays(driver);
    }
    
    public static void closeOverlaysWithAcceptButton(WebDriver driver) {
        OverlayUtils.closeOverlaysWithAcceptButton(driver);
    }
    
    // ================================
    // DROPDOWN OPERATIONS
    // ================================
    
    public static WebElement findDropdownOption(WebDriverWait wait, String optionText) {
        return DropdownUtils.findDropdownOption(wait, optionText);
    }
    
    public static void waitForDropdownOptions(WebDriverWait wait, By optionLocator, String optionName) {
        DropdownUtils.waitForDropdownOptions(wait, optionLocator, optionName);
    }
}