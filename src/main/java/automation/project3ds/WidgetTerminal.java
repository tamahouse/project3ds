package automation.project3ds;

import org.openqa.selenium.By;

public class WidgetTerminal extends WidgetMainFrame{
	
	
	public WidgetTerminal(Driver driver) {
		super(driver);
	}
	

	public void clickPaymentMethod(String shortcode, String logo) throws Exception {
		String by = null;
		if(logo.equals("")) {
			by = "//*[./img[contains(@src,'"+shortcode+"')]]";
		}else {
			by = "//*[./img[contains(@src,'"+shortcode+"') or contains(@src,'"+logo+"')]]";
		}
		for (int i = 0; i < 20; i++) {
			try {
				Element element = driver.getElement(By.xpath(by));
				element.click();
				i = 500;
			} catch (Exception e) {
				driver.getElement(By.xpath("//button[contains(@class,'payment-options-control-next')]")).click();
			}
		}

	}
}
