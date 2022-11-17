package ARC4Planit.PlanitTA.tests;


import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ARC4Planit.PlanitTA.pageObjects.ContactPage;
import ARC4Planit.PlanitTA.pageObjects.HomePage;
import ARC4Planit.PlanitTA.testComponents.BaseTest;

public class ContactPageTest extends BaseTest
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
	public void testCase1() throws IOException
	{
		//Go to contact page
		homePage.clickContact();
		//Click submit button 
		ContactPage contactPage = new ContactPage(this.driver);
		contactPage.clickSubmit();
		//Verify error messages
		String headerErrMesg = driver.findElement(By.xpath("//div[@class='alert alert-error ng-scope']")).getText();
		String expectedHeaderErrMesg = "We welcome your feedback - but we won't get it unless you complete the form correctly.";
		Assert.assertEquals(headerErrMesg, expectedHeaderErrMesg);
		
		String forenameErrMesg = driver.findElement(By.id("forename-err")).getText();
		String expectedForenameErrMesg = "Forename is required";
		Assert.assertEquals(forenameErrMesg, expectedForenameErrMesg);
		
		String emailErrMesg = driver.findElement(By.id("email-err")).getText();
		String expectedEmailErrMesg = "Email is required";
		Assert.assertEquals(emailErrMesg, expectedEmailErrMesg);
		
		String messageErrMesg = driver.findElement(By.id("message-err")).getText();
		String expectedMessageErrMesg = "Message is required";
		Assert.assertEquals(messageErrMesg, expectedMessageErrMesg);
		
		//Populate mandatory fields
		contactPage.mandatoryFields("John", "john.example@planit.net.au", "John has entered a message this time");
		
		//Validate errors are gone - taking screenshots after populating the fields
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "\\src\\test\\java\\ScreenShots\\ContactPageErrorsGone.png");
		FileUtils.copyFile(src, dest);
	}
	
	@Test(invocationCount = 5)
	public void testCase2() throws IOException
	{
		homePage.clickContact();
		ContactPage contactPage = new ContactPage(this.driver);
		contactPage.mandatoryFields("John", "john.example@planit.net.au", "John has entered a message this time");
		contactPage.clickSubmit();
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));
		String submitSuccess = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String [] subStr1 = submitSuccess.split(" ", 2);
		String [] subStr2 = subStr1[1].split(" ", 2);
		String concatSubStr = subStr1[0] + " " + subStr2[1];
		String expectedSubmitSuccess = "Thanks we appreciate your feedback.";
		Assert.assertEquals(concatSubStr, expectedSubmitSuccess);
		
	}
	
	
	@Test(dataProvider="getData")
	public void testCase2b(String forename, String email, String message)
	{
		homePage.clickContact();
		ContactPage contactPage = new ContactPage(this.driver);
		contactPage.mandatoryFields(forename, email, message);
		contactPage.clickSubmit();
		WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='alert alert-success']")));
		String submitSuccess = driver.findElement(By.xpath("//div[@class='alert alert-success']")).getText();
		String [] subStr1 = submitSuccess.split(" ", 2);
		String [] subStr2 = subStr1[1].split(" ", 2);
		String concatSubStr = subStr1[0] + " " + subStr2[1];
		String expectedSubmitSuccess = "Thanks we appreciate your feedback.";
		Assert.assertEquals(concatSubStr, expectedSubmitSuccess);
	}
	@DataProvider
	public Object[][] getData()
	{
		Object[][] data = new Object[5][3];
		
		data[0][0] = "John";
		data[0][1] = "john.example@planit.net.au";  
		data[0][2] = "John's Message";
		
		data[1][0] = "Jane";
		data[1][1] = "jane.example@planit.net.au";  
		data[1][2] = "Jane's Message";
		
		data[2][0] = "Jack";
		data[2][1] = "jack.example@planit.net.au";  
		data[2][2] = "Jack's Message";
		
		data[3][0] = "Jill";
		data[3][1] = "jill.example@planit.net.au";  
		data[3][2] = "Jill's Message";
		
		data[4][0] = "Jerry";
		data[4][1] = "jerry.example@planit.net.au";  
		data[4][2] = "Jerry's Message";	
		
		return data;
	}
	
}