package api;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_WaitVI_Mixing {
	WebDriver driver;
	WebDriverWait explicitWait;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Element_Found() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.MILLISECONDS);
		explicitWait = new WebDriverWait(driver, 10);

		System.out.println("Start - " + getDateTimeNow());
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='FirstName']")));
		System.out.println("End - " + getDateTimeNow());

		System.out.println("Start - " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Automation");
		System.out.println("End - " + getDateTimeNow());
	}

	@Test
	public void TC_02_Element_Not_Found_Mix_Equal() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 6);

		System.out.println("Start - " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Books']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End - " + getDateTimeNow());
	}

	@Test
	public void TC_03_Element_Not_Found_Mix_Implicit_More() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.manage().timeouts().implicitlyWait(9, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 6);

		System.out.println("Start - " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Books']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End - " + getDateTimeNow());
	}

	@Test
	public void TC_04_Element_Not_Found_Mix_Eplicit_More() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		driver.manage().timeouts().implicitlyWait(6, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 9);

		System.out.println("Start - " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Books']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End - " + getDateTimeNow());
	}

	@Test
	public void TC_05_Element_Not_Found_Explicit_By() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		explicitWait = new WebDriverWait(driver, 6);

		System.out.println("Start - " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[text()='Books']")));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End - " + getDateTimeNow());
	}

	@Test
	public void TC_06_Element_Not_Found_Explicit_Element() {
		driver.get("https://demo.nopcommerce.com/register?returnUrl=%2F");
		explicitWait = new WebDriverWait(driver, 6);

		System.out.println("Start - " + getDateTimeNow());
		try {
			explicitWait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//h1[text()='Books']"))));
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("End - " + getDateTimeNow());
	}

	public String getDateTimeNow() {
		Date date = new Date();
		return date.toString();
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
