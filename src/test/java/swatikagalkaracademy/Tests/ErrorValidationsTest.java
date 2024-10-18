package swatikagalkaracademy.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import swatikagalkaracademy.TestComponents.BaseTest;
import swatikagalkaracademy.pageObjects.CartPage;
import swatikagalkaracademy.pageObjects.CheckoutPage;
import swatikagalkaracademy.pageObjects.ConfirmationPage;
import swatikagalkaracademy.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest
{
	@Test(groups= {"ErrorHandling"})
	public void LoginErrorValidation()
	{
		String productName="ZARA COAT 3";
		landingPage.loginApplication("anshika@gmail.com","Iamkig@000");	
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
	}
	
	@Test
	public void ProductErrorValidations() throws InterruptedException, IOException
	{
		String productName="ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("rahulshetty@gmail.com","Iamking@000");	
		List<WebElement> products=productCatalogue.getProductlist();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage=productCatalogue.goToCartPage();
		
		Boolean match=cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}
}
