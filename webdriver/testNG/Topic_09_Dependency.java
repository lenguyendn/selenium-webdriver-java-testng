package testNG;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(testNG.TestNGListener.class)
public class Topic_09_Dependency {
	
	@Test
	public void TC_01_Add_New_Customer() {
		Assert.assertTrue(false);
	}

	@Test(dependsOnMethods = "TC_01_Add_New_Customer")
	public void TC_02_View_Customer() {
	}

	@Test(dependsOnMethods = "TC_02_View_Customer")
	public void TC_03_Edit_Customer() {
	}

	@Test(dependsOnMethods = "TC_03_Edit_Customer")
	public void TC_04_Remove_Customer() {
	}

}
