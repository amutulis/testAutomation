package demoTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import pages.HomePage;
import pages.ResultPage;

public class CheckList {

	public WebDriver driver;

	String title;
	String titleExpected = "Euroffice Discount Office Supplies and Office Stationery";
	String resultTitleExpected = "Search";
	public String baseUrl = "https://www.euroffice.co.uk/";
	// String driverPath = "C:\\Users\\Artis
	// Mutulis\\Desktop\\chromedriver_win32\\chromedriver.exe";
	String driverPath = "C:\\Users\\Artis Mutulis\\Desktop\\geckodriver.exe";
	String searchPhrase = "Dymo Labelwriter";

	public static boolean isSorted(double[] s) {
		for (int i = 0; i < s.length - 1; i++) {
			if (s[i] < s[i + 1]) {
				return true;
			}
		}
		return false;
	}

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
	@AfterTest
	public void done() {
		driver.close();
	}
	

	@Test(priority = 0)
	public void checkSortedElements() throws InterruptedException {
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
		List<WebElement> priceList;
		List<WebElement> pages;

		resultP.selectSortValue("PriceAscending");
		Thread.sleep(1500);
		priceList = resultP.getPriceList();
		pages = resultP.getPages();
		// Go trough all pages and retrive prices for searched elements
		for (int j = 0; j < pages.size(); j++) {
			for (int i = 0; i < priceList.size(); i++) {
				js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
				saraksts.add(priceList.get(i).getAttribute("textContent"));
			}
			resultP.closePopup();
			Thread.sleep(500);
			resultP.clickNext();
			priceList = resultP.getPriceList();
		}
		// Converts String ArrayList to double Array
		double[] newDouble = new double[saraksts.size()];
		for (int z = 0; z < saraksts.size(); z++) {
			newDouble[z] = Double.parseDouble(saraksts.get(z));
		}

		// Validates if retrieved list was sorted
		Assert.assertTrue("Element is not sorted", isSorted(newDouble));
		// Prints list
		System.out.println(Arrays.toString(newDouble));
	

	}

}
