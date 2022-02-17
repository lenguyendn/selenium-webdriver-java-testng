package api;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_WaitV_Exercise_ExplicitWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	String projectLocation = System.getProperty("user.dir");
	String photo1FileName = "siberianHusky.jpg";
	String photo2FileName = "Yak.jpg";
	String photo1path = projectLocation + getFileSeparator() + "UploadFile" + getFileSeparator() + photo1FileName;
	String photo2path = projectLocation + getFileSeparator() + "UploadFile" + getFileSeparator() + photo2FileName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		explicitWait = new WebDriverWait(driver, 30);
	}

	@Test
	public void TC_01_ExplicitWait() {
		driver.get(
				"https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");

		// wait for Date picker visible
		explicitWait.until(
				ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='calendarContainer']")));

		// print No selected day
		System.out.println(driver.findElement(By.xpath("//div[@class='RadAjaxPanel']/span")).getText());

		sleepInSeconds(2);
		// close cookie banner
		explicitWait.until(
				ExpectedConditions.elementToBeClickable(By.xpath("//button[@id='onetrust-accept-btn-handler']")));
		driver.findElement(By.xpath("//button[@id='onetrust-accept-btn-handler']")).click();

		// select current date
		List<WebElement> days = driver.findElements(By.xpath("//div[@class='calendarContainer']//tbody//a"));
		for (WebElement day : days) {
			if (day.getText().contains(getDayToday())) {
				explicitWait.until(ExpectedConditions.elementToBeClickable(day));
				day.click();
				break;
			}
		}

		// Wait til AJAX Loading invisible
		explicitWait.until(ExpectedConditions
				.invisibilityOfElementLocated(By.xpath("//div[not(@style='display:none,')]/div[@class='raDiv']")));

		// verify ngay da chon
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='RadAjaxPanel']/span")).getText()
				.contains(getFullDateToday()));

		System.out.println(driver.findElement(By.xpath("//div[@class='RadAjaxPanel']/span")).getText());
	}

	@Test
	public void TC_02_ExplicitWait() {
		driver.get("https://filebin.net/");

		WebElement upload = explicitWait
				.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='file']")));
		upload.sendKeys(photo1path + "\n" + photo2path);

		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='progress']")));

		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + photo1FileName + "']")));
		explicitWait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[text()='" + photo2FileName + "']")));

		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + photo1FileName + "']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + photo2FileName + "']")).isDisplayed());

	}

	public String getDayToday() {
		DateFormat dateFormat = new SimpleDateFormat("dd");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public String getFullDateToday() {
		DateFormat dateFormat = new SimpleDateFormat("EEEE, MMMM dd, yyyy");
		Date date = new Date();
		return dateFormat.format(date);
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String getFileSeparator() { // tu detect he dieu hanh de lay separator
		return File.separator;
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
