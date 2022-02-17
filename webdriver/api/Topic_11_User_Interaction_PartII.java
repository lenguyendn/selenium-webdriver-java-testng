package api;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_11_User_Interaction_PartII {
	WebDriver driver;
	Actions action;
	JavascriptExecutor jsExecuter;
	String javascriptPath, jqueryPath;

	@BeforeClass
	public void beforeClass() {
		System.setProperty("webdriver.chrome.driver", ".\\browserDrivers\\chromedriver.exe");
		driver = new ChromeDriver();
		action = new Actions(driver);
		jsExecuter = (JavascriptExecutor) driver;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		String projectPath = System.getProperty("user.dir");
		javascriptPath = projectPath + "\\DragAndDrop\\drag_and_drop_helper.js";
		jqueryPath = projectPath + "\\DragAndDrop\\jquery_load_helper.js";

	}

	@Test
	public void TC_01_Right_Click() {
		driver.get("http://swisnl.github.io/jQuery-contextMenu/demo.html");
		// right click
		action.contextClick(driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']")))
				.perform();

		// Verify Quick display before hover
		Assert.assertTrue(driver.findElement(By.cssSelector(".context-menu-icon-quit")).isDisplayed());

		// hover on Quick
		action.moveToElement(driver.findElement(By.cssSelector(".context-menu-icon-quit"))).perform();
		sleepInSeconds(1);

		// verify Quick has (visible +hover)
		Assert.assertTrue(
				driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover"))
						.isDisplayed());

		// Click on Quick
		action.click(
				driver.findElement(By.cssSelector(".context-menu-icon-quit.context-menu-visible.context-menu-hover")))
				.perform();
		sleepInSeconds(1);

		// Verify clicked quit
		Assert.assertEquals(driver.switchTo().alert().getText(), "clicked: quit");

		// accept alert
		driver.switchTo().alert().accept();

		// Verify there no quit menu
		Assert.assertFalse(driver.findElement(By.cssSelector(".context-menu-icon-quit")).isDisplayed());
	}

	@Test
	public void TC_02_Drag_And_Drop_HTML4() {
		driver.get("https://automationfc.github.io/kendo-drag-drop/");
		sleepInSeconds(1);
		WebElement sourceCircle = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement targetCircle = driver.findElement(By.xpath("//div[@id='droptarget']"));
		action.dragAndDrop(sourceCircle, targetCircle).perform();
		sleepInSeconds(2);

		// verify change text and colour
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droptarget']")).getText(), "You did great!");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='droptarget']")).getCssValue("background-color"),
				convertColorHexToGrba("#03a9f4"));
	}

	@Test
	public void TC_03_Drag_And_Drop_HTML5_Css() throws IOException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		sleepInSeconds(1);

		String sourceCss = "#column-a";
		String targetCss = "#column-b";

		String java_script = readFileContent(javascriptPath);

		// Khong can vi page dang test da co jquery
		// Inject Jquery lib to site
		// String jqueryscript = readFileContent(jqueryPath);
		// javascriptExecutor.executeScript(jqueryscript);

		// A to B
		java_script = java_script + "$(\"" + sourceCss + "\").simulateDragDrop({ dropTarget: \"" + targetCss + "\"});";
		jsExecuter.executeScript(java_script);
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

		// B to A
		jsExecuter.executeScript(java_script);
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());
	}

	@Test
	public void TC_04_Drag_And_Drop_HTML5_xpath() throws AWTException {
		driver.get("https://automationfc.github.io/drag-drop-html5/");
		sleepInSeconds(1);

		String sourceXpath = "//div[@id='column-a']";
		String targetXpath = "//div[@id='column-b']";

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-a']/header[text()='B']")).isDisplayed());

		drag_the_and_drop_html5_by_xpath(sourceXpath, targetXpath);
		sleepInSeconds(3);
		Assert.assertTrue(driver.findElement(By.xpath("//div[@id='column-b']/header[text()='B']")).isDisplayed());

		// chi pass khi screen scale 100%

	}

	public String convertColorGrbaToHex(String grba) {
		return Color.fromString(grba).asHex();
	}

	public String convertColorHexToGrba(String hex) {
		return Color.fromString(hex).asRgba();
	}

	public void sleepInSeconds(long timeout) {
		try {
			Thread.sleep(timeout * 1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String readFileContent(String file) throws IOException {
		Charset cs = Charset.forName("UTF-8");
		FileInputStream stream = new FileInputStream(file);
		try {
			Reader reader = new BufferedReader(new InputStreamReader(stream, cs));
			StringBuilder builder = new StringBuilder();
			char[] buffer = new char[8192];
			int read;
			while ((read = reader.read(buffer, 0, buffer.length)) > 0) {
				builder.append(buffer, 0, read);
			}
			return builder.toString();
		} finally {
			stream.close();
		}
	}

	public void drag_the_and_drop_html5_by_xpath(String sourceLocator, String targetLocator) throws AWTException {

		WebElement source = driver.findElement(By.xpath(sourceLocator));
		WebElement target = driver.findElement(By.xpath(targetLocator));

		// Setup robot
		Robot robot = new Robot();
		robot.setAutoDelay(500);

		// Get size of elements
		Dimension sourceSize = source.getSize();
		Dimension targetSize = target.getSize();

		// Get center distance
		int xCentreSource = sourceSize.width / 2;
		int yCentreSource = sourceSize.height / 2;
		int xCentreTarget = targetSize.width / 2;
		int yCentreTarget = targetSize.height / 2;

		Point sourceLocation = source.getLocation();
		Point targetLocation = target.getLocation();
		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Make Mouse coordinate center of element
		sourceLocation.x += 20 + xCentreSource;
		sourceLocation.y += 110 + yCentreSource;
		targetLocation.x += 20 + xCentreTarget;
		targetLocation.y += 110 + yCentreTarget;

		System.out.println(sourceLocation.toString());
		System.out.println(targetLocation.toString());

		// Move mouse to drag from location
		robot.mouseMove(sourceLocation.x, sourceLocation.y);

		// Click and drag
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mousePress(InputEvent.BUTTON1_MASK);
		robot.mouseMove(((sourceLocation.x - targetLocation.x) / 2) + targetLocation.x,
				((sourceLocation.y - targetLocation.y) / 2) + targetLocation.y);

		// Move to final position
		robot.mouseMove(targetLocation.x, targetLocation.y);

		// Drop
		robot.mouseRelease(InputEvent.BUTTON1_MASK);
	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

}
