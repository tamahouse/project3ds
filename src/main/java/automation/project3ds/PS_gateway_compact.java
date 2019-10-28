package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class PS_gateway_compact {
	
	Driver driver;
	
	String name = "Payment Wall";
	String CPF = "72962940005";
	String CEP = "01452002";
	String areaCode = "11";
	String phone = "994283640";
	public String cardNumber = "4012001037141112";
	String addressNumber = "00";
	String addressComplement = "Any";
	String cardHolder = "Card Holder";
	String cardMonth = "01";
	String cardYear = "22";
	String cvv = "123";
	String street = "43 Flo";
	String city = "Florida";
	String zip = "32043";
	String state_code = "FL";
	String co_code = "US";
	public String co_id = "1";

	
	By id = By.id("cardsForm");
	By cardHolderTxb = By.id("cardholder");
	By cardNumberTxb = By.id("cnumber_field_id");
	By cardexpMonthTxb = By.id("expmonth_field");
	By cardexpYearTxb = By.id("expyear_field");
	By cardCvvTxb = By.id("cvv_id");
	By addressStreet = By.name("street");
	By addressCity = By.name("city");
	By addresstZip = By.name("zip");
	By addressState = By.name("state");
	By addressCountry = By.id("country_select");
	By buyButtonTxb = By.id("pay_button");
	
	
	public PS_gateway_compact (Driver driver) {
		WebElement iframe = driver.getElement(By.id("iframecc")).getWebElement();
		driver.switchTo().frame(iframe);
		this.driver = driver;
	}

	
	public void setCoID(String co_id) {
		this.co_id = co_id;
	}
	
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	public void createPayment() {
		AnnotationPage.sleep(5000);
		setCardHolder();
		setCardNumber();
		setCardFullExp();
		setCardCvv();
		setFullAddress(co_id);
		clickBuyButton();
	}
	
	public void finish3dsPolkOFF() {
		Widget3dsPolk obj = new Widget3dsPolk(driver);
		obj.clickSubmit();
	}
	
	public void finish3dsV1OFF() {
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
	}
	
	public void finish3dsV1ON() {
		WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
		driver.switchTo().frame(iframe);
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
	}
	
	public void finish3dsV2ON() {
		WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
		driver.switchTo().frame(iframe);
		Widget3dsV2 obj = new Widget3dsV2(driver);
		obj.finish();
	}
	
	
	private void setFullAddress(String co_id) {
		if(co_id.equals("2")) {
			street = "4122  Halsey Avenue";
			city = "Toronto";
			zip = "M3B 2W6";
			state_code = "ON";
			co_code = "CA";
		}
		setAddressStreet();
		setAddressCity();
		setAddressZip();
		setAddressState();
		setAddressCountry();
	}
	
	private void setCardFullExp() {
		setExpMonth();
		setExpYear();
	}
	
	private void setCardHolder() {
		AnnotationPage.sleep(7000);
		Element element = driver.getElement(cardHolderTxb);
		element.sendKeys(name);
	}
	
	private void setCardNumber() {
		Element element = driver.getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}
	
	private void setExpMonth() {
		Element element = driver.getElement(cardexpMonthTxb);
		element.sendKeys(cardMonth);
	}
	
	private void setExpYear() {
		Element element = driver.getElement(cardexpYearTxb);
		element.sendKeys(cardYear);
	}
	
	private void setCardCvv() {
		Element element = driver.getElement(cardCvvTxb);
		element.sendKeys(cvv);
	}
	
	private void setAddressStreet() {
		Element element = driver.getElement(addressStreet);
		element.sendKeys(street);
	}
	
	private void setAddressCity() {
		Element element = driver.getElement(addressCity);
		element.sendKeys(city);
	}
	
	private void setAddressZip() {
		Element element = driver.getElement(addresstZip);
		element.sendKeys(zip);
	}
	
	private void setAddressState() {
		try {
		Select select = new Select(driver.getElement(addressState));
		select.selectByValue(state_code);
		}catch (UnexpectedTagNameException e) {
			Element element = driver.getElement(addressState);
			element.sendKeys("Рига");
		}
		
	}
	
	private void setAddressCountry() {
		Select select = new Select(driver.getElement(addressCountry));
		select.selectByValue(co_code);
	}
	
	
	private void clickBuyButton() {
		Element element = driver.getElement(buyButtonTxb);
		element.click();
	}
	

}
