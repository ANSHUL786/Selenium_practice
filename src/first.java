import java.time.Duration;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class first {

	public static void main(String[] args) throws InterruptedException {
		
	//URL
	String url ="https://rahulshettyacademy.com/locatorspractice/";
	String userloginname="anshul";
	//Elements
	By username=By.id("inputUsername");
	By password=By.cssSelector("input[name='inputPassword']");
	By signin=By.className("signInBtn");
	By forgotText=By.cssSelector("p.error");
	By forgotButton =By.linkText("Forgot your password?");
	By name=By.xpath("//input[@placeholder='Name']");
	By email=By.xpath("//input[@placeholder='Email']");
	By phonenumber= By.xpath("//form/input[@placeholder='Phone Number']");
	By resetButton=By.className("reset-pwd-btn");
	By gotologinButton=By.className("go-to-login-btn");
	
	By getmyName=By.xpath("//div[@class='login-container']/h2");
	By infomsg=By.cssSelector("p.infoMsg");
	
	//System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.manage().window().maximize();
	driver.get(url);
	
	//Try to login with incorrect url and forgot password
	
	driver.findElement(username).sendKeys(userloginname);
	driver.findElement(password).sendKeys("adsfdgf");
	driver.findElement(signin).click();
	String actualForgetText=driver.findElement(forgotText).getText();
	Assert.assertEquals(actualForgetText,"* Incorrect username or password");
	
	driver.findElement(forgotButton).click();
	Thread.sleep(2000);
	
	driver.findElement(name).sendKeys(userloginname);
	driver.findElement(email).sendKeys("xyz@gmail.com");
	driver.findElement(phonenumber).sendKeys("8978787878");
	
	driver.findElement(resetButton).click();
	
	String loginpassword=getPassword(driver,infomsg);
	
	driver.findElement(gotologinButton).click();
	
	Thread.sleep(2000);
	driver.findElement(username).sendKeys(userloginname);
	driver.findElement(password).sendKeys(loginpassword);
	driver.findElement(signin).click();
	
	String actualName=driver.findElement(getmyName).getText();
	
	Assert.assertEquals(actualName, "Hello "+userloginname+",");
	}

	
	public static String getPassword(WebDriver driver, By infomsg) {
		
		String passinfo= driver.findElement(infomsg).getText();
		String password=passinfo.split("'")[1].split("'")[0];
		return password;
	}
}
