package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetMainFrame.WidgetType;

public class WidgetPage extends BasePage {
	
	By submitBtn = By.xpath("//*[@id='testOfferwall']//input[@value='Submit']");
	
	public WidgetPage(Driver driver) {
		super(driver);
	}
	
	public Object getWidgetMainFrame(String type) {
		switch(type) {
		case WidgetType.MULTI:
			return new WidgetMulti(driver);
		case WidgetType.UNI:
			return new WidgetUni(driver);
		case WidgetType.P2_3:
			return new Widget_p2_3(driver);
		case WidgetType.TERMNIAL:
			return new WidgetTerminal(driver);
		}
		return null;
	}

	public WidgetMulti getMultiWidget() {
		
		return new WidgetMulti(driver);
	}
	
	public WidgetUni getWidgetUni() {
		return new WidgetUni(driver);
	}

	public WidgetTerminal getWidgetTerminal() {
		return new WidgetTerminal(driver);
	}
	
	public Widget_p2_3 getWidget_p2_3() {
		return new Widget_p2_3(driver);
	}
	public void setCustomItem(String key, String value) {
		By customItem = By.xpath("//input[@name='data[custom]["+key+"]']");
		Element element = driver.getElement(customItem);
		element.moveToTopView();
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickSubmitButton() {
		Element element = driver.getElement(submitBtn);
		element.click();
		driver.sleep(1000);
	}
}
