package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_HTML2_Alada {
	WebDriver driver;
	String registerButton = ".btn_pink_sm.fs16";
	String fullNameField = "#txtFirstname";
	String emailField = "#txtEmail";
	String confirmEmailField = "#txtCEmail";
	String passwordField = "#txtPassword";
	String confirmPasswordField = "#txtCPassword";
	String phoneNumberField = "#txtPhone";

	String fullName = "John Wick";
	String email = "johnwick@gmail.net";
	String password = "123456";
	String phoneNumber = "0987654321";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
	}

	@Test
	public void TC_01_RegisterWithEmptyData() {
		ThreadSleep(3);
		driver.findElement(By.xpath("//button[@class='btn_pink_sm fs16']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtFirstname-error']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtEmail-error']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtCEmail-error']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtPassword-error']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtCPassword-error']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//label[@id='txtPhone-error']")).isDisplayed());

	}

	@Test
	public void TC_02_RegisterWithInvalidEmail() {
		driver.navigate().refresh();
		ThreadSleep(2);
		driver.findElement(By.cssSelector(fullNameField)).sendKeys(fullName);
		driver.findElement(By.cssSelector(emailField)).sendKeys("123@123.234@");
		driver.findElement(By.cssSelector(confirmEmailField)).sendKeys("123@123.234@");
		driver.findElement(By.cssSelector(passwordField)).sendKeys(password);
		driver.findElement(By.cssSelector(confirmPasswordField)).sendKeys(password);
		driver.findElement(By.cssSelector(phoneNumberField)).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector(registerButton)).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//label[@id='txtEmail-error' and text()= 'Vui lòng nhập email hợp lệ']"))
						.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[@id='txtCEmail-error' and text()= 'Email nhập lại không đúng']"))
						.isDisplayed());

	}

	@Test
	public void TC_03_RegisterWithIncorrectConfirmEmail() {
		driver.navigate().refresh();
		ThreadSleep(2);
		driver.findElement(By.cssSelector(fullNameField)).sendKeys(fullName);
		driver.findElement(By.cssSelector(emailField)).sendKeys(email);
		driver.findElement(By.cssSelector(confirmEmailField)).sendKeys("johnwick@gmail.com");
		driver.findElement(By.cssSelector(passwordField)).sendKeys(password);
		driver.findElement(By.cssSelector(confirmPasswordField)).sendKeys(password);
		driver.findElement(By.cssSelector(phoneNumberField)).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector(registerButton)).click();

		Assert.assertTrue(
				driver.findElement(By.xpath("//label[@id='txtCEmail-error' and text()= 'Email nhập lại không đúng']"))
						.isDisplayed());

	}

	@Test(enabled = true)
	public void TC_04_RegisterWithPasswordLessThan6Characters() {
		driver.navigate().refresh();
		ThreadSleep(2);
		driver.findElement(By.cssSelector(fullNameField)).sendKeys(fullName);
		driver.findElement(By.cssSelector(emailField)).sendKeys(email);
		driver.findElement(By.cssSelector(confirmEmailField)).sendKeys(email);
		driver.findElement(By.cssSelector(passwordField)).sendKeys("12345");
		driver.findElement(By.cssSelector(confirmPasswordField)).sendKeys("12345");
		driver.findElement(By.cssSelector(phoneNumberField)).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector(registerButton)).click();

		Assert.assertTrue(driver
				.findElement(
						By.xpath("//label[@id='txtPassword-error' and text()= 'Mật khẩu phải có ít nhất 6 ký tự']"))
				.isDisplayed());
		Assert.assertTrue(driver
				.findElement(
						By.xpath("//label[@id='txtCPassword-error' and text()= 'Mật khẩu phải có ít nhất 6 ký tự']"))
				.isDisplayed());
	}

	@Test(enabled = true)
	public void TC_05_RegisterWithIncorrectConfirmPassword() {
		driver.navigate().refresh();
		ThreadSleep(2);
		driver.findElement(By.cssSelector(fullNameField)).sendKeys(fullName);
		driver.findElement(By.cssSelector(emailField)).sendKeys(email);
		driver.findElement(By.cssSelector(confirmEmailField)).sendKeys(email);
		driver.findElement(By.cssSelector(passwordField)).sendKeys(password);
		driver.findElement(By.cssSelector(confirmPasswordField)).sendKeys("654321");
		driver.findElement(By.cssSelector(phoneNumberField)).sendKeys(phoneNumber);
		driver.findElement(By.cssSelector(registerButton)).click();

		Assert.assertTrue(driver
				.findElement(By.xpath("//label[@id='txtCPassword-error' and text()= 'Mật khẩu bạn nhập không khớp']"))
				.isDisplayed());
	}

	@Test(enabled = true)
	public void TC_06_RegisterWithInvalidPhoneNumber() {
		driver.navigate().refresh();
		ThreadSleep(2);
		driver.findElement(By.cssSelector(fullNameField)).sendKeys(fullName);
		driver.findElement(By.cssSelector(emailField)).sendKeys(email);
		driver.findElement(By.cssSelector(confirmEmailField)).sendKeys(email);
		driver.findElement(By.cssSelector(passwordField)).sendKeys(password);
		driver.findElement(By.cssSelector(confirmPasswordField)).sendKeys(password);
		driver.findElement(By.cssSelector(phoneNumberField)).sendKeys(email);
		driver.findElement(By.cssSelector(registerButton)).click();
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[@id='txtPhone-error' and text()= 'Vui lòng nhập con số']"))
						.isDisplayed());

		driver.findElement(By.cssSelector(phoneNumberField)).sendKeys(Keys.chord(Keys.CONTROL, "a"), "098765432");
		driver.findElement(By.cssSelector(registerButton)).click();
		Assert.assertTrue(driver
				.findElement(By.xpath("//label[@id='txtPhone-error' and text()= 'Số điện thoại phải từ 10-11 số. ']"))
				.isDisplayed());

		driver.findElement(By.cssSelector(phoneNumberField)).clear();
		driver.findElement(By.cssSelector(phoneNumberField)).sendKeys("123456");
		driver.findElement(By.cssSelector(registerButton)).click();
		Assert.assertTrue(driver.findElement(By.xpath(
				"//label[@id='txtPhone-error' and text()= 'Số điện thoại bắt đầu bằng: 09 - 03 - 012 - 016 - 018 - 019']"))
				.isDisplayed());

	}

	public void ThreadSleep(int sec) {
		try {
			Thread.sleep(sec * 1000);
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
