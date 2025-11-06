package com.hotstar.automation.test;

import org.testng.Assert;
import org.testng.annotations.*;

public class HotstarTitleTest extends TestBase {
    
    @BeforeMethod
    public void setUp() {
        startDriver();
    }
    
    @Test
    public void testHotstarTitle() {
        try {
            System.out.println("🌐 Testing Hotstar Title...");
            
            driver.get("https://www.hotstar.com/in");
            waitForPageLoad();
            
            String actualTitle = driver.getTitle();
            System.out.println("📄 Page Title: " + actualTitle);
            
            // Updated assertion - JioHotstar check karein
            Assert.assertTrue(actualTitle.contains("Hotstar") || actualTitle.contains("JioHotstar"), 
                "Title verification failed! Actual: " + actualTitle);
            
            System.out.println("✅ Title test passed!");
            
        } catch (Exception e) {
            System.out.println("❌ Title test failed: " + e.getMessage());
            Assert.fail("Test failed due to exception: " + e.getMessage());
        }
    }
    @AfterMethod
    public void tearDown() {
        quitDriver();
    }
}