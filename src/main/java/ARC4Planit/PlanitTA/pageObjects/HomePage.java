package ARC4Planit.PlanitTA.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage 
{
	WebDriver driver;
	
	public HomePage(WebDriver driver)
	{
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//Using PageFactory
	@FindBy(xpath="//a[@href ='#/home']")
	WebElement home;
	@FindBy(xpath="//a[@href ='#/shop']")
	WebElement shop;
	@FindBy(xpath="//a[@href ='#/contact']")
	WebElement contact; 
	@FindBy(xpath="//a[@href ='#/cart']")
	WebElement cart;
	@FindBy(xpath="//li[@id='nav-login']")
	WebElement login;
	
	public void goToHomePage()
	{
		driver.get("https://jupiter.cloud.planittesting.com");
	}
	public void clickHome()
	{
		home.click();
	}
	public void clickShop()
	{
		shop.click();
	}
	
	public void clickContact()
	{
		contact.click();
	}
	
	public void clickCart()
	{
		cart.click();
	}
	public void clickLogin()
	{
		login.click();
	}	
}
