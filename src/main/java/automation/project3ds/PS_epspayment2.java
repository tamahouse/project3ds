package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;

public class PS_epspayment2  {
	
	Driver driver;
	
	String redirectUrl = "ftg-customer-integration.giropay.de";
	String bic = "HYPTAT22XXX";
	
	By bicTxb = By.id("tags");
	By firstBicOption = By.id("ui-id-2");
	By WeiterZuMeinerBankBtn = By.xpath("//*[@id='idtoGiropayDiv']/input");
	By loginBtn = By.id("sbtnLogin");
	By auftragAsendenBtn = By.id("sbtnSign");
	By zahlungAbbrechenBtn = By.id("urisbtnSign");
	By sammelnUndZeichnenBtn = By.id("sbtnSignSingle");
	By okBtn = By.id("sbtnOk");
	By zurückBtn = By.name("back2Shop");

	public PS_epspayment2(Driver driver) {
		this.driver = driver;
		driver.switchToWindows(redirectUrl, true,60000);
	}
	
	public ThankyouPage createPayment() {
		this.setBic();
		this.clickWeiterZuButton();
		this.clickLoginButton();
		this.clickAuftragAsendenButton();
		this.clickSammelnUndButton();
		this.clickOkButton();
		this.clickZuruckButton();
		return new ThankyouPage(driver);
	}
	
	private void setBic() {
		Element element = driver.getElement(bicTxb);
		driver.sleep(1000);
		element.sendKeys(bic);
		driver.getElement(firstBicOption).click();
	}
	
	private void clickWeiterZuButton() {
		driver.getElement(WeiterZuMeinerBankBtn);
		List<Element> list = driver.getElements(WeiterZuMeinerBankBtn);
		for(Element element: list) {
			try {
				element.click();
				return;
			}catch(Exception e) {
				
			}
		}
		
	}
	
	private void clickLoginButton() {
		Element element = driver.getElement(loginBtn);
		element.click();
	}
	
	private void clickAuftragAsendenButton() {
		Element element = driver.getElement(auftragAsendenBtn);
		element.click();
	}
	
	private void clickSammelnUndButton() {
		Element element = driver.getElement(sammelnUndZeichnenBtn);
		element.click();
	}
	
	private void clickOkButton() {
		Element element = driver.getElement(okBtn);
		element.click();
	}
	
	private void clickZuruckButton() {
		Element element = driver.getElement(zurückBtn);
		element.click();
	}

}
