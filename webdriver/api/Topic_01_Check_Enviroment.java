package api;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterClass;

public class Topic_01_Check_Enviroment {
  WebDriver driver;
	
  @BeforeClass
  public void beforeClass() {
	  // mo trinh duyet
	  driver = new FirefoxDriver();
	  
	  // mo app
	  driver.get("https://google.com");
  }
  
  @Test
  public void TC_01() {
  }
  
  @Test
  public void TC_02() {
  }
  

  @AfterClass
  public void afterClass() {
	  driver.quit();
  }

}
