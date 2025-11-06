package com.hotstar.automation.test;

import java.io.File;
import java.time.Duration;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestBase {
    protected WebDriver driver;
    
    public void startDriver() {
        System.out.println("🚀 Starting Chrome Driver...");
        
        try {
            // Simple approach - use system Chrome
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            System.out.println("✅ Chrome Driver started successfully");
            
        } catch (Exception e) {
            System.out.println("❌ Chrome Driver failed: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            System.out.println("🔚 Driver closed");
        }
    }
    
    public WebElement waitForElement(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    
    public void clickElement(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
    }
    
    public void takeScreenshot(String fileName) {
        try {
            // Check if driver is initialized
            if (driver == null) {
                System.out.println("❌ Driver not initialized, cannot take screenshot");
                return;
            }
            
            File screenshotsDir = new File("screenshots");
            if (!screenshotsDir.exists()) {
                screenshotsDir.mkdir();
            }
            
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File("screenshots/" + fileName + ".png");
            
            // Simple file copy without FileUtils
            java.nio.file.Files.copy(screenshot.toPath(), destFile.toPath());
            System.out.println("📸 Screenshot saved: " + destFile.getName());
            
        } catch (Exception e) {
            System.out.println("❌ Screenshot failed: " + e.getMessage());
        }
    }
    
    public void waitForPageLoad() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}