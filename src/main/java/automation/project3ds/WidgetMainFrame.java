package automation.project3ds;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

public class WidgetMainFrame {

	Driver driver;
	
	private By thankyou2 = By.xpath("//h2[text()='Transaction was successful']");
	private By privacyPolicy = By.xpath("//*[contains(@class,'footer_copy_and_privacy')]/a");
	private By paymentMethodContainer = By.id("pay_methods_container");
	private By mobiamoTab = By.id("tab_mobilegateway");
	private By redirectMessage = By.id("ps_new_window_popup");
	private By price = By.xpath("//tr[contains(@id,'price_')]");

	private By thankyou = By.xpath("//*[@class='thankyou_widget']");
	
	public static class WidgetType {
		public static final String MULTI = "multi";
		public static final  String UNI = "uni";
		public static final  String P2_3 = "p2_3";
		public static final  String TERMNIAL = "terminal";
	}

	public WidgetMainFrame (Driver driver) {
		try {
			driver.switchTo().defaultContent();
		} catch (Exception ignore) {

		}
		Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
		iframe.moveToTopView();
		driver.switchTo().frame(iframe.getWebElement());
		this.driver = driver;
	}


	public void waitForThankYou() {
		driver.getElement(thankyou, 600000);
	}

	public List<Element> getPrices() {
		driver.getElement(price);
		return driver.getElements(price);
	}

	public void waitSpinner() throws Exception {
		driver.waitForNumberOfElement(
				By.xpath("//*[contains(@class,'paylet-body-loader') and contains(@class,'is-active')]"), 0, 5000);
	}

	public List<String> getRedirectWindows() {
		List<String> list = null;
		try {
			Element element = driver.getElement(redirectMessage);
			String style = element.getAttribute("style");
			if (!style.contains("none")) {
				list = new ArrayList<String>(driver.getWindowHandles());
			}

		} catch (Exception e) {
		}
		return list;
	}

	public void clickPaymentMethod(String type, String id) throws Exception {
		Element element = null;
		if (!(type.contains("multi") || type.contains("p1"))) {
			for (int i = 0; i < 20; i++) {
				try {
					driver.getElement(By.xpath("//*[@data-id='" + id + "']")).click();
					i = 500;
				} catch (Exception e) {
					driver.getElement(By.xpath("//button[contains(@class,'payment-options-control-next')]"))
							.click();
				}
			}
		} else {
			element = driver.getElement(By.id(id));
			for (int i = 0; i < 20; i++) {
				try {
					element.click();
					i = 500;
				} catch (Exception e) {
					driver.getElement(By.id("ps_next")).click();
				}
			}
		}

	}

	public String getPaymentMethod() {
		try {
			driver.getElement(paymentMethodContainer);
			return "ok";
		} catch (Exception e) {
			return null;
		}
	}

	public String getLogoUrl(String type, String tab_id_or_shortcode) throws Exception {

		String url = null;
		if (type.contains(AnnotationPage.Type.MULTI)) {
			clickPaymentMethod(type, tab_id_or_shortcode);
			ExtentManager.addScreenshot(driver, type);
//		Element tab = driver.getElement(By.id(tab_id));
			String script = "return window.getComputedStyle(document.querySelector('"
					+ tab_id_or_shortcode.replace("tab_", ".pm_") + "'),':after').getPropertyValue('background-image')";
			url = driver.getStringJS(script);
			if (!url.contains("http")) {
				url = driver.getElement(By.xpath("//*[@id='" + tab_id_or_shortcode + "']/b"))
						.getCssValue("background-image");
			}
			url = url.substring(5, url.indexOf(".png") + 4);

		} else if (type.equals(AnnotationPage.Type.UNI)) {
			ExtentManager.addScreenshot(driver, type);
			Element image = driver.getElement(By.xpath("//*[@class='t-img img']"));
			url = image.getAttribute("src");
		} else if (type.equals(AnnotationPage.Type.V5)) {
			driver.switchTo().defaultContent();

			Element image = driver
					.getElement(By.xpath("//div[./*[@id='" + tab_id_or_shortcode + "_active']]//img"));
			image.moveToTopView();
			ExtentManager.addScreenshot(driver, type);
			url = image.getAttribute("src");
		}
		System.out.println(url);
		ExtentManager.logInfo(url);
		return url;
	}

	public Boolean isMobiamo() {
		getPaymentMethod();
		return driver.isExistNow(mobiamoTab);
	}

//	BRICKJS
//	public List<String> getListPayment() {
//		String support = getPaymentMethod();
//		List<String> listString = new ArrayList<String>();
//		if (support == null) {
//		} else {
//			List<Element> list = driver
//					.getElements(By.xpath("//*[@id='pay_methods']/*[@class='t-payment-option-inner']/a"));
//			for (Element element : list) {
//				String value = element.getAttribute("id");
//				listString.add(value);
//			}
//		}
//		return listString;
//	}

	public List<String> getListPayment() {
		List<String> listString = new ArrayList<String>();
		driver.getElement(By.xpath("//*[@class='payment-options-wrapper']//*[@data-id]"));
		List<Element> list = driver.getElements(By.xpath("//*[@class='payment-options-wrapper']//*[@data-id]"));
		for (Element element : list) {
			String value = element.getAttribute("data-id");
			listString.add(value);
		}
		return listString;
	}

	public void clickPrivacyPolicy() {
		driver.getElement(privacyPolicy).click();
	}

//	public Boolean getCompleteMessage(String type) {
//		if (type.equals("p1")) {
//			return driver.isExist(thankyou);
//		} else {
//			return driver.isExist(thankyou2);
//		}
//	}

//	public void clickProcessButton() {
//		driver.getElement(processBtn).click();
//	}
}