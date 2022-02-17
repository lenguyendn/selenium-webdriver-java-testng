package testNG;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_04_AlwayRun {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// something in before class fail => TCs khong chay, afterClass khong chay =>
		// khong dong trinh duyet
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		Assert.assertTrue(false);
	}

	@Test
	public void TC_01() {
		System.out.println("Run TC 01");
	}

	@Test
	public void TC_02() {
		System.out.println("Run TC 02");
	}

	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}
}
