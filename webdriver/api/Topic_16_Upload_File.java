package api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_16_Upload_File {
	WebDriver driver;
	String projectLocation = System.getProperty("user.dir");
	String osName = System.getProperty("os.name");
	String photo1FileName = "siberianHusky.jpg";
	String photo2FileName = "Yak.jpg";
	String photo1path = projectLocation + getFileSeparator() + "UploadFile" + getFileSeparator() + photo1FileName;
	String photo2path = projectLocation + getFileSeparator() + "UploadFile" + getFileSeparator() + photo2FileName;

	String UploadOneFileChromeAutoIT = projectLocation + "\\autoIT\\chromeUploadOneTime.exe";
	String UploadOneFileFirefoxAutoIT = projectLocation + "\\autoIT\\firefoxUploadOneTime.exe";
	String UploadMutipleChromeAutoIT = projectLocation + "\\autoIT\\chromeUploadMultiple.exe";
	String UploadMultipleFirefoxAutoIT = projectLocation + "\\autoIT\\firefoxUploadMultiple.exe";

	@Test
	public void TC_01_Upload_One_File_Firefox() {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectLocation + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectLocation + "//browserDrivers//geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		// truyen duong dan cua files can upload
		uploadFile.sendKeys(photo1path);
		sleepInSeconds(2);

		// Verify load file thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + photo1FileName + "']")).isDisplayed());

		// Click start upload
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSeconds(2);

		// Verify upload thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + photo1FileName + "']")).isDisplayed());

		driver.quit();
	}

	@Test
	public void TC_02_Upload_One_File_Chrome() {
		// voi Mac phai set permission cho chromedriver
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectLocation + "//browserDrivers//chromedriver.exe");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		// truyen duong dan cua files can upload
		uploadFile.sendKeys(photo1path);
		sleepInSeconds(2);

		// Verify load file thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + photo1FileName + "']")).isDisplayed());

		// Click start upload
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSeconds(2);

		// Verify upload thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + photo1FileName + "']")).isDisplayed());

		driver.quit();
	}

	@Test
	public void TC_03_Upload_Multiple_Files() {
		// firefox ban cu khong the upload nhieu file, chi chay dc file cuoi cung =>
		// dung firefox ban moi
		// chrome thi khong can selenium ban moi, nhung firefox moi can selenium ban moi
		// + thu vien TestNG (khong phai plugin)
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectLocation + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectLocation + "//browserDrivers//geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		WebElement uploadFile = driver.findElement(By.xpath("//input[@type='file']"));
		// truyen duong dan cua files can upload
		uploadFile.sendKeys(photo1path + "\n" + photo2path);
		sleepInSeconds(2);

		// Verify load file thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + photo1FileName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + photo2FileName + "']")).isDisplayed());

		// Click start upload
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSeconds(2);

		// Verify upload thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + photo1FileName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + photo2FileName + "']")).isDisplayed());

		driver.quit();
	}

	@Test
	public void TC_04_Upload_One_File_Firefox_AutoIT() throws IOException {
		// voi Mac phai set permission cho chromedriver
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectLocation + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectLocation + "//browserDrivers//geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector(".fileinput-button")).click();
		sleepInSeconds(1);

		// load file = AutoIT
		System.out.println(driver.toString());
		if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { UploadOneFileChromeAutoIT, photo1path });
		} else {
			Runtime.getRuntime().exec(new String[] { UploadOneFileFirefoxAutoIT, photo1path });
		}
		sleepInSeconds(2);

		// Verify load file thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + photo1FileName + "']")).isDisplayed());

		// Click start upload
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSeconds(2);

		// Verify upload thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + photo1FileName + "']")).isDisplayed());

		driver.quit();
	}

	@Test
	public void TC_05_Upload_Multiple_Files_Chrome_AutoIT() throws IOException {
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
		} else {
			System.setProperty("webdriver.chrome.driver", projectLocation + "//browserDrivers//chromedriver.exe");
		}
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector(".fileinput-button")).click();
		sleepInSeconds(1);

		// load file = AutoIT
		// file mutiple Firefox cua thay khong nhan duoc Enter(?)
		System.out.println(driver.toString());
		if (driver.toString().contains("chrome")) {
			Runtime.getRuntime().exec(new String[] { UploadMutipleChromeAutoIT, photo1path, photo2path });
		} else {
			Runtime.getRuntime().exec(new String[] { UploadMultipleFirefoxAutoIT, photo1path, photo2path });
		}
		sleepInSeconds(2);

		// Verify load file thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + photo1FileName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + photo2FileName + "']")).isDisplayed());

		// Click start upload
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSeconds(2);

		// Verify upload thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + photo1FileName + "']")).isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + photo2FileName + "']")).isDisplayed());

		driver.quit();
	}

	@Test
	public void TC_06_Upload_One_File_Robot() throws AWTException {
		// voi Mac phai set permission cho chromedriver
		if (osName.contains("Windows")) {
			System.setProperty("webdriver.gecko.driver", projectLocation + "\\browserDrivers\\geckodriver.exe");
		} else {
			System.setProperty("webdriver.gecko.driver", projectLocation + "//browserDrivers//geckodriver.exe");
		}
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("https://blueimp.github.io/jQuery-File-Upload/");

		driver.findElement(By.cssSelector(".fileinput-button")).click();
		sleepInSeconds(1);

		// load file = Robot
		// Specify the file location with extension
		StringSelection select = new StringSelection(photo1path);

		// Copy to clipboard
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select, null);

		Robot robot = new Robot();
		sleepInSeconds(1);

		// Nhan phim Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);

		// Nhan xuong Ctrl - V
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);

		// Nha Ctrl - V
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_V);

		// Nhan Enter
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
		sleepInSeconds(2);

		// Verify load file thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name' and text()='" + photo1FileName + "']")).isDisplayed());

		// Click start upload
		driver.findElement(By.xpath("//button[@type='submit']")).click();
		sleepInSeconds(2);

		// Verify upload thanh cong
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='name']//a[@title='" + photo1FileName + "']")).isDisplayed());

		driver.quit();
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
}
