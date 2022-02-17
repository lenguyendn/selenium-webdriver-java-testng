package api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;




public class Topic_04_Browser_Method {
	WebDriver driver;
	
	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	@Test
	public void TC() {
		//mở ra 1 ứng dụng web/page
		driver.get("https://live.demoguru99.com");
		
		// Hàm tương tác với browser: driver. (có đuôi WebDriver)
		//driver.manage(). (hàm có đuôi Options)
		//Hàm tương tác với element: driver.findElement(by).
		
		
		//Đóng trình duyệt
		//Đóng tab đang mở, nếu có 1 tab = đóng browser
		driver.close();
		
		//Đóng trình duyệt
		// Không quan tâm tab, đóng hết browser
		driver.quit();
		
		//Mở ra trang Mobile
		driver.findElement(By.xpath("//a[text()='Mobile']")).click();
		
		//Kiểm tra được cái Url của page mới mở đó có đúng hay không
		driver.getCurrentUrl();
		
		Assert.assertEquals(driver.getCurrentUrl(), "http://live.demoguru99.com/index.php/mobile.html");
		
		//Lấy ra title của page hiện tại
		driver.getTitle();
		
		//Lấy ra source code của page hiện tại
		driver.getPageSource();
		
		//Lấy ID của tab/window hiện tại
		driver.getWindowHandle();
		
		//lấy ID tab tất cả tab/ window
		driver.getWindowHandles();
		
		//Chờ cho page được load xong trong vòng ... giây
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		
		//Chờ cho 1 đoạn script của JavascriptExecutor thực thi xong trong...giây
		driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		
		//Phóng to browser
		driver.manage().window().maximize();
		
		//F11
		driver.manage().window().fullscreen();
		
		//setSize, getSize: kích thước của trình duyệt
		//setPosition, getPosition: vị trí của trình duyệt
		
		driver.navigate().back();
		driver.navigate().forward();
		driver.navigate().refresh();
		driver.navigate().to("");
		
		//Thao tác với Alert
		driver.switchTo().alert();
		
		//Thao tác với Frame/Iframe
		driver.switchTo().frame("");
		
		
		
		
		
		
	}
	
	@AfterClass
	public void AfterClass() {
		driver.quit();
	}
}
