package com.insider.utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

/**
 * Utility class for page-level operations
 */
public class PageUtils {
    
    /**
     * Wait for page to load completely
     * @param driver - WebDriver instance
     */
    public static void waitForPageLoad(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));
    }
    
    /**
     * Wait for document ready state using JavaScript Promise
     * @param driver - WebDriver instance
     */
    public static void waitForDocumentReady(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript(
            "return new Promise((resolve) => { " +
            "  if (document.readyState === 'complete') { " +
            "    resolve(true); " +
            "  } else { " +
            "    window.addEventListener('load', () => resolve(true)); " +
            "  } " +
            "});"
        );
    }
    
    /**
     * Wait for AJAX requests to complete
     * @param driver - WebDriver instance
     */
    public static void waitForAjaxToComplete(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));
        wait.until(webDriver -> {
            JavascriptExecutor js = (JavascriptExecutor) webDriver;
            return (Boolean) js.executeScript("return jQuery.active == 0");
        });
    }
}
