package com.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.base.TestBase;
import io.qameta.allure.Step;

public class PassengerDetailsPage extends TestBase {
	
	@FindBy(xpath="//button[@type='submit']") WebElement next;	
	@FindBy(xpath="//div[@class='popup-content passenger-popup']") WebElement skipLoginPopup;
	@FindBy(xpath="//a[text()='Skip Login']") WebElement skipLogin;
	@FindBy(xpath="//input[@id='pax-title-00']") WebElement selectTitle1;
	@FindBy(xpath="//input[@id='pax-title-01']") WebElement selectTitle2;
	@FindBy(xpath="//div[@id='pax-accor-0']//input[@placeholder='First Name']") WebElement firstName1;
	@FindBy(xpath="//div[@id='pax-accor-1']//input[@placeholder='First Name']") WebElement firstName2;
	
	@FindBy(xpath="//div[@id='pax-accor-0']//input[@placeholder='Last Name']") WebElement lastName1;
	@FindBy(xpath="//div[@id='pax-accor-1']//input[@placeholder='Last Name']") WebElement lastName2;
	@FindBy(xpath="//button[@id='continue-button']") WebElement continueButton;	

	@FindBy(xpath="//h1") WebElement PageHeader;
	public PassengerDetailsPage(){
		PageFactory.initElements(driver, this);
	}
	
	@Step("Filling Passenger Details")
	public void fillingPassengerDetails(String fName1, String lName1,String fName2, String lName2) throws InterruptedException {
		
		try {
			if(skipLoginPopup.isDisplayed()) {
				skipLogin.click();
			}
		}catch(Exception e) {
			e.getMessage();
		}
		
		
		selectTitle1.click();
		firstName1.sendKeys(fName1);
		lastName1.sendKeys(lName1);
		Thread.sleep(1000);
		next.click();
		Thread.sleep(1000);
		selectTitle2.click();
		firstName2.sendKeys(fName2);
		lastName2.sendKeys(lName2);
		
		String paymentURL = null ;
		System.out.println(paymentURL);

		for(int i=0;i<5;++i) {
			Thread.sleep(1000);
			paymentURL = driver.getCurrentUrl();
			if(paymentURL.contains("Payment")){
				System.err.println(paymentURL+"     : Assignment Done!");
				break;
			}else {
				continueButton.click();
				Thread.sleep(500);
			}
		}
		
	}
	
}
