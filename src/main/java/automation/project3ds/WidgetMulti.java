package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class WidgetMulti extends WidgetObject {
	
	private By buyBtn = By.id("ps_psb");
	private By thankyou = By.xpath("//*[@id='ps_content']/h3[text()]");
	
	WidgetMulti(Driver driver) {
		super(driver);
	}
	
	public void click(String shortcode) throws Exception{
		this.clickPaymentMethod(shortcode);
		this.clickPrice(0);
		this.clickBuyButton();
	}
	
	public void click(String shortcode, String price, String currency) throws Exception{
		this.clickPaymentMethod(shortcode);
		this.clickPrice(price, currency);
		this.clickBuyButton();
	}
	
	
	public Boolean waitForSuccess() {
		return driver.isExist(thankyou);
}

	public String getLogoUrl(String shortcode) throws Exception {
		String url = null;
		clickPaymentMethod(shortcode);
		String script = "return window.getComputedStyle(document.querySelector('" + ".pm_" + shortcode
				+ "'),':after').getPropertyValue('background-image')";
		url = driver.getStringJS(script);
		if (!url.contains("http")) {
			url = driver.getElement(By.xpath("//*[@id='" + "tab_"+shortcode + "']/b")).getCssValue("background-image");
		}
		url = url.replace("url(\"", "").replace("\")", "");

		return url;
	}
	
	public void clickPrice(int index) {
		By byprice = By.xpath("//*[@id='ps_price']//tr");
		driver.getElement(byprice);
		List<Element> list = driver.getElements(byprice);
		Element element = list.get(index);
		element.highlight();
		try {
		element.click();
		}catch (Exception e) {
			
		}
	}
	
	public void clickPrice(String price, String currency) {
		By byprice = By.xpath("//*[@id='ps_price']//tr/td[@class='price']/b");
		driver.getElement(byprice);
		List<Element> list = driver.getElements(byprice);
		for(Element element: list) {
			String str = element.getText();
			String actualPrice = str.replaceAll("[^0-9.,]+","");;
			String actualCurrency = str.replaceAll("[0-9., $]","");
			if(price.equals(actualPrice) && actualCurrency.equals(currency)) {
				element.click();
			}
		}
	}
	
	public void clickBuyButton() throws Exception {
		Element element = driver.getElement(buyBtn, 5000);
		element.click();

	}

	public void clickPaymentMethod(String shortcode) throws Exception {
		String id = "tab_"+shortcode;
			for (int i = 0; i < 20; i++) {
				try {
					Element element = driver.getElement(By.id(id));
					String className = element.getAttribute("class");
					if(!className.contains(" sel")) {
					element.click();
					}
					i = 500;
				} catch (Exception e) {
					driver.getElement(By.id("ps_next")).click();
				}
		}

	}
}
