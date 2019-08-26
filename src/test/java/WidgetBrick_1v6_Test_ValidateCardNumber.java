import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.Login;
import automation.project3ds.WidgetIframecc;
import automation.project3ds.WidgetMainFrame;

public class WidgetBrick_1v6_Test_ValidateCardNumber {

	String host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";

	static Driver driver;

	@BeforeClass
	public void setUp() throws Exception {
		Login.login(host);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		AnnotationPage.driver = null;
	}

	@Test
	public void invalidValue() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String cardNumber = "7777777777777777";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton();
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.setCardNumber(cardNumber);
		Element element = WidgetIframecc.getCardNumberTextbox();
		Assertion.isInvalid(element);
		Assertion.end();
	}
	
	@Test
	public void unsupportedBrand() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String cardNumber = "3566000020000410";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton();
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.setCardNumber(cardNumber);
		Element element = WidgetIframecc.getCardNumberTextbox();
		Assertion.isValid(element);
		Assertion.end();
	}
	
	@Test
	public void supportedBrandButInvalidValue() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String cardNumber = "4000000000000000";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton();
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.setCardNumber(cardNumber);
		Element element = WidgetIframecc.getCardNumberTextbox();
		Assertion.isInvalid(element);
		Element icon = WidgetIframecc.getActiveBrandIcon();
		Boolean isIconActive = icon.getAttribute("class").contains("visa");
		Assertion.get().assertEquals(isIconActive, true, "[Icon]");
		Assertion.end();
	}
	
	@Test
	public void visaCard() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String cardNumber = "4000000000000002";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton();
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.setCardNumber(cardNumber);
		Element element = WidgetIframecc.getCardNumberTextbox();
		Assertion.isValid(element);
		Element icon = WidgetIframecc.getActiveBrandIcon();
		Boolean isIconActive = icon.getAttribute("class").contains("visa");
		Element cvvTextbox = WidgetIframecc.getCvvTextbox();
		Element cvvLabel = cvvTextbox.getElement(By.xpath("./*[contains(@class,'label')]"));
		String cvvTxt = cvvLabel.getText();
		Element cvvIcon = cvvTextbox.getElement(By.xpath("./*[contains(@class,'active')]"));
		Boolean isCVVIconCorrect = cvvIcon.getAttribute("class").contains("cvv");
		Assertion.get().assertEquals(cvvTxt	, "CVV/CVC", "[CVV Text]");
		Assertion.get().assertEquals(isIconActive, true, "[Icon]");
		Assertion.get().assertEquals(isCVVIconCorrect, true, "[CVV Icon]");
		Assertion.end();
	}
	
	@Test
	public void masterCard() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String cardNumber = "5200000000000007";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton();
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.setCardNumber(cardNumber);
		Element element = WidgetIframecc.getCardNumberTextbox();
		Assertion.isValid(element);
		Element icon = WidgetIframecc.getActiveBrandIcon();
		Boolean isIconActive = icon.getAttribute("class").contains("mastercard");
		Element cvvTextbox = WidgetIframecc.getCvvTextbox();
		Element cvvLabel = cvvTextbox.getElement(By.xpath("./*[contains(@class,'label')]"));
		String cvvTxt = cvvLabel.getText();
		Element cvvIcon = cvvTextbox.getElement(By.xpath("./*[contains(@class,'active')]"));
		Boolean isCVVIconCorrect = cvvIcon.getAttribute("class").contains("cvv");
		Assertion.get().assertEquals(cvvTxt	, "CVV/CVC", "[CVV Text]");
		Assertion.get().assertEquals(isIconActive, true, "[Icon]");
		Assertion.get().assertEquals(isCVVIconCorrect, true, "[CVV Icon]");
		Assertion.end();
	}
	
	@Test
	public void amexCard() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		String cardNumber = "373953192351004";
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton();
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.setCardNumber(cardNumber);
		Element element = WidgetIframecc.getCardNumberTextbox();
		Assertion.isValid(element);
		Element icon = WidgetIframecc.getActiveBrandIcon();
		Boolean isIconActive = icon.getAttribute("class").contains("amex");
		Element cvvTextbox = WidgetIframecc.getCvvTextbox();
		Element cvvLabel = cvvTextbox.getElement(By.xpath("./*[contains(@class,'label')]"));
		String cvvTxt = cvvLabel.getText();
		Element cvvIcon = cvvTextbox.getElement(By.xpath("./*[contains(@class,'active')]"));
		Boolean isCVVIconCorrect = cvvIcon.getAttribute("class").contains("cid");
		Assertion.get().assertEquals(cvvTxt	, "CID", "[CVV Text]");
		Assertion.get().assertEquals(isIconActive, true, "[Icon]");
		Assertion.get().assertEquals(isCVVIconCorrect, true, "[CVV Icon]");
		Assertion.end();
	}
	

	
	
}
