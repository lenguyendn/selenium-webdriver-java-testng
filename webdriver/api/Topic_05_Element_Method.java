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

public class Topic_05_Element_Method {
	WebDriver driver;

	@BeforeClass
	public void BeforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
	}

	@Test
	public void TC_01_Element_Command() {
		WebElement element = driver.findElement(By.xpath(""));
		// Khai báo và thao tác lên element + không cần khai báo biển
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("selenium_19@gmail.com");

		// Khai báo biến rồi mới thao tác: action click/getText/select...
		// Dùng nhiều lần thì nên khai báo biến (đỡ viết lại code)

		// Xóa dữ liệu đã nhập trong 1 textbox/ textarea/ dropdown (editable)
		driver.findElement(By.xpath("//input[@id='email']")).clear();

		// Click vào button/ checkbox/ radio button/ link/ dropdown/ image
		element.click();

		// lấy ra giá trị nằm trong 1 attribute
		element.getAttribute("placeholder");

		// lấy ra style của 1 element (font/size/color/background...) test GUI
		element.getCssValue("background");
		// #3399cc

		element.getLocation();
		element.getRect();
		element.getSize();

		// chụp screenshot làm report
		// element.getScreenshotAs(target);

		String emailTextBoxTagname = element.getTagName();
		// input/ div/ span
		// Đầu ra của hàm này -> tên thẻ -> đầu vào của một element khác (tagname trong
		// xpath)
		driver.findElement(By.xpath("//" + emailTextBoxTagname + "[@id='email']"));

		// Lấy ra text của 1 element bất kì, text không nằm trong attribute
		element.getText();

		// Kiểm tra 1 element hiển thị
		Assert.assertTrue(element.isDisplayed());
		// Kiểm tra 1 element không hiển thị
		Assert.assertFalse(element.isDisplayed());

		element.isEnabled();
		element.isSelected();

		// submit form
		element.submit();
	}

	@AfterClass
	public void AfterClass() {
		driver.quit();
	}

}
