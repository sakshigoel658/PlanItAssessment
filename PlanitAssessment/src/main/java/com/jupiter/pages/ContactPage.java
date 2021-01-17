package com.jupiter.pages;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.jupiter.base.TestBase;


public class ContactPage  extends TestBase {
	
	public ContactPage() throws IOException {
		PageFactory.initElements(driver, this);
	}
	
	public void clickSubmit() {
		driver.findElement(By.xpath("//a[@class='btn-contact btn btn-primary']")).click();
	}
	
	public String getSpanFieldText(String fieldName) {
		WebElement ForenameMessage=driver.findElement(By.xpath("//span[@id='"+fieldName+"']"));
		return ForenameMessage.getText();
	}
	
	public String getAlertSuccessMessage() {
		WebElement ForenameMessage=driver.findElement(By.xpath("//div[@class='alert alert-success']"));
		return ForenameMessage.getText();
	}

	public void setInputFieldValue(String fieldName, String fieldValue) {
		driver.findElement(By.xpath("//input[@id='"+fieldName+"']")).sendKeys(fieldValue);
	}
	
	public void setMessageFieldValue(String fieldName, String fieldValue) {
		driver.findElement(By.xpath("//textarea[@id='"+fieldName+"']")).sendKeys(fieldValue);
	}
	
	public void waitForProgressBarToFinish() {
		new WebDriverWait(driver,20).ignoring(StaleElementReferenceException.class).
		until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='alert alert-success']")));
		
	}
	
	public String getNotificationMessage() {
		WebElement ValidateMessage=driver.findElement(By.xpath("//div[@class='alert alert-info ng-scope']"));
		return ValidateMessage.getText();
	}

}
