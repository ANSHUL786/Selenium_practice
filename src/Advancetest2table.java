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



public class Advancetest2table {
	static WebDriver driver;
	static By priceColumn=By.xpath(".//following-sibling::td[1]");
	public static void main(String[] args) throws InterruptedException, IOException {
		
	//URL
	String url ="https://rahulshettyacademy.com/seleniumPractise/#/offers";
	String expectedProduct="Rice";
	
	//elements
	By productNameColumn=By.xpath("//tr/td[1]");
	By sortColumnName=By.xpath("//tr/th[@role='columnheader'][1]");
	By nextButton =By.cssSelector("a[aria-label='Next']");
	By firstButton=By.cssSelector("a[aria-label='First']");
	
	//System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
	driver=new ChromeDriver();
	
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
	driver.manage().window().maximize();
	driver.get(url);
	SoftAssert sa=new SoftAssert();
	
	List<WebElement> nameList=driver.findElements(productNameColumn);
	
	//Check the product is present
	Boolean isPresent = nameList.stream().anyMatch(s->s.getText().equalsIgnoreCase(expectedProduct));
	sa.assertTrue(isPresent);
	
	//Sort and check products are sorted
	driver.findElement(sortColumnName).click();
	List<WebElement> nameListAfterSort=driver.findElements(productNameColumn);
	List<String> namesFromList=nameListAfterSort.stream().map(s->s.getText()).collect(Collectors.toList());
	List<String> sortedNames=namesFromList.stream().sorted().collect(Collectors.toList());
	sa.assertEquals(namesFromList, sortedNames);

	//Get the price of Rice and check it is equal to 37
	driver.findElement(sortColumnName).click();
	List<WebElement> nameList1=driver.findElements(productNameColumn);
	List<String> priceOfProduct=nameList1.stream().filter(s->s.getText().equalsIgnoreCase(expectedProduct)).map(s->getPrice(s)).collect(Collectors.toList());
	sa.assertEquals(Integer.parseInt(priceOfProduct.get(0)), 37);
	
	//Sort and iterate the table pagination until product is found
	List<String> searchedList;
	List<WebElement> nameList2;
	driver.findElement(sortColumnName).click();
	
	do {
	nameList2=driver.findElements(productNameColumn);
	searchedList=nameList2.stream().filter(s->s.getText().equalsIgnoreCase(expectedProduct)).map(s->getPrice(s)).collect(Collectors.toList());
	
	if(searchedList.size()<1) {
		driver.findElement(nextButton).click();
	}
	}while(searchedList.size()<1);
	sa.assertEquals(Integer.parseInt(searchedList.get(0)), 37);
	
	
	//Iterate all the tables from FirstPage until last and Capture all products
	driver.findElement(firstButton).click();
	
	
	List<String> productList = new ArrayList<>();
	List<String> intermediateResult;
	Collection<WebElement> nameList3;
	/*
	 * while(driver.findElement(nextButton).getAttribute("aria-disabled").equals(
	 * "false")) { nameList3=driver.findElements(productNameColumn);
	 * intermediateResult=nameList3.stream().map(s->s.getText()).collect(Collectors.
	 * toList()); productList.addAll(intermediateResult);
	 * 
	 * driver.findElement(nextButton).click(); }
	 * nameList3=driver.findElements(productNameColumn);
	 * intermediateResult=nameList3.stream().map(s->s.getText()).collect(Collectors.
	 * toList()); productList.addAll(intermediateResult);
	 */
	
	while(true) {
		nameList3=driver.findElements(productNameColumn);
		intermediateResult=nameList3.stream().map(s->s.getText()).collect(Collectors.toList());
		productList.addAll(intermediateResult);
		
		System.out.println(productList);
		if(driver.findElement(nextButton).getAttribute("aria-disabled").equals("true"))
				break;
		driver.findElement(nextButton).click();
	}
	
	System.out.println(productList.size());
	
	
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
	
}
