package automation.project3ds;

import org.openqa.selenium.By;

public class MailHog {
	
	Driver driver;
	
	String url = "http://bamboo.stuffio.com:8025";
	
	By searchTxb = By.id("search");
	By numberInbox = By.xpath("//a[@ng-click='backToInboxFirst()']");
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
		hog.waitForLoaded();
		hog.setSearch(condition);
		hog.clickSearchTo();
		Boolean result = hog.isResult(condition);
		hog.quit();
		return result;
	}
	
	private void waitForLoaded() {
		for(int i = 0; i< 20 ; i++) {
			Element element = driver.getElement(numberInbox);
			String str = element.getText();
			String index = BasePage.getBetweenText(str, "(", ")");
			Boolean isNumber = BasePage.isNumeric(index);
			if(isNumber == true) {
				driver.sleep(500);
				return;
			}else {
				driver.sleep(500);
			}
		}
	}
	
	private void setSearch(String condition) {
		Element element = driver.getElement(searchTxb);
		element.sendKeys(condition);
	}
	
	private void clickSearchTo() {
		Element element = driver.getElement(searchToSelect);
		element.click();
	}
	
	private boolean isResult(String condition) {
		try {
			By resultBoxes = By.xpath("//div[@class='msglist-message row ng-scope']//*[contains(text(),'"+condition+"')]");
			Element element = driver.getElement(resultBoxes);
			element.highlight();
			System.out.println(element.getText());
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
}
