import org.testng.Assert;
import org.testng.annotations.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Autoitexample {

	@Test
	public void fileUpload() throws InterruptedException, IOException {
	
		String downloadPath=System.getProperty("user.dir");
		ChromeOptions op=new ChromeOptions();
		HashMap<String,Object> map=new HashMap<String,Object>();
		map.put("profile.default_content_settings.popups",0);
		map.put("download.default_directory", downloadPath);
		op.setExperimentalOption("prefs", map);
		
		WebDriver driver=new ChromeDriver(op);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		
		driver.manage().window().maximize();
		driver.get("https://www.ilovepdf.com/pdf_to_jpg");
		
		driver.findElement(By.xpath("//a[@id='pickfiles']")).click();
		Thread.sleep(2000);
		
		Runtime.getRuntime().exec(downloadPath +"\\up.exe");
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='processTask']")));

		driver.findElement(By.xpath("//button[@id='processTask']")).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#pickfiles")));
		driver.findElement(By.cssSelector("#pickfiles")).click();
		
		File f=new File(downloadPath +"\\Anshul_LIC_Proof_page-0001.jpg");
		Thread.sleep(3000);
		
		Assert.assertTrue(f.exists());
		f.delete();
		
		
	}
}
