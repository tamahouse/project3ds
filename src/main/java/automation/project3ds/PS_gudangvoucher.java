package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_gudangvoucher {
	
	Driver driver;
	
	String email = "meo@spam4.me";
	String username = "demouser4";
	String password = "6Jsm39WD";
	
	By byemailTxb = By.id("gudangvoucher_email");
	By continueButton = By.id("submitButton");


	public PS_gudangvoucher(Driver driver) {
		this.driver = driver;
	}
	
	public GudangIframe createPayment() {
		this.setEmail();
		this.clickContinue();
		return new GudangIframe(driver);
	}
	
	private void setEmail() {
		Element element = driver.getElement(byemailTxb);
		driver.sleep(1000);
		element.sendKeys(email);
	}
	
	private void clickContinue() {
		Element element = driver.getElement(continueButton);
		element.click();
	}
	
	public class GudangIframe {
		
		Driver driver;
		
		By bybank = By.xpath("//*[@class='products']/div[@class='item']/a[contains(text(),'GV')]");
		By byusernameTxb = By.name("c_username");
		By bypasswordTxb = By.name("c_password");
		By byloginButton = By.id("submit");
		By byBAYARButton = By.name("buy");
		By byreturnButton = By.xpath("//a[contains(@href,'success')]");
		By bythankyou = By.xpath("//h3[text()='Thank you for your purchase!']");
		
		private GudangIframe(Driver driver) {
			WebElement webElement = driver.getElement(By.tagName("iframe")).getWebElement();
			driver.switchTo().frame(webElement);
			this.driver = driver;

		}
		
		public void finishPayment() {
			this.clickBank();
			this.loginWallet();
			this.clickBAYARButton();
			this.clickReturnButton();
			this.waitForThankyou();
		}
				
		private void clickBank() {
			Element element = driver.getElement(bybank);
			element.click();
		}
		
		private void loginWallet() {
			driver.sleep(1000);
			this.setUsername();
			this.setPassword();
			this.clickLoginButton();
		}
		
		private void setUsername() {
			Element element = driver.getElement(byusernameTxb);
			element.sendKeys(username);
		}
		
		private void setPassword() {
			Element element = driver.getElement(bypasswordTxb);
			element.sendKeys(password);
		}
		
		private void clickLoginButton() {
			Element element = driver.getElement(byloginButton);
			element.click();
		}
		
		private void clickBAYARButton() {
			Element element = driver.getElement(byBAYARButton);
			element.click();
		}
		
		private void clickReturnButton() {
			Element element = driver.getElement(byreturnButton);
			element.click();
		}
		
		private void waitForThankyou() {
			driver.getElement(bythankyou);
		}
	}
	

	

}
