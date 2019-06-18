package demoTest;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

import pages.HomePage;
import pages.ResultPage;

public class CheckTotalProducts {
	WebDriver driver;

	String title;
	String titleExpected = "Euroffice Discount Office Supplies and Office Stationery";
	String resultTitleExpected = "Search";
	public String baseUrl = "https://www.euroffice.co.uk/";
	String driverPath = "C:\\Users\\Artis Mutulis\\Desktop\\chromedriver_win32\\chromedriver.exe";
	// String driverPath = "C:\\Users\\Artis Mutulis\\Desktop\\geckodriver.exe";
	String searchPhrase = "Mobile Phone Headsets";

	@BeforeTest
	// Open browser and goes to specified webpage
	public void prepare() throws InterruptedException {

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

	@Test(timeOut = 100000)
	public void checkFilteredElements() throws InterruptedException {
		HomePage homeP = new HomePage(driver);
		ResultPage resultP = new ResultPage(driver);
		List<WebElement> totalProducts;
		List<WebElement> priceList;
		List<WebElement> pages;
		ArrayList<Integer> totalP = new ArrayList<Integer>();
		ArrayList<Double> totalC = new ArrayList<Double>();
		Thread.sleep(1000);
		String title = homeP.getPageTitle();
		// Checking if test is on correct page
		Assert.assertTrue("Title is inccorect", title.equalsIgnoreCase(titleExpected));
		homeP.search(searchPhrase);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		String resultTitle = resultP.getPageTitle();
		// Checking if test is on correct page
		Assert.assertTrue("Title is inccorect", resultTitle.equalsIgnoreCase(resultTitleExpected));
		totalProducts = resultP.getTotalProducts();
		for (int i = 0; i < totalProducts.size(); i++) {

			totalP.add(Integer.parseInt(totalProducts.get(i).getAttribute("textContent")));
		}
		Thread.sleep(1500);
		priceList = resultP.getPriceList();
		pages = resultP.getPages();
		// Go trough all pages and retrive prices for searched elements
		if (pages.size() > 1) {
			for (int j = 0; j < pages.size(); j++) {
				priceList = resultP.getPriceList();
				for (int i = 0; i < priceList.size(); i++) {
					js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
					totalC.add(Double.parseDouble(priceList.get(i).getAttribute("textContent")));
					priceList = resultP.getPriceList();

				}
				resultP.closePopup();
				Thread.sleep(500);
				// js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
				resultP.clickNext();
				priceList = resultP.getPriceList();

			}
		} else {
			priceList = resultP.getPriceList();
			for (int i = 0; i < priceList.size(); i++) {
				js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
				totalC.add(Double.parseDouble(priceList.get(i).getAttribute("textContent")));
				
			}

			int lastElement = totalP.get(totalP.size() - 1);
			int totalElements = totalC.size();
			Assert.assertTrue("Product count incorrect", lastElement==totalElements);
			
			driver.close();

		}
	}
}
