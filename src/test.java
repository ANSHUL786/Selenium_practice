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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.testng.asserts.SoftAssert;



public class test {
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {
		
	//URL
	
	List<Integer> ls=Arrays.asList(1,2,3,4);
	
	int c=ls.stream().reduce(0,(sum,value)-> sum+value);
	System.out.println(c);
		
	}
	
	public static void takeScreenshot(String title) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path=".\\actgo\\Eclipse\\SS\\"+title+System.currentTimeMillis()+".png";
		FileUtils.copyFile(src,new File(path));
		
	}
	
}
