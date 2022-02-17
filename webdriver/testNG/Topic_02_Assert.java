package testNG;

import static org.testng.Assert.assertTrue;

import org.testng.Assert;
import org.testng.annotations.Test;

public class Topic_02_Assert {

	@Test
	public void TC_01() {
		boolean condition = true;
		// True
		// Class name > Method name
		condition = 3 < 5;
		Assert.assertTrue(condition);
		// static
		assertTrue(false);

		// False
		condition = 3 > 5;
		Assert.assertFalse(condition); // pass
		Assert.assertFalse(3 < 5); // fail

		// Equal
		Assert.assertEquals("Automation", "Auto"); // fail

	}
}
