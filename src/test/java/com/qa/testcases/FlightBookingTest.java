package com.qa.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.base.TestBase;
import com.qa.pages.FlightBookingPage;
import com.qa.pages.FlightSelectPage;
import com.qa.pages.PassengerDetailsPage;
import com.qa.util.TestUtil;

public class FlightBookingTest extends TestBase{

	TestUtil testUtil;
	FlightBookingPage flightBooking;	
	FlightSelectPage flightSelect;
	PassengerDetailsPage passDetails;
	String sheetName = "flightBookingData";

	public FlightBookingTest(){
		super();
	}


	@BeforeMethod
	public void setUp(){

		browserInitialization();
		testUtil = new TestUtil();
		flightBooking = new FlightBookingPage();
		flightBooking = PageFactory.initElements(driver, FlightBookingPage.class);

		flightSelect = new FlightSelectPage();
		flightSelect = PageFactory.initElements(driver, FlightSelectPage.class);
		
		passDetails = new PassengerDetailsPage();
		passDetails = PageFactory.initElements(driver, PassengerDetailsPage.class);
	}

	@Test(dataProvider="getData")
	public void testFlightBooking(String From, String To, String Mobile, String Email, String PassFirstName, String PassLastName, String PassFirstName1) throws InterruptedException {
		
		flightBooking.selectOneWay();
		flightBooking.enterFrom(From);
		flightBooking.enterTo(To);
		flightBooking.selectDepartureDate();
		flightBooking.selectPassengers();
		flightBooking.clcikSearchFlight();
		
		flightSelect.selectLeastPrice();
		flightSelect.clcikContinueButton();
		flightSelect.clickProceedAndFill(Mobile, Email);
		
		passDetails.fillingPassengerDetails(PassFirstName, PassLastName,PassFirstName1, PassLastName);
		
	}


	@DataProvider
	public Object[][] getData(){
		Object data[][] = TestUtil.getTestData(sheetName);
		return data;
	}

	@AfterMethod
	public void tearDown(){
		driver.quit();
	}


}
