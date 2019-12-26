package automation.project3ds;

import org.openqa.selenium.By;

public class WidgetPW extends WidgetObject{
	
	private By thankyou = By.xpath("//*[contains(@class,'js-step-success')][contains(@class,'is-active')]");
	
	public WidgetPW(Driver driver) {
		super(driver);
		
	}
	
	public Boolean waitForSuccess() {
		return driver.isExist(thankyou);
}
	
	public void click(String shortcode, String logo) {
		String tabName;
		if(!logo.equals("")) {
			tabName = logo;
		}else {
			tabName = shortcode;
		}
		driver.getElement(By.xpath("//*[@data-id]"));
		driver.sleep(1000);
		By tab = By.xpath("//*[@data-id='"+tabName+"']");
		for (int i = 0; i < 20; i++) {
			try {
				Element element = driver.getElement(tab);
				element.click();
				i = 500;
			} catch (Exception e) {
				driver.getElement(By.xpath("//button[contains(@class,'payment-options-control-next')]")).click();
				driver.sleep(100);
			}
		}

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
