package subhankar.SeleniumFrameworkDesign.tests;

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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import subhankar.SeleniumFrameworkDesign.TestComponents.BaseTest;
import subhankar.SeleniumFrameworkDesign.pageobjects.CartPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.LandingPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.OrderPage;
import subhankar.SeleniumFrameworkDesign.pageobjects.ProductCatalog;

public class SubmitOrderTest extends BaseTest{
	
	String productName = "ZARA COAT 3";

	@Test(dataProvider="getData",groups="Purchase")
	public void submitOrderTest(HashMap<String,String> input) throws IOException {

		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password"));

		//List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		List<WebElement> products = productCatalog.getProductList();

		//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		productCatalog.addProductToCart(input.get("productName"));
		
		//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		CartPage cartPage = productCatalog.goToCartPage();
		
		boolean match = cartPage.verifyProductDisplay(input.get("productName"));
		Assert.assertTrue(match);
		
		//driver.findElement(By.cssSelector(".totalRow button")).click();
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");

		//driver.findElement(By.cssSelector(".action__submit")).click();
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}
	
	@Test(dependsOnMethods= {"submitOrderTest"})
	public void orderHistoryTest()
	{
		ProductCatalog productCatalog = landingPage.loginApplication("rahuldash@gkmail.com", "Rahul@123");
		OrderPage orderPage = productCatalog.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "rahuldash@gkmail.com");
//		map.put("password", "Rahul@123");
//		map.put("productName", "ZARA COAT 3");
//		
//		HashMap<String,String> map1 = new HashMap<String,String>();
//		map1.put("email", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "ADIDAS ORIGINAL");
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "//src//test//java//subhankar//SeleniumFrameworkDesign//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
