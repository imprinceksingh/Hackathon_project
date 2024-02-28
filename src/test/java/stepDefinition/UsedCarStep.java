package stepDefinition;

import java.util.List;

import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.UsedCarPage;
import utilities.WriteExcelData;

public class UsedCarStep {

	WebDriver driver;
	UsedCarPage car;
	String filePath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata.xlsx";
	List<String> carModels;

	@When("user hover on Used Cars")
	public void user_hover_on_used_cars() {
		// Write code here that turns the phrase above into concrete actions
		car = new UsedCarPage(BaseClass.getDriver());
		car.hoverUsedCars();
	}

	@Then("user clicks on Chennai")
	public void user_clicks_on_chennai() {
		// Write code here that turns the phrase above into concrete actions
		car.clickChennai();
	}

	@When("user scroll to Popular Models")
	public void user_scroll_to_popular_models() {
		// Write code here that turns the phrase above into concrete actions
		car.scrollToPopularModel();
	}

	@Then("user extracts all popular models name")
	public void user_extracts_all_popular_models_name() {
		// Write code here that turns the phrase above into concrete actions
		car.printPopularCarModels();
		carModels = car.getPopularCarModels();
		WriteExcelData.writePopularCarModel(carModels, filePath);
	}

	@Then("user extracts all popular model details")
	public void user_extracts_all_popular_model_details() throws InterruptedException {
		// Write code here that turns the phrase above into concrete actions
		car.printAllPopularCarModelDetails();
		List<List<List<String>>> allPopularCarModelDetails = car.getAllPopularCarModelDetails();
		WriteExcelData.writeAllPopularCarModelDetails(allPopularCarModelDetails, carModels, filePath);
	}
}
