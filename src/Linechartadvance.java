import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.*;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.net.HttpURLConnection;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.*;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.asserts.SoftAssert;

import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Linechartadvance {
	static WebDriver driver;

	@Test
	public void multipleLineChart() throws InterruptedException, IOException {

		// URL
		String url = "https://www.covid19india.org/";

		// elements
		By dropdown = By.xpath("//div[@class='dropdown']/select");
		By confirmedCasesChart = By.xpath("(//*[local-name()='svg']/*[name()='path' and @class='trend-area'])[1]");
		By confirmedCaseTooltip = By.cssSelector(".stats.is-confirmed");

		// System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get(url);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("window.scrollBy(0,600)");
	
		WebDriverWait w = new WebDriverWait(driver, Duration.ofSeconds(10));
		w.until(ExpectedConditions.visibilityOfElementLocated(dropdown));
			
		Select s = new Select(driver.findElement(dropdown));
		s.selectByVisibleText("India");

		js.executeScript("arguments[0].scrollIntoView()", driver.findElement(confirmedCasesChart));
		WebElement ele = driver.findElement(confirmedCasesChart);
		Actions a = new Actions(driver);

		int xoffset = -(ele.getSize().getWidth()) / 2;
		int yoffset = -(ele.getSize().getHeight()) / 2;

		System.out.println(xoffset);
		System.out.println(yoffset);

		String startDate = "02-08-2022";
		String endDate = "31-20-2022";

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		// Parse the start and end dates
		LocalDate start = LocalDate.parse(startDate, formatter);
		LocalDate end = LocalDate.parse(endDate, formatter);

		// Calculate the difference in days
		long daysDifference = ChronoUnit.DAYS.between(start, end);
		System.out.println(daysDifference);

		for (int i = 0; i < daysDifference; i++) {
			a.moveToElement(ele, xoffset + i, yoffset).perform();
			String data = driver.findElement(confirmedCaseTooltip).getText();
			System.out.println(i + ">>>>" + data);
		}

	}

	public static void takeScreenshot(String title) throws IOException {
		File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		String path = ".\\SS\\" + title + System.currentTimeMillis() + ".png";
		FileUtils.copyFile(src, new File(path));

	}

}
