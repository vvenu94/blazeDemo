package org.stepdef;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import org.libraries.LibGlobal;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class BookingModule extends LibGlobal {
	SoftAssert a;

	@BeforeClass
	private void pageLaunch() {
		browserLaunch();
		urlLaunch("https://blazedemo.com/index.php");

	}

	@Parameters({ "depLocation", "arrLocation" })
	@Test
	private void homePage(String depLocation, String arrLocation) {

		String text = driver.findElement(By.xpath("//div[@class='container']/h1")).getText();
		a = new SoftAssert();
		a.assertTrue(text.equals("Welcome to the Simple Travel Agency!"), "HomePage text is invalid");
		WebElement depCity = driver.findElement(By.xpath("//select[@name='fromPort']"));
		selectByVisibleText(depCity, depLocation);
		WebElement arrCity = driver.findElement(By.xpath("//select[@name='toPort']"));
		selectByVisibleText(arrCity, arrLocation);
		WebElement btnFindFligths = driver.findElement(By.xpath("//input[@class='btn btn-primary']"));
		btnClick(btnFindFligths);

	}

	@Test(dependsOnMethods = "homePage")
	private void flightSelectionPage() {
		double d = 0;
		List<Double> li = new LinkedList<Double>();
		List<WebElement> tRows = driver.findElements(By.tagName("tr"));
		for (WebElement tRow : tRows) {
			List<WebElement> tData = tRow.findElements(By.tagName("td"));
			for (int i = 5; i < tData.size(); i++) {

				String s = tData.get(i).getText().substring(1);
				d = Double.parseDouble(s);

				li.add(d);

			}

		}

		Double min = Collections.min(li);
		int i = li.indexOf(min);
		List<WebElement> least = driver.findElements(By.xpath("//input[@class='btn btn-small']"));
		least.get(i).click();

	}

	@Parameters({ "inputName", "address", "city", "state", "zipCode", "cardType", "creditCardNumber", "creditCardMonth",
			"creditCardYear", "nameOnCard" })

	@Test(dependsOnMethods = "flightSelectionPage")
	private void paymentPage(String inputName, String address, String city, String state, String zipCode,
			String cardType, String creditCardNumber, String creditCardMonth, String creditCardYear,
			String nameOnCard) {
		String totAmount = driver.findElement(By.tagName("em")).getText();
	double total = Double.parseDouble(totAmount);
//		boolean b;
//		if (total % 1 != 0) {
//			b = true;
//
//		} else {
//			b = false;
//		}
		String b= String.format("%.2f",total );
		a.assertEquals(b, totAmount);
		a.assertAll();

		driver.findElement(By.id("inputName")).sendKeys(inputName);
		driver.findElement(By.id("address")).sendKeys(address);
		driver.findElement(By.id("city")).sendKeys(city);
		driver.findElement(By.id("state")).sendKeys(state);
		driver.findElement(By.id("zipCode")).sendKeys(zipCode);
		WebElement ele = driver.findElement(By.id("cardType"));
		selectByVisibleText(ele, cardType);
		driver.findElement(By.id("creditCardNumber")).sendKeys(creditCardNumber);

		driver.findElement(By.id("creditCardMonth")).sendKeys(creditCardMonth);

		driver.findElement(By.id("creditCardYear")).sendKeys(creditCardYear);
		driver.findElement(By.id("nameOnCard")).sendKeys(nameOnCard);

		driver.findElement(By.xpath("//input[@type='submit']")).click();
		String orderId = driver.findElement(By.xpath("//tr/td[2]")).getText();
		System.out.println("Booking order number is : " +orderId);

	}

	@AfterClass
	private void closeBrowser() {
		quit();
	}

}
