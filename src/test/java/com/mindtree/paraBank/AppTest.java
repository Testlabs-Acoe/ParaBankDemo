package com.mindtree.paraBank;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class AppTest {
    
    public static WebDriver driver;
    public static ExtentReports extent;
    public static ExtentTest test;
    
    @BeforeSuite
    public void beforeSuite() throws Exception {
        System.out.println("print from before suite");
        Date currentDate = new Date();
        String reportFilename = currentDate.toString().replace(" ", "-").replace(":", "-");
        extent = new ExtentReports("./Reports/" + reportFilename + ".html", true);
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("print from before method");
        test = extent.startTest("paraBank");
        test.log(LogStatus.PASS, "Driver started successfully");
    }
    
    @BeforeTest
    public void beforeTest() {
        System.setProperty("webdriver.chrome.driver", "path/to/chromedriver"); // Update the path to the ChromeDriver executable
        driver = new ChromeDriver();
    }

    @Test
    public void test_1() throws Exception {
        System.out.println("print from test");
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("john");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("demo");
        driver.findElement(By.xpath("//input[@type='submit']")).click();
        test.log(LogStatus.PASS, "test passed");
    }    
    
    @AfterMethod
    public void afterMethod() {
        extent.endTest(test);
    }

    @AfterTest
    public void afterTest() {
        driver.close();
        driver.quit();
        test.log(LogStatus.PASS, "Driver closed successfully");
    }

    @AfterSuite
    public void afterSuite() {
        extent.flush();
        extent.close();
    }
}
