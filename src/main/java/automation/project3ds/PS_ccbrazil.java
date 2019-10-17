package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_ccbrazil {
	
	static String name = "Payment Wall";
	static String CPF = "72962940005";
	static String CEP = "01452002";
	static String areaCode = "11";
	static String phone = "994283640";
	public static String cardNumber = "4111111111111111";
	static String addressNumber = "00";
	static String addressComplement = "Any";
	static String cardHolder = "Card Holder";
	static String cardMonth = "01";
	static String cardYear = "22";
	static String cvv = "123";
	static String birth = "20/05/1980";
	static String birthDay = "20";
	static String birthMonth = "05";
	static String birthYear = "1980";
	public static String email;
	
	static By id = By.id("cardsForm");
	static By cardHolderTxb = By.id("cardholder");
	static By cardNumberTxb = By.id("cnumber_field_id");
	static By cardexpMonthTxb = By.id("expmonth_field");
	static By cardexpYearTxb = By.id("expyear_field");
	static By cardCvvTxb = By.id("cvv_id");
	static By zipTxb = By.name("zip");
	static By cpfTxb = By.id("cpf");
	static By emailTxb = By.id("email");
	static By birthDayTxb = By.id("birth_date_day");
	static By birthMonthTxb = By.id("birth_date_month");
	static By birthYearTxb = By.id("birth_date_year");
	static By phoneTxb = By.id("phone");
	static By buyButtonTxb = By.id("pay_button");
	
	public static Driver getFrame() {
		Driver driver = WidgetMainFrame.getFrame();
		WebElement iframe = driver.getElement(By.id("iframecc")).getWebElement();
		driver.switchTo().frame(iframe);
		driver.getElement(id);
		return driver;
	}
	
	
	public static void createPayment() {
		setCardHolder();
		setCardNumber();
		setCardFullExp();
		setCardCvv();
		setZip();
		setCPF();
		setEmail();
		setFullBirth();
		setPhone();
		clickBuyButton();
	}
	
	protected static void setCardFullExp() {
		setExpMonth();
		setExpYear();
	}
	
	protected static void setCardHolder() {
		Element element = getFrame().getElement(cardHolderTxb);
		element.sendKeys(name);
	}
	
	protected static void setCardNumber() {
		Element element = getFrame().getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}
	
	protected static void setExpMonth() {
		Element element = getFrame().getElement(cardexpMonthTxb);
		element.sendKeys(cardMonth);
	}
	
	protected static void setExpYear() {
		Element element = getFrame().getElement(cardexpYearTxb);
		element.sendKeys(cardYear);
	}
	
	protected static void setCardCvv() {
		Element element = getFrame().getElement(cardCvvTxb);
		element.sendKeys(cvv);
	}
	
	protected static void setZip() {
		Element element = getFrame().getElement(zipTxb);
		element.sendKeys(CEP);
	}
	
	protected static void setCPF() {
		Element element = getFrame().getElement(cpfTxb);
		element.sendKeys(CPF);
	}
	
	protected static void setEmail() {
		email = "meo"+ AnnotationPage.timestamp()+"@spam4.me";
		Element element = getFrame().getElement(emailTxb);
		element.sendKeys(email);
	}
	
	protected static void setBirthDay() {
		Element element = getFrame().getElement(birthDayTxb);
		element.sendKeys(birthDay);
	}
	
	protected static void setBirthMonth() {
		Element element = getFrame().getElement(birthMonthTxb);
		element.sendKeys(birthMonth);
	}
	
	protected static void setBirthYear() {
		Element element = getFrame().getElement(birthYearTxb);
		element.sendKeys(birthYear);
	}
	
	protected static void setPhone() {
		Element element = getFrame().getElement(phoneTxb);
		element.sendKeys(phone);
	}
	
	protected static void clickBuyButton() {
		Element element = getFrame().getElement(buyButtonTxb);
		element.click();
	}
	
	protected static void setFullBirth() {
		setBirthDay();
		setBirthMonth();
		setBirthYear();
	}

}
