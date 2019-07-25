package automation.project3ds;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.interactions.Coordinates;
import org.openqa.selenium.interactions.Locatable;
import org.openqa.selenium.support.events.WebDriverEventListener;

public class ElementBackUp implements WebElement, WrapsElement, WrapsDriver, org.openqa.selenium.interactions.Locatable {

	/**
	 * 
	 */
	private final DriverBackUp driver;
	public WebElement element;
	private final WebElement underlyingElement;
	private By by;

	ElementBackUp(DriverBackUp driver, final WebElement element) {
		this.driver = driver;
		this.element = (WebElement) Proxy.newProxyInstance(WebDriverEventListener.class.getClassLoader(),
				this.driver.extractInterfaces(element), (proxy, method, args) -> {
					if (method.getName().equals("getWrappedElement")) {
						return element;
					}
					try {
						return method.invoke(element, args);
					} catch (InvocationTargetException e) {
						this.driver.dispatcher.onException(e.getTargetException(), this.driver.driver);
						throw e.getTargetException();
					}
				});
		this.underlyingElement = element;
	}

	public void setBy(By by) {
		this.by = by;
	}

	public By getBy() {
		return this.by;
	}

	public DriverBackUp getDriver() {
		return this.driver;
	}

	@Override
	public void click() {
		this.driver.dispatcher.beforeClickOn(element, this.driver.driver);
		Boolean x = false;
		int count = 0;
		while (x == false) {
			try {
				element.click();
				x = true;
			} catch (WebDriverException e2) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				this.element = this.driver.findElement(by);
				if (++count > 200) {
					throw e2;
				}
			} catch (Exception e) {
				throw e;
			}
		}
		this.driver.dispatcher.afterClickOn(element, this.driver.driver);
	}

	public ElementBackUp highlight() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
		return this;
	}

	public void clicked() {
		this.driver.dispatcher.beforeClickOn(element, this.driver.driver);
		element.click();
		this.driver.dispatcher.afterClickOn(element, this.driver.driver);
	}

	public void clickJS() {
		JavascriptExecutor executor = (JavascriptExecutor) driver;
		executor.executeScript("arguments[0].click();", element);
	}

	public ElementBackUp containsText(String text) {
		String str = this.getXPath();
		str = str + "[./descendant::*[contains(text(),'admin')]]";
		return this;
	}

	public void isRefreshed() throws Exception {
		Boolean x = false;
		int count = 0;
		while (x == false) {
			try {
				driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
				element.getSize();
				if (++count > 200) {
					x = true;
				}
				Thread.sleep(100);
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				x = true;

			} catch (ElementClickInterceptedException e1) {

			}
		}
		Thread.sleep(500);
	}

	public void isRestored() throws Exception {
		Boolean x = false;
		int count = 0;
		while (x == false) {
			try {
				driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
				element.getSize();
				if (++count > 200) {
					x = true;
				}
				Thread.sleep(100);
			} catch (StaleElementReferenceException | NoSuchElementException e) {
				ElementBackUp newElement = driver.getElement(this.by);
				newElement.isRefreshed();
				x = true;
			} catch (ElementClickInterceptedException e1) {

			}
		}
		Thread.sleep(500);
	}

	public String getXPath() {
		return "//*" + (String) ((JavascriptExecutor) this.driver.driver).executeScript(
				"gPt=function(c){if(c.id!==''){return'[@id=\'+'\"'+c.id+'\'+'\"]'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();",
				element);
	}

	public Boolean checkExist(By by) {
		this.driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		try {
			this.findElement(by);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void clearBackSpace() throws Exception {
		element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		element.sendKeys(Keys.BACK_SPACE);
		Thread.sleep(500);
	}

	public void clickDebug() {

		element.click();

	}

	@Override
	public void submit() {
		element.submit();
	}

	@Override
	public void sendKeys(CharSequence... keysToSend) {
		if (keysToSend != null) {
			this.driver.dispatcher.beforeChangeValueOf(element, this.driver.driver, keysToSend);
			Boolean x = false;
			int count = 0;
			while (x == false) {
				try {
					element.sendKeys(keysToSend);
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
			this.driver.dispatcher.afterChangeValueOf(element, this.driver.driver, keysToSend);
		}
	}

	public void sendKeysSlow(int speed, CharSequence... keysToSend) throws Exception {
		if (keysToSend != null) {
			this.driver.dispatcher.beforeChangeValueOf(element, this.driver.driver, keysToSend);
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
			this.driver.dispatcher.afterChangeValueOf(element, this.driver.driver, keysToSend);
		}
	}

	public void sendKeysSlow(CharSequence... keysToSend) throws Exception {
		int speed = 10;
		this.sendKeysSlow(speed, keysToSend);
	}
	@Override
	public void clear() {
		this.driver.dispatcher.beforeChangeValueOf(element, this.driver.driver, null);
		element.clear();
		this.driver.dispatcher.afterChangeValueOf(element, this.driver.driver, null);

		this.driver.dispatcher.beforeChangeValueOf(element, this.driver.driver, null);
		Boolean x = false;
		int count = 0;
		while (x == false) {
			try {
				element.clear();
				x = true;
			} catch (Exception e) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (++count > 200) {
					throw e;
				}
			}
		}
		this.driver.dispatcher.afterChangeValueOf(element, this.driver.driver, null);
	}

	public void clearBasic() {
		this.driver.dispatcher.beforeChangeValueOf(element, this.driver.driver, null);
		element.clear();
		this.driver.dispatcher.afterChangeValueOf(element, this.driver.driver, null);

	}

	@Override
	public String getTagName() {
		return element.getTagName();
	}

	@Override
	public String getAttribute(String name) {
		return element.getAttribute(name);
	}

	@Override
	public boolean isSelected() {
		return element.isSelected();
	}

	@Override
	public boolean isEnabled() {
		return element.isEnabled();
	}

	@Override
	public String getText() {
		this.driver.dispatcher.beforeGetText(element, this.driver.driver);
		String text = element.getText();
		this.driver.dispatcher.afterGetText(element, this.driver.driver, text);
		return text;
	}

	@Override
	public boolean isDisplayed() {
		return element.isDisplayed();
	}

	@Override
	public Point getLocation() {
		return element.getLocation();
	}

	@Override
	public Dimension getSize() {
		return element.getSize();
	}

	@Override
	public Rectangle getRect() {
		return element.getRect();
	}

	@Override
	public String getCssValue(String propertyName) {
		return element.getCssValue(propertyName);
	}

	@Override
	public ElementBackUp findElement(By by) {
		this.driver.dispatcher.beforeFindBy(by, element, this.driver.driver);
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		WebElement temp = element.findElement(by);
		this.driver.dispatcher.afterFindBy(by, element, this.driver.driver);
		return this.driver.createWebElement(temp, by);
	}

	public ElementBackUp getElement(By by) {
		this.driver.dispatcher.beforeFindBy(by, element, this.driver.driver);
		Boolean x = false;
		int count = 0;
		WebElement temp = null;
		while (x == false) {
			try {
				temp = element.findElement(by);
				x = true;
			} catch (Exception e) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				this.element = this.driver.getElement(this.by);
				if (++count > 200) {
					System.out.println(this.by.toString().substring(this.by.toString().indexOf(" ") + 1)
							+ by.toString().substring(by.toString().indexOf(" ") + 2));
					x = true;
				}
			}
		}
		By lastBy = By.xpath(this.by.toString().substring(this.by.toString().indexOf(" ") + 1)
				+ by.toString().substring(by.toString().indexOf(" ") + 2));
		this.driver.dispatcher.afterFindBy(by, element, this.driver.driver);
		return this.driver.createWebElement(temp, lastBy);
	}

	public WebElement toWebElement() {
		return element;
	}

	@Override
	public List<WebElement> findElements(By by) {
		this.driver.dispatcher.beforeFindBy(by, element, this.driver.driver);
		List<WebElement> temp = element.findElements(by);
		this.driver.dispatcher.afterFindBy(by, element, this.driver.driver);
		List<WebElement> result = new ArrayList<>(temp.size());
		for (WebElement element : temp) {
			result.add(this.driver.createWebElement(element, by));
		}
		return result;
	}

	public List<ElementBackUp> getElements(By by) {
		this.driver.dispatcher.beforeFindBy(by, element, this.driver.driver);
		List<WebElement> temp = element.findElements(by);
		this.driver.dispatcher.afterFindBy(by, element, this.driver.driver);
		List<ElementBackUp> result = new ArrayList<>(temp.size());
		for (WebElement element : temp) {
			result.add(this.driver.createWebElement(element, by));
		}
		return result;
	}

	public void clickScroll() throws Exception {
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
				Thread.sleep(500);
//					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			}
			Thread.sleep(100);
		}
	}

//		public void moveToView() {
//			this.moveToView(byNavigationBar);
//		}

	public void moveToView() throws Exception {
		((JavascriptExecutor) this.driver.driver).executeScript("arguments[0].scrollIntoView(true);", element);
		Thread.sleep(500);
		try {
			driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
			ElementBackUp navigationBar = this.findElement(By.xpath("//nav"));
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

	@Override
	public WebElement getWrappedElement() {
		return underlyingElement;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof WebElement)) {
			return false;
		}

		WebElement other = (WebElement) obj;
		if (other instanceof WrapsElement) {
			other = ((WrapsElement) other).getWrappedElement();
		}

		return underlyingElement.equals(other);
	}

	@Override
	public int hashCode() {
		return underlyingElement.hashCode();
	}

	@Override
	public String toString() {
		return underlyingElement.toString();
	}

	@Override
	public WebDriver getWrappedDriver() {
		return this.driver.driver;
	}

	@Override
	public Coordinates getCoordinates() {
		return ((Locatable) underlyingElement).getCoordinates();
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> outputType) throws WebDriverException {
		return element.getScreenshotAs(outputType);
	}
}