package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_12_Popup {
	WebDriver driver;
	WebDriverWait explicitWait;
	Actions action;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		explicitWait = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	//@Test
	public void TC_01_Popup_In_DOM() {
		driver.get("https://bni.vn/");
		sleepInSeconds(60);

		// Verify popup is display
		Assert.assertTrue(driver.findElement(By.id("sgpb-popup-dialog-main-div-wrapper")).isDisplayed());
		sleepInSeconds(2);
		
		//close popup
		driver.findElement(By.xpath("//img[@class='sgpb-popup-close-button-1']")).click();
		sleepInSeconds(2);
		
		//verify popup is not display
		Assert.assertFalse(driver.findElement(By.id("sgpb-popup-dialog-main-div-wrapper")).isDisplayed());
	}
	
	//@Test
	public void TC_02_Popup_In_DOM_Condition() {
		driver.get("https://blog.testproject.io/");
		sleepInSeconds(10);
		//có hoặc không xuất hiện đều có trong DOM
		if (driver.findElement(By.xpath("//div[@class='mailch-wrap']")).isDisplayed()) {
			//close popup
			driver.findElement(By.xpath("//div[@id='close-mailch']")).click();
			sleepInSeconds(3);
			Assert.assertFalse(driver.findElement(By.id("//div[@class='mailch-wrap']")).isDisplayed());
		}
		
		driver.findElement(By.xpath("//section[@id='search-2']//input[@placeholder='Search Articles']")).sendKeys("Robot framework");
		sleepInSeconds(2);
		driver.findElement(By.xpath("//section[@id='search-2']//span[@class='glass']")).click();
		sleepInSeconds(2);
		Assert.assertTrue(driver.findElement(By.xpath("//h2[@class='page-title']/span")).getText().contains("Robot framework"));
	}

	@Test
	public void TC_03_Popup_Not_In_DOM() {
		driver.get("https://tiki.vn/");
		// Hover mouse
		action.moveToElement(driver.findElement(By.xpath("//span[@class='account-label']"))).perform();
		sleepInSeconds(2);
		//Click to Dang nhap button
		driver.findElement(By.xpath("//button[text()='Đăng nhập']")).click();
		sleepInSeconds(2);
		
		// Verfiy popup is display
		Assert.assertTrue(driver.findElement(By.xpath("//div[@role='dialog']/div")).isDisplayed());
		
		//close popup
		driver.findElement(By.xpath("//img[@class='close-img']")).click();
		
		//verify popup not display trong DOM: khong the dung findElement vi element khong co trong DOM => error before thuc thi isDisplay
		// dung findElements
				
		//Assert.assertTrue(explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@role='dialog']/div")))); //dung invisible
		Assert.assertEquals(driver.findElements(By.xpath("//div[@role='dialog']/div")).size(), 0);
		
	}
	
	//@Test
	public void TC_04_Popup_Not_In_DOM_Condition() {
		driver.get("https://shopee.vn/");
		sleepInSeconds(5);		
		
		//có xuất hiện thì có trong DOM
		//không xuất hiện thì không có trong DOM
		if (driver.findElements(By.xpath("//img[@alt='home_popup_banner']")).size() >=1) {
			//close popup
			driver.findElement(By.xpath("//div[@class='shopee-popup__close-btn']")).click();
			sleepInSeconds(3);
			Assert.assertEquals(driver.findElements(By.xpath("//img[@alt='home_popup_banner']")).size(), 0);
		}
		
		driver.findElement(By.xpath("//input[@class='shopee-searchbar-input__input']")).sendKeys("Macbook Pro");
		sleepInSeconds(2);
		driver.findElement(By.xpath("//div[@class='shopee-searchbar']/button")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//span[@class='shopee-search-user-brief__header-text-highlight']")).getText(), "Macbook Pro".toLowerCase());
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
