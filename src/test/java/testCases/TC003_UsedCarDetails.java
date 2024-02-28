package testCases;

import java.util.List;

import org.testng.annotations.Test;

import factory.CrossBrowsing;
import pageObjects.UsedCarPage;
import utilities.WriteExcelData;

public class TC003_UsedCarDetails extends CrossBrowsing {
	UsedCarPage usedcar;
	String filepath = System.getProperty("user.dir") + "\\src\\test\\resources\\testdata.xlsx";
	List<String> carModels;

	@Test(priority = 0)
	public void testUsedCars() throws InterruptedException {
		usedcar = new UsedCarPage(driver);
		usedcar.hoverUsedCars();
		usedcar.clickChennai();
		usedcar.scrollToPopularModel();
		usedcar.printPopularCarModels();
		usedcar.printAllPopularCarModelDetails();
	}

	@Test(priority = 1)
	public void testWritePopularCarModel() {
		carModels = usedcar.getPopularCarModels();
		WriteExcelData.writePopularCarModel(carModels, filepath);
	}

	@Test(priority = 2)
	public void testWriteAllPopularCarModelDetails() throws InterruptedException {
		List<List<List<String>>> allPopularCarModelDetails = usedcar.getAllPopularCarModelDetails();
		WriteExcelData.writeAllPopularCarModelDetails(allPopularCarModelDetails, carModels, filepath);
	}
}
