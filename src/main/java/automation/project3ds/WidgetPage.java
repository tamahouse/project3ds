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
		case WidgetType.PW:
			 WidgetPW widgetPW = new WidgetPW(driver);
			 widgetPW.click(originShortcode, logo);
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
		case WidgetType.PW:
			 WidgetPW widgetPW = new WidgetPW(driver);
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
		case PS_shortcode.WEBMONEY:
			return new PS_webmoney(driver,widget);
		case PS_shortcode.CHERRYCREDITS:
			return new PS_cherrycredits(driver,widget);
		case PS_shortcode.ALLTHEGATE:
			return new PS_allthegate (driver,widget);
		case PS_shortcode.KFTC:
			return new PS_kftc (driver,widget);
		case PS_shortcode.TODITO:
			return new PS_todito (driver);
		case PS_shortcode.PAYSAFECARD:
			return new PS_paysafecard (driver);
		case PS_shortcode.WAVEGAME:
			return new PS_wavegame (driver, widget);
		case PS_shortcode.MOL:
			return new PS_mol (driver);
		case PS_shortcode.OXXOMEXICO:
			return new PS_oxxomexico (driver);
		case PS_shortcode.DRAGONPAY:
			return new PS_dragonpay (driver);
		case PS_shortcode.YAMONEY:
			return new PS_yamoney (driver, widget);
		case PS_shortcode.MYCARDCARD:
			return new PS_mycardcard (driver);
		case PS_shortcode.TICKETSURF:
			return new PS_ticketsurf (driver);
		case PS_shortcode.EPREPAG:
			return new PS_eprepag (driver);
		case PS_shortcode.EFECTYCOLOMBIA:
			return new PS_efectycolombia (driver);
		case PS_shortcode.BTCOLOMBIA:
			return new PS_btcolombia (driver);
		case PS_shortcode.BALOTO:
			return new PS_baloto (driver);
		case PS_shortcode.GANA:
			return new PS_gana (driver);
		case PS_shortcode.PSE:
			return new PS_pse (driver);
		case PS_shortcode.BTPERU:
			return new PS_btperu (driver);
		case PS_shortcode.QIWIWALLET:
			return new PS_qiwiwallet (driver, widget);
		case PS_shortcode.UNIONPAY:
			return new PS_unionpay (driver, widget);
		case PS_shortcode.MOBILEGATEWAY:
			return new PS_mobilegateway (driver);
		case PS_shortcode.EPINPAYMENTSYSTEM:
			return new PS_epinpaymentsystem (driver);
		case PS_shortcode.GAMEON:
			return new PS_gameon(driver);
		case PS_shortcode.BOLETO:
			return new PS_boleto(driver);
		case PS_shortcode.TRANSFERENCIABANCARIA:
			return new PS_transferenciabancaria(driver);
		case PS_shortcode.BANKTRANSFERMEXICO:
			return new PS_banktransfermexico(driver, widget);
		case PS_shortcode.BANCOMERMEXICO:
			return new PS_bancomermexico(driver);
		case PS_shortcode.BANAMEXMEXICO:
			return new PS_banamexmexico(driver);
		case PS_shortcode.SANTANDERMEXICO:
			return new PS_santandermexico(driver);
		case PS_shortcode.OXXO:
			return new PS_oxxo(driver, widget);
		case PS_shortcode.REDCOMPRA:
			return new PS_redcompra(driver, widget);
		case PS_shortcode.REDPAGOS:
			return new PS_redpagos(driver, widget);
		case PS_shortcode.CVSPHARMACY:
			return new PS_cvspharmacy (driver, widget);
		case PS_shortcode.DOLLARGENERAL:
			return new PS_dollargeneral (driver, widget);
		case PS_shortcode.IDEALNL:
			return new PS_idealnl(driver);
		case PS_shortcode.BANCONTACT:
			return new PS_bancontact (driver, widget);
		case PS_shortcode.EVROSET:
			return new PS_evroset(driver);
		case PS_shortcode.SVYASNOI:
			return new PS_svyasnoi(driver);
		case PS_shortcode.SEPADIRECTDEBIT:
			return new PS_sepadirectdebit(driver);
		case PS_shortcode.PAGOFACIL:
			return new PS_pagofacil(driver, widget);
		case PS_shortcode.RAPIPAGO:
			return new PS_rapipago(driver, widget);
		case PS_shortcode.WALMART:
			return new PS_walmart(driver, widget);
		case PS_shortcode.MONEYGRAM:
			return new PS_moneygram(driver, widget);
		case PS_shortcode.CANADAPOST:
			return new PS_canadapost(driver, widget);
		case PS_shortcode.OPENBUCKS:
			return new PS_openbucks(driver, widget);
		case PS_shortcode.TEENCASHKR:
			return new PS_teencashkr (driver);
		case PS_shortcode.BTPOLAND:
			return new PS_btpoland(driver);
		case PS_shortcode.WECHATPAYMENTS:
			return new PS_wechatpayments(driver);
		case PS_shortcode.DOTPAY:
			return new PS_dotpay(driver);
		case PS_shortcode.DOTPAYMT:
			return new PS_dotpaymt(driver);
		case PS_shortcode.DOTPAYBZWBK:
			return new PS_dotpaybzwbk(driver);
		case PS_shortcode.DOTPAYINGB:
			return new PS_dotpayingb(driver);
		case PS_shortcode.DOTPAYINGBACC:
			return new PS_dotpayingbacc(driver);
		case PS_shortcode.DOTPAYINTELIGO:
			return new PS_dotpayinteligo(driver);
		case PS_shortcode.DOTPAYPEKAO:
			return new PS_dotpaypekao(driver);
		case PS_shortcode.PAYPAL:
			return new PS_paypal(driver, widget);
		case PS_shortcode.FASTERPAY:
			return new PS_fasterpay(driver, widget);
		case PS_shortcode.BANKTRANSFERCN:
			return new PS_banktransfercn(driver, widget);
		case PS_shortcode.ALIPAY:
			return new PS_alipay(driver, widget);
		case PS_shortcode.CULTUREVOUCHERKR:
			return new PS_culturevoucherkr(driver);
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
