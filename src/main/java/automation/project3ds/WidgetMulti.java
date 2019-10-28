package automation.project3ds;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;

public class WidgetMulti extends WidgetMainFrame {
	
	private By buyBtn = By.id("ps_psb");
	private By thankyou = By.xpath("//h3[text()='Thank you for your purchase!']");
	
	WidgetMulti(Driver driver) {
		super(driver);
	}

	public Object createClick(String shortcode) throws Exception{
		this.clickPaymentMethod(shortcode);
		this.clickPrice(0);
		this.clickBuyButton();
		if(shortcode.equals("neosurf")) {
			return new PS_Neosurf(driver);
		}else if(shortcode.equals("pagseguro")) {
			return new PS_Pagseguro(driver);
		}else if(shortcode.equals("ccbrazil")) {
			return new PS_ccbrazil(driver);
		}else if(shortcode.equals("sofortbt")) {
			return new PS_Sofortbt(driver);
		}else if(shortcode.equals("giropay")) {
			return new PS_Giropay(driver);
		}else if(shortcode.equals("vtc")) {
			return new PS_vtc(driver);
		}
		return null;
	}
	
	
	public PS_gateway_compact getPS_gateway_old() {
		return new PS_gateway_compact(driver);
	}
	
	public PS_gateway_brick_1v5 getPS_gateway_brick_1v5() {
		return new PS_gateway_brick_1v5(driver);
	}
	
	
	public Boolean getCompleteMessage() {
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
		url = url.substring(5, url.indexOf(".png") + 4);

		return url;
	}
	
	public void clickPrice(int index) {
		By byprice = By.xpath("//*[@id='ps_price']//tr");
		driver.getElement(byprice);
		List<Element> list = driver.getElements(byprice);
		Element element = list.get(index);
		element.click();
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
