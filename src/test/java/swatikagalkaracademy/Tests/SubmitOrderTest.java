package swatikagalkaracademy.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import swatikagalkaracademy.TestComponents.BaseTest;
import swatikagalkaracademy.pageObjects.CartPage;
import swatikagalkaracademy.pageObjects.CheckoutPage;
import swatikagalkaracademy.pageObjects.ConfirmationPage;
import swatikagalkaracademy.pageObjects.LandingPage;
import swatikagalkaracademy.pageObjects.OrderPage;
import swatikagalkaracademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest
{
	String productName="ZARA COAT 3";
	@Test(dataProvider = "getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws InterruptedException, IOException
	{
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"),input.get("password"));	
		List<WebElement> products=productCatalogue.getProductlist();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckoutPage();
		checkoutPage.SelectCountry("India");
		ConfirmationPage confirmPage=checkoutPage.submitOrder();
		String confirmMessage=confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
	
	@Test(dependsOnMethods= {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue=landingPage.loginApplication("anshika@gmail.com","Iamking@000");	
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	//@DataProvider
	//@Test
	//public Object[][] getData()
	//{
		//return new Object[][] {
			//{"anshika@gmail.com","Iamking@000","ZARA COAT 3"},
			//{"rahulshetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
	//}
	
	//Driving test using hashmap
	
	//@DataProvider
	//@Test
	//public  Object[][] getData()
	//{
		//HashMap<String,String> map=new HashMap<String,String>();
		//map.put("email", "anshika@gmail.com");
		//map.put("password","Iamking@000");
		//map.put("product", "ZARA COAT 3");
		
		//HashMap<String,String> map1=new HashMap<String,String>();
		//map1.put("email", "rahulshetty@gmail.com");
		//map1.put("password","Iamking@000");
		//map1.put("product", "ADIDAS ORIGINAL");
		
		//return new Object[][] {{map},{map1}};
		
	//}
	
	//Driving data from JSON
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir")+
				"//src//test//java//swatikagalkaracademy//data//PurschaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	

}
