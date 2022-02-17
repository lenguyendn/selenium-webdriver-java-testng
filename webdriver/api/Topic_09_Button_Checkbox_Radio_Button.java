package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_09_Button_Checkbox_Radio_Button {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Button() {
		driver.get("https://www.fahasa.com/customer/account/create?attempt=1");
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		sleepInSeconds(2);
		By loginButton = By.cssSelector(".fhs-btn-login");
		Assert.assertFalse(driver.findElement(loginButton).isEnabled());

		driver.findElement(By.id("login_username")).sendKeys("automationfc" + getRandomNumber() + "@gmail.com");
		driver.findElement(By.id("login_password")).sendKeys("123456");
		Assert.assertTrue(driver.findElement(loginButton).isEnabled());
		sleepInSeconds(2);
		Assert.assertEquals(convertColorGrbaToHex(driver.findElement(loginButton).getCssValue("background-color")), "#c92127");

		driver.navigate().refresh();
		driver.findElement(By.xpath("//a[text()='Đăng nhập']")).click();
		sleepInSeconds(2);

		removeDisabledAttributeByJS(loginButton);
		driver.findElement(loginButton).click();
		Assert.assertEquals(driver
				.findElement(By.xpath("//input[@id='login_username']//parent::div//following-sibling::div")).getText(),
				"Thông tin này không thể để trống");
		Assert.assertEquals(driver
				.findElement(By.xpath("//input[@id='login_password']//parent::div//following-sibling::div")).getText(),
				"Thông tin này không thể để trống");

	}

	@Test
	public void TC_02_Default_Checkbox_Radio_Button() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		sleepInSeconds(5);
		driver.findElement(By.id("onetrust-accept-btn-handler")).click();
		sleepInSeconds(1);
		WebElement DualZonecheckbox = driver.findElement(By.id("eq5"));
		//jsExecutor.executeScript("arguments[0].click();", DualZonecheckbox); //trick bằng click js, không quan tâm element ẩn hay hiện
		selectCheckboxOrRadioButton(DualZonecheckbox);
		Assert.assertTrue(DualZonecheckbox.isSelected());
		deselectCheckbox(DualZonecheckbox);
		//jsExecutor.executeScript("arguments[0].click();", DualZonecheckbox);
		Assert.assertFalse(DualZonecheckbox.isSelected());

		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		WebElement PetroRadioButton = driver.findElement(By.id("engine3"));
		selectCheckboxOrRadioButton(PetroRadioButton);
		Assert.assertTrue(PetroRadioButton.isSelected());
		deselectCheckbox(PetroRadioButton); // can not deseclect a radio button
		Assert.assertTrue(PetroRadioButton.isSelected());

	}

	@Test
	public void TC_03_Custom_Radio_Checkbox() {
		driver.get("https://material.angular.io/components/radio/examples");
		//dùng click của JS vừa click vừa có thể verify với thẻ input
		By Spring = By.xpath("//input[@value='Spring']");
		clickByJS(Spring);
		sleepInSeconds(2);
		Assert.assertTrue(driver.findElement(Spring).isSelected());
		
	}

	@Test
	public void TC_04_CustomII() {
		driver.get("https://docs.google.com/forms/d/e/1FAIpQLSfiypnd69zhuDkjKgqvpID9kwO29UCzeCVrGGtbNPZXQok0jA/viewform");
		//không có thẻ input để verify => tìm sự khác nhau trong attribute
		//before click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='false']")).isDisplayed());
		
		driver.findElement(By.xpath("//div[@data-value='Cần Thơ']")).click();
		
		//After click
		Assert.assertTrue(driver.findElement(By.xpath("//div[@data-value='Cần Thơ' and @aria-checked='true']")).isDisplayed());
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);
	}

	public void removeDisabledAttributeByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].removeAttribute(\"disabled\")", element);
	}

	public void selectCheckboxOrRadioButton(WebElement element) {
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void deselectCheckbox(WebElement element) {
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public void clickByJS(By by) {
		WebElement element = driver.findElement(by);
		jsExecutor.executeScript("arguments[0].click();", element);
	}
	
	 public String convertColorGrbaToHex(String grba) {
		  return Color.fromString(grba).asHex();
	  }
	  
	  public String convertColorHexToGrba(String hex) {
		  return Color.fromString(hex).asRgba();
	  }

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
