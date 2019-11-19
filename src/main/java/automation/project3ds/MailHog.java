package automation.project3ds;

import org.openqa.selenium.By;

public class MailHog {
	
	Driver driver;
	
	String url = "http://bamboo.stuffio.com:8025";
	
	By searchTxb = By.id("search");
	By searchToSelect = By.xpath("//a[@ng-click=\"search('to', searchText)\"]");
	By resultBoxes = By.xpath("//div[@class='msglist-message row ng-scope']");

	public MailHog() {
		this.driver = new Driver();
		driver.get(url);
	}
	
	private void quit() {
		this.driver.quit();
	}
	
	public static boolean isMailSentTo(String condition) {
		MailHog hog = new MailHog();
		hog.setSearch(condition);
		hog.clickSearchTo();
		Boolean result = hog.isResult();
		hog.quit();
		return result;
	}
	
	private void setSearch(String condition) {
		Element element = driver.getElement(searchTxb);
		element.sendKeys(condition);
	}
	
	private void clickSearchTo() {
		Element element = driver.getElement(searchToSelect);
		element.click();
	}
	
	private boolean isResult() {
		try {
			driver.getElement(resultBoxes);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
