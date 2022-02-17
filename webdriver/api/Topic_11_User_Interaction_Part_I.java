package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_Part_I {
	WebDriver driver;
	Actions action;

	@BeforeClass
	public void beforeclass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

	}

	@Test
	public void TC_01_Hover() {
		driver.get("https://automationfc.github.io/jquery-tooltip/");
		action.moveToElement(driver.findElement(By.xpath("//input[@id='age']"))).perform();
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='ui-tooltip-content']")).getText(),
				"We ask for your age only for statistical purposes.");

	}

	@Test
	public void TC_02_HoverII() {
		driver.get("https://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//a[text()='Kids']"))).perform();
		sleepInSeconds(1);
		driver.findElement(By.xpath("//a[text()='Home & Bath']")).click();
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='breadcrumbs-item']/span")).getText(),
				"Kids Home Bath");
	}

	@Test
	public void TC_03_Click_And_hold() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		action.clickAndHold(listNumber.get(0)).moveToElement(listNumber.get(3)).release().perform();
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector(".ui-selected"));
		sleepInSeconds(2);
		Assert.assertEquals(listSelectedNumber.size(), 4);
	}

	@Test
	public void TC_04_Click_And_Select_Element() {
		driver.get("https://automationfc.github.io/jquery-selectable/");
		List<WebElement> listNumber = driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee']"));
		// click on 4, 11, 1, 9
		action.keyDown(Keys.CONTROL).click(listNumber.get(3)).click(listNumber.get(10)).click(listNumber.get(0))
				.click(listNumber.get(8)).perform();
		action.keyUp(Keys.CONTROL).perform();
		sleepInSeconds(2);
		List<WebElement> listSelectedNumber = driver.findElements(By.cssSelector(".ui-selected"));
		Assert.assertEquals(listSelectedNumber.size(), 4);
	}

	@Test
	public void TC_05_Double_Click() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");

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
	public void AfterClass() {
		driver.quit();
	}

}
