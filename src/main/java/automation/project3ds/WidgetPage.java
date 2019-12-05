package automation.project3ds;

import org.openqa.selenium.By;

import automation.project3ds.WidgetObject.WidgetType;

public class WidgetPage extends BasePage {
	
	By submitBtn = By.xpath("//*[@id='testOfferwall']//input[@value='Submit']");
	String widget;
	
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
	
	public void waitForThankyou (String widget) throws Exception {
		switch(widget) {
		case WidgetType.MULTI:
			WidgetMulti widgetMulti = new WidgetMulti(driver);
			this.widget = widget;
			widgetMulti.waitForSuccess();
			break;
		case WidgetType.UNI:
			WidgetUni widgetUni = new WidgetUni(driver);
			this.widget = widget;
			break;
		case WidgetType.P2_3:
			Widget_p2_3 widgetP23 = new Widget_p2_3(driver);
			this.widget = widget;
			break;
		case WidgetType.TERMNIAL:
			 WidgetTerminal widgetTerminal = new WidgetTerminal(driver);
			 this.widget = widget;
			 widgetTerminal.waitForSuccess();
			 break;
		}
	}
	
	public Object getPS(String widget, String shortcode, String logo) throws Exception {
		String originShortcode = shortcode;
		try {
		originShortcode = shortcode.substring(0,shortcode.indexOf("_"));
		}catch(Exception e) {
			
		}
		switch(widget) {
		case WidgetType.MULTI:
			WidgetMulti widgetMulti = new WidgetMulti(driver);
			widgetMulti.click(originShortcode);
			this.widget = widget;
			break;
		case WidgetType.UNI:
			WidgetUni widgetUni = new WidgetUni(driver);
			this.widget = widget;
			break;
		case WidgetType.P2_3:
			Widget_p2_3 widgetP23 = new Widget_p2_3(driver);
			this.widget = widget;
			break;
		case WidgetType.P2_1:
			WidgetUni widgetUni2 = new WidgetUni(driver);
			this.widget = widget;
			break;
		case WidgetType.TERMNIAL:
			 WidgetTerminal widgetTerminal = new WidgetTerminal(driver);
			 widgetTerminal.click(originShortcode, logo);
			 this.widget = widget;
			 break;
		}
		return this.getPS(shortcode);
	}

	
	public Object reGetPS(String widget, String shortcode, String logo) throws Exception {
		switch(widget) {
		case WidgetType.MULTI:
			WidgetMulti widgetMulti = new WidgetMulti(driver);
			this.widget = widget;
			break;
		case WidgetType.UNI:
			WidgetUni widgetUni = new WidgetUni(driver);
			this.widget = widget;
			break;
		case WidgetType.P2_3:
			Widget_p2_3 widgetP23 = new Widget_p2_3(driver);
			this.widget = widget;
			break;
		case WidgetType.TERMNIAL:
			 WidgetTerminal widgetTerminal = new WidgetTerminal(driver);
			 this.widget = widget;
			 break;
		}
		return this.getPS(shortcode);
	}
	
	
	public Object getPS (String shortcode){
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
		case PS_shortcode.EPSPAYMENT:
			return new PS_epspayment(driver,widget);
		case PS_shortcode.PAGSEGURO:
			return new PS_pagseguro(driver);
		case PS_shortcode.GIROPAY:
			return new PS_giropay(driver);
		case PS_shortcode.BOLETOBANCARIO:
			return new PS_boletobancario(driver);
		case PS_shortcode.BOLETOBR:
			return new PS_boletobr(driver);
		case PS_shortcode.GUDANGVOUCHER:
			return new PS_gudangvoucher(driver);
		case PS_shortcode.CBC:
			return new PS_cbc(driver,widget);
		case PS_shortcode.KBC:
			return new PS_kcb(driver,widget);
		case PS_shortcode.BELFIUS:
			return new PS_belfius(driver, widget);
		case PS_shortcode.POLIPAYMENTS:
			return new PS_polipayments(driver, widget);
		case PS_shortcode.BTFINLAND:
			return new PS_btfinland(driver);
		case PS_shortcode.EPSPAYMENTS:
			return new PS_epspayments(driver);
		case PS_shortcode.NEOSURF:
			return new PS_Neosurf(driver);
		case PS_shortcode.IDEALPAYMENTS:
			return new PS_idealpayments(driver);
		case PS_shortcode.MULTIBANCO:
			return new PS_multibanco(driver);
		case PS_shortcode.MYBANK:
			return new PS_mybank(driver);
		case PS_shortcode.SOFORTBT:
			return new PS_sofortbt(driver, widget);
		case PS_shortcode.PRZELEWY24:
			return new PS_przelewy24(driver);
		case PS_shortcode.SOFORTBANKTRANSFER:
			return new PS_sofortbanktransfer(driver, widget);
		case PS_shortcode.VTC:
			return new PS_vtc(driver, widget);
		case PS_shortcode.YANDEXMONEY:
			return new PS_yandexmoney(driver, widget);
		case PS_shortcode.CCBRAZIL_COMPACT:
			return new PS_ccbrazil_compact(driver);
		case PS_shortcode.CCBRAZIL_OLD:
			return new PS_ccbrazil_old(driver);
		case PS_shortcode.CCBRAZILHIPERCARD_COMPACT:
			return new PS_ccbrazil_compact(driver);
		case PS_shortcode.CCBRAZILHIPERCARD_OLD:
			return new PS_ccbrazil_old(driver);
		case PS_shortcode.GATEWAY_BRICK_15:
			return new PS_gateway_brick_1v5(driver);
		case PS_shortcode.GATEWAY_BRICK_16:
			return new PS_gateway_brick_1v6(driver);
		case PS_shortcode.GATEWAY_COMPACT:
			return new PS_gateway_compact(driver);
		case PS_shortcode.GATEWAY_CORAL_15:
			return new PS_gateway_brick_1v5_corel(driver);
		case PS_shortcode.GATEWAY_GAMEFORGE:
			return new PS_gateway_compact_short(driver);
		case PS_shortcode.GATEWAY_OLD:
			return new PS_gateway_old(driver);
		case PS_shortcode.NETBANKING:
			return new PS_netbanking(driver,widget);
		}
		
		return null;
	}

//	public WidgetMulti getMultiWidget() {
//		
//		return new WidgetMulti(driver);
//	}
//	
//	public WidgetUni getWidgetUni() {
//		return new WidgetUni(driver);
//	}
//
//	public WidgetTerminal getWidgetTerminal() {
//		return new WidgetTerminal(driver);
//	}
//	
//	public Widget_p2_3 getWidget_p2_3() {
//		return new Widget_p2_3(driver);
//	}
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
