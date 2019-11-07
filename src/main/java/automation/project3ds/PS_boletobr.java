package automation.project3ds;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class PS_boletobr {
	
	Driver driver;
	
	String TYPE = "personal";
	String CPF = "43622652101";
	String CNPJ = "67878183000113";
	String NAME = "Leonor S Alves";
	String FIRSTNAME = "Leonor";
	String LASTNAME = "S Alves";
	String BIRTH_DATE_DAY = "12";
	String BIRTH_DATE_MONTH = "12";
	String BIRTH_DATE_YEAR = "1912";
	String EMAIL;
	String PHONE = "(27) 4945-8003";
	String ZIPCODE = "74936-470";
	String COMPANY_NAME = "Company Name";
	String STREET_NUMBER = "H-094 1006";
	String STREET = "Rua";
	String DISTRICT = "Goiás";
	String CITY = "Aparecida de Goiânia";

	By type = By.id("type");
	By responsible_cpf = By.id("responsible_cpf");
	By cnpj = By.id("cnpj");
	By cpf = By.id("cpf");
	By first_name = By.id("first_name");
	By last_name = By.id("last_name");
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
	By street_number = By.id("street_number");
	By street = By.id("street_name");
	By district = By.id("neighborhood");
	By city = By.id("city");
	By state = By.id("state");



	public PS_boletobr(Driver driver) {
		this.driver = driver;
	}
	
	public String getEmail() {
		return this.EMAIL;
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

	public void set_firstname(String str) {
		driver.getElement(first_name).sendKeys(str);
	}
	
	public void set_lastname(String str) {
		driver.getElement(last_name).sendKeys(str);
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

//	public void set_name() {
//		set_name(NAME);
//	}
	
	public void set_firstname() {
		set_firstname(FIRSTNAME);
	}

	
	public void set_lastname() {
		set_lastname(LASTNAME);
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
		String timestamp = AnnotationPage.timestamp();
		EMAIL = "meo"+timestamp+"@spam4.me";
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
		driver.waitForNumberOfElement(spinner, 0, 20000);
		driver.getElement(payment_button).click();
		driver.waitForNumberOfElement(spinner, 0, 20000);
	}

	public Element getError() {
		return driver.getElement(error);
	}
	
	private void setStreetNumber() {
		Element element = driver.getElement(street_number);
		element.sendKeys(STREET_NUMBER);
	}

	public void createPersonalPayment() throws Exception {
		set_type("personal");
		set_cpf();
		set_firstname();
		set_lastname();
		set_email();
		set_zipcode();
		setStreetNumber();
		clickSummit();
	}

	public void createBussinessPayment() throws Exception {
		set_type("business");
		set_responsible_cpf();
		set_cnpj();
		set_firstname();
		set_lastname();
		set_email();
		set_company_name();
		set_zipcode();
		setStreetNumber();
		clickSummit();
	}
}
