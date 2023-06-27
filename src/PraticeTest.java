import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

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


public class PraticeTest {
	
	
	@Test(dataProvider="getdata")
	public void login(String email,String pass) {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.get("https://ux-toolkit.uxarmy.com/login/");
		
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("password")).sendKeys(pass);
		driver.findElement(By.cssSelector("button[type='submit']")).click();
		
		driver.quit();
	}
	
	@DataProvider
	public Object getdata() {
		
		Object[][] obj=new Object[2][2];
		obj[0][0]="dgsfjhk@hjds.op";
		obj[0][1]="dgsfjhk@hjds.op";
		obj[1][0]="dgsfjhk@hjds.op";
		obj[1][1]="dgsfjhk@hjds.op";
		//return obj;
		
		return new Object[][]{{"dgsfjhk@hjds.op","dsfdghukjdf"},{"actgoyal99@gmail.com","Ansh@987"}};
		
	}

}
