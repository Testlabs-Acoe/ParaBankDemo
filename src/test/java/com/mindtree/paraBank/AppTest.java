package com.mindtree.paraBank;

import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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

import io.github.bonigarcia.wdm.WebDriverManager;

public class AppTest {
	
	public static RemoteWebDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void beforeSuite() throws Exception {
		System.out.println("print from before suite");
		Date curentDate =new Date();
		String reportFilename = curentDate.toString().replace(" ", "-").replace(":", "-");
		extent = new ExtentReports(".//Reports//"+reportFilename+".html",true);
	}
	
	@BeforeMethod
	public void beforeMethod() {
		System.out.println("print from before method");
		test = extent.startTest("paraBank");
	}
	
	@BeforeTest
	public void beforeTest() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}
	
	@Test
	public void test_1() throws Exception {
		System.out.println("print from test");
		driver.get("https://parabank.parasoft.com/index.html");
		driver.manage().window().maximize();
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
	}
	
	@AfterSuite
	public void afterSuite() {
		extent.flush();
		extent.close();
	}

}
