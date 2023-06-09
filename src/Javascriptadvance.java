import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

public class Javascriptadvance {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		String url="https://qa.flowace.in/login";
		String script;
		System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		
		JavascriptExecutor js=(JavascriptExecutor)driver;
		Thread.sleep(1000);
		String emailaddress="anshul@flowace.in"; 
		String password="anshul@df123";
		driver.findElement(By.name("email")).sendKeys(emailaddress);
		driver.findElement(By.name("password")).sendKeys(password);

		
		/*
		 * script="document.querySelector(\"[name='email']\").click();" ;
		 * js.executeScript(script); Thread.sleep(1000);
		 * 
		 * 
		 * Thread.sleep(1000);
		 * 
		 * script="document.querySelector(\"[name='email']\").value=\""+emailaddress+
		 * "\";" ; js.executeScript(script); Thread.sleep(1000);
		 * 
		 * 
		 * 
		 * script="document.getElementsByName(\"password\")[0].click();";
		 * js.executeScript(script); Thread.sleep(1000);
		 * 
		 * script="document.getElementsByName(\"password\")[0].value=\""+password+"\";";
		 * js.executeScript(script); Thread.sleep(1000);
		 */
		script="return document.querySelector('.sign-in').innerText;";
		String text=(String)js.executeScript(script);
		System.out.println(text);
		script="document.querySelector('.sign-in').click();";
		js.executeScript(script);
		
		
		
		
	}

}
