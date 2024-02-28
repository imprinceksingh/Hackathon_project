package pageObjects;

import java.io.File;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.BaseClass;

public class BasePage {
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void explicitWait(WebElement element) {
		Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(d -> element.isDisplayed());
	}

	public void hoverOnElement(WebElement element) {
		Actions action = new Actions(driver);
		action.moveToElement(element).build().perform();
	}

	public void scrollToElement(WebElement element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(60));
		wait.until(ExpectedConditions.visibilityOfAllElements(element));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", element);

		
		
	}

	public void scrollToElementcar() {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
		
		
	}

	public void captureFullPageScreenshot(WebDriver driver, String outputPath) {
		try {
			// Using JavascriptExecutor to capture a full-page screenshot
			JavascriptExecutor js = (JavascriptExecutor) driver;

			// Get the entire HTML content
			String fullPageHtml = (String) js.executeScript("return document.documentElement.outerHTML");

			// Save the HTML content to a file (optional, for reference)
			FileUtils.write(new File(outputPath + ".\\fullpage.html"), fullPageHtml, "UTF-8");

			// Using TakesScreenshot interface to capture the screenshot
			TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
			File screenshot = screenshotDriver.getScreenshotAs(OutputType.FILE);

			// Save the screenshot to a file
			FileUtils.copyFile(screenshot, new File(outputPath + ".\\fullpagescreenshot.png"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
