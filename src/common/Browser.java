package common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Browser {
	public WebDriver driver;
	
	public String baseUrl = "https://www.euroffice.co.uk/";
	String driverPath = "C:\\Users\\Artis Mutulis\\Desktop\\chromedriver_win32\\chromedriver.exe";
	public Browser(WebDriver driver) {
		this.driver = driver;
	}

	
	@BeforeTest
	public void openBrowser() {
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.get(baseUrl);
}
	@AfterTest
	public void closeBrowser() {
		driver.close();
	}
}