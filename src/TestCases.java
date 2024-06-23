import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestCases {

	WebDriver driver=new ChromeDriver();
	String ExpectedTitle = "Products";

	
	@BeforeTest
	public void Setup() {
		driver.get("https://www.saucedemo.com/inventory.html\r\n");
		driver.manage().window().maximize();
		
	}
	
	@Test (priority=1)
	public void Login() {
		WebElement UserName= driver.findElement(By.id("user-name"));
		WebElement password=driver.findElement(By.id("password"));
		WebElement Login = driver.findElement(By.id("login-button"));
		
		UserName.sendKeys("standard_user");
		password.sendKeys("secret_sauce");
		Login.click();
		
	}
	
	@Test  (priority=2)
	public void WordProductIsThere() throws InterruptedException {
		Thread.sleep(2000);
		//String TitleText=driver.findElement(By.xpath("//span[@data-test='title']")).getText();
		WebElement MainName=driver.findElement(By.xpath("//span[@data-test='title']"));
		boolean ExpectedTitle=true;
		boolean TitleText=MainName.isDisplayed();
		Assert.assertEquals(TitleText, ExpectedTitle);
	}
	

	@Test  (priority=3)
	public void SortingItems() throws InterruptedException {
		
		Thread.sleep(1000);
		WebElement list=driver.findElement(By.className("product_sort_container"));
		 
		Select myselector =new Select(list);
		myselector.selectByVisibleText("Price (low to high)");


		List<WebElement> ThePrices= driver.findElements(By.className("inventory_item_price"));
		String highestPriceAsText=ThePrices.getLast().getText().replace("$","");
		String lowestPriceAsText=ThePrices.getFirst().getText().replace("$","");
		
		System.out.println(highestPriceAsText);
		System.out.println(lowestPriceAsText);
		
		double theHighestPrice=Double.parseDouble(highestPriceAsText);
		double theLowestPrice=Double.parseDouble(lowestPriceAsText);

		boolean expectedValue=true;
		Assert.assertEquals(theHighestPrice<theLowestPrice, expectedValue,"if the highest price is higher than the lowest this should return true");
	}
	
}
