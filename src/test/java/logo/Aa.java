package logo;


import org.testng.annotations.Test;

import automation.project3ds.AnnotationPage;
import automation.project3ds.Driver;

public class Aa {
	Driver driver;
	
	String host = "http://feature-wid-91.wallapi.bamboo.stuffio.com";
	
	@Test
	public void makePayment() throws Exception {
		String url = AnnotationPage.WallapiUrl.host(host).widget("t3").isDark().isBrick16().generate();
		System.out.println(url);
	}
	

}
