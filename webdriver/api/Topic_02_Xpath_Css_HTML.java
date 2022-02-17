package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Css_HTML {
	WebDriver driver;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}

	@Test (enabled = false)
	public void TC_01_ValidateCurrentUrl() {
		driver.get("https://alada.vn/tai-khoan/dang-ky.html");
		//driver: đại diện cho Selenium WebDriver - gọi thư viện ra để sử dụng
		//By.id/ classname/ name/ xpath / cssSelector/ tagname/ linkText/ partialLinkText
		driver.findElement(By.xpath("//form[@id=\"frmLogin\"]//button[text()=\"ĐĂNG KÝ\"]")).click();		
		ThreadSleep(3);
		
		driver.findElement(By.cssSelector("input[name='txtFirstname']")).sendKeys("Le Nguyen");
		ThreadSleep(3);
		
		driver.findElement(By.name("txtEmail")).sendKeys("test@abc.abc");
		ThreadSleep(3);
				
		driver.findElement(By.id("txtPassword")).sendKeys("123456");
		ThreadSleep(3);
		
	}

	@Test (enabled = false)
	public void TC_02_LinkText() {
		driver.get("https://login.ubuntu.com");		
		driver.findElement(By.linkText("Read More ›")).click();
		ThreadSleep(3);
		driver.navigate().back();
		ThreadSleep(3);
		driver.findElement(By.partialLinkText("Read")).click();
	}

	
	public void TC_03_Xpath() {
		driver.get("https://demo.nopcommerce.com");
		//ID, Name, Class, Attribute
		//Index
		driver.findElement(By.xpath("//form[@id='small-search-box-form']/input[1]")).sendKeys("abc");
		
	}
	
	public void ThreadSleep(int sec){
		try {
			Thread.sleep(sec *1000);
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
