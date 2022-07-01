package com.evs.qcodes.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebUtil {

	// ---------------------------------- OR -------------------------------\\

	public static final Logger logger = Logger.getLogger(WebUtil.class);

	private WebDriver driver;
	private ExtentTest extentLogger;

	private ExtentHtmlReporter htmlReporter;

	private static ExtentReports extent;

	public WebDriver getdriver() {
		return driver;
	}

	// ---------------LAUNCH BROWSER-----------------------\\

	public void launchBrowser(String browsername) {
		if (browsername.equalsIgnoreCase("chrome")) {

			WebDriverManager.chromedriver().setup();
			// options.addArguments("headless");
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[] { "enable-automation" });
			options.addArguments("disable-popup-blocking");
			options.addArguments("incognito");
			driver = new ChromeDriver(options);

			logger.info("Chrome Browser has been launched successfully");

		} else if (browsername.equalsIgnoreCase("firefox")) {

			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();

			logger.info("firefox Browser has been launched successfully");

		} else if (browsername.equalsIgnoreCase("ie")) {

			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();

			logger.info("InternetExplorer Browser has been launched successfully");

		} else if (browsername.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();

			logger.info(" edge Browser has been launched successfully");

		}
		logger.info(browsername + "is started  succesfully");
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
	}

	public void navigateUrl(String URL) {
      driver.get(URL);
	}
	// --------------------- close and quit------------\\

	public void close() {
		driver.close();
	}

	public void quit() {
		driver.quit();
	}

	public void setExtentLogger(ExtentReports extent, String TestCaseName) {
		extentLogger = extent.createTest(TestCaseName);
	}

	public ExtentTest getExtentLogger() {
		return extentLogger;
	}

	// ----------------------------------FindElement-------------------------------\\

	public WebElement getElement(String locatorType, String locatorValue) {
		WebElement element = null;
		if (locatorType.equalsIgnoreCase("xpath")) {
			Holdon(3000);
			element = driver.findElement(By.xpath(locatorValue));
		}

		else if (locatorType.equalsIgnoreCase("id")) {
			element = driver.findElement(By.id(locatorValue));
		}

		else if (locatorType.equalsIgnoreCase("class")) {
			element = driver.findElement(By.className(locatorValue));
		}

		else if (locatorType.equalsIgnoreCase("linktext")) {
			element = driver.findElement(By.linkText(locatorValue));
		}

		else if (locatorType.equalsIgnoreCase("Partiallinktext")) {
			element = driver.findElement(By.partialLinkText(locatorValue));
		}

		else if (locatorType.equalsIgnoreCase("name")) {
			element = driver.findElement(By.name(locatorValue));
		}else if(locatorType.equalsIgnoreCase(locatorValue)) {
			element = driver.findElement(By.cssSelector(locatorValue));

		}

		return element;

	}

	// ----------------------------------FindElements-------------------------------\\

	public List<WebElement> findelements(String locator, String path) {
		List<WebElement> elements = null;

		if (locator.equalsIgnoreCase("xpath")) {
			elements = driver.findElements(By.xpath(path));
		}

		else if (locator.equalsIgnoreCase("id")) {
			elements = driver.findElements(By.id(path));
		}

		else if (locator.equalsIgnoreCase("class")) {
			elements = driver.findElements(By.className(path));
		}

		else if (locator.equalsIgnoreCase("linktext")) {
			elements = driver.findElements(By.linkText(path));
		}

		else if (locator.equalsIgnoreCase("Partiallinktext")) {
			elements = driver.findElements(By.partialLinkText(path));
		}

		else if (locator.equalsIgnoreCase("name")) {
			elements = driver.findElements(By.name(path));
		}

		return elements;

	}

	// ----------------------------------WebElement-------------------------------\\

	public void sendKeys(WebElement element, String value, String objectName) {

		element.sendKeys(value);
	}

	public void click(WebElement element, String objectName) {

		try {
			element.click();
			logger.info("clicked on" + objectName + "is succesfully");

		} catch (ElementClickInterceptedException e) {
			logger.error("ElementClickInterceptedException throwing on" + objectName);

			HoldOn(1000);
			jsClick(element);

		} catch (StaleElementReferenceException e) {
			element.click();
		} catch (ElementNotVisibleException e) {
			HoldOn(1000);
			jsClick(element);

		}
	}

	public void HoldOn(int time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getText(WebElement element) {
		String text = element.getText();
		return text;
	}

	public String getAttributeValue(WebElement element, String Attribute) {
		element.getAttribute(Attribute);
		return Attribute;
	}

	// ------------------- WindowHandles---------------------\\

	public void windowHandles(String titles) {
		Set<String> wndw = driver.getWindowHandles();
		for (String window : wndw) {
			driver.switchTo().window(window);
			String title = driver.getTitle();
			if (title.equalsIgnoreCase(titles)) {
				break;
			}
		}
	}

	public void windowHandleExpectedOpen(String titles, String windowName) {
		Set<String> wndw = driver.getWindowHandles();
		for (String window : wndw) {
			driver.switchTo().window(window);
			String title = driver.getTitle();
			if (!title.equalsIgnoreCase(titles)) {
				driver.close();
			}
		}
	}

	// ---------------------------------- Alert -------------------------------\\

	public void alertAccept() {
		driver.switchTo().alert().accept();
	}

	public void alertDeny() {
		driver.switchTo().alert().dismiss();
	}

	public void alertSendKeys(String keysToSend) {
		driver.switchTo().alert().sendKeys(keysToSend);
	}

	public void alertGetText() {
		driver.switchTo().alert().getText();
	}
	// --------------- Navigate-------------\\

	public void refersh() {
		driver.navigate().refresh();
	}

	public void back() {
		driver.navigate().back();
	}

	public void forward() {
		driver.navigate().forward();
	}

	// ---------------------------------- select class
	// -------------------------------\\

	public void selectByIndex(WebElement element, int indexnumber) {
		Select se = new Select(element);
		se.selectByIndex(indexnumber);
	}

	public void selectByValue(WebElement element, String value) {
		Select se = new Select(element);
		se.selectByValue(value);
	}

	public void selectByVisibleText(WebElement element, String text) {
		Select se = new Select(element);
		se.selectByVisibleText(text);
	}

	public void deselectAll(WebElement element) {
		Select se = new Select(element);
		se.deselectAll();
	}

	public void deselectByIndex(WebElement element, int indexnumber) {
		Select se = new Select(element);
		se.deselectByIndex(indexnumber);

	}

	public void deselectByValue(WebElement element, String value) {
		Select se = new Select(element);
		se.deselectByValue(value);
	}

	public void deselectByVisibleText(WebElement element, String value) {
		Select se = new Select(element);
		se.deselectByVisibleText(value);
	}

	// ----------------------------------I-Frame-------------------------------\\

	public WebDriver frameByNumber(int number) {
		driver.switchTo().frame(number);
		return driver;
	}

	public WebDriver frameByWebelement(WebElement element) {
		driver.switchTo().frame(element);
		return driver;
	}

	public WebDriver frameByString(String value) {
		driver.switchTo().frame(value);
		return driver;
	}

	public void Holdon(int timeInMilliSecond) {
		try {
			Thread.sleep(timeInMilliSecond);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -----------------------------------ScreenShot-----------------------\\

	public String takeSnapshot(String methodname) {
		SimpleDateFormat timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss");
		Date date = new Date();
		String dat = timestamp.format(date);
		String da = dat.replace("/", "-");
		String d = da.replace(":", "_");

		TakesScreenshot tcc = (TakesScreenshot) driver;
		File srcFile = tcc.getScreenshotAs(OutputType.FILE);
		File destFile = new File("Report/" + methodname + d + ".png");
		String path = destFile.getAbsolutePath();

		try {
			Files.copy(srcFile, destFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}

	// -------------------------Verifications Methods-------------------------\\

	public void takesnapShotsOfElement(WebElement element) {

		// Get entire page screenshot
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImg = null;
		try {
			fullImg = ImageIO.read(screenshot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Get the location of element on the page
		Point point = element.getLocation();

		// Get width and height of the element
		int eleWidth = element.getSize().getWidth();
		int eleHeight = element.getSize().getHeight();

		// Crop the entire page screenshot to get only element screenshot
		BufferedImage eleScreenshot = fullImg.getSubimage(point.getX(), point.getY(), eleWidth, eleHeight);
		try {
			ImageIO.write(eleScreenshot, "png", screenshot);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Copy the element screenshot to disk
		File screenshotLocation = new File("SnapShots/" + SimpleDateTimeFormate() + ".png");
		try {
			Files.copy(screenshot, screenshotLocation);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void verifyText(WebElement element, String expectedText, String elementname) {
		String actualText = element.getText();
		if (actualText.equals(expectedText)) {
		
		} else {
					try {
				Assert.assertEquals(actualText, expectedText);
			} catch (Throwable t) {
				takesnapShotsOfElement(element);
				extentLogger.log(Status.FAIL, t);

			}
		}
	}

	public void verifyAttributeValue(WebElement element, String Atttributename, String expectedvalue,
			String elementname) {
		String actualValue = element.getAttribute(Atttributename);
		System.out.println(actualValue);
		if (actualValue.equals(expectedvalue)) {
		
		} else {
			extentLogger.log(Status.FAIL,
					MarkupHelper.createLabel(elementname + " attributeValue is not present", ExtentColor.RED));

			try {
				Assert.assertEquals(actualValue, expectedvalue);
			} catch (Throwable t) {

			}
		}

	}

	public void verifyElement_IsVisible(WebElement element, String elementname) {
		boolean actual = element.isDisplayed();
		
				Assert.assertEquals(actual, true);
			
		

	}

	public void verifyElement_InIsVisible(WebElement element, String elementname) {
		boolean actual = element.isDisplayed();
		
		

	}

	public void verifyElement_IsEnable(WebElement element, String elementname) {
		boolean actual = element.isEnabled();
		
				Assert.assertEquals(actual, true);
		
	}

	public void verifyElement_IsSelected(WebElement element, String elementname) {
		boolean actual = element.isSelected();
		
				Assert.assertEquals(actual, true);
			
	}

	// --------------------------------- test case wise
	// Excel-------------------------------\\
	private Map<String, String> testCaseDataMap;

	public void setTestCaseDataMap(Map<String, String> testCaseData) {

		testCaseDataMap = testCaseData;
	}

	public String getTestData(String dataName) {

		return testCaseDataMap.get(dataName);
	}

	// -----------------------Config File------------------------------------\\

	private static Properties propObj;

	public String getPropVal(String keyname) {
		if (propObj == null) {
			load_config(Filename);
		}

		String propVal = propObj.getProperty(keyname);
		return propVal;
	}

	public void setProp(String key, String value) {
		if (propObj == null) {
			load_config(Filename);
		}
		propObj.setProperty(key, value);
	}

	String Filename;

	public void load_config(String Filename) {
		this.Filename = Filename;
		propObj = new Properties();
		File fle = new File(Filename);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fle);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			propObj.load(fis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ----- Upload files with the help of Robot class----------\\

	public void uploadFiles_RobotClass(String FilePath) {
		StringSelection ss = new StringSelection(getTestData(FilePath));
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);

		// imitate mouse events like ENTER, CTRL+C, CTRL+V
		Robot robot = null;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		robot.delay(250);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.delay(90);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}

	// ---------------------------------- -------------------------------\\
	public void jsClick(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) getdriver();
		js.executeScript("arguments[0].click()", element);

	}

	public void jsScrollDown(int cordinte) {
		JavascriptExecutor jse = (JavascriptExecutor) getdriver();
		jse.executeScript("window.scrollTo(0,\"" + cordinte + "\")");

	}

	public void jsScrollUp(int cordinte) {
		JavascriptExecutor jse = (JavascriptExecutor) getdriver();
		jse.executeScript("window.scrollTo(\"" + cordinte + "\",0");
	}

	public Date getTime(long millis) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(millis);
		return calendar.getTime();

	}

	public String SimpleDateTimeFormate() {
		SimpleDateFormat timestamp = new SimpleDateFormat("yyyy_MM_dd__hh_mm_ss");
		Date date = new Date();
		String dat = timestamp.format(date);
		String da = dat.replace("/", "-");
		String d = da.replace(":", "_");
		return d;
	}

	public void setExtentLogger(String TestCaseName) {
		extentLogger = extent.createTest(TestCaseName);
	}

	public void initHtmlReport() {
		// specify location of the report
		htmlReporter = new ExtentHtmlReporter("SnapShots/" + SimpleDateTimeFormate() + ".html");

		htmlReporter.config().setDocumentTitle("Automation Report"); // Tile of report

		htmlReporter.config().setReportName("Functional Testing"); // Name of the report
		htmlReporter.config().setTheme(Theme.DARK);

		extent = new ExtentReports();
		extent.attachReporter(htmlReporter);

		// Passing General information
		extent.setSystemInfo("Host name", "localhost");
		extent.setSystemInfo("Environemnt", "QA");
		extent.setSystemInfo("user", "Roshan");
	}

	public void flushExtentsReport() {
		extent.flush();
	}
}