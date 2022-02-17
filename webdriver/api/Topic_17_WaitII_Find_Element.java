package api;

import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_17_WaitII_Find_Element {
	WebDriver driver;
	
	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();		
	}
	
	@Test
	public void TC_01_FindElement() {
		driver.get("https://facebook.com/");
		//tim thay 1 element
		//Tìm thấy thì không chờ hết timeout, tìm không thấy thì cứ mỗi nửa giây tìm lại
		System.out.println("Start - " + getDateTimeNow());
		driver.findElement(By.id("email"));
		System.out.println("End - " + getDateTimeNow());
		
		//tim thay nhieu hon 1 element
		// Sẽ thao tác với element đầu tiên tìm được, không quan tâm ẩn hay hiện
		System.out.println("Start - " + getDateTimeNow());
		driver.findElement(By.xpath("//input[@id='email'or @id='pass']"));
		System.out.println("End - " + getDateTimeNow());
		
		//khong tim thay element nao
		//Chờ hết timeout rồi mà vẫn không thấy element (không có trong DOM)
		// -> fail testcase ở step này, dừng testcase
		// throw ra 1 exception: no such element
		// thêm try catch để thấy chờ hết timeout
		System.out.println("Start - " + getDateTimeNow());
		
		try {
			driver.findElement(By.xpath("//label"));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("End - " + getDateTimeNow());
		}
	}
	
	@Test
	public void TC_02_FindElements() {
		driver.get("https://facebook.com/");
		
		// Tìm thấy thì không chờ hết timeout
		// Tìm không thấy thì mỗi nửa giây tìm lại
		// Tìm không thấy hết timeout thì qua step tiếp theo
		// Lưu kết quả vafp list: 0, 1 hay nhiều
		// Không throw exception nếu không tìm thấy
		
		//tim thay 1 element
		System.out.println("Start - " + getDateTimeNow());
		List<WebElement> elements = driver.findElements(By.id("email"));
		System.out.println(elements.size());
		System.out.println("End - " + getDateTimeNow());
		
		//tim thay nhieu hon 1 element
		System.out.println("Start - " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//input[@id='email'or @id='pass']"));
		System.out.println(elements.size());
		System.out.println("End - " + getDateTimeNow());
		
		//khong tim thay element nao
		System.out.println("Start - " + getDateTimeNow());
		elements = driver.findElements(By.xpath("//label"));
		System.out.println(elements.size());
		System.out.println("End - " + getDateTimeNow());
	}
	
	@Test
	public void TC_03_Find_Elements() {
		driver.get("https://automationfc.github.io/multiple-fields/");
		List<WebElement> checkboxes = driver.findElements(By.xpath("//input[@type='checkbox']"));
		System.out.println(checkboxes.size());
		
		for (WebElement checkbox : checkboxes) {
			checkbox.click();
			sleepInSeconds(1);
		}		
	}
	
	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String getDateTimeNow(){
		Date date = new Date();
		return date.toString();
	}
	
	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
