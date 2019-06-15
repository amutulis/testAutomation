package pages;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.testng.annotations.AfterTest;




public class HomePage {
	WebDriver driver;
	//public String baseUrl = "https://www.euroffice.co.uk/";
	//String driverPath = "C:\\Users\\Artis Mutulis\\Desktop\\chromedriver_win32\\chromedriver.exe";
	
	
	By SEARCHBOX = By.id("term");
	By SEARCHBUTTON = By.id("searchButton");
	/*public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get(baseUrl);
}*/
	
	public void closeBrowser() {
		driver.close();
	}

	
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	/**
	 * 
	 * Sets search criteria from the testcase
	 */
	public void setSearchPhrase(String phrase) {
		driver.findElement(SEARCHBOX).sendKeys(phrase);
	}

	/**
	 * 
	 * presses search button
	 */
	public void clickButton() {
		driver.findElement(SEARCHBUTTON).click();
	}
	/**
	 * returns page Title
	 * @return
	 */
	public String getPageTitle() {
		return driver.getTitle();

	}
	/**
	 * sets search parameter and preses search button
	 * @param phrase
	 */
	public void search(String phrase) {
		this.setSearchPhrase(phrase);
		this.clickButton();
	}
}
