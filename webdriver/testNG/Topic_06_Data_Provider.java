package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Topic_06_Data_Provider {
	WebDriver driver;
	By emailTextBox = By.xpath("//*[@id='email']");
	By passwordTextBox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "user_pass")
	public void TC_Login(String username, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");

		driver.findElement(emailTextBox).sendKeys(username);
		driver.findElement(passwordTextBox).sendKeys(password);
		driver.findElement(loginButton).click();

		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	@DataProvider(name = "user_pass")
	public Object[][] UserAndPasswordData() {
		return new Object[][] { { "selenium_11_01@gmail.com", "111111" }, { "selenium_11_02@gmail.com", "111111" },
				{ "selenium_11_03@gmail.com", "111111" }, };
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}
