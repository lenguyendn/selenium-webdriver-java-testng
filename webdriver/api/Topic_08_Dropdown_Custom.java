package api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_08_Dropdown_Custom {
	WebDriver driver;
	WebDriverWait explicitWait;
	JavascriptExecutor jsExecutor;
	String projectLocation = System.getProperty("user.dir");
	String[] threeMonths= {"January", "March", "May"};
	String[] fourMonths= {"February", "December", "November", "August"};
	String[] twelthMonths = {"January", "March", "May", "February", "December", "November", "August", "April", "June", "July", "October", "September"};
	String[] wrongmonths = {"Januari", "Morch", "Mey"};

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", projectLocation + "\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		explicitWait = new WebDriverWait(driver, 15);
		driver.manage().window().maximize();
		

	}

	 @Test
	public void TC_01_JQuery() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		selectCustomDropdownbyText("//span[@id='number-button']", "//ul[@id='number-menu']//div", "10");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"10");

		selectCustomDropdownbyText("//span[@id='number-button']", "//ul[@id='number-menu']//div", "19");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"19");

		selectCustomDropdownbyText("//span[@id='number-button']", "//ul[@id='number-menu']//div", "5");
		Assert.assertEquals(driver
				.findElement(By.xpath("//span[@id='number-button']//span[@class='ui-selectmenu-text']")).getText(),
				"5");

	}

	@Test
	public void TC_02_NopCommerce() {
		driver.get("https://demo.nopcommerce.com/register");
		// I dont see any value to use custom for a default dropdown, it's just an
		// example, but do not do that
		selectCustomDropdownbyText("//select[@name='DateOfBirthDay']", "//select[@name='DateOfBirthDay']//option",
				"31");
		selectCustomDropdownbyText("//select[@name='DateOfBirthMonth']", "//select[@name='DateOfBirthDay']//option",
				"March");
		// selectCustomDropdownbyText("//select[@name='DateOfBirthYear']",
		// "//select[@name='DateOfBirthDay']//option", "1988");
	}

	@Test
	public void TC_03_Angular() {
		// Khó: phần assert, có element trong DOM nhưng không hiện thị được, isDisplayed
		// trả về false, getText() trả về null
		// không dùng selenium được mà phải dùng javascript
		String selectedTextcss = "select[id='games_hidden']>option";
		driver.get(
				"https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");
		selectCustomDropdownbyText("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']//li", "Basketball");
		Assert.assertEquals(getAngularSelectedText(selectedTextcss), "Basketball");

		selectCustomDropdownbyText("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']//li", "Cricket");
		Assert.assertEquals(getAngularSelectedText(selectedTextcss), "Cricket");

		selectCustomDropdownbyText("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']//li", "Football");
		Assert.assertEquals(getAngularSelectedText(selectedTextcss), "Football");
	}

	@Test
	public void TC_04_React() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-selection/");

		selectCustomDropdownbyText("//div[@role='listbox']", "//div[@role='option']//span", "Christian");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Christian");

		selectCustomDropdownbyText("//div[@role='listbox']", "//div[@role='option']//span", "Matt");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Matt");

		selectCustomDropdownbyText("//div[@role='listbox']", "//div[@role='option']//span", "Jenny Hess");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Jenny Hess");
	}

	@Test
	public void TC_05_VueJS() {
		driver.get("https://mikerodham.github.io/vue-dropdowns/");
		selectCustomDropdownbyText("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a",
				"Second Option");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Second Option");

		selectCustomDropdownbyText("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "First Option");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "First Option");

		selectCustomDropdownbyText("//li[@class='dropdown-toggle']", "//ul[@class='dropdown-menu']//a", "Third Option");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//li[@class='dropdown-toggle']")).getText(), "Third Option");
	}

	@Test
	public void TC_06_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectEditableDropdownbyText("//input[@class='search']", "//div[@role='option']//span", "Afghanistan");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Afghanistan");

		selectEditableDropdownbyText("//input[@class='search']", "//div[@role='option']//span", "Albania");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Albania");

		selectEditableDropdownbyText("//input[@class='search']", "//div[@role='option']//span", "Belgium");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Belgium");

	}

	@Test
	public void TC_07_Editable() {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-search-selection/");
		selectEditableDropdownbyText("//input[@class='search']", "Afghanistan");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Afghanistan");

		selectEditableDropdownbyText("//input[@class='search']", "Albania");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Albania");

		selectEditableDropdownbyText("//input[@class='search']", "Belgium");
		sleepInSeconds(1);
		Assert.assertEquals(driver.findElement(By.xpath("//div[@role='alert']")).getText(), "Belgium");

	}

	@Test
	public void TC_08_Multiple_Select() {		
		driver.get("https://multiple-select.wenzhixin.net.cn/templates/template.html?v=189&url=basic.html");
		
		selectMultipleDropdownbyText("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span", threeMonths);
		Assert.assertTrue(areItemSelected(threeMonths));
		
		sleepInSeconds(1);
		driver.navigate().refresh();
		selectMultipleDropdownbyText("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span", fourMonths);
		Assert.assertTrue(areItemSelected(fourMonths));
		
		sleepInSeconds(1);
		driver.navigate().refresh();
		selectMultipleDropdownbyText("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span", twelthMonths);
		Assert.assertTrue(areItemSelected(twelthMonths));
		
		sleepInSeconds(1);
		driver.navigate().refresh();
		selectMultipleDropdownbyText("(//button[@class='ms-choice'])[1]", "(//div[@class='ms-drop bottom'])[1]//li//span", wrongmonths);
		Assert.assertFalse(areItemSelected(wrongmonths));	

	}

	public void selectCustomDropdownbyText(String parentXpath, String allItemsXpath, String text) {
		// Hành vi của một dropdown
		// Click vào dropdown
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSeconds(1);

		// Chờ các item được hiển thị ra
		explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		// Tìm các item cần chọn -> Lấy ra text của item -> So sánh với text mình mong
		// muốn
		// + Item mình cần nó nằm trong tầm nhìn của User -> click
		// + Không nằm trong tầm nhìn thấy (viewport) -> Scroll xuống -> Click
		// Bấm vào
		// Thoát khỏi vòng lặp
		List<WebElement> allOptionitems = driver.findElements(By.xpath(allItemsXpath));
		for (WebElement item : allOptionitems) {
			if (item.getText().equals(text)) {
				item.click();
				sleepInSeconds(1);
				break;
			}
		}
		// Kiểm tra xem chọn đúng chưa
	}

	public void selectMultipleDropdownbyText(String parentXpath, String allItemsXpath, String[] months) {
		driver.findElement(By.xpath(parentXpath)).click();
		sleepInSeconds(1);		
		List<WebElement> allOptionitems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
		
		for (String month : months) {
			
			for (WebElement item : allOptionitems) {
				if (item.getText().equals(month)) {
					item.click();
					sleepInSeconds(1);
					break;
				}
			}
		}
	}

	public void selectEditableDropdownbyText(String parentXpath, String allItemsXpath, String text) {
		driver.findElement(By.xpath(parentXpath)).clear();
		driver.findElement(By.xpath(parentXpath)).sendKeys(text);
		sleepInSeconds(1);

		List<WebElement> allOptionitems = explicitWait
				.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));
		for (WebElement item : allOptionitems) {
			if (item.getText().equals(text)) {
				item.click();
				sleepInSeconds(1);
				break;
			}
		}
	}

	public void selectEditableDropdownbyText(String parentXpath, String text) {
		driver.findElement(By.xpath(parentXpath)).clear();
		driver.findElement(By.xpath(parentXpath)).sendKeys(text);
		sleepInSeconds(1);

		driver.findElement(By.xpath(parentXpath)).sendKeys(Keys.TAB);
	}

	public String getAngularSelectedText(String textCssLocator) {
		return (String) jsExecutor.executeScript("return document.querySelector(\"" + textCssLocator + "\").text", textCssLocator);
	}
	
	public boolean areItemSelected(String[] months) {
		List<WebElement> allselectedItems = driver.findElements(By.xpath("//li[@class='selected']//input"));
		int numberOfselectedItems = allselectedItems.size();
		
		String selectedItemsText = driver.findElement(By.xpath("(//div[@class='ms-parent multiple-select'])[1]//button//span")).getText();
		
		if(numberOfselectedItems >0 && numberOfselectedItems <=3) {
			boolean status = true;
			for (String month : months) {
				if(!selectedItemsText.contains(month)) {
					status = false;
					return status;
				}
			}
			return status;
		}else if(numberOfselectedItems >3 && numberOfselectedItems <12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']//span[text()='" + numberOfselectedItems+ " of 12 selected']")).isDisplayed();
		}else if (numberOfselectedItems >=12) {
			return driver.findElement(By.xpath("//button[@class='ms-choice']//span[text()='All selected']")).isDisplayed();
		}else {
			return false;
		}
			
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
	public void afterClass() {
		driver.quit();
	}

}
