package demoTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ResultPage;

public class CheckFilter {
	WebDriver driver;
	String title;
	String titleExpected = "Euroffice Discount Office Supplies and Office Stationery";
	String resultTitleExpected = "Search";
	public String baseUrl = "https://www.euroffice.co.uk/";
	// String driverPath = "C:\\Users\\Artis
	// Mutulis\\Desktop\\chromedriver_win32\\chromedriver.exe";
	String driverPath = "C:\\Users\\Artis Mutulis\\Desktop\\geckodriver.exe";
	String searchPhrase = "phone";
	@BeforeTest
	// Open browser and goes to specified webpage
	public void prepare() throws InterruptedException {
		/*
		 * System.setProperty("webdriver.chrome.driver", driverPath); driver = new
		 * ChromeDriver();
		 */
		System.setProperty("webdriver.gecko.driver", driverPath);
		driver = new FirefoxDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get(baseUrl);

	}
	public void checkFilteredElements() throws InterruptedException {
		HomePage homeP = new HomePage(driver);
		ResultPage resultP = new ResultPage(driver);
		Thread.sleep(1000);
		String title = homeP.getPageTitle();
		// Checking if test is on correct page
		Assert.assertTrue("Title is inccorect", title.equalsIgnoreCase(titleExpected));
		homeP.search(searchPhrase);
		ArrayList<String> saraksts = new ArrayList<String>();
		// resultP = new ResultPage(driver);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String resultTitle = resultP.getPageTitle();
		// Checking if test is on correct page
		Assert.assertTrue("Title is inccorect", resultTitle.equalsIgnoreCase(resultTitleExpected));
		
	}
}
