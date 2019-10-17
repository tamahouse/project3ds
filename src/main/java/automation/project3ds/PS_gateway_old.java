package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.UnexpectedTagNameException;

public class PS_gateway_old {
	
	static String name = "Payment Wall";
	static String CPF = "72962940005";
	static String CEP = "01452002";
	static String areaCode = "11";
	static String phone = "994283640";
	public static String cardNumber = "4012001037141112";
	static String addressNumber = "00";
	static String addressComplement = "Any";
	static String cardHolder = "Card Holder";
	static String cardMonth = "01";
	static String cardYear = "22";
	static String cvv = "123";
	static String street = "43 Flo";
	static String city = "Florida";
	static String zip = "32043";
	static String state_code = "FL";
	static String co_code = "US";
	public static String co_id = "1";

	
	static By id = By.id("cardsForm");
	static By cardHolderTxb = By.id("cardholder");
	static By cardNumberTxb = By.id("cnumber_field_id");
	static By cardexpMonthTxb = By.id("expmonth_field");
	static By cardexpYearTxb = By.id("expyear_field");
	static By cardCvvTxb = By.id("cvv_id");
	static By addressStreet = By.name("street");
	static By addressCity = By.name("city");
	static By addresstZip = By.name("zip");
	static By addressState = By.name("state");
	static By addressCountry = By.id("country_select");
	static By buyButtonTxb = By.id("pay_button");
	
	public static Driver getFrame() {
		Driver driver = WidgetMainFrame.getFrame();
		WebElement iframe = driver.getElement(By.id("iframecc")).getWebElement();
		driver.switchTo().frame(iframe);
		return driver;
	}
	
	public static void createPayment() {
		setCardHolder();
		setCardNumber();
		setCardFullExp();
		setCardCvv();
		setFullAddress(co_id);
		clickBuyButton();
	}
	
	public static void finish3dsPolkOFF() {
		Driver driver = getFrame();
		Widget3dsPolk obj = new Widget3dsPolk(driver);
		obj.clickSubmit();
	}
	
	public static void finish3dsV1OFF() {
		Driver driver = getFrame();
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
	}
	
	public static void finish3dsV1ON() {
		Driver driver = getFrame();
		WebElement iframe = driver.getElement(By.id("Cardinal-CCA-IFrame")).getWebElement();
		driver.switchTo().frame(iframe);
		Widget3dsV1 obj = new Widget3dsV1(driver);
		obj.finish();
	}
	
	
	private static void setFullAddress(String co_id) {
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
	
	private static void setCardFullExp() {
		setExpMonth();
		setExpYear();
	}
	
	private static void setCardHolder() {
		Element element = getFrame().getElement(cardHolderTxb);
		element.sendKeys(name);
	}
	
	private static void setCardNumber() {
		Element element = getFrame().getElement(cardNumberTxb);
		element.sendKeys("preset");
		AnnotationPage.sleep(5000);
		element = getFrame().getElement(cardNumberTxb);
		element.sendKeys(cardNumber);
	}
	
	private static void setExpMonth() {
		Element element = getFrame().getElement(cardexpMonthTxb);
		element.sendKeys(cardMonth);
	}
	
	private static void setExpYear() {
		Element element = getFrame().getElement(cardexpYearTxb);
		element.sendKeys(cardYear);
	}
	
	private static void setCardCvv() {
		Element element = getFrame().getElement(cardCvvTxb);
		element.sendKeys(cvv);
	}
	
	private static void setAddressStreet() {
		Element element = getFrame().getElement(addressStreet);
		element.sendKeys(street);
	}
	
	private static void setAddressCity() {
		Element element = getFrame().getElement(addressCity);
		element.sendKeys(city);
	}
	
	private static void setAddressZip() {
		Element element = getFrame().getElement(addresstZip);
		element.sendKeys(zip);
	}
	
	private static void setAddressState() {
		try {
		Select select = new Select(getFrame().getElement(addressState));
		select.selectByValue(state_code);
		}catch (UnexpectedTagNameException e) {
			Element element = getFrame().getElement(addressState);
			element.sendKeys("Рига");
		}
		
	}
	
	private static void setAddressCountry() {
		Select select = new Select(getFrame().getElement(addressCountry));
		select.selectByValue(co_code);
	}
	
	
	private static void clickBuyButton() {
		Element element = getFrame().getElement(buyButtonTxb);
		element.click();
	}
	

}
