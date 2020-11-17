package org.libraries;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class LibGlobal {
	public static WebDriver driver;
	static Actions action;
	static Robot robot;
	static File src, des;
	static Alert alert;
	static JavascriptExecutor js;
	static Select select;
	static int i, j;
	static List<WebElement> tRows;

	

	// to launch the browser
	public WebDriver browserLaunch() {
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\venugopal\\eclipse-workspace\\BlazeDemo\\driver\\chromedriver.exe");
		return driver = new ChromeDriver();
	}

	// to launch the URL
	public void urlLaunch(String url) {
		driver.get(url);
		maximize();
		implicitWait();

	}
//implict wait
	public void implicitWait() {
driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	//to maximize window
	public void maximize() {
		driver.manage().window().maximize();
	}

	// to enter the text input
	public void type(WebElement element, String input) {
		element.sendKeys(input);

	}

	// to click the button
	public void btnClick(WebElement element) {
		element.click();

	}
	//to quit the browser
	public void quit() {
		driver.quit();

	}

	// to get title
	public String title() {
	return driver.getTitle();
		
	}

	// to get currenturl
	public String getUrl() {
		return driver.getCurrentUrl();
		 
	}

	// to get text
	public String getText(WebElement element) {
		return element.getText();
		

	}

	// to get Attribute value
	public String getAttribute(WebElement element) {
		return element.getAttribute("value");
		

	}

	// to perform movetoElement actions
	public void moveToElement(WebElement element) {
		action = new Actions(driver);
		action.moveToElement(element).perform();
	}

	// to perform DragAndDrop
	public void dragDrop(WebElement srcElement, WebElement desElement) {
		action = new Actions(driver);
		action.dragAndDrop(srcElement, desElement).perform();
	}

	// to perform contextClick
	public void contextClick(WebElement element) {
		action = new Actions(driver);
		action.contextClick(element).perform();
	}

	// to perform DoubleClick
	public void doubleClick(WebElement a) {
		action = new Actions(driver);
		action.doubleClick(a).perform();
	}

	// to perform KeyPress in robot
	public void keyPress(int c) throws Exception {
		robot = new Robot();
		robot.keyPress(c);

	}

	// to perform KeyRelease in robot
	public void keyRelease(int c) throws Exception {
		robot = new Robot();
		robot.keyRelease(c);

	}

	// to take Screenshot
	public void screenShot() throws IOException {
		TakesScreenshot ts = (TakesScreenshot) driver;
		src = ts.getScreenshotAs(OutputType.FILE);
		des = new File("C:\\Users\\venugopal\\eclipse-workspace\\JetAirways\\Excel\\Image1.png");

		FileUtils.copyFile(src, des);

	}

	// to accept the alert
	public void acceptAlert() {
		alert = driver.switchTo().alert();
		alert.accept();

	}

	// to dismiss the alert
	public void dismissAlert() {
		alert = driver.switchTo().alert();
		alert.dismiss();

	}

	// to provide input to text
	public void promptAlert(String a) {
		alert = driver.switchTo().alert();
		alert.sendKeys(a);
		;

	}

	// to get input from javaScript
	public void javaScriptInput(String a, WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeAsyncScript("arguments[0].setAttribute('value','"+a+"')",element);

	}

	// to click using javaScript
	public void javaScriptClick(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", element);

	}

	// to ScrollDown using JavaScript
	public void jsScrollDown(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true)", element);

	}

	// to ScrollUp using JavaScript
	public void jsScrollUp(WebElement element) {
		js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(false)", element);

	}

	// to get input text fromJavaScript
	public String getInputText(WebElement element) {
		js = (JavascriptExecutor) driver;
		String txtInput = js.executeScript("return arguments[0].getAttribute('value')", element).toString();
		return txtInput;

	}

	// to Switch Windows
	public void switchWindow() {
		String pId = driver.getWindowHandle();
		Set<String> cId = driver.getWindowHandles();
		for (String a : cId) {
			if (pId != a) {
				driver.switchTo().window(a);
			}

		}

	}

	// Switch window by index
	public void switchWindowByIndex(int b) {

		Set<String> cId = driver.getWindowHandles();
		List<String> a = new ArrayList<String>();
		a.addAll(cId);
		driver.switchTo().window(a.get(b));

	}

	// iframe by WebElement
	public void iframeByElement(WebElement element) {
		driver.switchTo().frame(element);

	}

	// iframe by id and Name
	public void iframeByIdAndName(String value) {
		driver.switchTo().frame(value);

	}

	// iframe by Index
	public void iframeByIndex(int index) {
		driver.switchTo().frame(index);

	}

	// iframe to default mainframe
	public void iframeDefault() {
		driver.switchTo().defaultContent();
	}

	// iframe to parentframe
	public void iframeparent() {
		driver.switchTo().parentFrame();
	}

	// dropdown select by index
	public void selectByIndex(WebElement element, int index) {
		select = new Select(element);
		select.selectByIndex(index);

	}

	// dropdown select by value
	public void selectByValue(WebElement element, String value) {
		select = new Select(element);
		select.selectByValue(value);

	}

	// dropdown select by Visibletext
	public void selectByVisibleText(WebElement element, String visibleText) {
		select = new Select(element);
		select.selectByVisibleText(visibleText);

	}

	// to check the text is multiple
	public boolean multiple(WebElement element) {
		select = new Select(element);
		boolean multiple = select.isMultiple();

		return multiple;

	}

	// dropdown deselect by index
	public void deselectByIndex(WebElement element, int index) {
		select = new Select(element);
		select.deselectByIndex(index);

	}

	// dropdown deselect by value
	public void deselectByValue(WebElement element, String value) {
		select = new Select(element);
		select.deselectByValue(value);
		;

	}

	// dropdown deselect by Visibletext
	public void deselectByVisibleText(WebElement element, String visibleText) {
		select = new Select(element);
		select.deselectByVisibleText(visibleText);

	}

	// to select all selected text
	public void deselectAll(WebElement element) {
		select = new Select(element);
		select.deselectAll();

	}

	// to get all options
	public List<WebElement> getAllOptions(WebElement element) {
		select = new Select(element);
		List<WebElement> options = select.getOptions();
		return options;

	}

	// to get all selectedoptions
	public List<WebElement> getAllSelectedOptions(WebElement element) {
		select = new Select(element);
		List<WebElement> options = select.getAllSelectedOptions();

		return options;

	}

	// to get first selected option
	public WebElement getFirstSelectedOption(WebElement element) {
		select = new Select(element);
		WebElement firstSelectedOption = select.getFirstSelectedOption();
		return firstSelectedOption;

	}


}
