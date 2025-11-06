package com.hotstar.automation.test;

import org.openqa.selenium.*;
import org.testng.annotations.*;

public class HotstarSearchTest extends TestBase {
    
    @BeforeMethod
    public void setUp() {
        startDriver();
    }
    
    @Test
    public void testSearchFunctionality() {
        try {
            System.out.println("🔍 Testing Search Functionality...");
            
            driver.get("https://www.hotstar.com/in");
            waitForPageLoad();

            // Better search icon locators
            WebElement searchIcon = null;
            try {
                // Try multiple possible locators for search icon
                searchIcon = driver.findElement(By.cssSelector("div[class*='search'], svg[class*='search'], button[class*='search']"));
            } catch (Exception e) {
                try {
                    searchIcon = driver.findElement(By.xpath("//*[contains(@class, 'search') or contains(@aria-label, 'Search')]"));
                } catch (Exception e2) {
                    System.out.println("⚠️ Search icon not found with standard locators");
                }
            }

            if (searchIcon != null) {
                searchIcon.click();
                System.out.println("🔍 Search icon clicked");
            } else {
                // Direct URL approach
                driver.get("https://www.hotstar.com/in/search");
                System.out.println("🔍 Directly opened search page");
            }
            
            waitForPageLoad();

            // Find search input with multiple locators
            WebElement searchBox = null;
            try {
                searchBox = waitForElement(By.cssSelector("input[placeholder*='Search'], input[type='search']"), 10);
            } catch (Exception e) {
                try {
                    searchBox = driver.findElement(By.tagName("input"));
                } catch (Exception e2) {
                    System.out.println("⚠️ Search box not found");
                }
            }

            if (searchBox != null) {
                searchBox.clear();
                searchBox.sendKeys("Avengers");
                System.out.println("⌨️ Entered search term: Avengers");
                
                searchBox.sendKeys(Keys.ENTER);
                System.out.println("✅ Search submitted");
                
                waitForPageLoad();
                takeScreenshot("search_results");
                System.out.println("✅ Search test completed!");
            } else {
                System.out.println("❌ Search box not found, test failed");
            }
            
        } catch (Exception e) {
            takeScreenshot("search_error");
            System.out.println("❌ Search test failed: " + e.getMessage());
        }
    }
    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}