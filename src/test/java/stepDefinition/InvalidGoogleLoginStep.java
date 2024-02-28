package stepDefinition;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.InvalidGoogleLoginPage;

public class InvalidGoogleLoginStep {
	WebDriver driver;
	InvalidGoogleLoginPage login;

	@When("user clicks to signup button")
	public void user_clicks_to_signup_button() {
		// Write code here that turns the phrase above into concrete actions
		login = new InvalidGoogleLoginPage(BaseClass.getDriver());
		login.clickLoginButton();
	}

	@Then("user clicks to the google option")
	public void user_clicks_to_the_google_option() {
		// Write code here that turns the phrase above into concrete actions
		login.clickGoogleAccount();
	}

	@When("user enter invalid account details")
	public void user_enter_invalid_account_details() {
		// Write code here that turns the phrase above into concrete actions
		login.enterRandomEmail();
	}

	@Then("user captures error message")
	public void user_captures_error_message() {
		// Write code here that turns the phrase above into concrete actions
		login.printErrorMessage();
	}
}
