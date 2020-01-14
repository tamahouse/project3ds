package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PS_banktransfercn2 {
	
	Driver driver;
	String redirectUrl = "uatfx.soopay.net";
	
	String PHONE = "15012345678";
	String SMS1 = "123456";
	String CARDNUMBER = "6251625533117716";
	String CARDHOLDER = "罗淳雅";
	String IDCODE = "431381198109106573";
	String CARDMONTH = "01";
	String CARDYEAR = "22";
	String CARDCVV = "358";
	String SMS2 = "1234";
	
	By phoneTxb = By.id("mobileIdText");
	By sendSMSButton1 = By.id("login_sms_btn");
	By smsTxb1 = By.id("verifyCode");
	By nextBtn = By.xpath("//button[@class='loginNextPay']");
	
	By moreBankButton = By.id("morebankbox");
	By icbcBank = By.xpath("//*[@id='bindBankPopbar']//*[@class='icbc']");
	By popupNextBtn = By.xpath("//*[@id='bindBankPopbar']//*[@class='initnextbtn']");
	By iframe = By.id("payTypePop");
	By creditCardOption = By.id("creditBandCard");
	By popupCloseBtn = By.xpath("//*[contains(@href,'nextbtn')]");
	
	By cardNumberTxb = By.id("cardIdV");
	By cardHolderTxb = By.id("cardHolderV");
	By idcodeTxb = By.id("identityCodeV");
	By cardMonthSlt = By.id("validDateMV");
	By cardYearSlt = By.id("validDateYV");
	By cardCVV = By.xpath("//*[@id='cvv2V'][not(contains(@style,'none'))]");
	By sendSMSButton2 = By.id("btn");
	By smsTxb2 = By.id("verifyCodeV");
	By finishBtn = By.id("fastComfirmPayBtn");
	
	By returnMerchantSiteBtn = By.id("reminder_blue");
	
	public PS_banktransfercn2(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage finishPayment() {
		this.setPhone();
		this.clickSMSButton1();
		this.setSMS1();
		this.clickNextButton();
		this.clickMoreBankButton();
		this.clickICBC();
		this.clickPopupNextButton();
		this.clickCreditCardOption();
		this.clickPopupCloseButton();
		this.setCardNumber();
		this.setCardHolder();
		this.setIDCode();
		this.setCardMonth();
		this.setCardYear();
		this.setCVV();
		this.clickSendSMSButton2();
		this.setSMS2();
		this.clickFinishButton();
		this.clickReturnButton();
		driver.switchToWindows("paymentpingback", true,60000);
		return new ThankyouPage(driver);
	}
	
	
	private void setPhone() {
		Element element = driver.getElement(phoneTxb);
		element.sendKeys(PHONE);
	}
	
	private void clickSMSButton1() {
		Element element = driver.getElement(sendSMSButton1);
		element.click();
	}
	
	private void setSMS1() {
		Element element = driver.getElement(smsTxb1);
		element.sendKeys(SMS1);
	}
	
	private void clickNextButton() {
		Element element = driver.getElement(nextBtn);
		driver.sleep(2000);
		element.click();
	}
	
	private void clickMoreBankButton() {
		Element element = driver.getElement(moreBankButton,60000);
		element.click();
	}
	
	private void clickICBC() {
		Element element = driver.getElement(icbcBank);
		element.click();
	}
	
	private void clickPopupNextButton() {
		Element element = driver.getElement(popupNextBtn);
		element.click();
	}
	
	private void clickCreditCardOption() {
		WebElement webElement = driver.getElement(iframe).getWebElement();
		driver.switchTo().frame(webElement);
		Element element = driver.getElement(creditCardOption);
		element.click();
		driver.switchTo().defaultContent();
	}
	
	private void clickPopupCloseButton() {
		WebElement webElement = driver.getElement(iframe).getWebElement();
		driver.switchTo().frame(webElement);
		Element element = driver.getElement(popupCloseBtn);
		element.click();
		driver.switchTo().defaultContent();
	}
	
	private void setCardNumber() {
		Element element = driver.getElement(cardNumberTxb);
		element.sendKeys(CARDNUMBER);
	}
	
	private void setCardHolder() {
		Element element = driver.getElement(cardHolderTxb);
		element.sendKeys(CARDHOLDER);
	}
	
	private void setIDCode() {
		Element element = driver.getElement(idcodeTxb);
		element.sendKeys(IDCODE);
	}
	
	private void setCardMonth() {
		Select select = driver.getSelect(cardMonthSlt);
		select.selectByValue(CARDMONTH);
	}
	
	private void setCardYear() {
		Select select = driver.getSelect(cardYearSlt);
		select.selectByValue(CARDYEAR);
	}
	
	private void setCVV() {
		Element element = driver.getElement(cardCVV);
		element.click();
		element = driver.getElement(cardCVV);
		element.sendKeys(CARDCVV);
	}
	
	private void clickSendSMSButton2() {
		Element element = driver.getElement(sendSMSButton2);
		element.click();
	}
	
	private void setSMS2() {
		Element element = driver.getElement(smsTxb2);
		element.sendKeys(SMS2);
	}
	
	private void clickFinishButton() {
		Element element = driver.getElement(finishBtn);
		driver.sleep(2000);
		element.click();
	}

	private void clickReturnButton() {
		Element element = driver.getElement(returnMerchantSiteBtn);
		element.click();
	}

}
