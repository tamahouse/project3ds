package automation.project3ds;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.remote.CapabilityType;

public class Driver implements WebDriver {

	WebDriver driver;
	public static String browser = Browser.Chrome;
	

	public static class Browser {
		public static final String Chrome = "Chrome";
		public static final String Firefox = "Firefox";
		public static final String IE = "IE";
	}

	public Driver() {
		this(browser);
	}
	
	public String switchToWindows(String containsInUrl, Boolean x) {
		List<String> tabs = new ArrayList<>(driver.getWindowHandles());
		for(String tab: tabs) {
			driver.switchTo().window(tab);
			String url = driver.getCurrentUrl();
			if(x == true && url.contains(containsInUrl)) {
					return tab;
			}else if(x == false && !url.contains(containsInUrl)) {
				return tab;
			}
		}
		return null;
	}
	public static String timestamp() {
		return String.valueOf(System.currentTimeMillis());
	}
	
	public static void screenShot(WebDriver driver, String filePath) {
		
		TakesScreenshot scrShot =((TakesScreenshot)driver);
		 File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
		 File DestFile=new File(filePath);
		 Boolean x = DestFile.exists();
		 int i = 0;
		 while(x == true) {
		 DestFile=new File(filePath.replace(".png", ++i+".png"));
		 x = DestFile.exists();
		 }
		 try {
			FileUtils.copyFile(SrcFile, DestFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void screenShot(String filePath) {
		screenShot(driver, filePath);
	}
	
	public void screenShot() {
		String[] time = utility.ConfigFile.getTime();
		String folder = "screenShot/"+time[0]+"_"+time[1]+".png";
		screenShot(folder);
	}
	
	public void waitForNumberOfElement(By by, int number, int milisecond) throws Exception {
		int timeout = 100;
		int trialTimes = milisecond/timeout;
		List<WebElement> webElements = null;
		for(int i = 0; i < trialTimes;i++) {
			webElements = driver.findElements(by);
			if(webElements.size() == number) {
				return;
			}
			this.sleep(timeout);
		}
		throw new Exception("expect to have "+number+" but found "+webElements.size()+"");
	}
	
	
	public List<String> waitForNewTab(int numberOfTab) throws Exception {
		for(int i =0; i<200; i++) {
			List<String> list = new ArrayList<>(driver.getWindowHandles());
			if(list.size() == numberOfTab +1) {
				return list;
			}
			Thread.sleep(100);
		}
		return null;
	}
	
	public List<String> waitForNewTab() throws Exception {
		return this.waitForNewTab(1);
	}

	public String getXpathString(By by) {
		String str = by.toString();
		str = str.replace("By.xpath: ", "");
		return str;
	}

	public Driver(String browser) {
		Point p = this.getLastScreenPosition();
		if (browser.equals(Browser.Chrome)) {
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			driver = new ChromeDriver(options);
		} else if (browser.equals(Browser.Firefox)) {
			System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
			driver = new FirefoxDriver();
		}else if(browser.equals(Browser.IE)) {
//			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
//			capabilities.setCapability("requireWindowFocus", true);
			driver = new InternetExplorerDriver();
		}
		try {
		driver.manage().window().setPosition(p);
		}catch (Exception ignore) {
			
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
	}

	private Point getLastScreenPosition() {
		GraphicsEnvironment meo = GraphicsEnvironment.getLocalGraphicsEnvironment();
		GraphicsDevice[] arr = meo.getScreenDevices();
		List<GraphicsDevice> list = Arrays.asList(arr);
		Rectangle screen = null;
		for (int i = list.size() - 1; i > -1; i--) {
			try {
				GraphicsDevice device = list.get(i);
				screen = device.getDefaultConfiguration().getBounds();
				break;
			} catch (Exception e) {

			}
		}
		double xd = screen.getLocation().getX();
		double yd = screen.getLocation().getY();
		int x = (int) xd;
		int y = (int) yd;
		Point p = new Point(x, y);
		return p;
	}

	public Boolean isExistNow(By by) {
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			driver.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public Boolean isExist(By by, int miliSecond) {
		try {
			this.getElement(by, miliSecond);
			return true;
		}catch (Exception e) {
			return false;
		}
	}
	
	public Boolean isExist(By by) {
		return this.isExist(by, 20000);
	}
	
	public String getSelection(Map<By, String> map){
		for(int i = 0; i < 200;i++) {
			for (Map.Entry<By,String> entry : map.entrySet()) {
				if (this.isExistNow(entry.getKey()) == true) {
					String str =  entry.getValue();
					System.out.println(str);
					return str;
				}
			}
			this.sleep(100);
		}
		return null;
	}

	public Element getElement(By by, int miliSecond) {
		int timeout = 100;
		int trialTimes = miliSecond/timeout;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		for (int count = 0; count < trialTimes; count ++) {
			if (this.isExistNow(by) == true) {
				WebElement webElement = driver.findElement(by);
				Element element = new Element(webElement);
				element.setDriver(this);
				element.setBy(by);
//				element.highlight();
				return element;
			}
			this.sleep(timeout);
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		return new Element(driver.findElement(by));
	}
	
	public Element getElement(By by) {
		return this.getElement(by, 20000);
	}

	public List<Element> getElements(By by) {
		List<Element> list = new ArrayList<Element>();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		List<WebElement> webElements = driver.findElements(by);
		for (int i = 0; i < webElements.size(); i++) {
			WebElement webElement = webElements.get(i);
			Element element = new Element(webElement);
			element.setDriver(this);
			String str = this.getXpathString(by);
			str = "(" + str + ")[" + (i+1) + "]";
			element.setBy(By.xpath(str));
//			element.highlight();
			list.add(element);
		}
		return list;
	}
	
	public void waitUrlNotBlank() throws Exception {
		for(int i = 0; i < 200; i++) {
			String url = driver.getCurrentUrl();
			if(url.contains("http")) {
				return;
			}else {
				Thread.sleep(100);
			}
		}
	}

	public WebDriver getWebDriver() {
		return this.driver;
	}

	public void sleep(int milisecond) {
		try {
			Thread.sleep(milisecond);
		} catch (InterruptedException ignore) {
		}
	}

	@Override
	public void get(String url) {
		// TODO Auto-generated method stub
		driver.get(url);
	}

	@Override
	public String getCurrentUrl() {
		// TODO Auto-generated method stub
		return driver.getCurrentUrl();
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return driver.getTitle();
	}

	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return driver.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return driver.findElement(by);
	}

	@Override
	public String getPageSource() {
		// TODO Auto-generated method stub
		return driver.getPageSource();
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		driver.close();
	}

	@Override
	public void quit() {
		// TODO Auto-generated method stub
		driver.quit();
	}

	@Override
	public Set<String> getWindowHandles() {
		// TODO Auto-generated method stub
		return driver.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		// TODO Auto-generated method stub
		return driver.getWindowHandle();
	}

	@Override
	public TargetLocator switchTo() {
		// TODO Auto-generated method stub
		return driver.switchTo();
	}
	
	@Override
	public Navigation navigate() {
		// TODO Auto-generated method stub
		return driver.navigate();
	}

	@Override
	public Options manage() {
		// TODO Auto-generated method stub
		return driver.manage();
	}

}
