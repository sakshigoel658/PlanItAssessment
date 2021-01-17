package com.jupiter.testcases;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.jupiter.base.TestBase;
import com.jupiter.pages.ContactPage;
import com.jupiter.pages.HomePage;
import com.jupiter.pages.ShopPage;

public class JupiterToysTest extends TestBase {

	HomePage homePage;
	ContactPage contactPage;
	ShopPage shopPage;
	
	public JupiterToysTest() throws IOException {
		super();
	}
	
	@BeforeMethod
	public void setUp() throws IOException {
		initialization();
		homePage=new HomePage();
	}
	
	//Test case 1:
	@Test (priority = 1)
	public void verifyErrorOnSubmit() throws IOException {
		//	1.	From the home page go to contact page
		contactPage=homePage.clickOnContactsLink();		
		
		//	2.	Click submit button
		contactPage.clickSubmit();
		
		//	3.	Validate errors
		assertEquals("Forename is required",contactPage.getSpanFieldText("forename-err"));
		assertEquals("Email is required",contactPage.getSpanFieldText("email-err"));
		assertEquals("Message is required",contactPage.getSpanFieldText("message-err"));
		 
		//	4.	Populate mandatory fields
		contactPage.setInputFieldValue("forename","Automation");
		contactPage.setInputFieldValue("email","Automation@email.com");
		contactPage.setMessageFieldValue("message","Automation Testing");
		 
		//	5.	Validate errors are gone
		assertEquals("We welcome your feedback - tell it how it is.",contactPage.getNotificationMessage());
	}
	
	// Test case 2:
	@Test (priority = 2)
	public void verifySuccessOnSubmit() throws IOException, InterruptedException {
		
		// 1.	From the home page go to contact page		
		contactPage=homePage.clickOnContactsLink();
		
		// 2.	Populate mandatory fields
		String forename = "Automation";
		contactPage.setInputFieldValue("forename",forename);
		contactPage.setInputFieldValue("email","Automation@email.com");
		contactPage.setMessageFieldValue("message","Automation Testing");
		
		// 3.	Click submit button
		contactPage.clickSubmit();
		contactPage.waitForProgressBarToFinish();
		
		// 4.	Validate successful submission message
		assertEquals("Thanks "+forename+", we appreciate your feedback.",contactPage.getAlertSuccessMessage());
	}
	
	// Test Case 3
	@Test (priority = 3)
	public void verifyErrorForInvalidData() throws IOException, InterruptedException {
		// 1.	From the home page go to contact page
		contactPage=homePage.clickOnContactsLink();
		
		// 2.	Populate mandatory fields with invalid data
		contactPage.setInputFieldValue("forename","Automation");
		contactPage.setInputFieldValue("email","invalidemail");
		contactPage.setMessageFieldValue("message","Testing Invalid data");
		
		// 3.	Validate errors	
		assertEquals("Please enter a valid email",contactPage.getSpanFieldText("email-err"));
	}
	
	// Test Case 4
	@Test (priority = 4)
	public void verifyItemsInShoppingCart() throws IOException, InterruptedException {
		
		// 1.	From the home page go to shop page
		shopPage=homePage.clickOnShopLink();
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {}
		
		// 2.	Click buy button 2 times on “Funny Cow”
		shopPage.addItemToCart("Funny Cow", 2);
		 
		// 3.	Click buy button 1 time on “Fluffy Bunny”
		shopPage.addItemToCart("Fluffy Bunny", 1);
		 
		// 4.	Click the cart menu
		shopPage.clickOnCart();
		 
		// 5.	Verify the items are in the cart
		Map<String, Integer> expectedItems = new HashMap<String,Integer>();
		expectedItems.put("Funny Cow", 2);
		expectedItems.put("Fluffy Bunny", 1);
		Map<String, Integer> actualItems = shopPage.getItemsFromCart();
		assertEquals(actualItems, expectedItems);
	}
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
