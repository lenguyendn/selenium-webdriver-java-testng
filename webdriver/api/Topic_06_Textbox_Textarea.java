package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_06_Textbox_Textarea {
	WebDriver driver;
	String loginPageUrl, userID, password, customerID;
	String customerName, dateOfBirth, address, city, state, Pin, email, mobileNumber, pass;
	String editAddress, editCity, editState, editPin, editNumber;
	By nameTextBoxBy = By.name("name");
	By femaleRadioCheckboxBy = By.xpath("//input[@name='rad1' and @value='f']");
	By genderBy = By.name("gender");
	By DoBTextBoxBy = By.name("dob");;
	By addressTextBoxBy = By.name("addr");
	By cityTextBoxBy = By.name("city");
	By stateTextBoxBy = By.name("state");
	By pinTextBoxBy = By.name("pinno");
	By numberTextBoxBy = By.name("telephoneno");
	By emailTextBoxBy = By.name("emailid");
	By passwordTextBoxBy = By.name("password");

	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://demo.guru99.com/v4/");

		customerName = "John Kennedy";
		dateOfBirth = "1960-01-01";
		address = "654 Suitable Adress";
		city = "New York";
		state = "California";
		Pin = "999666";
		mobileNumber = "0985654123";
		email = "automationfc" + getRandomNumber() + "@gmail.com";
		pass = "123456";

		editAddress = "123 Suitable Adress";
		editCity = "Chicago";
		editState = "Illinois";
		editPin = "123321";
		editNumber = "0985654000";
	}

	@Test
	public void TC_01_Register() {
		loginPageUrl = driver.getCurrentUrl();

		driver.findElement(By.xpath("//a[text()='here']")).click();
		driver.findElement(By.name("emailid")).sendKeys("automationfc" + getRandomNumber() + "@gmail.com");
		driver.findElement(By.name("btnLogin")).click();

		//get thong tin user, pass luu ra bien
		userID = driver.findElement(By.xpath("//td[text()='User ID :']//following-sibling::td")).getText();
		password = driver.findElement(By.xpath("//td[text()='Password :']//following-sibling::td")).getText();

	}

	@Test
	public void TC_02_Login() {
		driver.get(loginPageUrl);
		//dua gia tri tu bien vao form dang nhap
		driver.findElement(By.name("uid")).sendKeys(userID);
		driver.findElement(By.name("password")).sendKeys(password);
		driver.findElement(By.name("btnLogin")).click();

		Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());

	}

	@Test
	public void TC_03_New_Customer() {

		driver.findElement(By.xpath("//a[text()='New Customer']")).click();
		
		driver.findElement(nameTextBoxBy).sendKeys(customerName);
		driver.findElement(femaleRadioCheckboxBy).click();
		driver.findElement(DoBTextBoxBy).click();
		driver.findElement(DoBTextBoxBy).sendKeys(dateOfBirth); //chi ok voi firefox cu
		driver.findElement(addressTextBoxBy).sendKeys(address);
		driver.findElement(cityTextBoxBy).sendKeys(city);
		driver.findElement(stateTextBoxBy).sendKeys(state);
		driver.findElement(pinTextBoxBy).sendKeys(Pin);
		driver.findElement(numberTextBoxBy).sendKeys(mobileNumber);
		driver.findElement(emailTextBoxBy).sendKeys(email);
		driver.findElement(passwordTextBoxBy).sendKeys(pass);
		
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer Registered Successfully!!!']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), address);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), city);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(), state);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), Pin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(), mobileNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(), email);
		
		customerID = driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText();
	}

	@Test
	public void TC_04_Edit_Customer() {
		driver.findElement(By.xpath("//a[text()='Edit Customer']")).click();
		driver.findElement(By.name("cusid")).sendKeys(customerID);
		driver.findElement(By.name("AccSubmit")).click();

		// Verify some fields disabled
		Assert.assertFalse(isElementEnabled(nameTextBoxBy));
		Assert.assertFalse(isElementEnabled(genderBy));
		Assert.assertFalse(isElementEnabled(DoBTextBoxBy));

		// Verify value is right
		Assert.assertEquals(driver.findElement(nameTextBoxBy).getAttribute("value"), customerName);
		Assert.assertEquals(driver.findElement(DoBTextBoxBy).getAttribute("value"), dateOfBirth);
		Assert.assertEquals(driver.findElement(addressTextBoxBy).getText(), address);
		Assert.assertEquals(driver.findElement(cityTextBoxBy).getAttribute("value"), city);
		Assert.assertEquals(driver.findElement(stateTextBoxBy).getAttribute("value"), state);
		Assert.assertEquals(driver.findElement(pinTextBoxBy).getAttribute("value"), Pin);
		Assert.assertEquals(driver.findElement(numberTextBoxBy).getAttribute("value"), mobileNumber);
		Assert.assertEquals(driver.findElement(emailTextBoxBy).getAttribute("value"), email);

		// Edit
		driver.findElement(addressTextBoxBy).clear();
		driver.findElement(addressTextBoxBy).sendKeys(editAddress);
		driver.findElement(cityTextBoxBy).clear();
		driver.findElement(cityTextBoxBy).sendKeys(editCity);
		driver.findElement(stateTextBoxBy).clear();
		driver.findElement(stateTextBoxBy).sendKeys(editState);
		driver.findElement(pinTextBoxBy).clear();
		driver.findElement(pinTextBoxBy).sendKeys(editPin);
		driver.findElement(numberTextBoxBy).clear();
		driver.findElement(numberTextBoxBy).sendKeys(editNumber);
		driver.findElement(By.xpath("//input[@value='Submit']")).click();
		
		//Verify
		Assert.assertTrue(driver.findElement(By.xpath("//p[text()='Customer details updated Successfully!!!']")).isDisplayed());

		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer ID']//following-sibling::td")).getText(), customerID);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Customer Name']//following-sibling::td")).getText(), customerName);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Birthdate']//following-sibling::td")).getText(), dateOfBirth);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Address']//following-sibling::td")).getText(), editAddress);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='City']//following-sibling::td")).getText(), editCity);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='State']//following-sibling::td")).getText(), editState);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Pin']//following-sibling::td")).getText(), editPin);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Mobile No.']//following-sibling::td")).getText(), editNumber);
		Assert.assertEquals(driver.findElement(By.xpath("//td[text()='Email']//following-sibling::td")).getText(), email);
		
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			return true;
		} else {
			return false;
		}
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

}
