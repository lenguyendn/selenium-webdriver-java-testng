package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Method_Excercise_Part_I {
	WebDriver driver;

	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().window().maximize();
	}

	@Test(enabled = false)
	public void TC_01_isDisplayed() {
		boolean emailTextBoxStatus = driver.findElement(By.xpath("//input[@id='email']")).isDisplayed();
		boolean ageUnder18RadioStatus = driver.findElement(By.xpath("//input[@id='under_18']")).isDisplayed();
		boolean eduTextareaStatus = driver.findElement(By.xpath("//textarea[@id='edu']")).isDisplayed();
		boolean user5TextStatus = driver.findElement(By.xpath("//h5[text()='Name: User5']")).isDisplayed();

		if (emailTextBoxStatus == true) {
			driver.findElement(By.xpath("//input[@id='email']")).sendKeys("Automation Testing");
			System.out.println("Email is displayed");
		} else {
			System.out.println("Email is not displayed");
		}

		if (ageUnder18RadioStatus == true) {
			driver.findElement(By.xpath("//label[@for='under_18']")).click();
			System.out.println("Age (Under 18) is displayed");
		} else {
			System.out.println("Age (Under 18) is not displayed");
		}

		if (eduTextareaStatus == true) {
			driver.findElement(By.xpath("//textarea[@id='edu']")).sendKeys("Automation Testing");
			System.out.println("Education is displayed");
		} else {
			System.out.println("Education is not displayed");
		}

		if (user5TextStatus == true) {
			System.out.println("Name: User5 is displayed");
		} else {
			System.out.println("Name: User5 is not displayed");
		}

	}

	@Test(enabled = false)
	public void TC_02_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='mail']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//textarea[@id='edu']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//select[@id='job1']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//select[@id='job2']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='development']")).isEnabled());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled());

		Assert.assertFalse(driver
				.findElement(By.xpath("//input[@id='password' and @placeholder='Textbox is disabled']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='radio-disabled']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//textarea[@id='bio']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//select[@id='job3']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='check-disbaled']")).isEnabled());
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled());

		boolean slider1status = driver.findElement(By.xpath("//input[@id='slider-1']")).isEnabled();
		boolean slider2status = driver.findElement(By.xpath("//input[@id='slider-2']")).isEnabled();
		if (slider1status == true) {
			System.out.println("Slider 1 is enabled");
		} else {
			System.out.println("Slider 1 is disabled");
		}

		if (slider2status == true) {
			System.out.println("Slider 2 is enabled");
		} else {
			System.out.println("Slider 2 is disabled");
		}
	}

	@Test(enabled = false)
	public void TC_03_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");

		driver.findElement(By.xpath("//input[@id='under_18']")).click();
		driver.findElement(By.xpath("//input[@id='java']")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='under_18']")).isSelected());
		Assert.assertTrue(driver.findElement(By.xpath("//input[@id='java']")).isSelected());

		driver.findElement(By.xpath("//input[@id='java']")).click();
		Assert.assertFalse(driver.findElement(By.xpath("//input[@id='java']")).isSelected());
	}

	@Test(enabled = true)
	public void TC_04_RegisterAtMailChimp() throws InterruptedException {
		driver.get("https://login.mailchimp.com/signup/");
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("abc@abc.com");
		driver.findElement(By.xpath("//input[@id='new_username']")).sendKeys("TestAuto");

		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("1");
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='number-char completed' and text()='One number']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());

		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("auto");
		Thread.sleep(1000);
		Assert.assertTrue(driver
				.findElement(By.xpath("//li[@class='lowercase-char completed' and text()='One lowercase character']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());

		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("Auto");
		Thread.sleep(1000);
		Assert.assertTrue(driver
				.findElement(By.xpath("//li[@class='uppercase-char completed' and text()='One uppercase character']"))
				.isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());

		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("#");
		Thread.sleep(1000);
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='special-char completed' and text()='One special character']"))
						.isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());

		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("abcd1234");
		Thread.sleep(1000);
		Assert.assertTrue(
				driver.findElement(By.xpath("//li[@class='8-char completed' and text()='8 characters minimum']"))
						.isDisplayed());
		Assert.assertFalse(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());

		driver.findElement(By.xpath("//input[@id='new_password']")).clear();
		driver.findElement(By.xpath("//input[@id='new_password']")).sendKeys("Automation1$");
		Thread.sleep(1000);
		Assert.assertTrue(driver.findElement(By.xpath("//button[@id='create-account']")).isEnabled());

	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

}
