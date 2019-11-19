package automation.project3ds;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_ebanx {
	
	Driver driver;
	
	String TYPE = "personal";
	String CPF = "43622652101";
	String CNPJ = "67878183000113";
	String NAME = "Leonor S Alves";
	String BIRTH_DATE_DAY = "12";
	String BIRTH_DATE_MONTH = "12";
	String BIRTH_DATE_YEAR = "1912";
	String PHONE = "(27) 4945-8003";
	String ZIPCODE = "29161401";
	String COMPANY_NAME = "Company Name";
	
	String email;
	
	By bankOptions = By.xpath("//*[@id='step1']//div[@class='payment-method']");
	By radioButton = By.xpath("(//i[@class='icon-radio'])[2]");
	By name = By.id("name");
	By birth_date_day = By.id("birth_date_day");
	By birth_date_month = By.id("birth_date_month");
	By birth_date_year = By.id("birth_date_year");
	By emailTxb = By.id("email");
	By phone = By.id("phone");
	By cpf = By.id("cpf");
	By zipcode = By.id("zipcode");
	By payment_button = By.id("payment_button");
	By spinner = By.xpath("//*[@id='ajaxloader' and not(contains(@style,'none'))]");

	public PS_ebanx(Driver driver) {
		this.driver = driver;
	}
	
	public PS_ebanx2 createPayment() throws Exception {
		this.set_name();
		this.set_birth_date_day();
		this.set_birth_date_month();
		this.set_birth_date_year();
		this.set_email();
		this.set_phone();
		this.set_cpf();
		this.set_zipcode();
		this.clickSummit();
		return new PS_ebanx2(driver);
	}
	

	public void set_cpf(String str) {
		driver.getElement(cpf).sendKeys(str);
	}


	public void set_name(String str) {
		driver.getElement(name).sendKeys(str);
	}

	public void set_birth_date_day(String str) {
		driver.getElement(birth_date_day).sendKeys(str);
	}

	public void set_birth_date_month(String str) {
		driver.getElement(birth_date_month).sendKeys(str);
	}

	public void set_birth_date_year(String str) {
		driver.getElement(birth_date_year).sendKeys(str);
	}

	public void set_email(String str) {
		driver.getElement(emailTxb).sendKeys(str);
	}

	public void set_phone(String str) {
		driver.getElement(phone).sendKeys(str);
	}


	public void set_zipcode(String str) {
		driver.getElement(zipcode).sendKeys(str);
	}

	public void set_cpf() {
		set_cpf(CPF);
	}


	public void set_name() {
		set_name(NAME);
	}

	public void set_birth_date_day() {
		set_birth_date_day(BIRTH_DATE_DAY);
	}

	public void set_birth_date_month() {
		set_birth_date_month(BIRTH_DATE_MONTH);
	}

	public void set_birth_date_year() {
		set_birth_date_year(BIRTH_DATE_YEAR);
	}

	public void set_email() {
		String timeStamp = AnnotationPage.timestamp();
		email = "meo"+ timeStamp + "@spam4.me";
		set_email(email);
	}
	
	public String getEmail() {
		return this.email;
	}

	public void set_phone() {
		set_phone(PHONE);
	}


	public void set_zipcode() {
		set_zipcode(ZIPCODE);
	}

	public void clickSummit() throws Exception {
		driver.getElement(payment_button).click();
		driver.waitForNumberOfElement(spinner, 0, 20000);
	}

}

