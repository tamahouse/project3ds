package p1;
import java.util.List;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import automation.project3ds.*;

public class Case01_12_WidgetBrick_1v6_Test_EmptyTextbox {

	String host = AnnotationPage.hostMap.get("p1");
	String shortcode = "gateway";
	
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
	public void case01_06_emptyTextboxClick() throws Exception {
		List<Element> list = Brick_1v6.getTextboxList();
		for (Element element : list) {
			element.click();
			Brick_1v6.clickWhiteSpace();
			Assertion.assertErrorText(element);
		}
		ExtentManager.addScreenshot("isInvalid");
		Assertion.end();
	}
	

	
	@Test
	public void case07_12_emptyTextboxSubmit() throws Exception {
		Brick_1v6.clickBuyButton();
		List<Element> list = Brick_1v6.getTextboxList();
		for (Element element : list) {
			Assertion.assertErrorText(element);
		}
		ExtentManager.addScreenshot("isInvalid");
		Assertion.end();
	}
	
	
}
