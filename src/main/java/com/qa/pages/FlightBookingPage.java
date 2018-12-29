package com.qa.pages;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.xml.crypto.dsig.Transform;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.qa.base.TestBase;
import com.qa.util.EmailValidator;
import com.qa.util.MobileValidator;

import io.qameta.allure.Step;

public class FlightBookingPage extends TestBase {
	
	@FindBy(xpath="//label[@for='oneWayTrip']") WebElement oneWay;	
	@FindBy(xpath="//input[@name='or-src']") WebElement source;	
	@FindBy(xpath="//input[@name='or-dest']") WebElement destination;	
	@FindBy(xpath="//td[@data-month='0']//a[text()='15']") WebElement departDate;	
	@FindBy(xpath="//input[@name='passenger']") WebElement noPassengers;	
	@FindBy(xpath="//li[@class='adult-pax-list']//button[@title='Up']") WebElement addPassenger;
	@FindBy(xpath="//button[@class='btn btn-primary pax-done']") WebElement Done;
	@FindBy(xpath="//button[@type='submit']//span[text()='Search Flight']") WebElement searchFlight;	
	@FindBy(xpath="//button[@id='continue-button']") WebElement continueButton;	
	

	public FlightBookingPage(){
		PageFactory.initElements(driver, this);
	}
	
	
	@Step("Entering From Location")
	public void enterFrom(String fromValue) throws InterruptedException {
		Thread.sleep(1000);
		source.click();
		source.sendKeys(fromValue);
		source.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}
	
	@Step("Entering To Location")
	public void enterTo(String toValue) throws InterruptedException {
		destination.click();
		destination.sendKeys(toValue);
		destination.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}
	
	@Step("Selecting Tripe Type")
	public void selectOneWay() {
		oneWay.click();
	}
	
	@Step("Selecting Departure Date")
	public void selectDepartureDate() {
		departDate.click();
	}
	
	@Step("Selecting Passengers")
	public void selectPassengers() {
//		JavascriptExecutor js = (JavascriptExecutor)driver;	
//		js.executeScript("arguments[0].value='"+noPass+"';", noPassengers);
//		return noPass;
		noPassengers.click();
		addPassenger.click();
		Done.click();
	}
	
	@Step("Clicking Search Flight Button")
	public void clcikSearchFlight() {
		searchFlight.click();
	}
	
}
