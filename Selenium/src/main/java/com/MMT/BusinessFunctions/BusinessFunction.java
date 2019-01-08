package com.MMT.BusinessFunctions;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.MMT.PageObjects.MMTFlightReservation;
import com.MMT.PageObjects.MMTFlightSelection;
import com.MMT.PageObjects.MMTHotelReservation;
import com.MMT.PageObjects.MMTLoginpage;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class BusinessFunction {
	
	public static WebDriver setup()
	{
		WebDriver driver;
		System.setProperty("webdriver.chrome.driver", "C:\\Java_Projects\\Selenium\\src\\test\\resources\\chromedriver.exe");
		driver=new ChromeDriver();
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		return driver;
	}
	
	public static void Login(WebDriver driver,String useremail,String password,ExtentTest test)
	{
		boolean statusloginpagedisplay;
		try
		{	
			MMTLoginpage pgMMTLoginPage=new MMTLoginpage(driver);
			statusloginpagedisplay=pgMMTLoginPage.checkMMTLogoPresence("MMTLogo",test);
			if(statusloginpagedisplay)
			{
				pgMMTLoginPage.ClickLoginlink("Login Link",test);
				pgMMTLoginPage.Enteremail(useremail,"Text Box User Id",test);
				pgMMTLoginPage.Enterpassword(password,"Text Box User Id",test);
				pgMMTLoginPage.ClickLoginButton("Login Button",test);
				if(pgMMTLoginPage.VerifyLogin("Hey User Menu",test))
				{
					System.out.println("Login to MMT Application is successful");
					test.log(LogStatus.PASS, "Login to MMT Application is successful");
				}
				else
				{
					System.out.println("Login to MMT Application has failed");
					test.log(LogStatus.FAIL, "Login to MMT Application has failed");
				}
			}
			
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
		}
	}
	
	public static void MakeFlightReservation(WebDriver driver,HashMap<String,String> hmtestdata,ExtentTest test)
	{
		String TotalFareEstimate="";
		MMTFlightReservation pgMMTFlightReservation=new MMTFlightReservation(driver);
		MMTFlightSelection pgMMTFlightSelection=new MMTFlightSelection(driver);
		try
		{
			pgMMTFlightReservation.EnterFromLocation("FlightFrom Text Box",hmtestdata,test);
			pgMMTFlightReservation.EnterToLocation("FlightDestination Text Box",hmtestdata,test);
			pgMMTFlightReservation.SelectFromToDateAndSearch(hmtestdata,test);
			
			pgMMTFlightSelection.BookSelectFlights("Depart & Return Flight Selection Radio Button",hmtestdata,test);
			TotalFareEstimate=pgMMTFlightSelection.GetTotalFare();
			System.out.println("Total estimated fare is :"+TotalFareEstimate);
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
		}
		
	}
	
	public static void MakeHotelReservation(WebDriver driver,HashMap<String,String> hmtestdata,ExtentTest test)
	{
		try
		{
			MMTHotelReservation pgMMTHotelReservation=new MMTHotelReservation(driver);
			pgMMTHotelReservation.clickHotelLink("Hotel Menu Link",test);
			pgMMTHotelReservation.EnterHotelCity("Hotel City Text Box", hmtestdata.get("HotelCity"),test);
			pgMMTHotelReservation.SelectCheckInout(hmtestdata,test);
			pgMMTHotelReservation.selectadultandchildno("Room/Guest Selection Box",test);
			pgMMTHotelReservation.SearchHotels("Hotel Search Button",test);
			pgMMTHotelReservation.ClickShowHotels("Show Hotels Pop-up Button",test);
			pgMMTHotelReservation.SwitchWindowandBookHotel(hmtestdata,test);
			pgMMTHotelReservation.ClickBookThisNow("Book This Now Button",test);
			pgMMTHotelReservation.checkTotalPayable("Total Payable label","innerText");
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
		}
	}

}
