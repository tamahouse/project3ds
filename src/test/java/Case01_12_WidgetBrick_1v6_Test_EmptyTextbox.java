import java.util.List;

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

public class Case01_12_WidgetBrick_1v6_Test_EmptyTextbox {

	String host = AnnotationPage.hostMap.get("p1");

	static Driver driver;


	@BeforeClass
	public void setUp() throws Exception {
		Login.login(host);
	}

	
	static Element whiteSpace;

	@AfterClass
	public void tearDown() {
		driver.quit();
		AnnotationPage.driver = null;
	}

	@Test
	public void case01_06_emptyTextboxClick() throws Exception {
		ThisAction action = new ThisAction() {
			@Override
			public void run(Element element){
				element.click();
				whiteSpace.click();
			}
		};
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		assertClickEmptyTextbox(action);
		Assertion.end();
	}
	
	@Test
	public void case07_12_emptyTextboxSubmit() throws Exception {
		ThisAction action = new ThisAction() {
			@Override
			public void run(Element element){
			}
		};
		driver = AnnotationPage.getDriver();
		driver.get(host);
		WidgetMainFrame.clickBuyButton(type);
		WidgetIframecc.clickUserDifferentCard();
		WidgetIframecc.clickBuyButton();
		assertSubmitEmptyTextbox(action);
		Assertion.end();
	}
	
	interface ThisAction {
		public default void run(Element element) {
		}
	}
	
	private static void assertSubmitEmptyTextbox(ThisAction action) throws Exception {
		Thread.sleep(2000);
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
	
	private static void assertClickEmptyTextbox(ThisAction action) throws Exception {
		Thread.sleep(2000);
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
				eText = "Please enter email";
			}
			Assertion.isInvalid(element, eText);
		}
	}
}
