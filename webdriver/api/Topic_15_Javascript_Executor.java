package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_Javascript_Executor {
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
	public void TC_01_Click_Hidden_Element() {
		driver.get("https://www.zingpoll.com/");

		WebElement vietnameseLang = driver.findElement(By.xpath("//a[@role='menuitem']//img[contains(@src, 'VN')]"));
		jsExecutor.executeScript("arguments[0].click();", vietnameseLang);
		sleepInSeconds(5);

	}

	@Test
	public void TC_02_Highlight_Element() {
		navigateToUrlByJS("http://live.demoguru99.com/");

		clickToElementByJS("//a[text()='Mobile']");
		sleepInSeconds(1);

		// Highlight
		highlightElement("//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button");
		sleepInSeconds(3);
	}

	@Test
	public void TC_03_Validation_Message() {
		navigateToUrlByJS("https://login.ubuntu.com/");
		String loginButtonLocator = "//button[@data-qa-id='login_button']";
		String emailFieldLocator = "//input[@type='email']";
		String passwordFieldLocator = "//input[@type='password']";

		// close dialog
		if (driver.findElements(By.xpath("//div[@class='p-modal__dialog']")).size() >= 1) {
			// close popup
			driver.findElement(By.xpath("//button[@id='cookie-policy-button-accept']")).click();
			sleepInSeconds(2);
		}
		clickToElementByJS(loginButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(emailFieldLocator), "Please fill out this field.");

		sendkeyToElementByJS(emailFieldLocator, "aa");
		clickToElementByJS(loginButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(emailFieldLocator),
				"Please include an '@' in the email address. 'aa' is missing an '@'.");

		sendkeyToElementByJS(emailFieldLocator, "a@");
		clickToElementByJS(loginButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(emailFieldLocator),
				"Please enter a part following '@'. 'a@' is incomplete.");

		sendkeyToElementByJS(emailFieldLocator, "123@...");
		clickToElementByJS(loginButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(emailFieldLocator),
				"'.' is used at a wrong position in '...'.");

		sendkeyToElementByJS(emailFieldLocator, "123@abc.com");
		clickToElementByJS(loginButtonLocator);
		sleepInSeconds(1);
		Assert.assertEquals(getElementValidationMessage(passwordFieldLocator), "Please fill out this field.");

	}

	@Test
	public void TC_04_Remove_Attribute() {
		// Remove attribute type=date cua field Date of Birth => input nhu field text
		navigateToUrlByJS("http://demo.guru99.com/v4/");

		String loginPageUrl, userID, password;
		String customerName, dateOfBirth, address, city, state, Pin, email, mobileNumber, pass;
		By nameTextBoxBy = By.name("name");
		By femaleRadioCheckboxBy = By.xpath("//input[@name='rad1' and @value='f']");
		By DoBTextBoxBy = By.name("dob");
		;
		By addressTextBoxBy = By.name("addr");
		By cityTextBoxBy = By.name("city");
		By stateTextBoxBy = By.name("state");
		By pinTextBoxBy = By.name("pinno");
		By numberTextBoxBy = By.name("telephoneno");
		By emailTextBoxBy = By.name("emailid");
		By passwordTextBoxBy = By.name("password");

		customerName = "John Kennedy";
		dateOfBirth = "1960-01-01";
		address = "654 Suitable Adress";
		city = "New York";
		state = "California";
		Pin = "999666";
		mobileNumber = "0985654123";
		email = "automationfc" + getRandomNumber() + "@gmail.com";
		pass = "123456";

		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("automationfc" + getRandomNumber() + "@gmail.com");
		driver.findElement(By.name("btnLogin")).click();

		// get thong tin user, pass luu ra bien
		userID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();

		driver.get(loginPageUrl);
		// dua gia tri tu bien vao form dang nhap
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();

		driver.findElement(nameTextBoxBy).sendKeys(customerName);
		driver.findElement(femaleRadioCheckboxBy).click();

		// remove attribute type=date
		jsExecutor.executeScript("arguments[0].removeAttribute('type');", driver.findElement(DoBTextBoxBy));
		sleepInSeconds(3);

		driver.findElement(DoBTextBoxBy).click();
		driver.findElement(DoBTextBoxBy).sendKeys(dateOfBirth);
		driver.findElement(addressTextBoxBy).sendKeys(address);
		driver.findElement(cityTextBoxBy).sendKeys(city);
		driver.findElement(stateTextBoxBy).sendKeys(state);
		driver.findElement(pinTextBoxBy).sendKeys(Pin);
		driver.findElement(numberTextBoxBy).sendKeys(mobileNumber);
		driver.findElement(emailTextBoxBy).sendKeys(email);
		driver.findElement(passwordTextBoxBy).sendKeys(pass);

		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		sleepInSeconds(2);

		Assert.assertTrue(
				driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

		Assert.assertEquals(
				driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(),
				customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(),
				dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(),
				address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(),
				state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), Pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(),
				mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(),
				email);

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

	public void sendkeyToElementByJS(String locator, String value) {
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", getElement(locator));
	}

	public String getElementValidationMessage(String locator) {
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getElement(locator));
	}

	public void highlightElement(String locator) {
		WebElement element = getElement(locator);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 10px solid red; border-style: dashed;");
		sleepInSeconds(2);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);
	}

	public void clickToElementByJS(String locator) {
		jsExecutor.executeScript("arguments[0].click();", getElement(locator));
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	public WebElement getElement(String locator) {
		return driver.findElement(By.xpath(locator));
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
