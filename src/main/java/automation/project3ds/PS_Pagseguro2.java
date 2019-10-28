package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PS_Pagseguro2 {
	
	Driver driver;
	
	String name = "Payment Wall";
	String CPF = "72962940005";
	String CEP = "01452002";
	String areaCode = "11";
	String phone = "994283640";
	String cardNumber = "4509 9535 6623 3704";
	String addressNumber = "00";
	String addressComplement = "Any";
	String cardHolder = "Card Holder";
	String cardMonth = "01";
	String cardYear = "22";
	String cvv = "123";
	String birth = "20/05/1980";
	
	
	By senderNameTxb = By.id("senderName");
	By senderCPFTxb = By.id("senderCPF");
	By senderAreaCodeTxb = By.id("senderAreaCode");
	By senderPhoneTxb = By.id("senderPhone");
	By shippingAddressPostalCodeTxb = By.id("shippingAddressPostalCode");
	By shippingAddressNumberTxb = By.xpath("//*[@style=\"display: block;\"]/*[@id='shippingAddressNumber']");
	By shippingAddressComplementTxb = By.xpath("//*[@style=\"display: block;\"]/*[@id='shippingAddressComplement']");
	
	By creditCardOption = By.id("creditCardOption");
	By bookletOption = By.id("bookletOption");
	
	By nextButton = By.xpath("//*[@class=\"next pagseguro-button mainActionButton\"]");
	
	By creditCardNumberTxb = By.id("creditCardNumber");
	By clickToOpenCardBrand = By.xpath("//*[@class='brandView unknown'][@style=\"display: block;\"]//*[@class='clickToOpen']");
	By visaBrand = By.xpath("//*[@class='brandView unknown opened']//a[@class='omniture-click'][@name='visa']");
	By creditCardHolderNameTxb = By.id("creditCardHolderName");
	By creditCardDueDate_MonthTxb = By.id("creditCardDueDate_Month");
	By creditCardDueDate_Year = By.id("creditCardDueDate_Year");
	By creditCardCVVTxb = By.id("creditCardCVV");
	By cardInstallmentQuantitySelect = By.id("cardInstallmentQuantity");
	
	By holderCPFTxb = By.id("holderCPF");
	By holderAreaCodeTxb = By.id("holderAreaCode");
	By holderPhoneTxb = By.id("holderPhone");
	By holderBornDateTxb = By.id("holderBornDate");
	
	By paymentTotal = By.xpath("//*[@id='payment-total']//dd");
	By completed = By.id("payment-details");

	public PS_Pagseguro2(Driver driver) {
		WebElement frameMedia = driver.getElement(By.xpath("//iframe[@id='uolPSMediator'][@style =\"display: block;\" ]"),30000).getWebElement();
		driver.switchTo().frame(frameMedia);
		WebElement frameApp = driver.getElement(By.id("UolPS-application")).getWebElement();
		driver.switchTo().frame(frameApp);
		this.driver = driver;
	}
	
	public String createBoletoPayment() {
		setSender();
		clickBoletoOption();
		clickNextButton();
		waitForComplete();
		String transactionCode = waitForComplete();
		return transactionCode;
	}
	
	public String createCreditCardPayment() {
		setSender();
		clickCreditCardOption();
		setCreditCard();
		setHolder();
		String transactionCode = waitForComplete();
		return transactionCode;
	}
	
	public void setCreditCard() {
		setCardFullNumber();
		setCardFullExp();
		setCardHolder();
		setCardCVV();
		selectProduct();
		clickNextButton();
	}
	
	private void setSender() {
		AnnotationPage.sleep(2000);
		setSenderFullAddress();
		setSenderName();
		setSenderCPF();
		setSenderFullPhone();
		clickNextButton();
	}
	
	private void setHolder() {
		setHolderCPF();
		setHolderFullPhone();
		setHolderBirth();
		clickNextButton();
	}
	
	private void setSenderName() {
		Element element = driver.getElement(senderNameTxb);
		element.sendKeys(name);
	}
	
	private void setSenderCPF() {
		Element element = driver.getElement(senderCPFTxb);
		element.sendKeys(CPF);
	}
	
	private void setSenderAreaCode() {
		Element element = driver.getElement(senderAreaCodeTxb);
		element.sendKeys(areaCode);
	}
	
	private void setSenderPhone() {
		Element element = driver.getElement(senderPhoneTxb);
		element.sendKeys(phone);
	}
	
	private void setSenderFullPhone() {
		setSenderAreaCode();
		setSenderPhone();
	}
	
	
	private void setSenderFullAddress() {
		setCEP();
		setAddressNumber();
		setAddressComplement();
	}
	
	private void setCEP() {
		Element element = driver.getElement(shippingAddressPostalCodeTxb);
		element.sendKeys(CEP);
	}
	
	private void setAddressNumber() {
		Element element = driver.getElement(shippingAddressNumberTxb);
		element.sendKeys(addressNumber);
	}
	
	private void setAddressComplement() {
		Element element = driver.getElement(shippingAddressComplementTxb);
		element.sendKeys(addressComplement);
	}
	
	private void clickNextButton() {
		Element element = driver.getElement(nextButton);
		element.click();
	}
	
	private void clickCreditCardOption() {
		Element element = driver.getElement(creditCardOption);
		element.click();
	}
	
	private void clickBoletoOption() {
		Element element = driver.getElement(bookletOption);
		element.click();
	}
	
	private void setCardNumber() {
		Element element = driver.getElement(creditCardNumberTxb);
		element.sendKeys(cardNumber);
	}
	
	private void clickToOpenCardBrand() {
		Element element = driver.getElement(clickToOpenCardBrand);
		element.click();
	}
	
	private void clickCardBrand() {
		AnnotationPage.sleep(3000);
		Element element = driver.getElement(visaBrand);
		element.clickJS();
	}
	
	private void setCardFullNumber() {
		setCardNumber();
		clickToOpenCardBrand();
		clickCardBrand();
	}
	
	private void setCardMonth() {
		Element element = driver.getElement(creditCardDueDate_MonthTxb);
		element.sendKeys(cardMonth);
	}
	
	private void setCardYear() {
		Element element = driver.getElement(creditCardDueDate_Year);
		element.sendKeys(cardYear);
	}
	
	private void setCardFullExp() {
		setCardMonth();
		setCardYear();
	}
	
	private void setCardHolder() {
		Element element = driver.getElement(creditCardHolderNameTxb);
		element.sendKeys(cardHolder);
	}
	
	private void setCardCVV() {
		Element element = driver.getElement(creditCardCVVTxb);
		element.sendKeys(cvv);
	}
	
	private void selectProduct() {
		try {
		Select select = new Select(driver.getElement(cardInstallmentQuantitySelect,1000));
		select.selectByValue("1");
		}catch(Exception e) {
			System.out.println("Not select product");
		}
	}
	
	private void setHolderCPF() {
		Element element = driver.getElement(holderCPFTxb);
		element.sendKeys(CPF);
	}
	
	private void setHolderAreaCode() {
		Element element = driver.getElement(holderAreaCodeTxb);
		element.sendKeys(areaCode);
	}
	
	private void setHolderPhone() {
		Element element = driver.getElement(holderPhoneTxb);
		element.sendKeys(phone);
	}
	
	private void setHolderFullPhone() {
		setHolderAreaCode();
		setHolderPhone();
	}
	
	private void setHolderBirth() {
		Element element = driver.getElement(holderBornDateTxb);
		element.sendKeys(birth);
	}
	
	private Boolean isPaymentDetailOpen() {
		Element element = driver.getElement(completed,60000);
		String str = element.getAttribute("class");
		if(str.contains("closed")) {
			return false;
		}else {
			return true;
		}
	}
	
	private void clickOpenPaymentDetail() {
		Boolean isOpen = isPaymentDetailOpen();
		if(!isOpen) {
			Element element = driver.getElement(completed,20000);
			element.click();
		}
	}
	
	private String waitForComplete() {
		clickOpenPaymentDetail();
		Element element = driver.getElement(By.id("transactionCode"));
		String transactionCode = element.getText().replace("-", "");
		return transactionCode;
	}
}
