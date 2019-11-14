package p1;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Assertion;
import automation.project3ds.Brick_1v6;
import automation.project3ds.Driver;
import automation.project3ds.Element;
import automation.project3ds.Login;
import automation.project3ds.WidgetMulti;

public class Case25_32_WidgetBrick_1v6_Test_ValidateOthers {

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
	public void firstNameAsNumber() throws Exception {
		Brick_1v6.payment("name", "123").create();
		Element formError = Brick_1v6.getFormError();
		String value = "Please check first name";
		Assertion.isFormError(formError, value);
		Assertion.end();
	}
	
	@Test
	public void lastNameAsNumber() throws Exception {
		Brick_1v6.payment("name", "First 123").create();
		Element formError = Brick_1v6.getFormError();
		String value = "Please check last name";
		Assertion.isFormError(formError, value);
		Assertion.end();
	}
	
	@Test
	public void nameAsNumber() throws Exception {
		Brick_1v6.payment("name", "123 123").create();
		Element formError = Brick_1v6.getFormError();
		String value = "Please check firstname/last name";
		Assertion.isFormError(formError, value);
		Assertion.end();
	}
	
	@Test
	public void emailWithOutAt() throws Exception {
		Brick_1v6.payment("email", "missingatsymbol").create();
		Element emailTextbox = Brick_1v6.getEmailTextbox();
		Assertion.assertErrorText(emailTextbox);
		Assertion.end();
	}
	
	@Test
	public void emailWithOutDot() throws Exception {
		Brick_1v6.payment("email", "missingdot@spam").create();
		Element emailTxb = Brick_1v6.getEmailTextbox();
		Assertion.assertErrorText(emailTxb);
		Assertion.end();
	}
	
	
	
	

	
	
}
