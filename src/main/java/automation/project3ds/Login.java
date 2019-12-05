package automation.project3ds;

import org.openqa.selenium.By;

public class Login extends BasePage {

	private By nameTxb = By.id("login");
	private By passwordTxb = By.id("password");
	private By loginBtn = By.id("submit_button");
	private String name = utility.ConfigFile.name;
	private String password = utility.ConfigFile.password;
	
	public Login(Driver driver, String branch) {
		super(driver, branch);
	}
	
	public Login(Driver driver) {
		super(driver);
	}
	
	public void open() {
		String url = this.branch + "/admin";
		driver.get(url);
	}
	
	public void login(String url) {
		String loginUrl = url;
		if(!url.contains("/admin/test-offerwall")) {
			loginUrl = url+"/admin/test-offerwall";
		}
		driver.get(loginUrl);
		setName();
		setPassword();
		clickLogin();
		driver.get(loginUrl);
	}
	
	public void simpleLogin(String url) {
		String loginUrl = url + "/admin";
		driver.get(loginUrl);
		setName();
		setPassword();
		clickLogin();
	}
	
	public void login() {
		this.open();
		setName();
		setPassword();
		clickLogin();
	}
	
	
	private void setName() {
		Element nameTextbox = driver.getElement(nameTxb);
		driver.sleep(1000);
		nameTextbox.sendKeys(name);
	}
	
	private void setPassword() {
		Element passwordTextbox = driver.getElement(passwordTxb);
		passwordTextbox.sendKeys(password);
	}
	
	private void clickLogin() {
		Element loginButton = driver.getElement(loginBtn);
		loginButton.click();
	}
	
	

}
