package com.qa.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import com.qa.base.TestBase;
import io.qameta.allure.Step;

public class FlightSelectPage extends TestBase {
	
	/**
	 *  Web Elements capturing by page factory model
	 */
	
	@FindBy(xpath="//button[text()='Price']/i") WebElement filterPrice;	
	@FindBy(xpath="//label[contains(@class,'col price-details')]") List<WebElement> priceList;
	@FindBy(xpath="//button[@type='submit']//span[text()='Search Flight']") WebElement searchFlight;	
	@FindBy(xpath="//button[@id='continue-button']") WebElement continueButton;	
	@FindBy(xpath="//div[@class='popup-content terminal-popup']") WebElement terminalChangePop;	

	@FindBy(xpath="//button[text()='Proceed']") WebElement proceed;	
	@FindBy(xpath="//input[@name='mobileNum']") WebElement mobNumber;	
	@FindBy(xpath="//input[@name='emailId']") WebElement eMail;
	@FindBy(xpath="//button[@type='submit']") WebElement next;	
	
	/**
	 * Web Elemnts initlising in constructor
	 */
	
	public FlightSelectPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	/**
	 *  Selecting least price using the following methd
	 * @throws InterruptedException
	 */
	public void selectLeastPrice() throws InterruptedException {
		try {
			if(terminalChangePop.isDisplayed()) {
				proceed.click();
			}
		}catch(Exception e) {
			e.getMessage();
		}
		
		
		List<String> saverPrice = new ArrayList<String>();

		int i=0;
		for(WebElement price:priceList) {
			i++;
			if(i%2 == 0) {

			}else {
				saverPrice.add(price.getText().replace("₹ ", "").replace(",", ""));
			}

		}
		//System.err.println(saverPrice);
		List<Integer> listOfPrices = convertStringListToIntList(saverPrice, Integer::parseInt); 
		Integer min = listOfPrices.stream().mapToInt(v -> v).min().orElseThrow(NoSuchElementException::new);
		//System.out.println("Minimum value-"+min);
		String leastPrice = Integer.toString(min);
		leastPrice = leastPrice.substring(0, 1) + "," + leastPrice.substring(1, leastPrice.length());
		//System.out.println(leastPrice);
		driver.findElement(By.xpath("//div[@class='price']/span[contains(text(),'"+leastPrice+"')]")).click();
	}

	public static <T, U> List<U> convertStringListToIntList(List<T> listOfString, Function<T, U> function){ 
		return listOfString.stream().map(function).collect(Collectors.toList()); 
	} 
	
	@Step("Clicking Search Flight Button")
	public void clcikSearchFlight() {
		searchFlight.click();
	}

	@Step("Clicking Continue Button")
	public void clcikContinueButton() {
		continueButton.click();
	}
	
	@Step("Clicking Proceed Button")
	public void clickProceedAndFill(String mobile, String email) {
		if(terminalChangePop.isDisplayed()) {
			proceed.click();
			mobNumber.sendKeys(mobile);
			eMail.sendKeys(email);
			next.click();
		}
	}
	
}
