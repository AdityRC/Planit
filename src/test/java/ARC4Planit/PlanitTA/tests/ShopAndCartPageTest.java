package ARC4Planit.PlanitTA.tests;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

//import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
//import org.openqa.selenium.OutputType;
//import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import ARC4Planit.PlanitTA.pageObjects.HomePage;
import ARC4Planit.PlanitTA.pageObjects.ShopPage;
import ARC4Planit.PlanitTA.reusableMethods.ReusableMethods;
import ARC4Planit.PlanitTA.testComponents.BaseTest;
import ARC4Planit.PlantTA.data.CartData;

public class ShopAndCartPageTest extends BaseTest
{
	HomePage homePage;
	
	
	@BeforeMethod
	public void openHomePage() throws IOException
	{
		homePage = launchWebApp();
	}
	@AfterMethod
	public void closeBrowser()
	{
		this.driver.close();
	}
	@Test
	public void testCase3() throws IOException
	{
		//Go to Shop page
		homePage.clickShop(); 
		//Add items to the cart
		HashMap<String, String>  toyAndQtty = CartData.getToyAndQtty();
		String [] toys = ReusableMethods.hashMapKeyToArray(toyAndQtty);//returns array of toys
					
		ShopPage shopPage = new ShopPage(this.driver); 
		shopPage.getToys();
		shopPage.addToCart(toys);
			
		List<WebElement> allToys = driver.findElements(By.xpath("//li[@class='product ng-scope']"));
		
		//Go to Cart page
		homePage.clickCart();
		List<WebElement> cartItems = driver.findElements(By.xpath("//tr[@class='cart-item ng-scope']"));
		List<String> toysList = Arrays.asList(toys);
		Object [] toysAndQttyArr =  ReusableMethods.hashMapToArray(toyAndQtty);
		float subtotal = 0;
		float sumSubtotal = 0;

		if(cartItems.size()>0)
		{
			int count = 0;
			for(int i=1; i<=allToys.size(); i++)
			{
			
				String toyName = driver.findElement(By.xpath("//tr[@class='cart-item ng-scope'][" + i + "]/td")).getText();
				
				//Split the Hashmap-Array into toy and qtty pair
				String str = toysAndQttyArr[i-1].toString();
				String [] splitStr = str.split("=", 2);
				//String toyToAdd= splitStr[0];
				String qttyToBuy = splitStr[1];
				if(toysList.contains(toyName))
				{
					float price = Float.parseFloat(driver.findElement(By.xpath("//tr[@class='cart-item ng-scope'][" + i+ "]/td[2]")).getText().replace("$", ""));
					
					driver.findElement(By.xpath("//tr[@class='cart-item ng-scope'][" + i + "]/td[3]/input")).clear();
					driver.findElement(By.xpath("//tr[@class='cart-item ng-scope'][" + i + "]/td[3]/input")).sendKeys(qttyToBuy);
					subtotal = Float.parseFloat(driver.findElement(By.xpath("//tr[@class='cart-item ng-scope'][" + i + "]/td[4]")).getText().replace("$", ""));
					sumSubtotal = sumSubtotal + subtotal;
					float expectedSubtotal = verifySubtotal(qttyToBuy, price);
					Assert.assertEquals(subtotal, ReusableMethods.roundFloat(expectedSubtotal));
				}
				count++;
				if(count == cartItems.size())
				{
					break;
				}
			}
			String cartSubTotal = driver.findElement(By.xpath("//table[@class='table table-striped cart-items']/tfoot/tr/td/strong")).getText();
			String [] subStr = cartSubTotal.split(":", 2);
			String cartValue = subStr[1].replace(" ", "");
			Assert.assertEquals(sumSubtotal, Float.parseFloat(cartValue));
		}
	}
	public float verifySubtotal(String quantity, float price)
	{
		float expectedSubtotal = Integer.parseInt(quantity)*price;
		return ReusableMethods.roundFloat(expectedSubtotal);
	}
}
