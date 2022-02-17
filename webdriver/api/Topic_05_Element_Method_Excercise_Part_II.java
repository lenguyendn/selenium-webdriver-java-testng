package api;



import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_05_Element_Method_Excercise_Part_II {
	WebDriver driver;
	By emailTextBox = By.cssSelector("#email");
	By ageUnder18Radio = By.xpath("//input[@id='under_18']");
	By educationTextarea = By.cssSelector("#edu");
	By user5Text = By.xpath("//h5[text()='Name: User5']");
	By slider1 = By.id("slider-1");
	By slider2 = By.id("slider-2");
	By javaLanguageCheckbox = By.xpath("//input[@id='java']");
	
	public boolean isElementDisplayed(By by){
		WebElement element = driver.findElement(by);
		if(element.isDisplayed()) {
			System.out.println(by+" is displayed");
			return true;
		}else {
			System.out.println(by +" is not displayed");
			return false;
		}
	}
	
	public boolean isElementEnabled(By by){
		WebElement element = driver.findElement(by);
		if(element.isEnabled()) {
			System.out.println(by+" is enabled");
			return true;
		}else {
			System.out.println(by +" is disabled");
			return false;
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
	
	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.clear();
		element.sendKeys(value);
	}
	
	public void clickToElement(By by) {
		driver.findElement(by).click();
	}
	
	
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.manage().window().maximize();
	}
	
	@Test
	public void TC_01_isDisplayed() {
		if(isElementDisplayed(emailTextBox)) {
			sendkeyToElement(emailTextBox, "Automation Testing");
		}
		
		if(isElementDisplayed(ageUnder18Radio)) {
			clickToElement(ageUnder18Radio);
		}
		
		if(isElementDisplayed(educationTextarea)) {
			sendkeyToElement(educationTextarea, "Automation Testing");
		}
		
		Assert.assertFalse(isElementDisplayed(user5Text));
	}
	
	@Test
	public void TC_02_isEnabled() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		Assert.assertTrue(isElementEnabled(slider1));
		Assert.assertFalse(isElementEnabled(slider2));
	}
	
	@Test
	public void TC_03_isSelected() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		clickToElement(ageUnder18Radio);
		clickToElement(javaLanguageCheckbox);		
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertTrue(isElementSelected(javaLanguageCheckbox));
		
		clickToElement(ageUnder18Radio);
		clickToElement(javaLanguageCheckbox);
		Assert.assertTrue(isElementSelected(ageUnder18Radio));
		Assert.assertFalse(isElementSelected(javaLanguageCheckbox));
	}
	
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
	
}
