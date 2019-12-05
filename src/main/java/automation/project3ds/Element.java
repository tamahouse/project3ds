package automation.project3ds;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;


public class Element implements WebElement{
	
	Driver driver;
	WebElement element;
	By by;
	
	
	public Element(WebElement element) {
		this.element = element;
	}
	
	public void moveToTopView() {
		((JavascriptExecutor) this.driver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.sleep(500);
	}
	
	public void moveToDown(int y) {
		y = 0-y;
		((JavascriptExecutor) this.driver.driver).executeScript("window.scrollBy(0," + y + ")", "");
		driver.sleep(500);
	}
	
	public void moveUnderNavigationBar(By byNavigationBar) {
			Element navigationBar = driver.getElement(byNavigationBar,7000);
			Boolean x = element.getLocation()
					.getY() > (navigationBar.getLocation().getY() + navigationBar.getSize().getHeight());
			int count = 0;
			while (x == false) {
				int y = 0 - navigationBar.getSize().getHeight();
				((JavascriptExecutor) this.driver.driver).executeScript("window.scrollBy(0," + y + ")", "");
				element = driver.getElement(by);
				x = element.getLocation()
						.getY() > (navigationBar.getLocation().getY() + navigationBar.getSize().getHeight());
				driver.sleep(100);
				if (++count > 200) {
					x = true;
				}
			}
	}
	
	private String getXPath() {
		return "//*" + (String) ((JavascriptExecutor) this.driver).executeScript(
				"gPt=function(c){if(c.id!==''){return'[@id=\'+'\"'+c.id+'\'+'\"]'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();",
				element);
	}
	
	public WebElement getWebElement() {
		return element;
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
	
	public Element getElement(By smallBy, int miliSecond) {
		int timeout = 100;
		int trialTimes = miliSecond/timeout;
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		for (int count = 0; count < trialTimes;count++) {
			if (this.isExistNow(by) == true) {
				WebElement webElement = element.findElement(smallBy);
				Element element = new Element(webElement);
				element.setDriver(driver);
				String bigStr = driver.getXpathString(by);
				String smallStr = driver.getXpathString(smallBy);
				smallStr = smallStr.replaceFirst(".", "/");
				String str = bigStr+smallStr;
				element.setBy(By.xpath(str));
//				element.highlight();
				return element;
			}
			driver.sleep(timeout);
		}
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		return new Element(element.findElement(by));
	}
	
	public Element getElement(By smallBy) {
		return this.getElement(smallBy, 20000);
	}
	
	public List<Element> getElements(By smallBy) {
		List<Element> list = new ArrayList<Element>();
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
		List<WebElement> webElements = element.findElements(smallBy);
		for (int i = 0; i < webElements.size(); i++) {
			WebElement webElement = webElements.get(i);
			Element element = new Element(webElement);
			element.setDriver(driver);
			String bigStr = driver.getXpathString(by);
			String smallStr = driver.getXpathString(smallBy);
			smallStr = smallStr.replaceFirst(".", "/");
			String str = bigStr+smallStr;
			str = "(" + str + ")[" + i + "]";
			element.setBy(By.xpath(str));
//			element.highlight();
			list.add(element);
		}
		return list;
	}
	
	public Element highlight() {
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		return this;
	}
	
	private WebElement highlight(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver.getWebDriver();
		js.executeScript("arguments[0].setAttribute('style', 'background: cyan; border: 2px solid red;');", element);
		return element;
	}
	
	public void clickJS() {
		JavascriptExecutor executor = (JavascriptExecutor) driver.getWebDriver();
		executor.executeScript("arguments[0].click();", element);
	}
	
	public void clickScroll() {
		int count = 0;
		int maxTries = 200;
		Boolean x = false;
		while (x == false) {
			try {
				this.moveToView();
				this.click();
				x = true;
			} catch (Exception e) {
				e.printStackTrace();
				if (++count > maxTries) {
					throw e;
				}
			} finally {
				driver.sleep(500);
//					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			driver.sleep(500);
		}
	}

//		public void moveToView() {
//			this.moveToView(byNavigationBar);
//		}

	public void moveToView() {
		((JavascriptExecutor) this.driver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		driver.sleep(500);
		try {
			Element navigationBar = driver.getElement(By.xpath("//nav"),7000);
			Boolean x = element.getLocation()
					.getY() > (navigationBar.getLocation().getY() + navigationBar.getSize().getHeight());
			int count = 0;
			while (x == false) {
				int y = 0 - navigationBar.getSize().getHeight();
				((JavascriptExecutor) this.driver.driver).executeScript("window.scrollBy(0," + y + ")", "");
				x = element.getLocation()
						.getY() > (navigationBar.getLocation().getY() + navigationBar.getSize().getHeight());
				Thread.sleep(100);
				if (++count > 200) {
					x = true;
				}
			}
		} catch (Exception e) {

		}
	}
	
	public void setBy(By by) {
		this.by = by;
	}
	
	public void setDriver(Driver driver) {
		this.driver = driver;
	}
	
	public String getTextJS() {
		return (String)((JavascriptExecutor) driver.getWebDriver()).executeScript("return arguments[0].textContent;", element);
	}
	
	public void sendKeysSlow(int speed, CharSequence... keysToSend) throws Exception {
		if (keysToSend != null) {
			Boolean x = false;
			int count = 0;
			while (x == false) {
				try {
					for(CharSequence charSeq: keysToSend ) {
						char[] chars = ((String)charSeq).toCharArray();
						for (char character : chars) {
							String str = String.valueOf(character);
							element.sendKeys(str);
							Thread.sleep(speed);
						}
					}
					x = true;
				} catch (Exception e) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					this.element = this.driver.findElement(by);
					if (++count > 200) {
						throw e;
					}
				}
			}
		}
	}

	public void sendKeysSlow(CharSequence... keysToSend) throws Exception {
		int speed = 10;
		this.sendKeysSlow(speed, keysToSend);
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		// TODO Auto-generated method stub
		return element.getScreenshotAs(target);
	}

	@Override
	public void click() {
		// TODO Auto-generated method stub
//		this.highlight(element);
//		driver.screenShot("C:\\Users\\chase\\Downloads\\Netbanking\\netbanking.png");
		element.click();
	}
	
	public void clickThroughLoading(int miliseconds) {
		int timeout = 300;
		for(int i = 0; i< miliseconds/timeout; i ++) {
			try {
			element.click();
			return;
			}catch(WebDriverException e) {
				this.sleep(timeout);
			}
		}
	}
	
	public void clickThroughLoading() {
		this.clickThroughLoading(20000);
	}
	
	public void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void submit() {
		// TODO Auto-generated method stub
		element.submit();
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		// TODO Auto-generated method stub
//		this.highlight(element);
		element.sendKeys(keysToSend);
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
//		this.highlight(element);
		element.clear();
	}

	@Override
	public String getTagName() {
		// TODO Auto-generated method stub
		return element.getTagName();
	}

	@Override
	public String getAttribute(String name) {
		// TODO Auto-generated method stub
		return element.getAttribute(name);
	}

	@Override
	public boolean isSelected() {
		// TODO Auto-generated method stub
		return element.isSelected();
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return element.isEnabled();
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return element.getText();
	}

	@Override
	public List<WebElement> findElements(By by) {
		// TODO Auto-generated method stub
		return element.findElements(by);
	}

	@Override
	public WebElement findElement(By by) {
		// TODO Auto-generated method stub
		return element.findElement(by);
	}

	@Override
	public boolean isDisplayed() {
		// TODO Auto-generated method stub
		return element.isDisplayed();
	}

	@Override
	public Point getLocation() {
		// TODO Auto-generated method stub
		return element.getLocation();
	}

	@Override
	public Dimension getSize() {
		// TODO Auto-generated method stub
		return element.getSize();
	}

	@Override
	public Rectangle getRect() {
		// TODO Auto-generated method stub
		return element.getRect();
	}

	@Override
	public String getCssValue(String propertyName) {
		// TODO Auto-generated method stub
		return element.getCssValue(propertyName);
	}

}
