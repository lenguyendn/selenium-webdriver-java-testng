package api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

public class Topic_03_Run_On_Browsers {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");

	@Test
	public void TC_01_Run_On_Firefox() {
		// Firefox 47 + Selenium 2.xx + kho dùng geckodriver
		driver = new FirefoxDriver();

		// Firefox 48 + Selenium 3.xx + Geckodriver
		// System.setProperty(webdriver.gecko.driver, [geckodriver path here])
		driver.get("https://google.com");
		driver.quit();
	}

	@Test
	public void TC_02_Run_On_Chrom() {
		// 01
		// System.setProperty("webdriver.chrome.driver", "D:\\Lop Auto test\\02 -
		// Selenium API\\browserDrivers\\chromedriver.exe");

		// 02 . = đại diện cho project location
		// System.setProperty("webdriver.chrome.driver",
		// ".\\browserDrivers\\chromedriver.exe");

		// 03
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");

		driver = new ChromeDriver();
		driver.get("https://google.com");
		driver.quit();
	}

}
