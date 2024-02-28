package pageObjects;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class BikeDetailsPage extends BasePage {

	public BikeDetailsPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\Screenshots";

	// Initialising LinkedHashMap to store bike details in ordered way
	LinkedHashMap<String, List<String>> bikeDetailsMap = new LinkedHashMap<>();

	// Web elements for different functionality
	@FindBy(xpath = "//a[contains(text(), \"New Bikes\")]")
	WebElement newbikes;

	@FindBy(xpath = "//ul[starts-with(@class, 'h-d-nav')]/li/a")
	List<WebElement> navheaderlist;

	@FindBy(xpath = "//span[contains(text(), \"Upcoming Bikes\")]")
	WebElement upcomingbike;

	@FindBy(xpath = "//select[@id='makeId']")
	WebElement selectmanufacturer;

	@FindBy(xpath = "//span[@data-track-label='view-more-models-button']")
	WebElement scroll;

	@FindBy(xpath = "//span[@class='zw-cmn-loadMore']")
	WebElement viewmore;

	// Storing list of web elements for bike name
	@FindBy(xpath = "//a[@data-track-label='model-name']")
	List<WebElement> bikenames;

	// Storing list of web elements for bike price
	@FindBy(xpath = "//a[@data-track-label='model-name']/following-sibling::div[1]")
	List<WebElement> bikeprices;

	// Storing list of web elements for bike launch date
	@FindBy(xpath = "//a[@data-track-label='model-name']/following-sibling::div[2]")
	List<WebElement> bikelaunchdate;

	public void hoverNewBikes() {
		explicitWait(newbikes);
		hoverOnElement(newbikes);
	}

	public void clickUpcomingBikes() {
		upcomingbike.click();
	}

	public void selectManufacturer() {
		Select select = new Select(selectmanufacturer);
		select.selectByVisibleText("Honda");
		captureFullPageScreenshot(driver, filepath);
	}

	public void clickToViewMore() throws IOException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		scrollToElement(scroll);
		explicitWait(viewmore);
		js.executeScript("arguments[0].click();", viewmore);
	}

	public LinkedHashMap<String, List<String>> getUpcomingBikeDetails() {
		for (int i = 0; i < bikenames.size(); i++) {
			String bikename = bikenames.get(i).getText();
			String bikeprice = bikeprices.get(i).getText();
			String launchdate = bikelaunchdate.get(i).getText();

			// Convert bikeprice to a numeric value for comparison
			String price = bikeprice.replaceAll("[^\\d.]", "").replaceFirst("\\.(?=.*\\.)", "");
			double priceValue = Double.parseDouble(price);

			// Check if the price is less than 4 lakhs
			if (priceValue < 4) {

				// Create a list to store details
				List<String> detailsList = new ArrayList<>();
				detailsList.add(bikename);
				detailsList.add(bikeprice);
				detailsList.add(launchdate);

				// Use 'Bikedetails' as the key and the list of details as the value
				String bikeDetailsKey = "BikeDetails" + (i + 1);
				bikeDetailsMap.put(bikeDetailsKey, detailsList);
			}
		}
		return bikeDetailsMap;
	}

	public void printUpcomingBikeDetails() {
		LinkedHashMap<String, List<String>> bikeDetails = getUpcomingBikeDetails();

		System.out.println("All upcoming bike details under 4 Lacks are displayed below :");
		int i = 1;
		for (String key : bikeDetails.keySet()) {
			List<String> detailsList = bikeDetails.get(key);
			System.out.println("\nBike Details" + (i) + ": " + detailsList);
			i++;
		}
	}

//	Functionality for Smoke Testing

	@FindBy(xpath = "//h1[contains(text(),'Upcoming Bikes in India')]")
	WebElement bikeheading;

	public void zigwheelspage() {
		String title = driver.getTitle();
		Assert.assertEquals(title, "New Cars & Bikes, Prices, News, Reviews, Buy & Sell Used Cars - ZigWheels.com");
		System.out.println("Zigwheels Page is Opened without any errors");
	}

	public void checkNewBikes() {
		boolean newbike = newbikes.isDisplayed();
		if (newbike) {
			System.out.println("New Bikes is displayed at header successfully..");
		} else {
			System.out.println("New Bikes at header is not visible..");
		}
	}

	public void checkUpcomingBikes() {
		explicitWait(upcomingbike);
		boolean newbike = upcomingbike.isDisplayed();
		if (newbike) {
			System.out.println("Upcoming Bikes is displayed in the list successfully..");
		} else {
			System.out.println("Upcoming Bikes is not visible..");
		}
	}

	public void validateHondaBikes() {
		explicitWait(bikeheading);
		boolean heading = bikeheading.isDisplayed();
		if (heading) {
			System.out.println("All upcoming Honda Bikes are displayed successfully..");
		} else {
			System.out.println("All upcoming Honda Bikes are not visible..");
		}
	}

}
