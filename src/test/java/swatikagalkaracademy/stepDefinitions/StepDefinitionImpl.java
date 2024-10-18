package swatikagalkaracademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import swatikagalkaracademy.TestComponents.BaseTest;
import swatikagalkaracademy.pageObjects.CartPage;
import swatikagalkaracademy.pageObjects.CheckoutPage;
import swatikagalkaracademy.pageObjects.ConfirmationPage;
import swatikagalkaracademy.pageObjects.LandingPage;
import swatikagalkaracademy.pageObjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest
{
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public ConfirmationPage confirmPage;

	@Given(": I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page() throws IOException 
	{
		landingPage=launchApplication();
	}
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String password)
	{
		productCatalogue=landingPage.loginApplication(username, password);
	}
	@When("^I added product (.+) to Cart$")
	public void I_added_product_to_cart(String productName) throws InterruptedException
	{
		List<WebElement> products=productCatalogue.getProductlist();
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_productname_and_submitorder(String productName)
	{
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage=cartPage.goToCheckoutPage();
		checkoutPage.SelectCountry("India");
		ConfirmationPage confirmPage=checkoutPage.submitOrder();
	}
	@Then("{string} message is displayed on Confirmation page.")
	public void message_is_displayed_on_confirmation_page(String string) 
	{
		String confirmMessage=confirmPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();	
	}
	@Given("I landed on Ecommerce Page")
	public void i_landed_on_ecommerce_page1() throws IOException 
	{    
		landingPage=launchApplication();
	}
	
	 @Then("{string} message is displayed")
	 public void something_message_is_displayed(String string)
	 {
		 Assert.assertEquals(string, landingPage.getErrorMessage());
		 driver.close();
	 }
}
