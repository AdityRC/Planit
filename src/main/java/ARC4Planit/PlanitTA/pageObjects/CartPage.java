package ARC4Planit.PlanitTA.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import ARC4PlanitTA.reusableComponents.ReusableComponents;

public class CartPage extends ReusableComponents
{
	WebDriver driver;
	public CartPage(WebDriver driver) 
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	

}
