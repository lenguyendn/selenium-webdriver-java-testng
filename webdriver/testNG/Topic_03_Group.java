package testNG;

import org.testng.annotations.Test;

public class Topic_03_Group {

	@Test(groups = { "user", "regression", "regression 1" })
	public void TC_01() {
	}

	@Test(groups = { "admin", "regression" })
	public void TC_02() {
	}

	@Test(groups = { "user", "regression" })
	public void TC_03() {
	}

	@Test(groups = { "smoke", "regression" })
	public void TC_04() {
	}

}
