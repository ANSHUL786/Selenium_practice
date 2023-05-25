import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Addtocart {

	public static void main(String[] args) throws InterruptedException {
		
	//URL
	String url ="https://rahulshettyacademy.com/seleniumPractise/#/";
	//Data
	String[] productToOrder= {"Corn","Mango","Apple"};
	
	//elements
	By productElement=By.cssSelector(".product-name");
	By addTocardElement=By.xpath("//div[@class='product-action']/button");
	By cartIcon = By.cssSelector("img[alt='Cart']");
	By proceedToCheckOut=By.xpath("//button[text()='PROCEED TO CHECKOUT']");
	By promocode=By.className("promoCode");
	By promoApply=By.className("promoBtn");
	By promoInfo=By.className("promoInfo");
	
	//System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	driver.manage().window().maximize();
	driver.get(url);
	//for iterating over array size j variable then break
	int j=0;
	List<WebElement> productWebelement =driver.findElements(productElement);
    List<String> expectedProductNameList=Arrays.asList(productToOrder);
	
	for(int i=0;i<productWebelement.size();i++) {
	 
		String actualProductName=productWebelement.get(i).getText().split("-")[0].trim(); 
		System.out.println(actualProductName);
		
		if(expectedProductNameList.contains(actualProductName)) {
			WebElement z=driver.findElements(addTocardElement).get(i);
			z.click();
			j++;
			if(j==expectedProductNameList.size()) {
				break;
			}
		}
		
	}
	driver.findElement(cartIcon).click();
	driver.findElement(proceedToCheckOut).click();
	driver.findElement(promocode).sendKeys("rahulshettyacademy");
	driver.findElement(promoApply).click();
	
	WebDriverWait w=new WebDriverWait(driver, Duration.ofSeconds(5));
	w.until(ExpectedConditions.visibilityOfElementLocated(promoInfo));
	String info=driver.findElement(promoInfo).getText();
	System.out.println(info);
	}
	
}
