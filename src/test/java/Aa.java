import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;
import org.testng.annotations.Test;

import automation.project3ds.Driver;
import automation.project3ds.Network;
import automation.project3ds.Driver.Browser;
import automation.project3ds.Wallapi;

public class Aa {

	@Test
	public void meo() throws Exception {
		Driver driver = new Driver(Browser.IE);
		driver.get("https://www.spam4.me/");
		try{
			   FileInputStream fstream = new FileInputStream("c:\\logs\\selenium.log");
			   BufferedReader br = new BufferedReader(new InputStreamReader(fstream));
			   String strLine;
			   /* read log line by line */
			   while ((strLine = br.readLine()) != null)   {
			     /* parse strLine to obtain what you want */
			     System.out.println (strLine);
			   }
			   fstream.close();
			} catch (Exception e) {
			     System.err.println("Error: " + e.getMessage());
			}
	}
}
