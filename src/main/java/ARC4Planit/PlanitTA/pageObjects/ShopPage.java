package ARC4Planit.PlanitTA.pageObjects;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ARC4PlanitTA.reusableComponents.ReusableComponents;

public class ShopPage extends ReusableComponents
{
	WebDriver driver;
	
	public ShopPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//Product Names
	@FindBy(xpath="//h4[@class='product-title ng-binding']")
	List<WebElement> toys;
	//Product Prices
	@FindBy(xpath="//span[@class='product-price ng-binding']")
	WebElement itemsPrice;
	@FindBy(xpath="//div[@class='products ng-scope']/ul/li/div/p/a")
	WebElement buyButton;
	
	By toysBy = By.xpath("//h4[@class='product-title ng-binding']");
	
	public List<WebElement> getToys()
	{
		elementsVisibleWait(toysBy);
		return toys;
	}
	public void addToCart(String [] items)
	{
		for (int i=0; i<toys.size(); i++)
		{
			String toyName = toys.get(i).getText();
			int j = 0;
			List <String> itemsBuy = Arrays.asList(items);
			//Compare to match items: 
			if(itemsBuy.contains(toyName))
			{
				j++;
				driver.findElements(By.xpath("//div[@class='products ng-scope']/ul/li/div/p/a")).get(i).click();
				if(j == itemsBuy.size())
				{
					break;
				}
			}
		}
	}	
}
