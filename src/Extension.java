import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

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
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Extension {
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {
		
	//URL
	String url ="chrome-extension://mkkjcacbbhdgdmbghfdpgkodcooechnl/popup.html";
	
	//elements
	By workspace = By.id("workspace");
	By email=By.id("email");
	
	//System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
	ChromeOptions option=new ChromeOptions();
	option.addExtensions(new File("C:\\Users\\actgo\\Eclipse\\Flowace-Asana.crx"));
	driver=new ChromeDriver(option);
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	driver.manage().window().maximize();
	driver.get(url);
	
	driver.findElement(workspace).sendKeys("axy");
	Thread.sleep(2000);
	takeScreenshot("before Copy");
	
	driver.findElement(workspace).sendKeys(Keys.chord(Keys.CONTROL,"A"));
	Thread.sleep(2000);
	driver.findElement(workspace).sendKeys(Keys.chord(Keys.CONTROL,"C"));
	Thread.sleep(2000);
	
	driver.findElement(email).sendKeys(Keys.chord(Keys.CONTROL,"V"));
	takeScreenshot("After Copy");

	Thread.sleep(2000);

	
	
	}
	
	public static void takeScreenshot(String title) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path="C:\\Users\\actgo\\Eclipse\\"+title+System.currentTimeMillis()+".png";
		FileUtils.copyFile(src,new File(path));
		
	}
	
}
