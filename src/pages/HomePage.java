package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;




public class HomePage {
	WebDriver driver;
	
	By SEARCHBOX = By.id("term");
	By SEARCHBUTTON = By.id("searchButton");

	
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
