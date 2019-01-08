package com.MMT.PageObjects;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.MMT.SeleniumUtility.SeleniumUtility;
import com.beust.jcommander.Strings;
import com.relevantcodes.extentreports.ExtentTest;

public class MMTHotelReservation {
	
	public static WebDriver driver;
	
	public MMTHotelReservation(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//a[text()='hotels']")
	public WebElement TopHotelLink; 
	
	
	@FindBy(xpath="//input[@id='hp-widget__sDest']")
	public WebElement Hotelcity;
	
	@FindBy(xpath="//input[@id='hp-widget__chkIn']")
	public WebElement CheckinDate;
	
	@FindBy(xpath="//input[@id='//input[@id='hp-widget__chkOut']']")
	public WebElement CheckoutDate;
	
	@FindBy(xpath="//ul[@id='js-adult_counter']/child::li[text()='3']")
	public WebElement adultcounter;
	
	@FindBy(xpath="//ul[@id='js-child_counter']/child::li[text()='1']")
	public WebElement childerncounter;
	
	@FindBy(xpath="//ul[@class='paxCounter__counter']/child::li[text()='2']")
	public WebElement childrenage;
	
	@FindBy(xpath="//input[@id='hp-widget__paxCounter']")
	public WebElement guesttextbox;
	
	@FindBy(xpath="//button[@id='searchBtn']")
	public WebElement btnSearch;
	
	@FindBy(xpath="//a[text()='SHOW HOTELS']")
	public WebElement btnShowHotels;
	
	@FindBy(xpath="//a[text()='Done']")
	public WebElement btnDone;
	
	@FindBy(xpath="//a[text()='BOOK THIS NOW']")
	public WebElement btnBookNow;
	
	@FindBy(xpath="//span[text()='Total Payable']/following-sibling::span[1]")
	public WebElement lblToTalPayable;
	
	
	
	public void clickHotelLink(String nameofelement,ExtentTest test)
	{
		SeleniumUtility.Click(driver, TopHotelLink, nameofelement,test);
	}
	
	public void EnterHotelCity(String nameofelement,String HotelCity,ExtentTest test)
	{
		try
		{
		SeleniumUtility.Click(driver, Hotelcity, nameofelement,test);
		Thread.sleep(500);
		SeleniumUtility.EnterTextusingKeys(driver, Hotelcity, nameofelement, HotelCity,test);
		Thread.sleep(200);
		List<WebElement> lstFromLoc=driver.findElements(By.xpath("//li[contains(@aria-label,'"+HotelCity+"')]"));
		
		for(WebElement e:lstFromLoc)
		{
			if((e.getSize()).height!=0 & (e.getSize()).width!=0)
			{
				SeleniumUtility.Click(driver,e,nameofelement,test);
				break;
			}
		}
		}
		catch(Exception E)
		{
			System.out.println(E.getMessage().toString());
		}
	}
	
	public void clickCheckinDate(String nameofelement,ExtentTest test)
	{
		SeleniumUtility.Click(driver, CheckinDate, nameofelement,test);
	}
	
	public void clickCheckoutDate(String nameofelement,ExtentTest test)
	{
		SeleniumUtility.Click(driver, CheckoutDate, nameofelement,test);
	}
	
	public void selectadultandchildno(String nameofelement,ExtentTest test)
	{
		SeleniumUtility.Click(driver, guesttextbox, "Rooms/Guest Selction textbox",test);
		SeleniumUtility.Click(driver, adultcounter, "Adult Number Select list",test);
		SeleniumUtility.Click(driver, childerncounter, "Children Number Select list",test);
		SeleniumUtility.Click(driver, childrenage, "Age of child selection list",test);
		SeleniumUtility.Click(driver, btnDone, "Age Selection Done button",test);
	}
	
	public void SearchHotels(String nameofelement,ExtentTest test)
	{
		SeleniumUtility.Click(driver, btnSearch, "SearchHotelsButton",test);
	}
	
	public void ClickShowHotels(String nameofelement,ExtentTest test)
	{
		SeleniumUtility.Click(driver, btnShowHotels, nameofelement,test);
	}
	
	public void SelectHotel(String nameofhotel,ExtentTest test)
	{
		boolean hotellinkvisibilitystatus=false;
		int cntr=0;
		WebElement hotel;
		String xpathHotel="//p[text()='"+nameofhotel+"']";
		while(true)
		{
			try {
				hotellinkvisibilitystatus=SeleniumUtility.Check_Presence_of_Element(driver, xpathHotel);
				if(hotellinkvisibilitystatus)
				{
					hotel=driver.findElement(By.xpath(xpathHotel));
					SeleniumUtility.Click(driver,hotel, nameofhotel,test);
					break;
				}
				else if(cntr>10)
				{
					break;
				}
				else
				{
					SeleniumUtility.scrollby(driver);
				}
				cntr++;
			}
			catch(Exception E)
			{
				System.out.println(E.getMessage().toString());
			}
		}
		
	}
	
	public void SelectCheckInout(HashMap<String,String> hmtestdata,ExtentTest test)
	{
		String strDepartdatetext=hmtestdata.get("DepartDate");
		String strReturndatetext=hmtestdata.get("ReturnDate");
		String xpathfromdate="";
		try
		{	
			SeleniumUtility.ClickonScreen(driver);
			SeleniumUtility.DatePicker(driver,strDepartdatetext,test,"CheckInDate");
			Thread.sleep(200);
			SeleniumUtility.DatePicker(driver,strReturndatetext,test,"CheckOutDate");
			
		}
		catch(Exception E)
		{
			System.out.println(E.getMessage().toString());
		}
		
	}
	
	public void SwitchWindowandBookHotel(HashMap<String,String> hmtestdata,ExtentTest test)
	{
		String currentWindowHandle=null;
		Set<String> currentwindowHandles;
		Set<String> AfterClickwindowHandles;
		int cntr=0;
		
		try
		{	
			currentWindowHandle=driver.getWindowHandle();
			currentwindowHandles=SeleniumUtility.GetWindowHandles(driver,test);
			SelectHotel(hmtestdata.get("Hotelname"),test);
			while(true)
			{
				AfterClickwindowHandles=SeleniumUtility.GetWindowHandles(driver,test);
				if(currentwindowHandles.size()+1==AfterClickwindowHandles.size())
				{
					break;
				}
				if(cntr>50)
				{
					break;
				}
				cntr++;
			}
			
			driver=SeleniumUtility.SwitchtoNewWindow(currentwindowHandles, AfterClickwindowHandles, driver,test);
			
		}
		catch(Exception E)
		{
			System.out.println(E.getMessage().toString());
		}
		
	}
	
	public void ClickBookThisNow(String nameofelement,ExtentTest test)
	{
		SeleniumUtility.Click(driver, btnBookNow,nameofelement,test);
	}
	
	public void checkTotalPayable(String nameofelement,String attributename)
	{
		System.out.println("The total payble for selected hotel is :"+SeleniumUtility.getattributevalue(driver,lblToTalPayable, "innerText", nameofelement));
	}
}
