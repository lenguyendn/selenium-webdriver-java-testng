package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_WaitIV_ExplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Enough() {
		driver.get("https://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		explicitWait = new WebDriverWait(driver, 2);
		//Visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='11']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}

	@Test
	public void TC_02_Less() {
		driver.get("https://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		explicitWait = new WebDriverWait(driver, 1);
		//Visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='11']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");
	}

	@Test
	public void TC_03_Greater() {
		driver.get("https://juliemr.github.io/protractor-demo/");

		driver.findElement(By.xpath("//input[@ng-model='first']")).sendKeys("5");
		driver.findElement(By.xpath("//input[@ng-model='second']")).sendKeys("6");
		driver.findElement(By.id("gobutton")).click();
		
		explicitWait = new WebDriverWait(driver, 6);
		//Visible
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[text()='11']")));
		
		Assert.assertEquals(driver.findElement(By.xpath("//h2[@class='ng-binding']")).getText(), "11");;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
