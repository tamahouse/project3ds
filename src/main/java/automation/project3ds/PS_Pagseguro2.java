package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class PS_Pagseguro2 {
	
	static String name = "Payment Wall";
	static String CPF = "72962940005";
	static String CEP = "01452002";
	static String areaCode = "11";
	static String phone = "994283640";
	static String cardNumber = "4509 9535 6623 3704";
	static String addressNumber = "00";
	static String addressComplement = "Any";
	static String cardHolder = "Card Holder";
	static String cardMonth = "01";
	static String cardYear = "22";
	static String cvv = "123";
	static String birth = "20/05/1980";
	
	
	static By senderNameTxb = By.id("senderName");
	static By senderCPFTxb = By.id("senderCPF");
	static By senderAreaCodeTxb = By.id("senderAreaCode");
	static By senderPhoneTxb = By.id("senderPhone");
	static By shippingAddressPostalCodeTxb = By.id("shippingAddressPostalCode");
	static By shippingAddressNumberTxb = By.xpath("//*[@style=\"display: block;\"]/*[@id='shippingAddressNumber']");
	static By shippingAddressComplementTxb = By.xpath("//*[@style=\"display: block;\"]/*[@id='shippingAddressComplement']");
	
	static By creditCardOption = By.id("creditCardOption");
	static By bookletOption = By.id("bookletOption");
	
	static By nextButton = By.xpath("//*[@class=\"next pagseguro-button mainActionButton\"]");
	
	static By creditCardNumberTxb = By.id("creditCardNumber");
	static By clickToOpenCardBrand = By.xpath("//*[@class='brandView unknown'][@style=\"display: block;\"]//*[@class='clickToOpen']");
	static By visaBrand = By.xpath("//*[@class='brandView unknown opened']//a[@class='omniture-click'][@name='visa']");
	static By creditCardHolderNameTxb = By.id("creditCardHolderName");
	static By creditCardDueDate_MonthTxb = By.id("creditCardDueDate_Month");
	static By creditCardDueDate_Year = By.id("creditCardDueDate_Year");
	static By creditCardCVVTxb = By.id("creditCardCVV");
	static By cardInstallmentQuantitySelect = By.id("cardInstallmentQuantity");
	
	static By holderCPFTxb = By.id("holderCPF");
	static By holderAreaCodeTxb = By.id("holderAreaCode");
	static By holderPhoneTxb = By.id("holderPhone");
	static By holderBornDateTxb = By.id("holderBornDate");
	
	static By paymentTotal = By.xpath("//*[@id='payment-total']//dd");
	static By completed = By.id("payment-details");

	public static Driver getFrame() {
		Driver driver = WidgetMainFrame.getFrame();
		WebElement frameMedia = driver.getElement(By.xpath("//iframe[@id='uolPSMediator'][@style =\"display: block;\" ]"),30000).getWebElement();
		driver.switchTo().frame(frameMedia);
		WebElement frameApp = driver.getElement(By.id("UolPS-application")).getWebElement();
		driver.switchTo().frame(frameApp);
		return driver;
	}
	
	public static String createBoletoPayment() {
		setSender();
		clickBoletoOption();
		clickNextButton();
		waitForComplete();
		String transactionCode = waitForComplete();
		return transactionCode;
	}
	
	public static String createCreditCardPayment() {
		setSender();
		clickCreditCardOption();
		setCreditCard();
		setHolder();
		String transactionCode = waitForComplete();
		return transactionCode;
	}
	
	public static void setCreditCard() {
		setCardFullNumber();
		setCardFullExp();
		setCardHolder();
		setCardCVV();
		selectProduct();
		clickNextButton();
	}
	
	private static void setSender() {
		AnnotationPage.sleep(2000);
		setSenderFullAddress();
		setSenderName();
		setSenderCPF();
		setSenderFullPhone();
		clickNextButton();
	}
	
	private static void setHolder() {
		setHolderCPF();
		setHolderFullPhone();
		setHolderBirth();
		clickNextButton();
	}
	
	private static void setSenderName() {
		Element element = getFrame().getElement(senderNameTxb);
		element.sendKeys(name);
	}
	
	private static void setSenderCPF() {
		Element element = getFrame().getElement(senderCPFTxb);
		element.sendKeys(CPF);
	}
	
	private static void setSenderAreaCode() {
		Element element = getFrame().getElement(senderAreaCodeTxb);
		element.sendKeys(areaCode);
	}
	
	private static void setSenderPhone() {
		Element element = getFrame().getElement(senderPhoneTxb);
		element.sendKeys(phone);
	}
	
	private static void setSenderFullPhone() {
		setSenderAreaCode();
		setSenderPhone();
	}
	
	
	private static void setSenderFullAddress() {
		setCEP();
		setAddressNumber();
		setAddressComplement();
	}
	
	private static void setCEP() {
		Element element = getFrame().getElement(shippingAddressPostalCodeTxb);
		element.sendKeys(CEP);
	}
	
	private static void setAddressNumber() {
		Element element = getFrame().getElement(shippingAddressNumberTxb);
		element.sendKeys(addressNumber);
	}
	
	private static void setAddressComplement() {
		Element element = getFrame().getElement(shippingAddressComplementTxb);
		element.sendKeys(addressComplement);
	}
	
	private static void clickNextButton() {
		Element element = getFrame().getElement(nextButton);
		element.click();
	}
	
	private static void clickCreditCardOption() {
		Element element = getFrame().getElement(creditCardOption);
		element.click();
	}
	
	private static void clickBoletoOption() {
		Element element = getFrame().getElement(bookletOption);
		element.click();
	}
	
	private static void setCardNumber() {
		Element element = getFrame().getElement(creditCardNumberTxb);
		element.sendKeys(cardNumber);
	}
	
	private static void clickToOpenCardBrand() {
		Element element = getFrame().getElement(clickToOpenCardBrand);
		element.click();
	}
	
	private static void clickCardBrand() {
		AnnotationPage.sleep(3000);
		Element element = getFrame().getElement(visaBrand);
		element.clickJS();
	}
	
	private static void setCardFullNumber() {
		setCardNumber();
		clickToOpenCardBrand();
		clickCardBrand();
	}
	
	private static void setCardMonth() {
		Element element = getFrame().getElement(creditCardDueDate_MonthTxb);
		element.sendKeys(cardMonth);
	}
	
	private static void setCardYear() {
		Element element = getFrame().getElement(creditCardDueDate_Year);
		element.sendKeys(cardYear);
	}
	
	private static void setCardFullExp() {
		setCardMonth();
		setCardYear();
	}
	
	private static void setCardHolder() {
		Element element = getFrame().getElement(creditCardHolderNameTxb);
		element.sendKeys(cardHolder);
	}
	
	private static void setCardCVV() {
		Element element = getFrame().getElement(creditCardCVVTxb);
		element.sendKeys(cvv);
	}
	
	private static void selectProduct() {
		try {
		Select select = new Select(getFrame().getElement(cardInstallmentQuantitySelect,1000));
		select.selectByValue("1");
		}catch(Exception e) {
			System.out.println(e.getClass().getName());
		}
	}
	
	private static void setHolderCPF() {
		Element element = getFrame().getElement(holderCPFTxb);
		element.sendKeys(CPF);
	}
	
	private static void setHolderAreaCode() {
		Element element = getFrame().getElement(holderAreaCodeTxb);
		element.sendKeys(areaCode);
	}
	
	private static void setHolderPhone() {
		Element element = getFrame().getElement(holderPhoneTxb);
		element.sendKeys(phone);
	}
	
	private static void setHolderFullPhone() {
		setHolderAreaCode();
		setHolderPhone();
	}
	
	private static void setHolderBirth() {
		Element element = getFrame().getElement(holderBornDateTxb);
		element.sendKeys(birth);
	}
	
	private static Boolean isPaymentDetailOpen() {
		Element element = getFrame().getElement(completed,20000);
		String str = element.getAttribute("class");
		if(str.contains("closed")) {
			return false;
		}else {
			return true;
		}
	}
	
	private static void clickOpenPaymentDetail() {
		Boolean isOpen = isPaymentDetailOpen();
		if(!isOpen) {
			Element element = getFrame().getElement(completed,20000);
			element.click();
		}
	}
	
	private static String waitForComplete() {
		clickOpenPaymentDetail();
		Element element = getFrame().getElement(By.id("transactionCode"));
		String transactionCode = element.getText().replace("-", "");
		return transactionCode;
	}
}
