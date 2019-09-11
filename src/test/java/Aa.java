

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import automation.project3ds.Driver;
import automation.project3ds.Element;

public class Aa {
	String host = "file:///C:/Users/chase/Documents/WXWork/1688853700350181/Cache/File/2019-09/bon_suraj.html";
	Driver driver;
	
	@Test
	public void makePayment() throws Exception {
		driver = new Driver();
		driver.get(host);
		this.clickFasterPayButton();
		this.setEmail();
		this.setFirstName();
		this.setLastName();
//		...
		this.clickPayButton();
	}
	
	public void clickFasterPayButton() throws Exception {

		Element payButton = driver.getElement(By.id("js-fp-button"));
		payButton.click();
	}
	
	public void setEmail() {
		
	}
	
	public void setFirstName() {
		
	}
	
	public void setLastName() {
		
	}
	
	public void clickPayButton() {
		
	}
}
