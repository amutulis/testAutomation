package demoTest;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
	String searchPhrase = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
	/*public static boolean isSorted(double[] newDouble) {

		for (int i = 0; i < newDouble.length-1; i++) {
			if (newDouble[i] > newDouble[i + 1]) 
				return true;
			
		}
		return false;
	}
*/
	@BeforeTest
	public void prepare() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get(baseUrl);
		homeP = new HomePage(driver);
		String title = homeP.getPageTitle();
		//Checking if test is on correct page
		Assert.assertTrue("Title is inccorect",title.equalsIgnoreCase(titleExpected));		
		homeP.search(searchPhrase);

	}
 
  @Test(priority=1)
  //Test case that test if phrase was shortened to 200 symbols, because of max length.
	public void checkSearchMaxLenght() throws InterruptedException {
		resultP = new ResultPage(driver);			
		String resultTitle = resultP.getPageTitle();
		//Checking if test is on correct page
		Assert.assertTrue("Title is inccorect",resultTitle.equalsIgnoreCase(resultTitleExpected));
		phrase = resultP.getSearchBoxPhrase();
		//Validates if phrase is shortened
		assertTrue((phrase.length()==200),"Phrase is longer than 200 symbols"); 
		Thread.sleep(500);
		driver.close();
	}
}
