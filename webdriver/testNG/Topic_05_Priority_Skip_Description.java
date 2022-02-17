package testNG;

import org.testng.annotations.Test;

public class Topic_05_Priority_Skip_Description {
	//we dont use Priority in real, we use alphabet order by naming testcase such as TC_01_...
	@Test(priority = 1)
	public void Add_New_Customer() {
	}

	@Test(enabled = false, description = "JIRA 323 - This testcase is about...")
	public void View_Customer() {
	}

	@Test(priority = 1)
	public void Edit_Customer() {
	}

	@Test(priority = 2)
	public void Remove_Customer() {
	}

}
