package api;

import java.io.File;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_16_Upload_File_Excercise {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	String photo1FileName = "siberianHusky.jpg";
	String photo2FileName = "Yak.jpg";
	String photo1path = projectLocation + getFileSeparator() + "UploadFile" + getFileSeparator() + photo1FileName;
	String photo2path = projectLocation + getFileSeparator() + "UploadFile" + getFileSeparator() + photo2FileName;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Upload() {
		driver.get("https://gofile.io/uploadFiles");
		String beforeUploadPage = driver.getWindowHandle();

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		uploadFile.sendKeys(photo1path + "\n" + photo2path);
		sleepInSeconds(10);

		// Verify Upload
		Assert.assertTrue(driver.findElement(By.xpath("//h5[text()='Your files have been successfully uploaded']"))
				.isDisplayed());

		// Click download link
		driver.findElement(By.cssSelector("#rowUploadSuccess-downloadPage")).click();

		// Switch to new tab
		switchToTheOtherWindowInTwoWindows(beforeUploadPage);
		sleepInSeconds(2);

		// Verify file download exist
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//a[contains(@href, '" + photo1FileName + "')]//span[text()='" + photo1FileName + "']"))
				.isDisplayed());
		Assert.assertTrue(driver
				.findElement(By
						.xpath("//a[contains(@href, '" + photo2FileName + "')]//span[text()='" + photo2FileName + "']"))
				.isDisplayed());
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

	public void switchToTheOtherWindowInTwoWindows(String windowID) {
		Set<String> allID = driver.getWindowHandles();
		for (String id : allID) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				sleepInSeconds(2);
				break;
			}
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
