package automation.project3ds;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class BusinessInfo extends BasePage {
	
	String COUNTRY = "1";
	String TYPE = "individual";
	String STREET = "43 Florida";
	String CITY = "Florida";
	String REGION = "3974";
	String ZIP = "32043";
	String TAX = "12345123412345";
	String WEBSITE = "https://www.paymentwall.com";
	
	String FIRSTNAME = "Firstname";
	String LASTNAME = "Lastname";
	String BIRTH = "1995-01-20";
	String NATIONALITLY = "1";
	String PASSPORT ="123456789";
	String PHONE = "0987654321";
	
	String PAYOUT = "string:1";  //Check
	String BENEFI = "Beneficiary Name";
	String FILE = "C:\\Workspace\\project3ds\\upload.png";
	
	By loadingBlock = By.xpath("//*[@loading-blocker][contains(@style,'none')]");
	
	By countrySlt = By.xpath("//*[contains(@ng-model,'data.selectedCountry')]");
	By typeSlt = By.xpath("//*[contains(@ng-model,'data.dp_type')]");
	By streetTxb = By.xpath("//*[contains(@ng-model,'data.dp_street')]");
	By cityTxb = By.xpath("//*[contains(@ng-model,'data.dp_city')]");
	By regionSlt = By.xpath("//*[contains(@ng-model,'data.selectedRegion')]");
	By zipTxb = By.xpath("//*[contains(@ng-model,'data.dp_zip_code')]");
	By taxTxb = By.xpath("//*[contains(@ng-model,'data.dp_tax_id')]");
	By websiteTxb = By.xpath("//*[contains(@ng-model,'data.dp_website')]");
	
	By firstnameTxb = By.xpath("//*[contains(@ng-model,'ud_first_name')]");
	By lastnameTxb = By.xpath("//*[contains(@ng-model,'ud_last_name')]");
	By birthTxb = By.xpath("//*[contains(@ng-model,'ud_dt_birthday')]");
	By nationalitySlt = By.xpath("//*[contains(@ng-model,'selectedNationality')]");
	By passportTxb = By.xpath("//*[contains(@ng-model,'ud_passport_id')]");
	By phoneTxb = By.xpath("//*[contains(@ng-model,'ud_phone')]");
	
	By payoutSlt = By.xpath("//*[contains(@ng-model,'dp_method')]");
	By beneficiaryTxb = By.xpath("//*[contains(@ng-model,'beneficiary_name')]");
	By sameMailCheckbox = By.xpath("//*[contains(@ng-model,'dp_mailing_same_as_business')]");
	
	By uploadBtn = By.xpath("//*[contains(@ng-hide,'imageSuccess')]");

	
	By nextBtn = By.xpath("//*[contains(@ng-click,'proceedData')]");

	public BusinessInfo(Driver driver, String branch) {
		super(driver, branch);
	}
	
	public void open() {
		String url = this.branch + "/developers/business";
		driver.get(url);
	}
	
	public void fillProfile() {
		this.open();
		this.fillStep1();
		this.fillStep2();
		this.fillStep3();
	}
	
	private void setCountry() {
		Select select = new Select(driver.getElement(countrySlt));
		List<WebElement> list = select.getOptions();
		int size = list.size();
		System.out.println(size);
		while(size < 2) {
			Element element = driver.getElement(countrySlt);
			element.highlight();
			select = new Select(driver.getElement(countrySlt));
			list = select.getOptions();
			size = list.size();
		}
		driver.getElement(loadingBlock);
		select.selectByValue(COUNTRY);
	}
	
	private void setType() {
		Select select = new Select(driver.getElement(typeSlt));
		driver.sleep(1000);
		driver.getElement(loadingBlock);
		select.selectByValue(TYPE);
	}
	
	private void setStreet() {
		Element element = driver.getElement(streetTxb);
		element.clear();
		element.sendKeys(STREET);
	}
	
	private void setCity() {
		Element element = driver.getElement(cityTxb);
		element.clear();
		element.sendKeys(CITY);
	}
	
	private void setRegion() {
		Select select = new Select(driver.getElement(regionSlt));
		select.selectByValue(REGION);
	}
	
	private void setZip() {
		Element element = driver.getElement(zipTxb);
		element.clear();
		element.sendKeys(ZIP);
	}
	
	private void setTax() {
		Element element = driver.getElement(taxTxb);
		element.clear();
		element.sendKeys(TAX);
	}
	
	private void setWebsite() {
		Element element = driver.getElement(websiteTxb);
		element.clear();
		element.sendKeys(WEBSITE);
	}
	
	private void clickNextButton() {
		Element element = driver.getElement(nextBtn);
		element.click();
	}
	
	private void setFirstName() {
		Element element = driver.getElement(firstnameTxb);
		element.clear();
		element.sendKeys(FIRSTNAME);
	}
	
	private void setLastName() {
		Element element = driver.getElement(lastnameTxb);
		element.clear();
		element.sendKeys(LASTNAME);
	}
	
	private void setBirth() {
		
		Element element = driver.getElement(birthTxb);
		element.clear();
		element.click();
		element = driver.getElement(By.xpath("//div[./*[contains(@ng-model,'ud_dt_birthday')]]/div"));
		Element previous = element.getElement(By.xpath("//*[@ng-click='prev()']"));
		previous.click();
		try {
		Element month = element.getElement(By.xpath("//*[@ng-click='setDate(month)']"));
		month.click();
		}catch (Exception e) {
			
		}
		Element day = element.getElement(By.xpath("//*[@ng-click='setDate(day)'][not(contains(@class,'disabled'))]"));;
		day.click();

	}
	
	private void setNationality() {
		Select select = new Select (driver.getElement(nationalitySlt));
		select.selectByValue(NATIONALITLY);
	}
	
	private void setPassport() {
		Element element = driver.getElement(passportTxb);
		element.clear();
		element.sendKeys(PASSPORT);
	}
	
	private void setPhone() {
		Element element = driver.getElement(phoneTxb);
		element.clear();
		element.sendKeys(PHONE);
	}
	
	
	public void fillStep1() {
		this.setCountry();
		this.setType();
		this.setStreet();
		this.setCity();
		this.setRegion();
		this.setZip();
		this.setTax();
		this.setWebsite();
		
		this.setFirstName();
		this.setLastName();
		this.setBirth();
		this.setNationality();;
		this.setPassport();
		this.setPhone();
		this.clickNextButton();
	}
	
	private void setPayout() {
		Select select = new Select(driver.getElement(payoutSlt));
		List<WebElement> list = select.getOptions();
		int size = list.size();
		System.out.println(size);
		while(size < 2) {
			select = new Select(driver.getElement(payoutSlt));
			list = select.getOptions();
			size = list.size();
		}
		driver.getElement(loadingBlock);
		select.selectByValue(PAYOUT);
	}
	
	private void setBeneficiary() {
		Element element = driver.getElement(beneficiaryTxb);
		driver.getElement(loadingBlock);
		element.clear();
		element.sendKeys(BENEFI);
	}
	
	private void setSameMail() {
		Element element = driver.getElement(sameMailCheckbox);
		driver.getElement(loadingBlock);
		Boolean isChecked = element.isSelected();
		if(!isChecked) {
			element.click();
		}
	}
	
	public void fillStep2() {
		this.setPayout();
		this.setBeneficiary();
		this.setSameMail();
		this.clickNextButton();
	}
	
	private void uploadFiles() {
		driver.getElement(uploadBtn);
		for(int i = 1; i<4; i++) {
			String rowStr = "(//tbody[.//input[@type='file']])["+i+"]";
			String removeStr = rowStr+"//button";
			int numberOfRemove = driver.getElements(By.xpath(removeStr)).size();
			if(numberOfRemove == 0) {
				String fileStr = rowStr+"//input[@type='file']";
				Element element = driver.getElement(By.xpath(fileStr));
				element.sendKeys(FILE);
				driver.getElement(By.xpath(removeStr));
			}
			
		}
		
	}
	
	
	public void fillStep3() {
		this.uploadFiles();;
		this.clickNextButton();
	}

}
