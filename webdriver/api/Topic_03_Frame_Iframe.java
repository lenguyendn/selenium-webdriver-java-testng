package api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Topic_03_Frame_Iframe {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		// mở trình duyệt
		driver = new FirefoxDriver();

		// mở app
		driver.get("https://google.com");
	}

	@Test
	public void TC_01() {
	}

	@Test
	public void TC_02() {
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
