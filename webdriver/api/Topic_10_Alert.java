package api;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_10_Alert {
	WebDriver driver;
	Alert alert;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		explicitWait = new WebDriverWait(driver,20);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Alert']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		// wait above included alert = driver.switchTo().alert();
		sleepInSeconds(1);
		alert.accept();
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@id='result' and text()='You clicked an alert successfully ']"))
						.isDisplayed());
	}

	@Test
	public void TC_02_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Confirm']")).click();
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		sleepInSeconds(1);
		alert.dismiss();
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@id='result' and text()='You clicked: Cancel']")).isDisplayed());
	}

	@Test
	public void TC_03_Prompt_Alert() {
		String alertText = "testing";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.findElement(By.xpath("//button[text()='Click for JS Prompt']")).click();
		sleepInSeconds(1);
		alert = explicitWait.until(ExpectedConditions.alertIsPresent());
		//Assert.assertTrue(alert.getText().contains("I am a JS prompt"));
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(alertText);
		sleepInSeconds(2);
		alert.accept();
		sleepInSeconds(2);
		Assert.assertEquals(
				driver.findElement(By.xpath("//p[@id='result']")).getText(), "You entered: " + alertText);				
	}

	@Test
	public void TC_04_Authentication_Alert() {
		//add directly into url
		//username:password@
		//password co ki tu dac biet @: dung HTML url Encode, chuyen password qua chuoi moi, tay the password cu
		String url = "http://the-internet.herokuapp.com/basic_auth";
		url = "http://admin:admin@the-internet.herokuapp.com/basic_auth";
		driver.get(url);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']//p")).getText().trim(), "Congratulations! You must have the proper credentials.");
	}
	
	
	@Test
	public void TC_05_Authentication_Alert() {
		//add by code
		//username:password@
		String userName = "admin";
		String password = "admin";
		driver.get("http://the-internet.herokuapp.com");
		sleepInSeconds(1);
		String url = driver.findElement(By.xpath("//a[text()='Basic Auth']")).getAttribute("href");
		driver.get(getUrlByUsernameAndPassword(url, userName, password));
		driver.get(url);
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']//p")).getText().trim(), "Congratulations! You must have the proper credentials.");
	}
	
	@Test
	public void TC_06_Authentication_autoIT() throws IOException {
		//run autoIT script truoc roi get driver
		String userName = "admin";
		String password = "admin";
		String autoITpath = System.getProperty("user.dir") + "\\autoIT\\authen_alert_chrome.exe";
		// run script da viet san autoIT
		Runtime.getRuntime().exec(new String[] {autoITpath, userName, password});
		
		driver.get("http://the-internet.herokuapp.com/basic_auth");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='example']//p")).getText().trim(), "Congratulations! You must have the proper credentials.");
	}
	
	
	
	public String getUrlByUsernameAndPassword(String url, String username, String password) {
		String[] splitUrls = url.split("//");
		url = splitUrls[0] + "//" + username + ":" + password + "@" + splitUrls[1];
		return url;
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
