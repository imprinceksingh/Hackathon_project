package testCases;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.testng.annotations.Test;

import factory.CrossBrowsing;
import pageObjects.BikeDetailsPage;
import utilities.WriteExcelData;

public class TC002_BikeDetails extends CrossBrowsing {
	BikeDetailsPage bike;
	String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata.xlsx";

	@Test(priority = 0)
	public void testBikeDetails() throws IOException {
		// Pass the WebDriver instance to the BikeDetailsPage constructor
		bike = new BikeDetailsPage(driver);
		bike.hoverNewBikes();
		bike.clickUpcomingBikes();
		bike.selectManufacturer();
		bike.clickToViewMore();
		bike.printUpcomingBikeDetails();
	}

	@Test(priority = 1)
	public void testWriteBikeDetails() {
		LinkedHashMap<String, List<String>> bikeDetailsMap = bike.getUpcomingBikeDetails();
		WriteExcelData.writeBikeDetails(bikeDetailsMap, filepath);
	}
}
