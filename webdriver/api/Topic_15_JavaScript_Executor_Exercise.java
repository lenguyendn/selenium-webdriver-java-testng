package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_JavaScript_Executor_Exercise {
	WebDriver driver;
	JavascriptExecutor jsExecutor;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
		driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Javascript_Executer() {
		navigateToUrlByJS("http://live.demoguru99.com/");

		// Get domain
		String domain = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(domain, "live.demoguru99.com");
		sleepInSeconds(1);

		// Get URL
		String URL = (String) jsExecutor.executeScript("return document.URL");
		Assert.assertEquals(URL, "http://live.demoguru99.com/");
		sleepInSeconds(1);

		// Open MOBILE page
		clickToElementByJS("//a[text()='Mobile']");
		sleepInSeconds(1);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='Mobile']")).isDisplayed());

		// Add samsung vao cart
		clickToElementByJS(
				"//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button");
		sleepInSeconds(3);

		// Verify successful message
		String innerText = jsExecutor.executeScript("return document.documentElement.innerText;").toString();
		Assert.assertTrue(innerText.contains("Samsung Galaxy was added to your shopping cart."));

		// Open Customer Service Page
		jsExecutor.executeScript("window.location='http://live.demoguru99.com/index.php/customer-service/'");
		sleepInSeconds(1);

		String titlePage = jsExecutor.executeScript("return document.title").toString();
		Assert.assertEquals(titlePage, "Customer Service");

		// Scroll to element Newsletter textbox
		scrollToElement("//input[@type='email']");
		sleepInSeconds(2);

		// Input Email hop le vao textbox
		sendkeyToElementByJS("//input[@type='email']", "automationlele" + getRandomNumber() + "@gmail.com");
		clickToElementByJS("//div[@class='actions']//button[@type='submit']");
		sleepInSeconds(3);

		// Verify successful message
		Assert.assertTrue(areExpectedTextInInnerText("Thank you for your subscription."));

		// Navigate to domain
		jsExecutor.executeScript("window.location='http://demo.guru99.com/v4/'");
		sleepInSeconds(1);
		String domainTwo = (String) jsExecutor.executeScript("return document.domain");
		Assert.assertEquals(domainTwo, "demo.guru99.com");
	}

	public void TC_02_Verify_HTML5_Validation_Message() {
		driver.get("https://automationfc.github.io/html5/index.html");
		sleepInSeconds(2);
		String nameTextboxLocator = "//input[@id='fname']";
		String passwordTextboxLocator = "//input[@id='pass']";
		String emailTextboxLocator = "//input[@id='em']";
		String addressLocator = "//b[text()='✱ ADDRESS ']//parent::label/following-sibling::select";
		String submitButtonLocator = "//input[@value='SUBMIT']";

		// Click submit and verify message hien thi tai fiel Name texbox
		clickToElementByJS(submitButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(nameTextboxLocator), "Please fill out this field.");

		// Input du lieu vao va submit -> verify message hien thi tai Password textbox
		sendkeyToElementByJS(nameTextboxLocator, "Le");
		clickToElementByJS(submitButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(passwordTextboxLocator), "Please fill out this field.");

		// Input du lieu vao va submit -> verify message hien thi tai Email textbox
		sendkeyToElementByJS(passwordTextboxLocator, "123456");
		clickToElementByJS(submitButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(emailTextboxLocator), "Please fill out this field.");

		// Input du lieu khong hop le vao Email text box + click submit ->verify message
		// hien thi tai Email textbox
		sendkeyToElementByJS(emailTextboxLocator, "123456");
		clickToElementByJS(submitButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(emailTextboxLocator), "Please enter an email address.");

		// Input invalid format -> verify message
		sendkeyToElementByJS(emailTextboxLocator, "abc@abc");
		clickToElementByJS(submitButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(emailTextboxLocator), "Please match the requested format.");

		// Input valid email -> verify message at Address field
		sendkeyToElementByJS(emailTextboxLocator, "abc@abc.com");
		clickToElementByJS(submitButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(addressLocator), "Please select an item in the list.");
	}

	@Test
	public void TC_03_Verify_HTML5_Validation_MessageII() {
		navigateToUrlByJS("https://login.ubuntu.com");

		// close dialog
		if (driver.findElements(By.xpath("//div[@class='p-modal__dialog']")).size() >= 1) {
			// close popup
			driver.findElement(By.xpath("//button[@id='cookie-policy-button-accept']")).click();
			sleepInSeconds(3);
			// Assert.assertEquals(driver.findElements(By.id("//div[@class='p-modal__dialog']")).size(),
			// 0);
		}
		driver.findElement(By.xpath("//input[@type='email']")).sendKeys("a");
		sleepInSeconds(2);
		driver.findElement(By.xpath("//div[@class='actions']//button[@type='submit']")).click();
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage("//input[@type='email']"), "Please enter an email address.");
		sleepInSeconds(1);

		navigateToUrlByJS("https://sieuthimaymocthietbi.com/account/register");
		driver.findElement(By.xpath("//div[@class='form-signup clearfix']//button[@type='submit']")).click();
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage("//input[@id='lastName']"), "Please fill out this field.");

	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public void scrollToElement(String locator) {
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getElement(locator));
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	public boolean areExpectedTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
		return textActual.equals(textExpected);
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);
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
