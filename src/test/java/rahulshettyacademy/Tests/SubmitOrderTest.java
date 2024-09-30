package rahulshettyacademy.Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;


import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;


import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageObjects.CartPage;
import rahulshettyacademy.pageObjects.CheckoutPage;
import rahulshettyacademy.pageObjects.ConfirmationPage;
import rahulshettyacademy.pageObjects.LandingPage;
import rahulshettyacademy.pageObjects.OrderPage;
import rahulshettyacademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest
{

//Hello
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input)throws IOException,InterruptedException
	{
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products=productCatalogue.getProductList();
		productCatalogue.addProductTocart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckOut();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmPage=checkoutPage.submitOrder();
		String confirmMessage=confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication("anshika@gmail.com", "Iamking@000");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDsiplay(productName));
	}
	
	//Method 1 : Using parameterization
	//@DataProvider
	//public Object[][] getData()
	//{
		//return new Object[][] {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},{"swati12@gmail.com","Swati@123","ADIDAS ORIGINAL"}};
	//}
		
	//Method 2 : Data Drive using hashmap
	
	//@DataProvider
		//public Object[][] getData()
		//{
			//HashMap<String,String> map=new HashMap<String,String>();
			//map.put("email","anshika@gmail.com");
			//map.put("password","Iamking@000");
			//map.put("product","ZARA COAT 3");
			
			//HashMap<String,String> map1=new HashMap<String,String>();
			//map1.put("email","swati12@gmail.com");
			//map1.put("password","Iamking@000");
			//map1.put("product","ZADIDAS ORIGINAL");
	
			//return new Object[][] {{map},{map1}};
		//}
	
	//Method 3 : Reading data from JSON File
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List <HashMap<String,String>> data = getJsonDataToMap
				(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
		
	}
	
}

