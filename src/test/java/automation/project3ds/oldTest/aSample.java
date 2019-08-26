package automation.project3ds.oldTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.annotations.Test;

public class aSample {
	String host = "https://kiemthutudong.com/blog/wp-login.php";
	String xpath = "user_login";
	String outer = "<input type=\"text\" name=\"log\" id=\"user_login\" class=\"input\" value=\"\" size=\"20\">";
	
	@Test
	public static void meo() {
//		WebDriver driver = new InternetExplorerDriver();
	}
	
	  public static void listRunningProcesses() {
	    List<String> processes = new ArrayList<String>();
	    try {
	      String line;
	      Process p = Runtime.getRuntime().exec("tasklist.exe /fo csv /nh");
	      BufferedReader input = new BufferedReader
	          (new InputStreamReader(p.getInputStream()));
	      System.out.println(input.readLine());
	      while ((line = input.readLine()) != null) {
	    	  List<String> elephantList = Arrays.asList(line.split(","));
	    	  System.out.println(elephantList);
	      }
	      input.close();
	    }
	    catch (Exception err) {
	      err.printStackTrace();
	    }
	    System.out.println(processes);
	  }
	  
}
