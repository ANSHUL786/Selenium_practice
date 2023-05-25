import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.openqa.selenium.support.ui.Select;

public class dropdown {

	public static void main(String[] args) throws InterruptedException {
		
	//URL
	String url ="https://rahulshettyacademy.com/dropdownsPractise/";
	
	//elements
	By oneWayButton=By.id("ctl00_mainContent_rbtnl_Trip_0");
	By roundTripButton=By.id("ctl00_mainContent_rbtnl_Trip_1");
	By specialAssistantLink=By.linkText("Special Assistance");
	By specialAssistantClose=By.id("SpecialAssistanceWindow");
	By fromDestination=By.name("ctl00_mainContent_ddl_originStation1_CTXT");
	By toDestination=By.name("ctl00_mainContent_ddl_destinationStation1_CTXT");
	By fromCity=By.xpath("//a[@value='BLR']");
	By toCity=By.cssSelector("div[id=ctl00_mainContent_ddl_destinationStation1_CTNR] a[value='DEL']");
	By passengerDropdown=By.id("divpaxinfo");
	By adultCountPlusButton=By.id("hrefIncAdt");
	By childCountPlusButton=By.id("hrefIncChd");
	By passengerDoneButton=By.id("btnclosepaxoption");
	By currencyDropdown=By.name("ctl00$mainContent$DropDownListCurrency");
	By depatureDate;
	By currentDate=By.cssSelector(".ui-state-default.ui-state-highlight");
	By returnDate=By.xpath("//div[@id='Div1' and @class='picker-second']");
	By familyCheckbox=By.cssSelector("#ctl00_mainContent_chk_friendsandfamily");
	By getAllCheckbox=By.xpath("//input[@type='checkbox']");
	By searchButton=By.name("ctl00$mainContent$btn_FindFlights");
	
	
	//System.setProperty("webdriver.chrome.driver","/Users/actgo/Eclipse/chromedriver.exe");
	WebDriver driver=new ChromeDriver();
	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	driver.manage().window().maximize();
	driver.get(url);
	
	//
	Thread.sleep(1000);
	driver.findElement(roundTripButton).click();
	//check return date is enabled
	//System.out.println(driver.findElement(returnDate).getAttribute("style"));
	if(driver.findElement(returnDate).getAttribute("style").contains("1")) {
		Assert.assertTrue(true);
	}
	else
		Assert.assertFalse(true);
	
	Thread.sleep(1000);

	driver.findElement(oneWayButton).click();
	//check return date is disabled
	//System.out.println(driver.findElement(returnDate).getAttribute("style"));
	
	if(driver.findElement(returnDate).getAttribute("style").contains("0.5")) {
		Assert.assertTrue(true);
	}
	else
		Assert.assertFalse(true);
	
	Thread.sleep(1000);

	//read the special instruction
	driver.findElement(specialAssistantLink).click();
	driver.findElement(specialAssistantClose).click();
	Thread.sleep(1000);

	//start searching the flight
	driver.findElement(fromDestination).click();
	driver.findElement(fromCity).click();
	Thread.sleep(1000);
	driver.findElement(toCity).click();
	Thread.sleep(1000);
	driver.findElement(currentDate).click();
	
	driver.findElement(passengerDropdown).click();
	//select 2 child and 4 adult
	int ad=0;
	int ch=0;
	
	while(ad<3) {
		driver.findElement(adultCountPlusButton).click();
		ad++;
	}
	while(ch<2) {
		driver.findElement(childCountPlusButton).click();
		ch++;
	}
	driver.findElement(passengerDoneButton).click();
	
	//static dropdown - to select currency
	Select currencyDown= new Select(driver.findElement(currencyDropdown));
	
	currencyDown.selectByValue("USD");
	Assert.assertEquals(currencyDown.getFirstSelectedOption().getText(),"USD");
	
	currencyDown.selectByValue("INR");
	Assert.assertEquals(currencyDown.getFirstSelectedOption().getText(),"INR");

	//get size checkbox options
	List<WebElement> allcheckbox = driver.findElements(getAllCheckbox);
	System.out.println(allcheckbox.size());
	for(WebElement cb : allcheckbox) {
		System.out.println(cb.getAttribute("name"));
	}
	
	driver.findElement(familyCheckbox).click();
	Assert.assertTrue(driver.findElement(familyCheckbox).isSelected());
	
	//click on search
	driver.findElement(searchButton).click();
	
	
	}
	
}
