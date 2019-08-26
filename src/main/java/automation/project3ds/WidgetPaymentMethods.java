package automation.project3ds;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;

public class WidgetPaymentMethods extends WidgetMainFrame {

	public static class tab_epinpaymentsystem_Mint {

		static By pinCurrency = By.id("select_currency");
		static By buyMint = By.xpath("//*[text()=\"Don't have Mint yet? Click here to buy!\"]");
		static By displayAmount = By.xpath("//*[@id='form']/h3/strong");

		public static void setPinCurrency(String visibleText) {
			Select select = new Select(getFrame().getElement(pinCurrency));
			select.selectByVisibleText(visibleText);
		}

		public static void clickBuyMint() {
			Element element = getFrame().getElement(buyMint);
			element.click();
		}

		public static String getDisplayAmount() {
			Element element = getFrame().getElement(displayAmount);
			String str = element.getText();
			return str;
		}
	}

	public static class tab_mobilegateway_Mobiamo {
		static By enterPinIcon = By.xpath(
				"//article[contains (@id,'step') and not (contains(@style, 'none'))]//*[@id='input_pin_cover']/label");
		static By enterPinTxb = By
				.xpath("//article[contains (@id,'step') and not (contains(@style, 'none'))]//input[@id='input_pin']");
		static By enterPinErrorMessage = By.xpath(
				"//article[contains (@id,'step') and not (contains(@style, 'none'))]//*[@id='pwsmsError' and contains(@style,'block')]");
		static By continueButton = By
				.xpath("//article[contains (@id,'step') and not (contains(@style, 'none'))]//input[@id='button_pin']");
		static By success = By.xpath("//*[@id='final_animation' and contains(@style,'block')]");
		static By term = By.xpath("//footer//a[text()='Terms of Service']");
		private static By button_pin_loader = By.xpath(
				"//article[contains(@id,'step') and not (contains(@style,'none'))]//input[@id='button_pin_loader' and not(contains(@style,'none'))]");

		public static void setPin() {
			setPin("ACBDEF");
		}

		public static void setPin(String pin) {
			Element element = getFrame().getElement(enterPinTxb);
			element.sendKeys(pin);
		}

		public static void clickContinue() throws Exception {
			Element element = getFrame().getElement(continueButton);
			element.click();
			getFrame().waitForNumberOfElement(button_pin_loader, 0, 60000);
		}

		public static Element getEnterPinIcon() {
			return getFrame().getElement(enterPinIcon);
		}

		public static Element getEnterPinTxb() {
			return getFrame().getElement(enterPinTxb);
		}

		public static Element getPinErrorMessage() {
			return getFrame().getElement(enterPinErrorMessage);
		}

		public static Element getSuccessBlock() {
			return getFrame().getElement(success);
		}

		public static void clickTerm() {
			Element element = getFrame().getElement(term);
			element.click();
		}
	}

	public static class tab_boletobancario_Boleto {

		static By type = By.id("type");
		static By responsible_cpf = By.id("responsible_cpf");
		static By cnpj = By.id("cnpj");
		static By cpf = By.id("cpf");
		static By name = By.id("name");
		static By birth_date_day = By.id("birth_date_day");
		static By birth_date_month = By.id("birth_date_month");
		static By birth_date_year = By.id("birth_date_year");
		static By email = By.id("email");
		static By phone = By.id("phone");
		static By company_name = By.id("company_name");
		static By zipcode = By.id("zipcode");
		static By payment_button = By.id("payment_button");
		static By error = By.xpath("//*[@id='error_message' and contains(@style,'block')]");
		static By spinner = By.xpath("//*[@id='preloader_container' and not(contains(@style,'none'))]");
		static By print = By.xpath(
				"//*[@id='ps_content']/*[contains(@id,'step') and not(contains(@style,'none'))]//*[@id ='step2_link']");

		static String TYPE = "personal";
		static String CPF = "43622652101";
		static String CNPJ = "67878183000113";
		static String NAME = "Leonor S Alves";
		static String BIRTH_DATE_DAY = "12";
		static String BIRTH_DATE_MONTH = "12";
		static String BIRTH_DATE_YEAR = "1912";
		static String EMAIL = "meo@spam4.me";
		static String PHONE = "(27) 4945-8003";
		static String ZIPCODE = "29161401";
		static String COMPANY_NAME = "Company Name";

		public static Element getPrintButton() {
			return getFrame().getElement(print);
		}

		public static void set_type(String str) {
			Select select = new Select(getFrame().getElement(type));
			select.selectByValue(str);
		}

		public static void set_cpf(String str) {
			getFrame().getElement(cpf).sendKeys(str);
		}

		public static void set_responsible_cpf(String str) {
			getFrame().getElement(responsible_cpf).sendKeys(str);
		}

		public static void set_cnpj(String str) {
			getFrame().getElement(cnpj).sendKeys(str);
		}

		public static void set_name(String str) {
			getFrame().getElement(name).sendKeys(str);
		}

		public static void set_birth_date_day(String str) {
			getFrame().getElement(birth_date_day).sendKeys(str);
		}

		public static void set_birth_date_month(String str) {
			getFrame().getElement(birth_date_month).sendKeys(str);
		}

		public static void set_birth_date_year(String str) {
			getFrame().getElement(birth_date_year).sendKeys(str);
		}

		public static void set_email(String str) {
			getFrame().getElement(email).sendKeys(str);
		}

		public static void set_phone(String str) {
			getFrame().getElement(phone).sendKeys(str);
		}

		public static void set_company_name(String str) {
			getFrame().getElement(company_name).sendKeys(str);
		}

		public static void set_zipcode(String str) {
			getFrame().getElement(zipcode).sendKeys(str);
		}

		public static void set_cpf() {
			set_cpf(CPF);
		}

		public static void set_responsible_cpf() {
			set_responsible_cpf(CPF);
		}

		public static void set_cnpj() {
			set_cnpj(CNPJ);
		}

		public static void set_name() {
			set_name(NAME);
		}

		public static void set_birth_date_day() {
			set_birth_date_day(BIRTH_DATE_DAY);
		}

		public static void set_birth_date_month() {
			set_birth_date_month(BIRTH_DATE_MONTH);
		}

		public static void set_birth_date_year() {
			set_birth_date_year(BIRTH_DATE_YEAR);
		}

		public static void set_email() {
			set_email(EMAIL);
		}

		public static void set_phone() {
			set_phone(PHONE);
		}

		public static void set_company_name() {
			set_company_name(COMPANY_NAME);
		}

		public static void set_zipcode() {
			set_zipcode(ZIPCODE);
		}

		public static void clickSummit() throws Exception {
			getFrame().getElement(payment_button).click();
			getFrame().waitForNumberOfElement(spinner, 0, 20000);
		}

		public static Element getError() {
			return getFrame().getElement(error);
		}

		public static void createPersonalPayment() throws Exception {
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

		public static void createBussinessPayment() throws Exception {
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

	public static class tab_idealpayments_Ideal {
		static By paymentOptions = By.xpath("//*[@id='payment-options']/*[@class='payment-method']");
		static By thankyou = By.xpath("//*[./h3[text()='Thank you for your purchase!']]");

		public static List<String> getListBankIds() {
			List<String> list = new ArrayList<String>();
			getFrame().getElement(paymentOptions);
			List<Element> elements = getFrame().getElements(paymentOptions);
			for (Element element : elements) {
				String data_id = element.getElement(By.xpath("./a")).getAttribute("data-id");
				list.add(data_id);
			}
			return list;
		}

		public static void clickBank(String data_id) {
			By by = By.xpath("//*[@id='payment-options']//*[@data-id='" + data_id + "']");
			getFrame().getElement(by).click();
		}

		public static Element getThankyou() {
			return getFrame().getElement(thankyou);
		}

		public static void finishPaymentStep() throws Exception {
			By primaryButton = By.xpath("//button[@class='btn btn-primary']");
			List<String> tabs = getFrame().waitForNewTab();
			Driver driver = AnnotationPage.driver;
			driver.switchTo().window(tabs.get(1));
			driver.getElement(primaryButton).click();;
			driver.getElement(primaryButton).click();
			driver.getElement(primaryButton).click();
			driver.navigate().refresh();
			driver.getElement(thankyou);
			driver.close();
			driver.switchTo().window(tabs.get(0));
		}

	}

}
