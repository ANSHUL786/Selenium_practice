import java.io.File;
import java.io.IOException;
import java.net.URL;
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



public class Barchart {
	static WebDriver driver;
	
	
	@Test
	public  void barChart() throws InterruptedException, IOException {
		
	//URL
	String url ="https://emicalculator.net/";
	
	//elements
	By eachBarxpath = By.xpath("(//*[local-name()='svg']//*[name()='g' and @class='highcharts-series-group'])[2]//*[name()='rect']");
	By tooltip = By.xpath("//*[local-name()='svg']//*[name()='g' and contains(@class,'label highcharts-tooltip high')]");
	
	
	//System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
	driver=new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	driver.manage().window().maximize();
	driver.get(url);
	
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,1180)");

	List<WebElement> allrect=driver.findElements(eachBarxpath);
	Actions a= new Actions(driver);
	
	for(WebElement ele : allrect) {
		a.moveToElement(ele).perform();
		Thread.sleep(100);
		String toolTipData=driver.findElement(tooltip).getText();
		System.out.println(toolTipData);
		
	}
	
	
	
	
	
	}
	
	public static void takeScreenshot(String title) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path=".\\SS\\"+title+System.currentTimeMillis()+".png";
		FileUtils.copyFile(src,new File(path));
		
	}
	
}
