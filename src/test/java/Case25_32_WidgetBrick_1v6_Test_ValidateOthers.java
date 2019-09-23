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

public class Case25_32_WidgetBrick_1v6_Test_ValidateOthers {

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
	public void firstNameAsNumber() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.replace("name", "123").create();
		Element formError = WidgetIframecc.getFormError();
		String value = "Please check first name";
		Assertion.isFormError(formError, value);
		Assertion.end();
	}
	
	@Test
	public void lastNameAsNumber() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.replace("name", "First 123").create();
		Element formError = WidgetIframecc.getFormError();
		String value = "Please check last name";
		Assertion.isFormError(formError, value);
		Assertion.end();
	}
	
	@Test
	public void nameAsNumber() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.replace("name", "123 123").create();
		Element formError = WidgetIframecc.getFormError();
		String value = "Please check firstname/last name";
		Assertion.isFormError(formError, value);
		Assertion.end();
	}
	
	@Test
	public void emailWithOutAt() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.replace("email", "missingatsymbol").create();
		Element emailTxb = WidgetIframecc.getEmailTextbox();
		String value = "Please enter a valid email address";
		Assertion.isInvalid(emailTxb, value);
		Assertion.end();
	}
	
	@Test
	public void emailWithOutDot() throws Exception {
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.replace("email", "missingdot@spam").create();
		Element emailTxb = WidgetIframecc.getEmailTextbox();
		String value = "Please enter a valid email address";
		Assertion.isInvalid(emailTxb, value);
		Assertion.end();
	}
	
	
	
	

	
	
}
