package api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_HTML2_demoguru {
	WebDriver driver;
	String emailField = "#email";
	String passwordField = "#pass";
	String loginButton = "//button[@type='submit' and @title='Login']";
	
	String email = "automation@gmail.com";
	String password ="123123";
	
	String registerFirstName ="Le";
	String registerMiddletName ="TH";
	String registerLastName ="Nguyen";
	String fullName = registerFirstName +" " +registerMiddletName + " " + registerLastName;
	String registerEmail ="lenguyen"+ getRamdomNumber() +"@gmail.com";
	String registerPassword = "123456";

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test (enabled = false)
	public void TC_01_LoginWithEmptyEmailAndPassword() {
		driver.get("http://live.demoguru99.com");
		ThreadSleep(1);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']")).click();
		ThreadSleep(1);
		driver.findElement(By.xpath(loginButton)).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText(), "This is a required field.");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText(), "This is a required field.");		
		
	}

	@Test (enabled = false)
	public void TC_02_LoginWithInvalidEmail() {
		driver.get("http://live.demoguru99.com");
		ThreadSleep(1);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']")).click();
		ThreadSleep(1);		
		driver.findElement(By.cssSelector(emailField)).sendKeys("12344321@12345.123123");
		driver.findElement(By.cssSelector(passwordField)).sendKeys(password);
		
		driver.findElement(By.xpath(loginButton)).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText(), "Please enter a valid email address. For example johndoe@domain.com.");
	}

	@Test (enabled = false)
	public void TC_03_LoginWithPasswordLessThan6Characters() {
		driver.get("http://live.demoguru99.com");
		ThreadSleep(1);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']")).click();
		ThreadSleep(1);
		
		driver.findElement(By.cssSelector(emailField)).sendKeys(email);
		driver.findElement(By.cssSelector(passwordField)).sendKeys("123");
		
		driver.findElement(By.xpath(loginButton)).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText(), "Please enter 6 or more characters without leading or trailing spaces.");		
		
	}
	
	@Test (enabled = true)
	public void TC_04_LoginWithIncorrectEmailPassword() {
		driver.get("http://live.demoguru99.com");
		ThreadSleep(1);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']")).click();
		ThreadSleep(1);
		
		driver.findElement(By.cssSelector(emailField)).sendKeys(email);
		driver.findElement(By.cssSelector(passwordField)).sendKeys("123456");
		
		driver.findElement(By.xpath(loginButton)).click();
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText(), "Invalid login or password.");
		
	}
	
	@Test (enabled = false)
	public void TC_05_CreateNewAccount() {
		driver.get("http://live.demoguru99.com");
		ThreadSleep(1);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']")).click();
		ThreadSleep(1);
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		driver.findElement(By.cssSelector("#firstname")).sendKeys(registerFirstName);
		driver.findElement(By.cssSelector("#middlename")).sendKeys(registerMiddletName);
		driver.findElement(By.cssSelector("#lastname")).sendKeys(registerLastName);
		driver.findElement(By.cssSelector("#email_address")).sendKeys(registerEmail);
		driver.findElement(By.cssSelector("#password")).sendKeys(registerPassword);
		driver.findElement(By.cssSelector("#confirmation")).sendKeys(registerPassword);
		if(!driver.findElement(By.cssSelector("#is_subscribed")).isSelected()) {
			driver.findElement(By.cssSelector("#is_subscribed")).click();
		}
		driver.findElement(By.xpath("//button[@title='Register']")).click();
		ThreadSleep(2);
		driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed();
		String text =driver.findElement(By.xpath("//p[@class='hello']/strong")).getText();
		text.equals("Hello, " + fullName +"!");
		driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed();
		driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div"
				+ "/p[contains(text(),fullName) and contains(.,registerEmail)]")).isDisplayed();
		
		driver.findElement(By.xpath("//span[@class='label' and text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
	}
	
	@Test (enabled = false)
	public void TC_06_RegisterWithInvalidPhoneNumber() {
		driver.get("http://live.demoguru99.com");
		ThreadSleep(1);
		driver.findElement(By.xpath("//div[@class='footer']//a[@title ='My Account']")).click();
		ThreadSleep(1);
		
		driver.findElement(By.cssSelector(emailField)).sendKeys(registerEmail);
		driver.findElement(By.cssSelector(passwordField)).sendKeys(registerPassword);
		driver.findElement(By.xpath(loginButton)).click();
		
		ThreadSleep(2);
		Assert.assertTrue(driver.findElement(By.xpath("//h1[text()='My Dashboard']")).isDisplayed());
		String text =driver.findElement(By.xpath("//p[@class='hello']/strong")).getText();
		text.equals("Hello, " + fullName +"!");
		driver.findElement(By.xpath("//h3[text()='Contact Information']/parent::div[@class='box-title']/following-sibling::div"
				+ "/p[contains(text(),fullName) and contains(.,registerEmail)]")).isDisplayed();
		
	}
	
	public void ThreadSleep(int sec){
		try {
			Thread.sleep(sec*1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int getRamdomNumber() {
		Random ran = new Random();
		return ran.nextInt(1000);
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
