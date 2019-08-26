import java.util.List;

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

public class WidgetBrick_1v6_Test_EmptyTextbox {

	String host = "http://develop.wallapi.bamboo.stuffio.com/admin/test-offerwall?_application_name=QA+Test+Project+-+Digital+Goods+%28%29%5B101280%5D&data%5Ba_id%5D=101280&data%5Bwidget%5D=p1&data%5Bco_id%5D=1&data%5Buid%5D=test_user_chase&are_flexible_call=on&data%5Bamount%5D=5&data%5BcurrencyCode%5D=USD&data%5Bag_name%5D=Test+Product&data%5Bag_type%5D=fixed&data%5Bag_external_id%5D=1&data%5Bag_period_length%5D=&data%5Bag_period_type%5D=&data%5Bag_recurring%5D=&data%5Bcustom%5D%5Bbrick_1_6%5D=1";

	static Driver driver;
	static Element whiteSpace;

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
	public void emptyTextboxClick() throws Exception {
		ThisAction action = new ThisAction() {
			@Override
			public void run(Element element){
				element.click();
				whiteSpace.click();
			}
		};
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton();
		WidgetIframecc.clickUserDifferentCard();
		assertEmptyTextbox(action);
		Assertion.end();
	}
	
	@Test
	public void emptyTextboxSubmit() throws Exception {
		ThisAction action = new ThisAction() {
			@Override
			public void run(Element element){
			}
		};
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton();
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.clickBuyButton();
		assertEmptyTextbox(action);
		Assertion.end();
	}
	
	interface ThisAction {
		public default void run(Element element) {
		}
	}
	
	private static void assertEmptyTextbox(ThisAction action) {
		By row = By.xpath("//div[contains(@class, 'is-active')]//div[@class='brick-form-row']/div/div");
		By white = WidgetIframecc.white;
		whiteSpace = driver.getElement(white);
		List<Element> list = driver.getElements(row);
		for (Element element : list) {
			action.run(element);
//			element.click();
//			whiteSpace.click();
			Element label = element.getElement(By.xpath("./*[text()][1]"));
			String labelText = label.getText();
			String eText = null;
			if (labelText.contains("Name on card")) {
				eText = "Please enter your name on the card";
			}else if(labelText.contains("Card number")) {
				eText = "Please enter your card number";
			}else if(labelText.contains("Expiration date")) {
				eText = "Please enter an expiry date";
			}else if(labelText.contains("CVV")) {
				eText = "Please enter CVV code";
			}else if(labelText.contains("ZIP / Postal code")) {
				eText = "Please enter ZIP code";
			}else if(labelText.contains("Email")) {
				eText = "Please enter a valid email address";
			}
			Assertion.isInvalid(element, eText);
		}
	}
}
