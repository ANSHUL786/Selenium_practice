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



public class Advancetest3 {
	static WebDriver driver;
	static By priceColumn=By.xpath(".//following-sibling::td[1]");
	public static void main(String[] args) throws InterruptedException, IOException {
		
	//URL
	String url ="https://rahulshettyacademy.com/client";
	String expectedProduct="ADIDAS ORIGINAL";
	String useremail="test@anshul.mtp";
	String correctPassword="987654@Mtp";
	String country="French Polynesia";
	
	//elements
	By email=By.id("userEmail");
	By password=By.id("userPassword");
	By login=By.id("login");
	By incorrectToast=By.id("toast-container");
	By productsCard=By.cssSelector(".card-body");
	By productTextRelativeCss=By.cssSelector("b");
	By addToCartRelativeCss=By.cssSelector("button:last-of-type");
	By productAddedToast=By.cssSelector(".toast-bottom-right.toast-container");
	By loader=By.cssSelector(".ng-animating");
	By cartHeader=By.xpath("//*[contains(@routerlink,'cart')]");
	By cartItems=By.cssSelector(".cartSection h3");
	//By cartItemNameRelative=By.cssSelector(" h3");
	By checkout=By.xpath("//button[text()='Checkout']");
	By selectCountry=By.cssSelector("input[placeholder='Select Country']");
	By countryResultBox=By.cssSelector(".ta-results");
	By eachCountryFromResult=By.cssSelector(".list-group-item span");
	By placeOrder=By.xpath("//a[text()='Place Order ']");
	By thanksText=By.cssSelector(".hero-primary");
	By orderHeader=By.xpath("//*[contains(@routerlink,'order')]");
	By toastCss=By.cssSelector(".toast-container");
	By placedOrderList=By.xpath("//tr/td[2]");
	By orderViewButtonRelative=By.xpath("./following-sibling::td[3]/button");
	By thanksOrderMsg=By.cssSelector(".email-preheader");
	By viewOrderTitle=By.cssSelector(".title");
	
	//System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
	driver=new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	driver.manage().window().maximize();
	driver.get(url);
	SoftAssert sa=new SoftAssert();
	
	driver.findElement(email).sendKeys(useremail);
	driver.findElement(password).sendKeys(correctPassword);
	driver.findElement(login).click();
	JavascriptExecutor js=(JavascriptExecutor)driver;
	
	waitTillElementVisible(productsCard);
	
	List<WebElement> allProductsList=driver.findElements(productsCard);
	//Find the product which we want to order
	WebElement actualProductElement=allProductsList.stream().filter(p->p.findElement(productTextRelativeCss).getText().equalsIgnoreCase(expectedProduct)).findFirst().orElse(null);
	
	//add to cart
	actualProductElement.findElement(addToCartRelativeCss).click();
	
	waitTillElementInVisible(driver.findElement(loader));
	
	waitTillElementVisible(productAddedToast);
	String toast=driver.findElement(productAddedToast).getText();
	System.out.println(toast);
	waitTillElementInVisible(driver.findElement(loader));
	
	//go to cart
	driver.findElement(cartHeader).click();
	
	//verify Product is present
	waitTillElementVisible(cartItems);
	List<WebElement> cartItemsList=driver.findElements(cartItems);
	
	Boolean isPresent=cartItemsList.stream().anyMatch(s->s.getText().equalsIgnoreCase(expectedProduct));
	Assert.assertTrue(isPresent);
	//proceed To checkOut
	driver.findElement(checkout).click();
	
	//Select Country
	waitTillElementVisible(selectCountry);
	driver.findElement(selectCountry).sendKeys("pol");
	waitTillElementVisible(countryResultBox);
	List<WebElement> eachcountryfromdropdown=driver.findElements(eachCountryFromResult);
	
	WebElement element =eachcountryfromdropdown.stream().filter(s->s.getText().equalsIgnoreCase(country)).findFirst().orElse(null);
	element.click();
	
	//Proceed to order
	js.executeScript("window.scrollBy(0,15000)");
	Thread.sleep(2000);
	waitTillElementclickable(driver.findElement(placeOrder));
	driver.findElement(placeOrder).click();
	
	String textThanks=driver.findElement(thanksText).getText();
	
	Assert.assertEquals(textThanks, "THANKYOU FOR THE ORDER." );
	
	//Go to Orders
	
	//Actions actions = new Actions(driver);
	//js.executeScript("window.scrollBy(0,0)");
	//actions.moveToElement(driver.findElement(orderHeader)).click().build().perform();
	
	//orderWorkAroundwhen element is not clickable
	//js.executeScript("document.querySelector(\"[routerlink*='order']\").click();");
	js.executeScript("arguments[0].click();", driver.findElement(orderHeader));
	
	
	//get orders and verify order is present
	List<WebElement> orderList=driver.findElements(placedOrderList);
	WebElement foundOrderEle=orderList.stream().filter(s->s.getText().equalsIgnoreCase(expectedProduct)).findFirst().orElse(null);
	Assert.assertEquals(foundOrderEle.getText(), expectedProduct.toLowerCase());
	
	//View that order
	foundOrderEle.findElement(orderViewButtonRelative).click();
	Assert.assertEquals(driver.findElement(thanksOrderMsg).getText(),"Thank you for Shopping With Us");
	Assert.assertEquals(driver.findElement(viewOrderTitle).getText(),expectedProduct.toLowerCase());
	
	
	
	sa.assertAll();
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
