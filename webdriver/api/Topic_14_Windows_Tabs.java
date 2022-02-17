package api;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_14_Windows_Tabs {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Two_Windows_Or_Tabs() {
		driver.get("https://kyna.vn/");
		sleepInSecond(2);
		// get window handle
		String kynaID = driver.getWindowHandle();

		// close popup if any
		if (driver.findElements(By.xpath("//div[@class='fancybox-outer']")).size() >= 1) {
			// close popup
			driver.findElement(By.xpath("//a[@title='Close']")).click();
			sleepInSecond(2);
			Assert.assertEquals(driver.findElements(By.xpath("//div[@class='fancybox-outer']")).size(), 0);
		}

		// Click to FB link at footer
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();
		sleepInSecond(2);

		// Switch to FB page (tab)
		switchToOtherWindowInTwoWindows(kynaID);

		// Verify we are at FB page
		Assert.assertTrue(driver.getCurrentUrl().contains("facebook"));
		String FBPageID = driver.getWindowHandle();

		// Switch to kyna (parent) again
		switchToOtherWindowInTwoWindows(FBPageID);

		// Verify we are at kyna page
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		closeAllWindowsbutParent(kynaID);
	}

	@Test
	public void TC_02_Greater_Than_Two_Windows_Or_Tabs() {
		driver.get("https://kyna.vn/");

		// close popup if any
		if (driver.findElements(By.xpath("//div[@class='fancybox-outer']")).size() >= 1) {
			// close popup
			driver.findElement(By.xpath("//a[@title='Close']")).click();
			sleepInSecond(2);
			Assert.assertEquals(driver.findElements(By.xpath("//div[@class='fancybox-outer']")).size(), 0);
		}

		// Click to FB at footer
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='facebook']")).click();

		// Switch to FB
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");

		// Verify FB
		Assert.assertTrue(driver.getCurrentUrl().contains("facebook"));

		// Switch to Kyna(*)
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");

		// Verify Kyna
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");

		// Click to YouTube
		driver.findElement(By.xpath("//div[@id='k-footer']//img[@alt='youtube']")).click();

		// Switch to Youtube
		switchToWindowByTitle("Kyna.vn - YouTube");

		// Verify YouTube
		Assert.assertEquals(driver.getCurrentUrl(), "https://www.youtube.com/user/kynavn");

		// Switch to Kyna(*)
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");

		// Click to Bo Cong Thuong
		driver.findElement(By.xpath("//div[@id='k-footer-copyright']//a[contains(@href,'CustomWebsiteDisplay')]"))
				.click();

		// Switch to Bo Cong Thuong
		switchToWindowByTitle("Thông tin website thương mại điện tử - Online.Gov.VN");

		// Verify Bo Cong Thuong
		Assert.assertTrue(driver.getCurrentUrl().contains("online.gov.vn"));

		// Switch to Kyna(*)
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Assert.assertEquals(driver.getCurrentUrl(), "https://kyna.vn/");
		String KynaID = driver.getWindowHandle();
		System.out.println("KynaID: " + KynaID);

		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			System.out.println(id);
		}

		// Close all tab but Kyna
		// Will back to Kyna at the end
		closeAllWindowsbutParent(KynaID);
		System.out.println(driver.getTitle());

	}

	public void sleepInSecond(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void switchToOtherWindowInTwoWindows(String currentWindowID) {
		// Lấy hết tất cả các ID của window/ tab đang có
		Set<String> allIDs = driver.getWindowHandles();

		// Dùng vòng lặp duyệt qua từng giá trị
		for (String id : allIDs) {
			// Nếu như nó không phải là giá trị của window hiện tại thì switch vào
			if (!id.equals(currentWindowID)) {
				driver.switchTo().window(id);
				sleepInSecond(2);
				break;
			}
		}
	}

	public void switchToWindowByTitle(String titleWindow) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			driver.switchTo().window(id);
			if (driver.getTitle().equals(titleWindow)) {
				sleepInSecond(2);
				break;
			}

		}
	}

	public void closeAllWindowsbutParent(String windowID) {
		Set<String> allIDs = driver.getWindowHandles();
		for (String id : allIDs) {
			if (!id.equals(windowID)) {
				driver.switchTo().window(id);
				driver.close();
				sleepInSecond(2);
			}
		}
		driver.switchTo().window(windowID);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
