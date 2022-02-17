package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Topic_07_Multiple_Parallel {
	WebDriver driver;
	By emailTextBox = By.xpath("//*[@id='email']");
	By passwordTextBox = By.xpath("//*[@id='pass']");
	By loginButton = By.xpath("//*[@id='send2']");
	
	@Parameters("browser")
	@BeforeClass
	//optional : chi khi khong tim thay browser nao trong xml file
	public void beforeClass(@Optional("firefox") String browserName) {
		if(browserName.equals("firefox")) {
			System.setProperty("webdriver.gecko.driver", ".\\browserDrivers\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browserName.equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browserName.equals("edge")) {
			System.setProperty("webdriver.edge.driver", ".\\browserDrivers\\\\msedgedriver.exe");
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("The browser name is not correct");
		}
		
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@Test(dataProvider = "user_pass")
	public void TC_Login(String username, String password) {
		driver.get("http://live.demoguru99.com/index.php/customer/account/login/");
		
		driver.findElement(emailTextBox).sendKeys(username);
		driver.findElement(passwordTextBox).sendKeys(password);
		driver.findElement(loginButton).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p")).getText().contains(username));
		driver.findElement(By.xpath("//header[@id='header']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[text()='Log Out']")).click();
	}

	@DataProvider(name = "user_pass")
	public Object[][] UserAndPasswordData(){
		return new Object[][] {
			{"selenium_11_01@gmail.com", "111111"}
		};
	}
	
	@AfterClass(alwaysRun = true)
	public void afterClass() {
		driver.quit();
	}

}
