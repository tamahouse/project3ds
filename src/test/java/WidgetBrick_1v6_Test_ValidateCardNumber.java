import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.Login;
import automation.project3ds.WidgetIframecc;
import automation.project3ds.WidgetMainFrame;

public class WidgetBrick_1v6_Test_ValidateCardNumber {

	String host;
	String type;
	
	static Driver driver;

	@Parameters({"type"})
	@BeforeClass
	public void setUp(String type) throws Exception {
		this.type = type;
		host = AnnotationPage.hostMap.get(type);
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
		WidgetMainFrame.clickBuyButton(type);
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
		WidgetMainFrame.clickBuyButton(type);
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
		WidgetMainFrame.clickBuyButton(type);
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
		WidgetMainFrame.clickBuyButton(type);
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
		WidgetMainFrame.clickBuyButton(type);
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
		WidgetMainFrame.clickBuyButton(type);
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
