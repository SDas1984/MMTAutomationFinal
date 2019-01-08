package com.MMT.PageObjects;

import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.MMT.SeleniumUtility.SeleniumUtility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class MMTFlightReservation {
	
	public static WebDriver driver;
	
	public MMTFlightReservation(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//input[@id='hp-widget__sfrom']")
	public WebElement txtFromlocation;
	
	@FindBy(xpath="//input[@id='hp-widget__sTo']")
	public WebElement txtTolocation;
	
	@FindBy(xpath="//input[@class='checkSpecialCharacters']")
	public WebElement txtDatefrom; 
	
	@FindBy(xpath="//div[@class='dateFilter hasDatepicker']")
	public WebElement divDatePicker;
	
	@FindBy(xpath="//input[@id='hp-widget__return']")
	public WebElement txtReturnDate;
	
	@FindBy(xpath="//button[@id='searchBtn']")
	public WebElement btnSearch;
	
	public void EnterFromLocation(String nameofelement,HashMap<String,String> hmtestdata,ExtentTest test)
	{
		try
		{
			//SeleniumUtility.EnterText(driver, txtFromlocation, nameofelement,hmtestdata.get("FromLocation"));
			//SeleniumUtility.Click(driver, txtFromlocation, nameofelement);
			SeleniumUtility.EnterText(driver, txtFromlocation, nameofelement,hmtestdata.get("FromLocation"),test);
			Thread.sleep(200);
			List<WebElement> lstFromLoc=driver.findElements(By.xpath("//li[contains(@aria-label,'"+hmtestdata.get("FromLocation")+"')]"));
			
			for(WebElement e:lstFromLoc)
			{
				if((e.getSize()).height!=0 & (e.getSize()).width!=0)
				{
					SeleniumUtility.Click(driver,e,nameofelement,test);
					test.log(LogStatus.PASS, "Selected "+nameofelement);
					break;
				}
			}
			
		}
		catch(Exception E)
		{
			System.out.println(E.getMessage().toString());
			test.log(LogStatus.FAIL,E.getMessage().toString());
		}
		
	}
	
	public void EnterToLocation(String nameofelement,HashMap<String,String> hmtestdata,ExtentTest test)
	{
		try
		{
			SeleniumUtility.EnterText(driver, txtTolocation, nameofelement,hmtestdata.get("ToDestination"),test);
			Thread.sleep(500);
			List<WebElement> lstFromLoc=driver.findElements(By.xpath("//li[contains(@aria-label,'"+hmtestdata.get("ToDestination")+"')]"));
			
			for(WebElement e:lstFromLoc)
			{
				if((e.getSize()).height!=0 & (e.getSize()).width!=0)
				{
					SeleniumUtility.Click(driver,e,nameofelement,test);
					test.log(LogStatus.PASS, "Selected "+nameofelement);
					break;
				}
			}
		}
		catch(Exception E)
		{
			System.out.println(E.getMessage().toString());
			test.log(LogStatus.FAIL,E.getMessage().toString());
		}
		
	}
	
	public void SelectFromToDateAndSearch(HashMap<String,String> hmtestdata,ExtentTest test)
	{
		String strDepartdatetext=hmtestdata.get("DepartDate");
		String strReturndatetext=hmtestdata.get("ReturnDate");
		String xpathfromdate="";
		try
		{	
			SeleniumUtility.ClickonScreen(driver);
			SeleniumUtility.Click(driver, txtDatefrom, "DateFromTextBox",test);
			Thread.sleep(500);
			SeleniumUtility.DatePicker(driver,strDepartdatetext,test,"Departdate");
			Thread.sleep(500);
			SeleniumUtility.Click(driver, txtReturnDate, "ReturnDateTextBox",test);
			Thread.sleep(500);
			SeleniumUtility.DatePicker(driver,strReturndatetext,test,"Returndate");
			SeleniumUtility.ForceClick(driver, btnSearch, "SearchFlightButton",test);
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage().toString());
		}
		
	}

}
