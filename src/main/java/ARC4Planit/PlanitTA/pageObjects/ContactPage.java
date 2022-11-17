package ARC4Planit.PlanitTA.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import ARC4PlanitTA.reusableComponents.ReusableComponents;

public class ContactPage extends ReusableComponents
{
	WebDriver driver;
	
	public ContactPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="forename")
	WebElement forenameTextbox;
	@FindBy(id="surname")
	WebElement surnameTextbox;
	@FindBy(id="email")
	WebElement emailTextbox;
	@FindBy(id="telephone")
	WebElement telephoneTextbox;
	@FindBy(id="message")
	WebElement messageTextbox; 
	@FindBy(xpath="//a[@class='btn-contact btn btn-primary']")
	WebElement submitButton;
	
	//WebElement action methods
	public void mandatoryFields(String forename, String email, String message)
	{
		forenameTextbox.sendKeys(forename);
		emailTextbox.sendKeys(email);
		messageTextbox.sendKeys(message);
	}
	public void optionalFields(String surname, String telephone)
	{
		surnameTextbox.sendKeys(surname);
		telephoneTextbox.sendKeys(telephone);
	}
	public void clickSubmit()
	{
		submitButton.click();
	}
	/*
	public void enterForename(String forename)
	{
		forenameTextbox.sendKeys(forename);
	}
	public void enterSurname(String surname)
	{
		surnameTextbox.sendKeys(surname);
	}
	public void enterEmail(String email)
	{
		emailTextbox.sendKeys(email);
	}
	public void enterTelephone(String telephone)
	{
		telephoneTextbox.sendKeys(telephone);
	}
	public void enterMessage(String message)
	{
		messageTextbox.sendKeys(message);
	}
	*/
}
