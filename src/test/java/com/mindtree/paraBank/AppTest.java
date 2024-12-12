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

import io.github.bonigarcia.wdm.WebDriverManager;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class AppTest {
	
	// public static WebDriver driver;
	public static ChromeDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;
	
	@BeforeSuite
	public void beforeSuite() throws Exception {
		System.out.println("print from before suit");
		Date currentDate = new Date();
		String reportFilename = currentDate.toString().replace(" ", "-").replace(":", "-");
		extent = new ExtentReports(".//Reports//"+reportFilename+".html", true);
	}

	@BeforeMethod
	public void beforeMethod() {
		System.out.println("print from before method");
		test = extent.startTest("paraBank");
		test.log(LogStatus.PASS, "Driver started successFully");
	}
	
	@BeforeTest
	public void beforeTest() {
		// System.setProperty("webdriver.chrome.driver", "C:\\Users\\azureuser\\Desktop\\SmartExecution_VM_local_project\\VMDemo\\Driver\\chromedriver.exe");
		// WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	}

	@Test()
	public void test_1() throws Exception {
		System.out.println("print from test");
		// driver.get("http://localhost:9090/para");
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
		test.log(LogStatus.PASS, "Driver closed Successfully");
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		extent.close();
	}


}
