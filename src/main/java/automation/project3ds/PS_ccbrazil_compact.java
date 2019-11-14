package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class PS_ccbrazil_compact {
	
	Driver driver;
	
	String name = "Payment Wall";
	String CPF = "72962940005";
	String CEP = "01452002";
	String areaCode = "11";
	String phone = "994283640";
	public String cardNumber = "4111111111111111";
	String addressNumber = "00";
	String addressComplement = "Any";
	String cardHolder = "Card Holder";
	String cardMonth = "01";
	String cardYear = "22";
	String cvv = "123";
	String birth = "20/05/1980";
	String birthDay = "20";
	String birthMonth = "05";
	String birthYear = "1980";
	public String email;
	
	By id = By.id("cardsForm");
	By cardHolderTxb = By.id("cardholder");
	By cardNumberTxb = By.id("cnumber_field_id");
	By cardexpMonthTxb = By.id("expmonth_field");
	By cardexpYearTxb = By.id("expyear_field");
	By cardCvvTxb = By.id("cvv_id");
	By zipTxb = By.name("zip");
	By cpfTxb = By.id("cpf");
	By emailTxb = By.id("email");
	By birthDayTxb = By.id("birth_date_day");
	By birthMonthTxb = By.id("birth_date_month");
	By birthYearTxb = By.id("birth_date_year");
	By phoneTxb = By.id("phone");
	By buyButtonTxb = By.id("pay_button");
	
	public PS_ccbrazil_compact(Driver driver) {
		WebElement iframe = driver.getElement(By.id("iframecc")).getWebElement();
		driver.switchTo().frame(iframe);
		driver.getElement(id);
		this.driver = driver;
	}
	
	
	public void createPayment() {
		AnnotationPage.sleep(5000);
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
	
	
	public String getEmail() {
		return this.email;
	}
	
	protected void setCardFullExp() {
		setExpMonth();
		setExpYear();
	}
	
	protected void setCardHolder() {
		Element element = driver.getElement(cardHolderTxb);
		element.sendKeys(name);
	}
	
	protected void setCardNumber() {
		Element element = driver.getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	protected void setExpMonth() {
		Element element = driver.getElement(cardexpMonthTxb);
		element.sendKeys(cardMonth);
	}
	
	protected void setExpYear() {
		Element element = driver.getElement(cardexpYearTxb);
		element.sendKeys(cardYear);
	}
	
	protected void setCardCvv() {
		Element element = driver.getElement(cardCvvTxb);
		element.sendKeys(cvv);
	}
	
	protected void setZip() {
		Element element = driver.getElement(zipTxb);
		element.sendKeys(CEP);
	}
	
	protected void setCPF() {
		Element element = driver.getElement(cpfTxb);
		element.sendKeys(CPF);
	}
	
	protected void setEmail() {
		email = "meo"+ AnnotationPage.timestamp()+"@spam4.me";
		Element element = driver.getElement(emailTxb);
		element.sendKeys(email);
	}
	
	protected void setBirthDay() {
		Element element = driver.getElement(birthDayTxb);
		element.sendKeys(birthDay);
	}
	
	protected void setBirthMonth() {
		Element element = driver.getElement(birthMonthTxb);
		element.sendKeys(birthMonth);
	}
	
	protected void setBirthYear() {
		Element element = driver.getElement(birthYearTxb);
		element.sendKeys(birthYear);
	}
	
	protected void setPhone() {
		Element element = driver.getElement(phoneTxb);
		element.sendKeys(phone);
	}
	
	protected void clickBuyButton() {
		Element element = driver.getElement(buyButtonTxb);
		element.click();
	}
	
	protected void setFullBirth() {
		setBirthDay();
		setBirthMonth();
		setBirthYear();
	}

}
