package api;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_WaitI_Element_Status {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Visible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		// wait for submit button visible within 15s
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));
	}

	@Test
	public void TC_01_Invisible() {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		// can not find in DOM
		explicitWait.until(
				ExpectedConditions.invisibilityOfElementLocated(By.xpath("//li[text()='An email address required.']")));

		// can find in DOM
		explicitWait
				.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='create_account_error']")));
	}

	@Test
	public void TC_03_Presence() {

		// can find in DOM
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='create_account_error']")));

		// wait for submit button visible within 15s
		explicitWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@id='SubmitLogin']")));

	}

	@Test
	public void TC_04_Staneless() {

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
