package com.jupiter.pages;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.jupiter.base.TestBase;


public class ShopPage  extends TestBase {
	
	public ShopPage() throws IOException {
		PageFactory.initElements(driver, this);
	}

	public void addItemToCart(String itemName, int quantity) {
		for(int i=0;i<quantity;i++) {
			driver.findElement(By.xpath("//h4[contains(text(),'"+itemName+"')]/following-sibling::p//a[@class='btn btn-success'][contains(text(),'Buy')]")).click();	
		}	
	}
	
	public void clickOnCart() {
		driver.findElement(By.xpath("//a[@href='#/cart']")).click();
	}

	public Map<String,Integer> getItemsFromCart() {
		
		Map<String,Integer> itemsInCart= new HashMap<String,Integer>();
		WebElement cart=driver.findElement(By.xpath("//table[@class='table table-striped cart-items']//tbody"));		
		WebElement header=driver.findElement(By.xpath("//table[@class='table table-striped cart-items']//thead"));
		List<WebElement> headerItems=header.findElements(By.tagName("tr"));
		List<WebElement> headerItemDetails=headerItems.get(0).findElements(By.tagName("th"));		
		List<WebElement> cartItems=cart.findElements(By.tagName("tr"));
		 
		 for(WebElement cartItem : cartItems) {
			 String itemName = null;
			 int itemQuantity = 0;
			 List<WebElement> itemDetails=cartItem.findElements(By.tagName("td"));
			 for (int i=0;i<itemDetails.size();i++) {
				 WebElement detail = itemDetails.get(i);
				 	if(headerItemDetails.get(i).getText().equals("Item")){
				 		itemName = detail.getText();
				 	}
				 	if(headerItemDetails.get(i).getText().equals("Quantity")){
				 		WebElement quantity = itemDetails.get(i).findElement(By.name("quantity"));
				 		itemQuantity = Integer.parseInt(quantity.getAttribute("value"));
				 	}
			 }
			 itemsInCart.put(itemName, itemQuantity);
		 }
		 return itemsInCart;
	}
	
}
