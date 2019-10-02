package automation.project3ds;

import org.openqa.selenium.By;

public class WidgetUni {
	
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
		Element image = getFrame().getElement(By.xpath("//*[@class='t-img img']"));
		String url = image.getAttribute("src");
		return url;
	}

	public static void clickPaymentMethod(String shortcode) throws Exception {
		for (int i = 0; i < 20; i++) {
			try {
				getFrame().getElement(By.xpath("//*[@data-id='" + "tab_" + shortcode + "']")).click();
				i = 500;
			} catch (Exception e) {
				getFrame().getElement(By.xpath("//button[contains(@class,'payment-options-control-next')]")).click();
			}
		}

	}
}
