package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_boletobancario {
	
	Driver driver;

	By type = By.id("type");
	By responsible_cpf = By.id("responsible_cpf");
	By cnpj = By.id("cnpj");
	By cpf = By.id("cpf");
	By name = By.id("name");
	By birth_date_day = By.id("birth_date_day");
	By birth_date_month = By.id("birth_date_month");
	By birth_date_year = By.id("birth_date_year");
	By email = By.id("email");
	By phone = By.id("phone");
	By company_name = By.id("company_name");
	By zipcode = By.id("zipcode");
	By payment_button = By.id("payment_button");
	By error = By.xpath("//*[@id='error_message' and contains(@style,'block')]");
	By spinner = By.xpath("//*[@id='preloader_container' and not(contains(@style,'none'))]");
	By print = By.xpath(
			"//*[contains(@id,'step') and not(contains(@style,'none'))]//*[@id ='step2_link']");

	String TYPE = "personal";
	String CPF = "43622652101";
	String CNPJ = "67878183000113";
	String NAME = "Leonor S Alves";
	String BIRTH_DATE_DAY = "12";
	String BIRTH_DATE_MONTH = "12";
	String BIRTH_DATE_YEAR = "1912";
	String EMAIL = "meo@spam4.me";
	String PHONE = "(27) 4945-8003";
	String ZIPCODE = "29161401";
	String COMPANY_NAME = "Company Name";

	public PS_boletobancario(Driver driver) {
		this.driver = driver;
	}

	public Element getPrintButton() {
		return driver.getElement(print);
	}

	public void set_type(String str) {
		Select select = new Select(driver.getElement(type));
		select.selectByValue(str);
	}

	public void set_cpf(String str) {
		driver.getElement(cpf).sendKeys(str);
	}

	public void set_responsible_cpf(String str) {
		driver.getElement(responsible_cpf).sendKeys(str);
	}

	public void set_cnpj(String str) {
		driver.getElement(cnpj).sendKeys(str);
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
		driver.getElement(email).sendKeys(str);
	}

	public void set_phone(String str) {
		driver.getElement(phone).sendKeys(str);
	}

	public void set_company_name(String str) {
		driver.getElement(company_name).sendKeys(str);
	}

	public void set_zipcode(String str) {
		driver.getElement(zipcode).sendKeys(str);
	}

	public void set_cpf() {
		set_cpf(CPF);
	}

	public void set_responsible_cpf() {
		set_responsible_cpf(CPF);
	}

	public void set_cnpj() {
		set_cnpj(CNPJ);
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
		set_email(EMAIL);
	}

	public void set_phone() {
		set_phone(PHONE);
	}

	public void set_company_name() {
		set_company_name(COMPANY_NAME);
	}

	public void set_zipcode() {
		set_zipcode(ZIPCODE);
	}

	public void clickSummit() throws Exception {
		driver.getElement(payment_button).click();
		driver.waitForNumberOfElement(spinner, 0, 20000);
	}

	public Element getError() {
		return driver.getElement(error);
	}

	public void createPersonalPayment() throws Exception {
		set_type("personal");
		set_cpf();
		set_name();
		set_birth_date_day();
		set_birth_date_month();
		set_birth_date_year();
		set_email();
		set_phone();
		set_zipcode();
		clickSummit();
	}

	public void createBussinessPayment() throws Exception {
		set_type("business");
		set_responsible_cpf();
		set_cnpj();
		set_name();
		set_birth_date_day();
		set_birth_date_month();
		set_birth_date_year();
		set_email();
		set_phone();
		set_company_name();
		set_zipcode();
		clickSummit();
	}
}

