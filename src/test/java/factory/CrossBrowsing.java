package factory;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CrossBrowsing {
	protected WebDriver driver;
	protected Properties property;

	@BeforeClass()

	@Parameters({ "browser" })
	public WebDriver initializeBrowser(@Optional("Edge") String browser) throws IOException {

		try {
			if (getProperties().getProperty("execution_env").equalsIgnoreCase("local")) {

				switch (browser.toLowerCase()) {
				case "chrome":
					WebDriverManager.chromedriver().setup();
					driver = new ChromeDriver();
					break;
				case "edge":
					WebDriverManager.edgedriver().setup();
					driver = new EdgeDriver();
					break;
				default:
					System.out.println("No matching browser");
					driver = null;
				}
			}

			if (driver != null) {
				System.out.println("Starting the browser session..");
				driver.manage().deleteAllCookies();
				driver.get(property.getProperty("baseUrl"));
				driver.manage().window().maximize();
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
			}

		} catch (Exception e) {
			System.err.println("Error during browser initialization: " + e.getMessage());
			e.printStackTrace();
		}
		return driver;
	}

	@AfterClass
	public void closeBrowser() {
		if (driver != null) {
			System.out.println("Closing the browser session..");
			driver.quit();
		}
	}

	public Properties getProperties() throws IOException {
		String propertyFile = System.getProperty("user.dir") + "\\src\\test\\resources\\config.properties";
		try (FileReader file = new FileReader(propertyFile)) {
			property = new Properties();
			property.load(file);
		}
		return property;
	}
}
