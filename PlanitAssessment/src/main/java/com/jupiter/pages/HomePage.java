package com.jupiter.pages;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.jupiter.base.TestBase;

public class HomePage extends TestBase {

	@FindBy(xpath="//a[contains(text(),'Contact')]")
	WebElement contactLink;
	@FindBy(xpath="//a[contains(text(),'Shop')]")
	WebElement shopLink;
	
	public HomePage() throws IOException {
		super();
		PageFactory.initElements(driver, this);
	}
	
	public ContactPage clickOnContactsLink() throws IOException {
		contactLink.click();
		return new ContactPage();
	}
	
	public ShopPage clickOnShopLink() throws IOException {
		shopLink.click();
		return new ShopPage();
	}

}
