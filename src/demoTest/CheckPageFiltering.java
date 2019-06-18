package demoTest;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.ResultPage;

public class CheckPageFiltering {
	WebDriver driver;
	boolean chrome = true;
	String title;
	String titleExpected = "Euroffice Discount Office Supplies and Office Stationery";
	String resultTitleExpected = "Search";
	public String baseUrl = "https://www.euroffice.co.uk/";

	String searchPhrase = "phone";

	@BeforeTest
	// Open browser and goes to specified webpage
	public void prepare() throws InterruptedException {
		if (chrome) {
			String driverPath = "C:\\Users\\Artis Mutulis\\Desktop\\chromedriver_win32\\chromedriver.exe";
			System.setProperty("webdriver.chrome.driver", driverPath);
			driver = new ChromeDriver();
		} else {
			String driverPath = "C:\\Users\\Artis Mutulis\\Desktop\\geckodriver.exe";
			System.setProperty("webdriver.gecko.driver", driverPath);
			driver = new FirefoxDriver();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get(baseUrl);

	}

	@AfterTest
	public void done() {
		driver.close();
	}

	@Test
	public void checkFilteredProducts() throws InterruptedException {
		HomePage homeP = new HomePage(driver);
		List<WebElement> priceList;
		List<WebElement> pages;
		List<WebElement> categories;
		List<WebElement> subCategories;

		ResultPage resultP = new ResultPage(driver);
		Thread.sleep(1000);
		String title = homeP.getPageTitle();
		// Checking if test is on correct page
		Assert.assertTrue("Title is inccorect", title.equalsIgnoreCase(titleExpected));
		homeP.search(searchPhrase);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String resultTitle = resultP.getPageTitle();
		// Checking if test is on correct page
		Assert.assertTrue("Title is inccorect", resultTitle.equalsIgnoreCase(resultTitleExpected));
		Thread.sleep(1500);
		priceList = resultP.getPriceList();
		pages = resultP.getPages();
		categories = resultP.getCategories();
		subCategories = resultP.getSubCategories();
		String elements = new String();
		String subElements = new String();
		/*
		 * for(i=0;i<categories.size();i++) {
		 * elements=categories.get(i).getAttribute("textContent");
		 * 
		 * }
		 */
		for (int j = 0; j < subCategories.size(); j++) {
			subElements = subCategories.get(j).getAttribute("textContent").replaceAll("\\(", "").replaceAll("\\)", "");
			System.out.println(subElements);
		}
	}

	// Go trough all pages and retrive prices for searched elements
	/*
	 * if (pages.size() > 1) { for (int j = 0; j < pages.size(); j++) { priceList =
	 * resultP.getPriceList(); for (int i = 0; i < priceList.size(); i++) {
	 * js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	 * totalC.add(Double.parseDouble(priceList.get(i).getAttribute("textContent")));
	 * priceList = resultP.getPriceList();
	 * 
	 * } resultP.closePopup(); Thread.sleep(500); //
	 * js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	 * resultP.clickNext(); priceList = resultP.getPriceList();
	 * 
	 * } } else { priceList = resultP.getPriceList(); for (int i = 0; i <
	 * priceList.size(); i++) {
	 * js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
	 * totalC.add(Double.parseDouble(priceList.get(i).getAttribute("textContent")));
	 * 
	 * }
	 */

}
