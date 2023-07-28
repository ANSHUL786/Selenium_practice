import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Arrays;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
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



public class test4 {
	static WebDriver driver;
	static By priceColumn=By.xpath(".//following-sibling::td[1]");
	public static void main(String[] args) throws InterruptedException, IOException {
		
	//URL
	By desiredXpath=By.xpath("//ytd-thumbnail-overlay-time-status-renderer/span[@id='text']");
	
	WebDriver driver=new ChromeDriver();
	driver.get("https://www.youtube.com/");
	//driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	driver.manage().window().maximize();
	
	WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(10));
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(desiredXpath));
	
	
	String text=driver.findElement(desiredXpath).getText();
	System.out.println(text);
	
	
	
	
		
	}
	
	 public static String getPrice(WebElement s) {
		String price=s.findElement(priceColumn).getText();
		return price;
	}

	public static void takeScreenshot(String title) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path=".\\SS\\"+title+System.currentTimeMillis()+".png";
		FileUtils.copyFile(src,new File(path));
		
	}
	
	public static void waitTillElementVisible(By byLocator) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}
	public static void waitTillElementclickable(WebElement element) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(10));
		w.until(ExpectedConditions.elementToBeClickable(element));
		
	}
	
	public static void waitTillElementInVisible(WebElement element) {
		WebDriverWait w=new WebDriverWait(driver,Duration.ofSeconds(5));
		w.until(ExpectedConditions.invisibilityOf(element));
	}
}
