package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_mercadopago2  {
	
	Driver driver;
	
	String CARDNUMBER = "4235647728025682";
	String CARDEXP = "0122";
	String CARDHOLDER = "Payment Wall";
	String CVV = "123";
	String CPF = "43622652101";
	
	By accountOption = By.xpath("//*[@for='mp_login_row']");
	By creditcardOption = By.xpath("//*[@for='new_card_row']");
	By cvvTxb = By.id("cvv");
	By selectItemBtn = By.xpath("//*[@id='installments_select_express']/button");
	By item = By.xpath("(//*[@class='andes-list__item-primary'])[2]");
	By payBtn = By.id("pay");
	
	By cardNumberTxb = By.id("card_number");
	By cardExpTxb = By.id("input_expiration_date");
//	By cardHolderTxb = By.id("fullname");
	By cardHolderTxb = By.xpath("//*[contains(@id,'full')][contains(@id,'name')]");
	By continueBtn = By.id("submit");
	By cpfTxb = By.id("number");
	By item1 = By.xpath("//li[./*[@id='1']]");

	public PS_mercadopago2(Driver driver) {
		WebElement frameMedia = driver.getElement(By.id("step2_iframe"),30000).getWebElement();
		driver.switchTo().frame(frameMedia);
		this.driver = driver;
	}
	
	public void finishPayment_AccountOption() {
		this.setCVV();
		this.selectItem();
		this.clickPayButton();
	}
	
	public void finishPayment_NOT_login() {
		this.clickCreditCardOption();
		this.setCardNumber();
		this.setCardExp();
		this.setCardHolder();
		this.setCVV();
		this.clickContinueButton();
		this.setCPF();
		this.clickContinueButton();
		this.clickItem1();
		this.clickPayButton();
	}
	
	private void clickAccountOption() {
		Element element = driver.getElement(accountOption);
		driver.sleep(2000);
		element.click();
	}
	
	private void  clickCreditCardOption() {
		Element element = driver.getElement(creditcardOption);
		driver.sleep(2000);
		element.click();
	}
	
	public PS_mercadopago3 getLoginPage() {
		this.clickAccountOption();;
		return new PS_mercadopago3(driver);
	}
	
	private void setCVV() {
		Element element = driver.getElement(cvvTxb);
		element.sendKeys(CVV);
	}
	
	private void clickSelectItemButton() {
		Element element = driver.getElement(selectItemBtn);
		element.click();
	}
	
	private void clickItem() {
		Element element = driver.getElement(item);
		element.click();
	}
	
	private void selectItem() {
		try {
		this.clickSelectItemButton();
		this.clickItem();
		}catch (Exception e) {
			
		}
	}
	
	private void clickPayButton() {
		Element element = driver.getElement(payBtn);
		element.click();
	}
	
	private void setCardNumber() {
		Element element = driver.getElement(cardNumberTxb);
		driver.sleep(2000);
		element.clear();
		element.sendKeys(CARDNUMBER);
	}
	
	private void setCardExp() {
		Element element = driver.getElement(cardExpTxb);
		element.sendKeys(CARDEXP);
	}
	
	private void setCardHolder() {
		Element element = driver.getElement(cardHolderTxb);
		element.clear();
		element.sendKeys(CARDHOLDER);
	}
	
	private void clickContinueButton() {
		Element element = driver.getElement(continueBtn);
		element.click();
	}
	
	private void clickItem1() {
		try {
		Element element = driver.getElement(item1);
		element.click();
		}catch(Exception e) {
			
		}
	}
	
	private void setCPF() {
		Element element = driver.getElement(cpfTxb);
		element.sendKeys(CPF);
	}

}
