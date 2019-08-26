package automation.project3ds;

import org.openqa.selenium.By;

public class Login {

	
	private static By nameTxb = By.id("login");
	private static By passwordTxb = By.id("password");
	private static By loginBtn = By.id("submit_button");
	private static String name = utility.ConfigFile.name;
	private static String password = utility.ConfigFile.password;
	
	public static Driver driver() {
		return AnnotationPage.getDriver();
	}
	
	public static void login(String host) {
		driver().get(host);
		setName();
		setPassword();
		clickLogin();
	}
	
	
	private static void setName() {
		Element nameTextbox = driver().getElement(nameTxb);
		nameTextbox.sendKeys(name);
	}
	
	private static void setPassword() {
		Element passwordTextbox = driver().getElement(passwordTxb);
		passwordTextbox.sendKeys(password);
	}
	
	private static void clickLogin() {
		Element loginButton = driver().getElement(loginBtn);
		loginButton.click();
	}
	
	

}
