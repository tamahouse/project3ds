package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;

import automation.project3ds.WidgetMainFrame.WidgetType;

public class WidgetMulti extends WidgetMainFrame {
	
	private By buyBtn = By.id("ps_psb");
	private By thankyou = By.xpath("//h3[text()='Thank you for your purchase!']");
	
	WidgetMulti(Driver driver) {
		super(driver);
	}
	
	
	public Object getPS(String shortcode){
		switch(shortcode) {
		case PS_shortcode.MERCADOPAGO:
			return new PS_mercadopago(driver);
		case PS_shortcode.MERCADOPAGO2:
			return new PS_mercadopago2(driver);
		case PS_shortcode.EBANXTRANSFER:
			return new PS_ebanxtransfer(driver);
		case PS_shortcode.BANRISULBRAZIL:
			return new PS_ebanx(driver);
		case PS_shortcode.BRADESCOBRAZIL:
			return new PS_bradescobrazil(driver);
		case PS_shortcode.BANCOBRAZIL:
			return new PS_ebanx(driver);
		case PS_shortcode.ITAUBRAZIL:
			return new PS_ebanx(driver);
		case PS_shortcode.SERVIPAGCHILE:
			return new PS_servipagchile(driver);
		case PS_shortcode.SENCILLITO:
			return new PS_servipagchile(driver);
		case PS_shortcode.PSECOLOMBIA:
			return new PS_psecolombia(driver);
		}
		return null;
	}

	public Object createClick(String shortcode) throws Exception{
		this.click(shortcode);
		if(shortcode.equals("neosurf")) {
			return new PS_Neosurf(driver);
		}else if(shortcode.equals("pagseguro")) {
			return new PS_Pagseguro(driver);
		}else if(shortcode.equals("ccbrazil") || shortcode.equals("ccbrazilhipercard")) {
			return new PS_ccbrazil_compact(driver);
		}else if(shortcode.equals("sofortbt")) {
			return new PS_Sofortbt(driver);
		}else if(shortcode.equals("giropay")) {
			return new PS_Giropay(driver);
		}else if(shortcode.equals("vtc")) {
			return new PS_vtc(driver);
		}else if(shortcode.equals("boletobr")) {
			return new PS_boletobr(driver);
		}else if(shortcode.equals("przelewy24")) {
			return new PS_przelewy24(driver);
		}else if(shortcode.equals("gudangvoucher")) {
			return new PS_gudangvoucher(driver);
		}
		return null;
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
	
	
	public PS_gateway_compact getPS_gateway_compact() throws Exception {
		return new PS_gateway_compact(driver);
	}
	
	
	public PS_gateway_old getPS_gateway_old() throws Exception {
		return new PS_gateway_old(driver);
	}
	
	public PS_gateway_brick_1v5 getPS_gateway_brick_1v5() throws Exception {
		return new PS_gateway_brick_1v5(driver);
	}
	
	public PS_gateway_brick_1v6 getPS_gateway_brick_1v6() throws Exception {
		return new PS_gateway_brick_1v6(driver);
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
		url = url.replace("url(\"", "").replace("\")", "");

		return url;
	}
	
	public void clickPrice(int index) {
		By byprice = By.xpath("//*[@id='ps_price']//tr");
		driver.getElement(byprice);
		List<Element> list = driver.getElements(byprice);
		Element element = list.get(index);
		element.click();
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
