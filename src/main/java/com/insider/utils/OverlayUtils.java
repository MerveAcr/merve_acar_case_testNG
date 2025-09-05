package com.insider.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import com.insider.utils.ElementUtils;

/**
 * Utility class for overlay and popup operations
 */
public class OverlayUtils {
    
    /**
     * Close all overlays and popups using JavaScript
     * @param driver - WebDriver instance
     */
    public static void closeOverlays(WebDriver driver) {
        try {
            ((JavascriptExecutor) driver).executeScript(
                "var overlays = document.querySelectorAll('.cli-wrapper, .cli-bar-message, .cookie-banner, .overlay, [class*=\"cli-\"], [class*=\"cookie\"]'); " +
                "overlays.forEach(function(overlay) { " +
                "  overlay.style.display = 'none'; " +
                "  overlay.remove(); " +
                "});"
            );
        } catch (Exception e) {
            // Silently handle overlay closing failures
        }
    }
    
    /**
     * Close overlays and try to click Accept All button
     * @param driver - WebDriver instance
     */
    public static void closeOverlaysWithAcceptButton(WebDriver driver) {
        closeOverlays(driver);
        
        // Also try to find and click the "Accept All" button if it exists
        try {
            WebElement acceptAllBtn = driver.findElement(By.cssSelector("a#wt-cli-accept-all-btn, .wt-cli-accept-all-btn, [class*='accept-all'], [class*='Accept All']"));
            if (acceptAllBtn.isDisplayed()) {
                ElementUtils.clickWithJavaScript(driver, acceptAllBtn);
                System.out.println("Accept All button clicked");
            }
        } catch (Exception e) {
            System.out.println("Accept All button not found or not clickable: " + e.getMessage());
        }
        
        System.out.println("Overlays closed");
    }
}
