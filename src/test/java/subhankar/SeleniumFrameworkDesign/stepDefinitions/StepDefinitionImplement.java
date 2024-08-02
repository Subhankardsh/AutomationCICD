package subhankar.SeleniumFrameworkDesign.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import subhankar.SeleniumFrameworkDesign.TestComponents.BaseTest;
import subhankar.SeleniumFrameworkDesign.pageobjects.CartPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.LandingPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.ProductCatalog;

public class StepDefinitionImplement extends BaseTest {
	
	public LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void i_landed_on_Ecommerce_page() throws IOException
	{
		landingPage = launchApplication();
	}
	
	@Given("^Login with the email (.+) and password (.+)$")
	public void login_email_and_password(String email,String password)
	{
		productCatalog = landingPage.loginApplication(email,password);
	}
	
	@When("^I add the product (.+) to cart$")
	public void i_add_product_to_cart(String productName)
	{
		List<WebElement> products = productCatalog.getProductList();
		productCatalog.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submiit the order$")
	public void checkout_submit_order(String productName)
	{
		CartPage cartPage = productCatalog.goToCartPage();
		boolean match = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		confirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page")
	public void message_displayed_confirmationPage(String string)
	{
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("^\"([^\"]*)\" message is displayed$")
	public void error_message_is_displayed(String string)
	{
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}

}
