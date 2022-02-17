package api;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.base.Function;

public class Topic_17_WaitVII_FluentWait {
	WebDriver driver;
	WebDriverWait explicitWait;
	FluentWait<WebDriver> fluentDriver;
	FluentWait<WebElement> fluentElement;
	long timeout = 10;
	long interval = 100;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		fluentDriver = new FluentWait<WebDriver>(driver);
		driver.manage().window().maximize();
	}

	@Test
	public void TC_01_Fluent() {
		driver.get("https://automationfc.github.io/dynamic-loading/");
		getwaitedWebElement(By.xpath("//div[@id='start']/button")).click();
		
		waitforElementAndIsDisplay(By.xpath("//h4[text()='Hello World!']"));
	}

	@Test
	public void TC_02_Fluent_CounDown() {
		driver.get("https://automationfc.github.io/fluent-wait/");
		WebElement countdown = driver.findElement(By.id("javascript_countdown_time"));
		fluentElement = new FluentWait<WebElement>(countdown);
		fluentElement.withTimeout(Duration.ofMinutes(15))
					 .pollingEvery(Duration.ofMillis(interval))
					 .ignoring(NoSuchElementException.class)
					 .until(new Function<WebElement, Boolean>(){
						 public Boolean apply(WebElement element) {
							 boolean flag = element.getText().endsWith("00");
							 System.out.println("Time= " + element.getText());
							 return flag;
						 }
					 });
	}

	
	public WebElement getwaitedWebElement(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);
		
		wait.withTimeout(Duration.ofSeconds(timeout))
			.pollingEvery(Duration.ofMillis(interval))
			.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		return element;
	}
	
	public void waitForElementAndClick(By locator) {
		FluentWait<WebDriver> wait = new FluentWait<WebDriver>(driver);

		wait.withTimeout(Duration.ofSeconds(timeout))
			.pollingEvery(Duration.ofMillis(interval))
			.ignoring(NoSuchElementException.class);
		
		WebElement element = wait.until(new Function<WebDriver, WebElement>(){
			public WebElement apply(WebDriver driver) {
				return driver.findElement(locator);
			}
		});
		element.click();
	}
	
	public boolean waitforElementAndIsDisplay(By locator) {
		WebElement element = getwaitedWebElement(locator);
		FluentWait<WebElement> wait = new FluentWait<WebElement>(element)
							.withTimeout(Duration.ofSeconds(timeout))
							.pollingEvery(Duration.ofMillis(interval))
							.ignoring(NoSuchElementException.class);
		
		boolean isDisplayed = wait.until(new Function<WebElement, Boolean>(){
			public Boolean apply(WebElement element) {
				boolean flag = element.isDisplayed();
				return flag;
			}
		});
		return isDisplayed;				
	}
	

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
