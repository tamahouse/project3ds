package logo;


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

import automation.project3ds.AnnotationPage;
import automation.project3ds.Driver;
import automation.project3ds.Element;

public class Aa {
	Driver driver;
	
	String host = "http://feature-wid-91.wallapi.bamboo.stuffio.com";
	
	@Test
	public void makePayment() throws Exception {
		String url = AnnotationPage.WallapiUrl.host(host).widget("t3").isDark(false).isBrick16(false).generate();
		System.out.println(url);
	}
	

}
