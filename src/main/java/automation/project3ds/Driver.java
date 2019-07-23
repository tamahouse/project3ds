package automation.project3ds;

import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.stream.Collectors;

import org.openqa.selenium.Alert;
import org.openqa.selenium.Beta;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.HasCapabilities;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WrapsDriver;
import org.openqa.selenium.WrapsElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.HasInputDevices;
import org.openqa.selenium.interactions.HasTouchScreen;
import org.openqa.selenium.interactions.Interactive;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.interactions.TouchScreen;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.logging.Logs;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.support.events.WebDriverEventListener;
import org.openqa.selenium.support.events.internal.EventFiringKeyboard;
import org.openqa.selenium.support.events.internal.EventFiringMouse;
import org.openqa.selenium.support.events.internal.EventFiringTouch;

import utility.ConfigFile;

/**
 * A wrapper around an arbitrary {@link WebDriver} instance which supports
 * registering of a {@link WebDriverEventListener}, e&#46;g&#46; for logging
 * purposes.
 */
@SuppressWarnings("deprecation")
public class Driver implements WebDriver, JavascriptExecutor, TakesScreenshot, WrapsDriver, HasInputDevices,
		HasTouchScreen, Interactive, HasCapabilities {

	final WebDriver driver;
	public static String browser = Browser.Firefox;

	public Driver() throws Exception {
		this(browser);
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
	


	public Driver(String browser) throws Exception {
		Driver driver = null;
		Point p = this.getLastScreenPosition();
		String workspacePath = ConfigFile.getWorkspacePath();
		if (browser.equals(Browser.Chrome)) {
			LoggingPreferences logPrefs = new LoggingPreferences();
			logPrefs.enable(LogType.BROWSER, Level.ALL);
			ChromeOptions options = new ChromeOptions();
			options.addArguments("disable-infobars");
			options.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);
			System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
			WebDriver e_driver = new ChromeDriver(options);
			driver = new Driver(e_driver);
		} else if (browser.equals(Browser.Firefox)) {
			System.setProperty("webdriver.firefox.driver", "geckodriver.exe");
			System.setProperty(FirefoxDriver.SystemProperty.DRIVER_USE_MARIONETTE, "true");
			System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE, "/dev/null");
			WebDriver e_driver = new FirefoxDriver();
			driver = new Driver(e_driver);
		}

		driver.manage().window().setPosition(p);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(7, TimeUnit.SECONDS);
		this.driver = driver;
	}

	public static class Browser {
		static final String Chrome = "Chrome";
		static final String Firefox = "Firefox";
	}
	
	public void waitUtilJSLoaded() {
		int count = 0;
		while(++count < 200) {
			if(this.isJSLoaded() == true) {
				count = 500;
			}
		}
	}
	
	private Boolean isJSLoaded() {
		return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	}

	private final List<WebDriverEventListener> eventListeners = new ArrayList<>();
	final WebDriverEventListener dispatcher = (WebDriverEventListener) Proxy.newProxyInstance(
			WebDriverEventListener.class.getClassLoader(), new Class[] { WebDriverEventListener.class },
			(proxy, method, args) -> {
				try {
					for (WebDriverEventListener eventListener : eventListeners) {
						method.invoke(eventListener, args);
					}
					return null;
				} catch (InvocationTargetException e) {
					throw e.getTargetException();
				}
			});

	public Driver(final WebDriver driver) {
		Class<?>[] allInterfaces = extractInterfaces(driver);

		this.driver = (WebDriver) Proxy.newProxyInstance(WebDriverEventListener.class.getClassLoader(), allInterfaces,
				(proxy, method, args) -> {
					if ("getWrappedDriver".equals(method.getName())) {
						return driver;
					}

					try {
						return method.invoke(driver, args);
					} catch (InvocationTargetException e) {
						dispatcher.onException(e.getTargetException(), driver);
						throw e.getTargetException();
					}
				});
	}

	Class<?>[] extractInterfaces(Object object) {
		Set<Class<?>> allInterfaces = new HashSet<>();
		allInterfaces.add(WrapsDriver.class);
		if (object instanceof WebElement) {
			allInterfaces.add(WrapsElement.class);
		}
		extractInterfaces(allInterfaces, object.getClass());

		return allInterfaces.toArray(new Class<?>[allInterfaces.size()]);
	}

	private void extractInterfaces(Set<Class<?>> addTo, Class<?> clazz) {
		if (Object.class.equals(clazz)) {
			return; // Done
		}

		Class<?>[] classes = clazz.getInterfaces();
		addTo.addAll(Arrays.asList(classes));
		extractInterfaces(addTo, clazz.getSuperclass());
	}

	/**
	 * @param eventListener the event listener to register
	 * @return this for method chaining.
	 */
	public Driver register(WebDriverEventListener eventListener) {
		eventListeners.add(eventListener);
		return this;
	}

	/**
	 * @param eventListener the event listener to unregister
	 * @return this for method chaining.
	 */
	public Driver unregister(WebDriverEventListener eventListener) {
		eventListeners.remove(eventListener);
		return this;
	}

	@Override
	public WebDriver getWrappedDriver() {
		if (driver instanceof WrapsDriver) {
			return ((WrapsDriver) driver).getWrappedDriver();
		}
		return driver;
	}

	@Override
	public void get(String url) {
		dispatcher.beforeNavigateTo(url, driver);
		driver.get(url);
		dispatcher.afterNavigateTo(url, driver);
	}

	@Override
	public String getCurrentUrl() {
		return driver.getCurrentUrl();
	}

	public String getCurrentUrl(String containsKey) throws Exception {
		int times = 200;
		return this.getCurrentUrl(containsKey, times);
	}

	public String getCurrentUrl(String containsKey, int times) throws Exception {
		Boolean x = false;
		int count = 0;
		while (x == false) {
			String str = this.getCurrentUrl();
			if (str.contains(containsKey)) {
				return str;
			} else {
				if (++count > times) {
					return null;
				}
			}
			Thread.sleep(100);
		}
		return null;
	}

	@Override
	public String getTitle() {
		return driver.getTitle();
	}

	@Override
	public List<WebElement> findElements(By by) {
		dispatcher.beforeFindBy(by, null, driver);
		List<WebElement> temp = driver.findElements(by);
		dispatcher.afterFindBy(by, null, driver);
		List<WebElement> result = new ArrayList<>(temp.size());
		for (WebElement element : temp) {
			result.add(createWebElement(element, by));
		}
		return result;
	}

	public List<Element> getElements(By by) {
		dispatcher.beforeFindBy(by, null, driver);
		List<WebElement> temp = driver.findElements(by);
		dispatcher.afterFindBy(by, null, driver);
		List<Element> result = new ArrayList<>(temp.size());
		for (WebElement element : temp) {
//			by = By.xpath(this.getXPath(element));
			result.add(createWebElement(element, by));
		}
		return result;
	}

	public String getXPath(WebElement element) {
		return "//*" + (String) ((JavascriptExecutor) this.driver).executeScript(
				"gPt=function(c){if(c.id!==''){return'[@id=\'+'\"'+c.id+'\'+'\"]'}if(c===document.body){return c.tagName}var a=0;var e=c.parentNode.childNodes;for(var b=0;b<e.length;b++){var d=e[b];if(d===c){return gPt(c.parentNode)+'/'+c.tagName+'['+(a+1)+']'}if(d.nodeType===1&&d.tagName===c.tagName){a++}}};return gPt(arguments[0]).toLowerCase();",
				element);
	}

	@Override
	public Element findElement(By by) {
		dispatcher.beforeFindBy(by, null, driver);
		driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
		WebElement temp = driver.findElement(by);
		dispatcher.afterFindBy(by, temp, driver);
		return createWebElement(temp, by);
	}

	public Element getElement(By by) {

		dispatcher.beforeFindBy(by, null, driver);
		Boolean x = false;
		int count = 0;
		WebElement temp = null;
		while (x == false) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
				temp = driver.findElement(by);
				highlight(driver, temp);
				x = true;
			} catch (Exception e) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (++count > 100) {
					throw e;
				}
			}
		}
		dispatcher.afterFindBy(by, temp, driver);
		return createWebElement(temp, by);
	}

	public void tryClick(By by, int time) throws Exception {
		if (this.checkExist(by, time) == true) {
			this.findElement(by).click();
		}
	}

	public void tryClick(By by) throws Exception {
		if (this.checkExist(by) == true) {
			this.findElement(by).click();
		}
	}

	public void tryClick(By main, By vice) throws Exception {
		int time = 20;
		if (this.checkExist(main, vice, time) == true) {
			this.findElement(main).click();
		}
	}

	public Boolean checkExist(By by) {
		int time = 10;
		return this.checkExist(by, time);
	}

	public Boolean isSucessful(By byelementSucessful, By byelementFailed) {
		Boolean x = false;
		Boolean result = null;
		int count = 0;
		WebElement temp = null;
		while (x == false) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
				temp = driver.findElement(byelementSucessful);
				highlight(driver, temp);
				result = true;
				x = true;
			} catch (Exception e) {
				try {
					driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
					temp = driver.findElement(byelementFailed);
					highlight(driver, temp);
					result = false;
					x = true;
				} catch (Exception e1) {
					if (++count > 20) {
						x = true;
					}
				}
			}
		}
		return result;
	}

	public Boolean isInstanceExist(By by) {
		WebElement temp = null;
		try {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			temp = driver.findElement(by);
			highlight(driver, temp);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public String getSelectedCase(Map<By, String> map) throws Exception {
		Boolean x = false;
		int count = 0;
		while (x == false) {
			for (Map.Entry<By, String> entry : map.entrySet()) {
				if (this.isInstanceExist(entry.getKey()) == true) {
					return entry.getValue();
				}
			}
			Thread.sleep(100);
			if (++count > 100) {
				return null;
			}
		}
		return null;
	}

	public Boolean checkExist(By by, int time) {
		Boolean x = false;
		int count = 0;
		while (x == false) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
				WebElement temp = driver.findElement(by);
				highlight(driver, temp);
				return true;
			} catch (Exception e) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (++count > time) {
					return false;
				}
			}
		}
		return false;
	}

	public Boolean checkAlertExist(int time) {
		Boolean x = false;
		int count = 0;
		while (x == false) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
				driver.switchTo().alert();
				return true;
			} catch (Exception e) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (++count > time) {
					return false;
				}
			}
		}
		return false;
	}

	private Boolean checkExist(By main, By vice, int time) {
		Boolean x = false;
		int count = 0;
		while (x == false) {
			try {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
				WebElement temp = driver.findElement(main);
				highlight(driver, temp);
				return true;
			} catch (Exception e) {
				try {
					WebElement viceElement = driver.findElement(vice);
					highlight(driver, viceElement);
					return false;
				} catch (Exception e1) {

				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				if (++count > time) {
					return false;
				}
			}
		}
		return false;
	}

	public static void highlight(WebDriver driver, WebElement element) {
//		JavascriptExecutor js = (JavascriptExecutor) driver;
//		js.executeScript("arguments[0].setAttribute('style', 'background: yellow; border: 2px solid red;');", element);
	}

	@Override
	public String getPageSource() {
		return driver.getPageSource();
	}

	@Override
	public void close() {
		driver.close();
	}

	@Override
	public void quit() {
		driver.quit();
	}

	@Override
	public Set<String> getWindowHandles() {
		return driver.getWindowHandles();
	}

	@Override
	public String getWindowHandle() {
		return driver.getWindowHandle();
	}

	@Override
	public Object executeScript(String script, Object... args) {
		if (driver instanceof JavascriptExecutor) {
			dispatcher.beforeScript(script, driver);
			Object[] usedArgs = unpackWrappedArgs(args);
			Object result = ((JavascriptExecutor) driver).executeScript(script, usedArgs);
			dispatcher.afterScript(script, driver);
			return wrapResult(result);
		}
		throw new UnsupportedOperationException("Underlying driver instance does not support executing javascript");
	}

	@Override
	public Object executeAsyncScript(String script, Object... args) {
		if (driver instanceof JavascriptExecutor) {
			dispatcher.beforeScript(script, driver);
			Object[] usedArgs = unpackWrappedArgs(args);
			Object result = ((JavascriptExecutor) driver).executeAsyncScript(script, usedArgs);
			dispatcher.afterScript(script, driver);
			return result;
		}
		throw new UnsupportedOperationException("Underlying driver instance does not support executing javascript");
	}

	private Object[] unpackWrappedArgs(Object... args) {
		// Walk the args: the various drivers expect unpacked versions of the elements
		Object[] usedArgs = new Object[args.length];
		for (int i = 0; i < args.length; i++) {
			usedArgs[i] = unpackWrappedElement(args[i]);
		}
		return usedArgs;
	}

	private Object unpackWrappedElement(Object arg) {
		if (arg instanceof List<?>) {
			List<?> aList = (List<?>) arg;
			List<Object> toReturn = new ArrayList<>();
			for (Object anAList : aList) {
				toReturn.add(unpackWrappedElement(anAList));
			}
			return toReturn;
		} else if (arg instanceof Map<?, ?>) {
			Map<?, ?> aMap = (Map<?, ?>) arg;
			Map<Object, Object> toReturn = new HashMap<>();
			for (Object key : aMap.keySet()) {
				toReturn.put(key, unpackWrappedElement(aMap.get(key)));
			}
			return toReturn;
		} else if (arg instanceof Element) {
			return ((Element) arg).getWrappedElement();
		} else {
			return arg;
		}
	}

	private Object wrapResult(Object result) {
		if (result instanceof WebElement) {
			return new Element(this, (WebElement) result);
		}
		if (result instanceof List) {
			return ((List<?>) result).stream().map(this::wrapResult).collect(Collectors.toList());
		}
		if (result instanceof Map) {
			return ((Map<?, ?>) result).entrySet().stream().collect(HashMap::new,
					(m, e) -> m.put(e.getKey(), e.getValue()), Map::putAll);
		}

		return result;
	}

	@Override
	public <X> X getScreenshotAs(OutputType<X> target) throws WebDriverException {
		if (driver instanceof TakesScreenshot) {
			dispatcher.beforeGetScreenshotAs(target);
			X screenshot = ((TakesScreenshot) driver).getScreenshotAs(target);
			dispatcher.afterGetScreenshotAs(target, screenshot);
			return screenshot;
		}

		throw new UnsupportedOperationException("Underlying driver instance does not support taking screenshots");
	}

	@Override
	public TargetLocator switchTo() {
		return new EventFiringTargetLocator(driver.switchTo());
	}

	@Override
	public Navigation navigate() {
		return new EventFiringNavigation(driver.navigate());
	}

	@Override
	public Options manage() {
		return new EventFiringOptions(driver.manage());
	}

	Element createWebElement(WebElement from, By by) {
		Element finalElement = new Element(this, from);
		finalElement.setBy(by);
		return finalElement;
	}

	@Override
	public Keyboard getKeyboard() {
		if (driver instanceof HasInputDevices) {
			return new EventFiringKeyboard(driver, dispatcher);
		}
		throw new UnsupportedOperationException(
				"Underlying driver does not implement advanced" + " user interactions yet.");
	}

	@Override
	public Mouse getMouse() {
		if (driver instanceof HasInputDevices) {
			return new EventFiringMouse(driver, dispatcher);
		}
		throw new UnsupportedOperationException(
				"Underlying driver does not implement advanced" + " user interactions yet.");
	}

	@Override
	public TouchScreen getTouch() {
		if (driver instanceof HasTouchScreen) {
			return new EventFiringTouch(driver, dispatcher);
		}
		throw new UnsupportedOperationException(
				"Underlying driver does not implement advanced" + " user interactions yet.");
	}

	@Override
	public void perform(Collection<Sequence> actions) {
		if (driver instanceof Interactive) {
			((Interactive) driver).perform(actions);
			return;
		}
		throw new UnsupportedOperationException(
				"Underlying driver does not implement advanced" + " user interactions yet.");

	}

	@Override
	public void resetInputState() {
		if (driver instanceof Interactive) {
			((Interactive) driver).resetInputState();
			return;
		}
		throw new UnsupportedOperationException(
				"Underlying driver does not implement advanced" + " user interactions yet.");

	}

	@Override
	public Capabilities getCapabilities() {
		if (driver instanceof HasCapabilities) {
			return ((HasCapabilities) driver).getCapabilities();
		}
		throw new UnsupportedOperationException("Underlying driver does not implement getting capabilities yet.");
	}

	private class EventFiringNavigation implements Navigation {

		private final WebDriver.Navigation navigation;

		EventFiringNavigation(Navigation navigation) {
			this.navigation = navigation;
		}

		@Override
		public void to(String url) {
			dispatcher.beforeNavigateTo(url, driver);
			navigation.to(url);
			dispatcher.afterNavigateTo(url, driver);
		}

		@Override
		public void to(URL url) {
			to(String.valueOf(url));
		}

		@Override
		public void back() {
			dispatcher.beforeNavigateBack(driver);
			navigation.back();
			dispatcher.afterNavigateBack(driver);
		}

		@Override
		public void forward() {
			dispatcher.beforeNavigateForward(driver);
			navigation.forward();
			dispatcher.afterNavigateForward(driver);
		}

		@Override
		public void refresh() {
			dispatcher.beforeNavigateRefresh(driver);
			navigation.refresh();
			dispatcher.afterNavigateRefresh(driver);
		}
	}

	private class EventFiringOptions implements Options {

		private Options options;

		private EventFiringOptions(Options options) {
			this.options = options;
		}

		@Override
		public Logs logs() {
			return options.logs();
		}

		@Override
		public void addCookie(Cookie cookie) {
			options.addCookie(cookie);
		}

		@Override
		public void deleteCookieNamed(String name) {
			options.deleteCookieNamed(name);
		}

		@Override
		public void deleteCookie(Cookie cookie) {
			options.deleteCookie(cookie);
		}

		@Override
		public void deleteAllCookies() {
			options.deleteAllCookies();
		}

		@Override
		public Set<Cookie> getCookies() {
			return options.getCookies();
		}

		@Override
		public Cookie getCookieNamed(String name) {
			return options.getCookieNamed(name);
		}

		@Override
		public Timeouts timeouts() {
			return new EventFiringTimeouts(options.timeouts());
		}

		@Override
		public ImeHandler ime() {
			return options.ime();
		}

		@Override
		@Beta
		public Window window() {
			return new EventFiringWindow(options.window());
		}
	}

	private class EventFiringTimeouts implements Timeouts {

		private final Timeouts timeouts;

		EventFiringTimeouts(Timeouts timeouts) {
			this.timeouts = timeouts;
		}

		@Override
		public Timeouts implicitlyWait(long time, TimeUnit unit) {
			timeouts.implicitlyWait(time, unit);
			return this;
		}

		@Override
		public Timeouts setScriptTimeout(long time, TimeUnit unit) {
			timeouts.setScriptTimeout(time, unit);
			return this;
		}

		@Override
		public Timeouts pageLoadTimeout(long time, TimeUnit unit) {
			timeouts.pageLoadTimeout(time, unit);
			return this;
		}
	}

	private class EventFiringTargetLocator implements TargetLocator {

		private TargetLocator targetLocator;

		private EventFiringTargetLocator(TargetLocator targetLocator) {
			this.targetLocator = targetLocator;
		}

		@Override
		public WebDriver frame(int frameIndex) {
			return targetLocator.frame(frameIndex);
		}

		@Override
		public WebDriver frame(String frameName) {
			return targetLocator.frame(frameName);
		}

		@Override
		public WebDriver frame(WebElement frameElement) {
			return targetLocator.frame(frameElement);
		}

		@Override
		public WebDriver parentFrame() {
			return targetLocator.parentFrame();
		}

		@Override
		public WebDriver window(String windowName) {
			dispatcher.beforeSwitchToWindow(windowName, driver);
			WebDriver driverToReturn = targetLocator.window(windowName);
			dispatcher.afterSwitchToWindow(windowName, driver);
			return driverToReturn;
		}

		@Override
		public WebDriver defaultContent() {
			return targetLocator.defaultContent();
		}

		@Override
		public WebElement activeElement() {
			return targetLocator.activeElement();
		}

		@Override
		public Alert alert() {
			return new EventFiringAlert(this.targetLocator.alert());
		}
	}

	@Beta
	private class EventFiringWindow implements Window {
		private final Window window;

		EventFiringWindow(Window window) {
			this.window = window;
		}

		@Override
		public void setSize(Dimension targetSize) {
			window.setSize(targetSize);
		}

		@Override
		public void setPosition(Point targetLocation) {
			window.setPosition(targetLocation);
		}

		@Override
		public Dimension getSize() {
			return window.getSize();
		}

		@Override
		public Point getPosition() {
			return window.getPosition();
		}

		@Override
		public void maximize() {
			window.maximize();
		}

		@Override
		public void fullscreen() {
			window.fullscreen();
		}
	}

	private class EventFiringAlert implements Alert {
		private final Alert alert;

		private EventFiringAlert(Alert alert) {
			this.alert = alert;
		}

		@Override
		public void dismiss() {
			dispatcher.beforeAlertDismiss(driver);
			alert.dismiss();
			dispatcher.afterAlertDismiss(driver);
		}

		@Override
		public void accept() {
			dispatcher.beforeAlertAccept(driver);
			alert.accept();
			dispatcher.afterAlertAccept(driver);
		}

		@Override
		public String getText() {
			return alert.getText();
		}

		@Override
		public void sendKeys(String keysToSend) {
			alert.sendKeys(keysToSend);
		}
	}

}
