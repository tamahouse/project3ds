package automation.project3ds;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;

public class PS_idealpayments {
	Driver driver;
	By paymentOptions = By.xpath("//*[@id='payment-options']/*[@class='payment-method']");
	By thankyou = By.xpath("//*[./h3[text()='Thank you for your purchase!']]");
	

	public PS_idealpayments(Driver driver) {
		this.driver = driver;
	}
	
	public PS_ppro createPayment() throws Exception {
		this.clickBank();
		return new PS_ppro(driver);
	}

	private List<String> getListBankIds() {
		List<String> list = new ArrayList<String>();
		driver.getElement(paymentOptions);
		List<Element> elements = driver.getElements(paymentOptions);
		for (Element element : elements) {
			String data_id = element.getElement(By.xpath("./a")).getAttribute("data-id");
			list.add(data_id);
		}
		return list;
	}

	public void clickBank() {
		List<String> banks = this.getListBankIds();
		Random random = new Random();
		String data_id = banks.get(random.nextInt(banks.size()));
		By bybank = By.xpath("//*[@id='payment-options']//*[@data-id='" + data_id + "']");
		By byxButton = By.xpath("//*[@id='ps_new_window_popup']/div/a");
		driver.getElement(bybank).click();
	}

}
