package pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class ResultPage {
	WebDriver driver;
	WebElement button;
	WebElement popp;

	By PRICE = By.xpath(".//span[@class='addToBasket__unitPriceText' and @itemprop='price']");
	By BUTTONNEXT = By.xpath(".//a[@title='Next']");
	By POPUPCLOSE = By.xpath(".//button[@class='yieldifyPopup__closeIcon']");
	By PAGES = By.xpath(".//ul[@class='pagination']//li[contains(@class,'pagination__item pagination__item--page')]");
	By SORT = By.xpath(".//select[@class='select--small']");
	By PRODUCTCOUNT = By.xpath(".//span[@class='productList__resultsShowingText']/child::strong");
	By SEARCHBOX = By.id("term");
	/**
	 * Returns all prices for found products
	 * @return
	 */
	public List<WebElement> getPriceList() {
		return driver.findElements(PRICE);
	}
	/**
	 * returns serachbox value
	 * @return 
	 */
	public String getSearchBoxPhrase(){
		String srcValue = driver.findElement(SEARCHBOX).getAttribute("value");
		return srcValue;
	}
	/**
	 * Returns all pages
	 * @return
	 */
	public List<WebElement> getPages() {
		return driver.findElements(PAGES);
	}
	/**
	 * Returns total product count what is above products
	 * @return
	 */
	public List<WebElement> getTotalProducts() {
		return driver.findElements(PRODUCTCOUNT);
	}
	/**
	 * checks if Next button is enabled and click
	 */
	public void clickNext() {
		button = driver.findElement(BUTTONNEXT);
		Boolean chekbtn = button.isEnabled();
		if (chekbtn == true) {
			button.click();
		}

	}
	/**
	 * Closes pop up if it is displayed
	 */
	public void closePopup() {
		popp = driver.findElement(POPUPCLOSE);
		Boolean checkPop = popp.isDisplayed();
		if (checkPop == true) {
			popp.click();
		}
	}
	/**
	 * Selects value from SOrt drop down
	 * @param value
	 */
	public void selectSortValue(String value) {
		var slc = new Select(driver.findElement(SORT));
		slc.selectByValue(value);
	}

	public ResultPage(WebDriver driver) {
		this.driver = driver;
	}
	/**
	 * Get page title
	 * @return
	 */
	public String getPageTitle() {
		return driver.getTitle();
	}

	
}
