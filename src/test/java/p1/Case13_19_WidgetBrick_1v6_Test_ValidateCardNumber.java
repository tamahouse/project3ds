package p1;
import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.ExtentManager;
import automation.project3ds.Login;
import automation.project3ds.WidgetIframecc;
import automation.project3ds.WidgetMainFrame;
import automation.project3ds.WidgetMulti;

public class Case13_19_WidgetBrick_1v6_Test_ValidateCardNumber {

	String host = AnnotationPage.hostMap.get("p1");
	
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
	
	@BeforeMethod
	public void openBrick() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMulti.clickPaymentMethod("gateway");
		WidgetMulti.clickBuyButton();
	}

	@Test
	public void invalidValue() throws Exception {
		String cardNumber = "7777777777777777";
		WidgetIframecc.setCardNumber(cardNumber);
		ExtentManager.addScreenshot("isInvalid");
		Assertion.end();
	}
	
	@Test
	public void unsupportedBrand() throws Exception {
		String cardNumber = "3566000020000410";
		WidgetIframecc.setCardNumber(cardNumber);
		ExtentManager.addScreenshot("isInvalid");
		Assertion.end();
	}
	
	@Test
	public void supportedBrandButInvalidValue() throws Exception {
		String cardNumber = "4000000000000000";
		WidgetIframecc.setCardNumber(cardNumber);
		Element icon = WidgetIframecc.getActiveBrandIcon();
		Boolean isIconActive = icon.getAttribute("class").contains("visa");
		Assertion.get().assertEquals(isIconActive, true, "[Icon]");
		ExtentManager.addScreenshot("isInvalid");
		Assertion.end();
	}
	
	@Test
	public void visaCard() throws Exception {
		String cardNumber = "4000000000000002";
		WidgetIframecc.setCardNumber(cardNumber);
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
		ExtentManager.addScreenshot("visa");
		Assertion.end();
	}
	
	@Test
	public void masterCard() throws Exception {
		String cardNumber = "5200000000000007";
		WidgetIframecc.setCardNumber(cardNumber);
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
		ExtentManager.addScreenshot("master");
		Assertion.end();
	}
	
	@Test
	public void amexCard() throws Exception {
		String cardNumber = "373953192351004";
		WidgetIframecc.setCardNumber(cardNumber);
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
		ExtentManager.addScreenshot("amex");
		Assertion.end();
	}
	

	
	
}
