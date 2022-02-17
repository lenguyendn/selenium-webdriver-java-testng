package testNG;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Loop {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(invocationCount = 5)
	public void TC_Register() {
		driver.get("http://live.demoguru99.com/index.php/customer/account/create/");

		driver.findElement(By.id("firstname")).sendKeys("Le");
		driver.findElement(By.id("lastname")).sendKeys("Nguyen");

		String email = generateEmail();
		System.out.println("Email address: " + email);
		String password = String.valueOf(generateEPassword());
		System.out.println("Password: " + password);

		driver.findElement(By.id("email_address")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("confirmation")).sendKeys(password);
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	public String generateEmail() {
		Random rand = new Random();
		return "automationfc" + rand.nextInt(99999) + "@gmail.com";
	}

	public int generateEPassword() {
		Random rand = new Random();
		return rand.nextInt(999999999);
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}
