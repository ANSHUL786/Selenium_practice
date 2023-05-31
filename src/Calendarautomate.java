import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Iterator;
import java.util.*;
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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.asserts.SoftAssert;



public class Calendarautomate {
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {
		
	//URL
	String url ="https://www.spicejet.com/";
	
	//elements
	By departureDate=By.cssSelector("div[data-testid='departure-date-dropdown-label-test-id']");
	
	
	Map<String, Object> prefs = new HashMap<String, Object>();
    
	//add key and value to map as follow to switch off browser notification
	//Pass the argument 1 to allow and 2 to block
	prefs.put("profile.default_content_setting_values.notifications", 2);
	    
	//Create an instance of ChromeOptions 
	ChromeOptions options = new ChromeOptions();
	    
	// set ExperimentalOption - prefs 
	options.setExperimentalOption("prefs", prefs);//System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
	driver=new ChromeDriver(options);
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	driver.manage().window().maximize();
	driver.get(url);
	
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,50)");
	
	Actions a= new Actions(driver);
	a.moveToElement(driver.findElement(departureDate)).build().perform();
	driver.findElement(departureDate).click();
	js.executeScript("window.scrollBy(0,50)");
	
	
	//Screenshot
	takeScreenshot("Footer");
	
	}
	
	public static void takeScreenshot(String title) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path=".\\actgo\\Eclipse\\SS\\"+title+System.currentTimeMillis()+".png";
		FileUtils.copyFile(src,new File(path));
		
	}
	
}
