package subhankar.SeleniumFrameworkDesign.tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import subhankar.SeleniumFrameworkDesign.TestComponents.BaseTest;
import subhankar.SeleniumFrameworkDesign.TestComponents.Retry;
import subhankar.SeleniumFrameworkDesign.pageobjects.CartPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.LandingPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.ProductCatalog;

public class ErrorValidationsTest extends BaseTest{

	@Test(groups = {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException {

		String productName = "ZARA COAT 3";
		
		landingPage.loginApplication("rahuldash@gkmail.com", "Rahul123");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException{

		String productName = "ZARA COAT 3";
		ProductCatalog productCatalog = landingPage.loginApplication("rahuldash@gkmail.com", "Rahul@123");
		List<WebElement> products = productCatalog.getProductList();
		Thread.sleep(2000);
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		boolean match = cartPage.verifyProductDisplay("ZARA COAT 3");
		Assert.assertTrue(match);
		
	}

}
