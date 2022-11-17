package ARC4PlanitTA.reusableComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ReusableComponents 
{
	WebDriver driver;
	public ReusableComponents(WebDriver driver)
	{
		this.driver = driver;
	}
	public void elementsVisibleWait(By findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
}
