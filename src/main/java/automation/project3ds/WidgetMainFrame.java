package automation.project3ds;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;

public class WidgetMainFrame {

	private static By buyBtn = By.id("ps_psb");
	private static By thankyou = By.xpath("//h3[text()='Thank you for your purchase!']");
	private static By privacyPolicy = By.xpath("//*[contains(@class,'footer_copy_and_privacy')]/a");
	private static By paymentMethodContainer = By.id("pay_methods_container");
	private static By mobiamoTab = By.id("tab_mobilegateway");
	private static By redirectMessage = By.id("ps_new_window_popup");
	private static By price = By.xpath("//tr[contains(@id,'price_')]");

	public static Driver getFrame() {
		Driver driver = AnnotationPage.getDriver();
		driver.switchTo().defaultContent();
		Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
		iframe.moveToTopView();
		driver.switchTo().frame(iframe.getWebElement());
		return driver;
	}

	public static List<Element> getPrices() {
		getFrame().getElement(price);
		return getFrame().getElements(price);
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

	public static void clickPaymentMethod(String id) throws Exception {
		Element element = getFrame().getElement(By.id(id));
		try {
			element.click();
		} catch (Exception e) {
			getFrame().getElement(By.id("ps_next")).click();
			getFrame().getElement(By.id(id)).click();
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

	public static Boolean isMobiamo() {
		getPaymentMethod();
		return getFrame().isExistNow(mobiamoTab);
	}

	public static List<String> getListPayment() {
		String support = getPaymentMethod();
		List<String> listString = new ArrayList<String>();
		if (support == null) {
		} else {
			List<Element> list = getFrame()
					.getElements(By.xpath("//*[@id='pay_methods']/*[@class='t-payment-option-inner']/a"));
			for (Element element : list) {
				String value = element.getAttribute("id");
				listString.add(value);
			}
		}
		return listString;
	}

	public static void clickPrivacyPolicy() {
		getFrame().getElement(privacyPolicy).click();
	}

	public static void clickBuyButton() throws Exception {
		getFrame().getElement(buyBtn).click();
		Thread.sleep(3000);
	}

	public static Boolean getCompleteMessage() {
		return getFrame().isExist(thankyou);
	}

//	public static void clickProcessButton() {
//		getFrame().getElement(processBtn).click();
//	}
}