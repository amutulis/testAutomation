package demoTest;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ResultPage;

public class testNG2 {

	public WebDriver driver;
	HomePage homeP;
	ResultPage resultP;
	String phrase;
	String titleExpected = "Euroffice Discount Office Supplies and Office Stationery";
	String resultTitleExpected = "Search";
	public String baseUrl = "https://www.euroffice.co.uk/";
	String driverPath = "C:\\Users\\Artis Mutulis\\Desktop\\chromedriver_win32\\chromedriver.exe";
	// String driverPath = "C:\\Users\\Artis Mutulis\\Desktop\\geckodriver.exe";
	String searchPhrase = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";

	@BeforeTest
	public void prepare() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		/*
		 * System.setProperty("webdriver.gecko.driver", driverPath); driver = new
		 * FirefoxDriver();
		 */
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get(baseUrl);

	}

	@Test(priority = 1)
	// Test case that test if phrase was shortened to 200 symbols, because of max
	// length.
	public void checkSearchMaxLenght() throws InterruptedException {
		homeP = new HomePage(driver);
		String title = homeP.getPageTitle();
		// Checking if test is on correct page
		Assert.assertTrue("Title is inccorect", title.equalsIgnoreCase(titleExpected));
		homeP.search(searchPhrase);
		resultP = new ResultPage(driver);
		String resultTitle = resultP.getPageTitle();
		// Checking if test is on correct page
		Assert.assertTrue("Title is inccorect", resultTitle.equalsIgnoreCase(resultTitleExpected));
		phrase = resultP.getSearchBoxPhrase();
		// Validates if phrase is shortened
		assertTrue((phrase.length() == 200), "Phrase is longer than 200 symbols");
		Thread.sleep(500);
		driver.close();
	}
}
