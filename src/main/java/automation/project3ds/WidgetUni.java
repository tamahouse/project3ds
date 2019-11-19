package automation.project3ds;

import org.openqa.selenium.By;

public class WidgetUni extends WidgetMainFrame{
	
	
	public WidgetUni(Driver driver) {
		super(driver);
	}
	
	public void get(String host) {
		driver.get(host);
	}
	
	public PS_gateway_compact_short getPS_gateway_compact_short() throws Exception {
		return new PS_gateway_compact_short(driver);
	}


	public String getLogoUrl(String shortcode) throws Exception {
		Element image = driver.getElement(By.xpath("//*[@class='t-img img']"));
		String url = image.getAttribute("src");
		return url;
	}

	public void clickPaymentMethod(String shortcode) throws Exception {
		for (int i = 0; i < 20; i++) {
			try {
				driver.getElement(By.xpath("//*[@data-id='" + "tab_" + shortcode + "']")).click();
				i = 500;
			} catch (Exception e) {
				driver.getElement(By.xpath("//button[contains(@class,'payment-options-control-next')]")).click();
			}
		}

	}
	

}
