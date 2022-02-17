package api;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_Default {
	WebDriver driver;
	Select select;
	Actions action;
	String firstName, lastName, dayOfBirth, monthOfBirth, yearOfBirth, email, password;

	By femaleGenderBy = By.id("gender-female");
	By firstNameBy = By.id("FirstName");
	By lastNameBy = By.id("LastName");
	By dayOfBirthBy = By.name("DateOfBirthDay");
	By monthOfBirthhBy = By.name("DateOfBirthMonth");
	By yearOfBirthBy = By.name("DateOfBirthYear");
	By emailBy = By.id("Email");
	By passwordBy = By.id("Password");
	By confirmPasswordBy = By.id("ConfirmPassword");

	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();		

		firstName = "John";
		lastName = "Wick";
		dayOfBirth = "31";
		monthOfBirth = "March";
		yearOfBirth = "1988";
		email = "johnwick" + getRandomNumber() +"@gmail.com";
		password = "123456";

	}

	@Test
	public void TC_01_Register() {
		driver.get("https://demo.nopcommerce.com/");
		//open Register
		driver.findElement(By.cssSelector(".ico-register")).click();
		// input all required fields
		checkToCheckboxOrRadio(femaleGenderBy);
		driver.findElement(firstNameBy).sendKeys(firstName);
		driver.findElement(lastNameBy).sendKeys(lastName);
		
		select = new Select(driver.findElement(dayOfBirthBy));
		select.selectByVisibleText(dayOfBirth);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dayOfBirth);
		
		select = new Select(driver.findElement(monthOfBirthhBy));
		select.selectByVisibleText(monthOfBirth);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), monthOfBirth);
		
		// check if select is not multiple
		Assert.assertFalse(select.isMultiple());
		
		// Take all items in a dropdown
		List<WebElement> allItem = select.getOptions();
		
		// verify number of items
		Assert.assertEquals(allItem.size(), 13);
		
		select = new Select(driver.findElement(yearOfBirthBy));
		select.selectByVisibleText(yearOfBirth);
		Assert.assertEquals(select.getFirstSelectedOption().getText(), yearOfBirth);
		
		
		driver.findElement(emailBy).sendKeys(email);
		driver.findElement(passwordBy).sendKeys(password);
		driver.findElement(confirmPasswordBy).sendKeys(password);
		
		
		// click submit
		WebElement buttonRegister = driver.findElement(By.xpath("//button[@id='register-button']"));
		driver.findElement(By.xpath("//body")).click();
		buttonRegister.click();
		
		// Verify success message
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
		
		// Go to My account
		driver.findElement(By.cssSelector(".ico-account")).click();
		
		// Verify all info
		Assert.assertTrue(isElementSelected(femaleGenderBy));
		Assert.assertEquals(driver.findElement(firstNameBy).getAttribute("value"), firstName);
		Assert.assertEquals(driver.findElement(lastNameBy).getAttribute("value"), lastName);
		
		select = new Select(driver.findElement(dayOfBirthBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), dayOfBirth);
		
		select = new Select(driver.findElement(monthOfBirthhBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), monthOfBirth);
		
		select = new Select(driver.findElement(yearOfBirthBy));
		Assert.assertEquals(select.getFirstSelectedOption().getText(), yearOfBirth);
				
		Assert.assertEquals(driver.findElement(emailBy).getAttribute("value"), email);		
		Assert.assertTrue(isElementSelected(By.id("Newsletter")));
		
	}
	
	@Test
	public void TC_02_Multiple_Select() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		select = new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		
		ArrayList<String> OptionText = new ArrayList<String>();
		OptionText.add("Automation");
		OptionText.add("Mobile");
		OptionText.add("Perfomance"); //typo @@
		OptionText.add("Functional UI");
		
		//Choose 4 items
		for (String text : OptionText) {
			select.selectByVisibleText(text);
		}
		sleepInSeconds(5);
		//check if support multiple select
		Assert.assertTrue(select.isMultiple());
		
		//Verify choose 4 items
		List<WebElement> allSelectedItems = select.getAllSelectedOptions();
		Assert.assertEquals(allSelectedItems.size(), 4);
		
		ArrayList<String> allSelectedText = new ArrayList<String>();
		for (WebElement item : allSelectedItems) {
			allSelectedText.add(item.getText());
		}
		
		Assert.assertEquals(OptionText, allSelectedText);
	}

	public void checkToCheckboxOrRadio(By by) {
		WebElement element = driver.findElement(by);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(By by) {
		WebElement element = driver.findElement(by);
		if (element.isSelected()) {
			element.click();
		}
	}
	
	public boolean isElementSelected(By by){
		WebElement element = driver.findElement(by);
		if(element.isSelected()) {
			System.out.println(by+" is selected");
			return true;
		}else {
			System.out.println(by +" is not selected");
			return false;
		}
	}

	public int getRandomNumber() {
		Random rand = new Random();
		return rand.nextInt(1000);
	}

	public void sleepInSeconds(long second) {
		try {
			Thread.sleep(second * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

}
