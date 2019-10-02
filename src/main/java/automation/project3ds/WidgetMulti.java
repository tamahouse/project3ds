package automation.project3ds;

import org.openqa.selenium.By;

public class WidgetMulti {
	
	public static void get(String host) {
		Driver driver = AnnotationPage.getDriver();
		driver.get(host);
	}

	public static Driver getFrame() {
		Driver driver = AnnotationPage.getDriver();
		driver.switchTo().defaultContent();
		Element iframe = driver.getElement(By.xpath("//*[@id=\"main\"]/iframe"));
		iframe.moveToTopView();
		driver.switchTo().frame(iframe.getWebElement());
		return driver;
	}

	public static String getLogoUrl(String shortcode) throws Exception {
		String url = null;
		clickPaymentMethod(shortcode);
		String script = "return window.getComputedStyle(document.querySelector('" + ".pm_" + shortcode
				+ "'),':after').getPropertyValue('background-image')";
		url = getFrame().getStringJS(script);
		if (!url.contains("http")) {
			url = getFrame().getElement(By.xpath("//*[@id='" + "tab_"+shortcode + "']/b")).getCssValue("background-image");
		}
		url = url.substring(5, url.indexOf(".png") + 4);

		return url;
	}

	public static void clickPaymentMethod(String shortcode) throws Exception {
		String id = "tab_"+shortcode;
			for (int i = 0; i < 20; i++) {
				try {
					Element element = getFrame().getElement(By.id(id));;
					element.click();
					i = 500;
				} catch (Exception e) {
					getFrame().getElement(By.id("ps_next")).click();
				}
		}

	}
}
