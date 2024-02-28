package testCases;

import org.testng.annotations.Test;

import factory.CrossBrowsing;
import pageObjects.InvalidGoogleLoginPage;

public class TC001_InvalidGoogleLogin extends CrossBrowsing {
	InvalidGoogleLoginPage login;

	@Test
	public void testInvalidGoogleLogin() {
		login = new InvalidGoogleLoginPage(driver);
		login.clickLoginButton();
		login.clickGoogleAccount();
		login.enterRandomEmail();
		login.printErrorMessage();
	}

}
