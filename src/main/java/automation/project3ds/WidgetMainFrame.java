package automation.project3ds;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class WidgetMainFrame {

	
	
	private static By thankyou2 = By.xpath("//h2[text()='Transaction was successful']");
	private static By privacyPolicy = By.xpath("//*[contains(@class,'footer_copy_and_privacy')]/a");
	private static By paymentMethodContainer = By.id("pay_methods_container");
	private static By mobiamoTab = By.id("tab_mobilegateway");
	private static By redirectMessage = By.id("ps_new_window_popup");
	private static By price = By.xpath("//tr[contains(@id,'price_')]");
	
	private static By thankyou = By.xpath("//*[@class='thankyou_widget']");

	public static Driver getFrame() {
		Driver driver = AnnotationPage.getDriver();
		driver.switchTo().defaultContent();
		Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
		iframe.moveToTopView();
		driver.switchTo().frame(iframe.getWebElement());
		return driver;
	}
	
	public static void waitForThankYou() {
		getFrame().getElement(thankyou,30000);
	}

	public static List<Element> getPrices() {
		getFrame().getElement(price);
		return getFrame().getElements(price);
	}

	public static void waitSpinner() throws Exception {
		getFrame().waitForNumberOfElement(
				By.xpath("//*[contains(@class,'paylet-body-loader') and contains(@class,'is-active')]"), 0, 5000);
	}

	public static List<String> getRedirectWindows() {
		List<String> list = null;
		try {
			Element element = getFrame().getElement(redirectMessage);
			String style = element.getAttribute("style");
			if (!style.contains("none")) {
				list = new ArrayList<String>(getFrame().getWindowHandles());
			}

		} catch (Exception e) {
		}
		return list;
	}

	public static void clickPaymentMethod(String type, String id) throws Exception {
		Element element = null;
		if (!(type.contains("multi") || type.contains("p1"))) {
			for (int i = 0; i < 20; i++) {
				try {
					getFrame().getElement(By.xpath("//*[@data-id='" + id + "']")).click();
					i = 500;
				} catch (Exception e) {
					getFrame().getElement(By.xpath("//button[contains(@class,'payment-options-control-next')]"))
							.click();
				}
			}
		} else {
			element = getFrame().getElement(By.id(id));
			for (int i = 0; i < 20; i++) {
				try {
					element.click();
					i = 500;
				} catch (Exception e) {
					getFrame().getElement(By.id("ps_next")).click();
				}
			}
		}

	}

	public static String getPaymentMethod() {
		try {
			getFrame().getElement(paymentMethodContainer);
			return "ok";
		} catch (Exception e) {
			return null;
		}
	}

	public static String getLogoUrl(String type, String tab_id_or_shortcode) throws Exception {

		String url = null;
		if (type.contains(AnnotationPage.Type.MULTI)) {
			clickPaymentMethod(type, tab_id_or_shortcode);
			ExtentManager.addScreenshot(type);
//		Element tab = getFrame().getElement(By.id(tab_id));
			String script = "return window.getComputedStyle(document.querySelector('" + tab_id_or_shortcode.replace("tab_", ".pm_")
					+ "'),':after').getPropertyValue('background-image')";
			url = getFrame().getStringJS(script);
			if(!url.contains("http")) {
				 url = getFrame().getElement(By.xpath("//*[@id='"+tab_id_or_shortcode+"']/b")).getCssValue("background-image");
			}
			url = url.substring(5,url.indexOf(".png")+4);

		}else if(type.equals(AnnotationPage.Type.UNI)) {
			ExtentManager.addScreenshot(type);
			Element image = getFrame().getElement(By.xpath("//*[@class='t-img img']"));
			url = image.getAttribute("src");
		}else if(type.equals(AnnotationPage.Type.V5)) {
			Driver driver = AnnotationPage.getDriver();
			driver.switchTo().defaultContent();
			
			Element image = driver.getElement(By.xpath("//div[./*[@id='"+tab_id_or_shortcode+"_active']]//img"));
			image.moveToTopView();
			ExtentManager.addScreenshot(type);
			url = image.getAttribute("src");
		}
		System.out.println(url);
		ExtentManager.logInfo(url);
		return url;
	}

	public static Boolean isMobiamo() {
		getPaymentMethod();
		return getFrame().isExistNow(mobiamoTab);
	}

//	BRICKJS
//	public static List<String> getListPayment() {
//		String support = getPaymentMethod();
//		List<String> listString = new ArrayList<String>();
//		if (support == null) {
//		} else {
//			List<Element> list = getFrame()
//					.getElements(By.xpath("//*[@id='pay_methods']/*[@class='t-payment-option-inner']/a"));
//			for (Element element : list) {
//				String value = element.getAttribute("id");
//				listString.add(value);
//			}
//		}
//		return listString;
//	}

	public static List<String> getListPayment() {
		List<String> listString = new ArrayList<String>();
		getFrame().getElement(By.xpath("//*[@class='payment-options-wrapper']//*[@data-id]"));
		List<Element> list = getFrame().getElements(By.xpath("//*[@class='payment-options-wrapper']//*[@data-id]"));
		for (Element element : list) {
			String value = element.getAttribute("data-id");
			listString.add(value);
		}
		return listString;
	}

	public static void clickPrivacyPolicy() {
		getFrame().getElement(privacyPolicy).click();
	}



//	public static Boolean getCompleteMessage(String type) {
//		if (type.equals("p1")) {
//			return getFrame().isExist(thankyou);
//		} else {
//			return getFrame().isExist(thankyou2);
//		}
//	}

//	public static void clickProcessButton() {
//		getFrame().getElement(processBtn).click();
//	}
}