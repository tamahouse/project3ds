package automation.project3ds;

import org.openqa.selenium.By;

public class WidgetTerminal {
	
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


	public static void clickPaymentMethod(String shortcode, String logo) throws Exception {
		String by = null;
		if(logo.equals("")) {
			by = "//*[./img[contains(@src,'"+shortcode+"')]]";
		}else {
			by = "//*[./img[contains(@src,'"+shortcode+"') or contains(@src,'"+logo+"')]]";
		}
		for (int i = 0; i < 20; i++) {
			try {
				Element element = getFrame().getElement(By.xpath(by));
				element.click();
				i = 500;
			} catch (Exception e) {
				getFrame().getElement(By.xpath("//button[contains(@class,'payment-options-control-next')]")).click();
			}
		}

	}
}
