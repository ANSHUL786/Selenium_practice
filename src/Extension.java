import java.awt.GraphicsConfiguration;
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
import java.lang.ProcessBuilder;
import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.OutputType;

import org.openqa.selenium.support.ui.WebDriverWait;


public class Extension {
	static WebDriver driver;
	static ExtentReports extent;
    static ExtentTest test;
    static ProcessBuilder processBuilder ;
    
    
	public static void main(String[] args) throws Exception {
		 String reportPath = "./src/report.html";
		 
	        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(reportPath);
	        extent = new ExtentReports();
	        extent.setSystemInfo("OS", "Windows");
	        extent.setSystemInfo("Browser", "Chrome");
	        extent.setSystemInfo("Tester", "John Doe");
	        htmlReporter.config().setDocumentTitle("Test Report");
	        htmlReporter.config().setReportName("Test Automation Results");
	        extent.attachReporter(htmlReporter);
	        test = extent.createTest("Test Name", "Test Description");
	        
	//URL
	String url ="chrome-extension://mkkjcacbbhdgdmbghfdpgkodcooechnl/popup.html";
	
	//elements
	By workspace = By.id("workspace");
	By email=By.id("email");
	
	//System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
	ChromeOptions option=new ChromeOptions();
	option.addExtensions(new File("C:\\Users\\actgo\\Eclipse\\Flowace-Asana.crx"));
	driver=new ChromeDriver(option);
	
	 takeScreenshot();

	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	driver.manage().window().maximize();
	driver.get(url);
	
	
	driver.findElement(workspace).sendKeys("axy");
	Thread.sleep(1000);
	takeScreenshot("before Copy");
	
	driver.findElement(workspace).sendKeys(Keys.chord(Keys.CONTROL,"A"));
	Thread.sleep(1000);
	driver.findElement(workspace).sendKeys(Keys.chord(Keys.CONTROL,"C"));
	Thread.sleep(1000);
	
	driver.findElement(email).sendKeys(Keys.chord(Keys.CONTROL,"V"));
	takeScreenshot("After Copy");

	Thread.sleep(2000);

    extent.flush();
	
	
	}
	
	  public static void startRecording() throws IOException {
	        processBuilder = new ProcessBuilder("ffmpeg", "-f", "x11grab", "-s", "1920x1080", "-i", ":0.0", "-r", "30", ".//path/to/video.mp4");
	        processBuilder.start();
	    }

	    public static void stopRecording() throws IOException {
	         processBuilder = new ProcessBuilder("pkill", "ffmpeg");
	        processBuilder.start();
	    }

	public static void takeScreenshot() throws IOException {
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        test.pass("Test Step Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotFile.getAbsolutePath()).build());
    }

    private static void attachVideoToReport() throws IOException {
        File videoFile = new File("path/to/video.mp4");
        test.info("Test Video", MediaEntityBuilder.createScreenCaptureFromPath(videoFile.getAbsolutePath()).build());
    }
	public static void takeScreenshot(String title) throws IOException {
		File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String path="C:\\Users\\actgo\\Eclipse\\SS\\"+title+System.currentTimeMillis()+".png";
		FileUtils.copyFile(src,new File(path));
		
	}
	
}
