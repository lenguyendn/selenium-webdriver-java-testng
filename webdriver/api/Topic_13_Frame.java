package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Topic_13_Frame {
	WebDriver driver;
	Select select;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01() {
		driver.get("https://automationfc.com/2020/02/18/training-online-automation-testing/");

		// Switch by index
		// driver.switchTo().frame(3);

		// switch by id or name
		// driver.switchTo().frame("");

		// Switch vao Facebook iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//div[@class='fb-page fb_iframe_widget']//iframe")));

		// get like number
		String likeNumber = driver
				.findElement(By.xpath("//a[text()='Automation FC']//parent::div//following-sibling::div")).getText();
		System.out.println(likeNumber);

		// Default conten (back lai parent)
		driver.switchTo().defaultContent();

		// Switch vao google doc iframe
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src, 'docs.google.com')]")));

		// verify Doc header is displayed
		Assert.assertTrue(
				driver.findElement(By.xpath("//div[@role='heading' and text()='KHÓA HỌC SELENIUM AUTOMATION TESTING']"))
						.isDisplayed());

	}

	@Test
	public void TC_02() {
		driver.get("https://kyna.vn/");
		sleepInSecond(1);
		// Switch to iframe
		driver.switchTo().frame("cs_chat_iframe");
		driver.findElement(By.xpath("//div[@class='border_overlay meshim_widget_widgets_BorderOverlay']")).click();
		sleepInSecond(2);
		driver.findElement(By.cssSelector("input.input_name")).sendKeys("John Wick");
		driver.findElement(By.cssSelector("input.input_phone")).sendKeys("098765432");

		select = new Select(driver.findElement(By.cssSelector("#serviceSelect")));
		select.selectByVisibleText("TƯ VẤN TUYỂN SINH");
		sleepInSecond(1);
		driver.findElement(By.xpath("//textarea[@name='message']")).sendKeys("testing");
		sleepInSecond(1);
		driver.findElement(By.cssSelector("input.submit")).click();
		sleepInSecond(2);

		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(@class,'logged_in_name') and text()='John Wick']"))
						.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//label[contains(@class,'logged_in_phone') and text()='098765432']"))
						.isDisplayed());
		Assert.assertTrue(
				driver.findElement(By.xpath("//textarea[@name='message' and text()='testing']")).isDisplayed());

		// Switch back to main page
		driver.switchTo().defaultContent();
		// search
		driver.findElement(By.cssSelector("#live-search-bar")).sendKeys("excel");
		sleepInSecond(1);
		driver.findElement(By.cssSelector("#live-search-bar")).sendKeys(Keys.ENTER);
		sleepInSecond(2);

		List<WebElement> results = driver.findElements(By.xpath("//div[@class='content']//h4"));
		for (WebElement result : results) {
			Assert.assertTrue(result.getText().contains("Excel"));
		}

	}

	public void sleepInSecond(long timeout) {
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
