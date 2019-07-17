package automation.project3ds;

import org.testng.annotations.Test;

public class aSample {
	
	@Test
	public void sample() throws Exception {
		Driver driver = new Driver();
		driver.get("https://www.paymentwall.com/");
	}

}
