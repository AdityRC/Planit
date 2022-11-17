package ARC4Planit.PlanitTA.testComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ARC4Planit.PlanitTA.pageObjects.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest
{
	public WebDriver driver;
	
	public WebDriver initializeDriver() throws IOException
	{
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\ARC4Planit\\PlanitTA\\resources\\GlobalData.properties");
		prop.load(fis);
		String browserName =prop.getProperty("browser");
		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		//else if for firefox/gecko and edge/edge drivers
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
		
		return driver;
	}
	public HomePage launchWebApp() throws IOException
	{
		driver = initializeDriver();
		HomePage homePage = new HomePage(driver);
		homePage.goToHomePage();
		return homePage;
	}
}
