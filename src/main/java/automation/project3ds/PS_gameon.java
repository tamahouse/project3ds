package automation.project3ds;

import org.openqa.selenium.By;

public class PS_gameon {
	
	Driver driver;
	
	String CODE = "TF7E0260DB66642CF8A95C76C";
	
	By codeTxb = By.xpath("//*[@id='cardCodePartAll' or @id = 'cardCodePart1']");
	By submitBtn = By.id("submit_button");
	By cardBalanceTxt = By.id("card_balance_label");

	public PS_gameon(Driver driver) {
		this.driver = driver;
	}
	
	public void createPayment() {
		this.setCode();
		this.clickSubmitButton();
		this.waitForCardBalance();
		this.clickSubmitButton();
	}

	private void setCode() {
		Element element = driver.getElement(codeTxb);
		element.sendKeys(CODE);
	}
	
	private void clickSubmitButton() {
		Element element = driver.getElement(submitBtn);
		element.click();
	}
	
	private void waitForCardBalance() {
		driver.getElement(cardBalanceTxt,120000);
	}

	
}
