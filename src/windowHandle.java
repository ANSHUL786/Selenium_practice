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



public class windowHandle {
	static WebDriver driver;
	public static void main(String[] args) throws InterruptedException, IOException {
		
	//URL
	String url ="https://www.amazon.in/";
	
	//elements
	By signinoption = By.xpath("//a[@data-nav-ref='nav_ya_signin']");
	By signinButton=By.cssSelector(".nav-action-inner");
	By countrylinkFooter=By.xpath("(//div[@class='navFooterLine navFooterLinkLine navFooterPadItemLine'])[2]");
	By eachCountry=By.cssSelector(".nav_a");
	
	//System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
	driver=new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	driver.manage().window().maximize();
	driver.get(url);
	
	Actions a= new Actions(driver);
	
	a.moveToElement(driver.findElement(signinoption)).build().perform();
	driver.findElement(signinButton).click();
	driver.navigate().back();
	//Javascript executor
	JavascriptExecutor js=(JavascriptExecutor)driver;
	js.executeScript("window.scrollBy(0,6000)");
	
	//Soft assert
	SoftAssert as=new SoftAssert();
	
	
	//Screenshot
	takeScreenshot("Footer");
	WebElement footer=driver.findElement(countrylinkFooter);
	List<WebElement> element=footer.findElements(By.tagName("a"));
	
	int count=element.size();
	System.out.println(count);
	
	Assert.assertEquals(count, 17);
	
	for(int i=0;i<count;i++) {
		Thread.sleep(1000);
		String link=footer.findElements(eachCountry).get(i).getAttribute("href");
		footer.findElements(eachCountry).get(i).sendKeys(Keys.chord(Keys.CONTROL,Keys.ENTER));
		
		HttpURLConnection conn=(HttpURLConnection)new URL(link).openConnection();
		conn.setRequestMethod("GET");
		conn.connect();
		
		int code =conn.getResponseCode();
		System.out.println(code);
		as.assertTrue(code<400 || code>500,"Broken Link is Present "+link +" with status code="+code);
	}
	
	Set<String> handles=driver.getWindowHandles();
	
	Iterator<String> it=handles.iterator();
	while(it.hasNext()) {
		driver.switchTo().window(it.next());
		System.out.println(driver.getTitle());
	}
	
	as.assertAll();
	}
	
	public static void takeScreenshot(String title) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path=".\\actgo\\Eclipse\\SS\\"+title+System.currentTimeMillis()+".png";
		FileUtils.copyFile(src,new File(path));
		
	}
	
}
