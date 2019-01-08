package com.MMT.PageObjects;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.MMT.SeleniumUtility.SeleniumUtility;
import com.relevantcodes.extentreports.ExtentTest;

public class MMTFlightSelection {
	
public static WebDriver driver;
	
	public MMTFlightSelection(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//div[@class='row wrap-dep']//span[contains(@title,'Jet Airways')]/preceding-sibling::span[@class='radio_click pull-left']//child::span[@class='radio_icon']")
	public WebElement chkboxDepartFlt;
	
	@FindBy(xpath="//div[@class='row wrap-ret']//span[contains(@title,'Jet Airways')]/preceding-sibling::span[@class='radio_click pull-left']//child::span[@class='radio_icon']")
	public WebElement chkboxReturnFlt;
	
	@FindBy(xpath="//a[text()='Book']")
	public WebElement btnBook;
	
	@FindBy(xpath="//span[text()='GRAND TOTAL']/following-sibling::span[1]")
	public WebElement lblTotalFare;
	
	
	public void BookSelectFlights(String nameofelement,HashMap<String,String> hmtestdata,ExtentTest test)
	{
		SeleniumUtility.Wait_For_Element(driver, btnBook,test);
		SeleniumUtility.scrooltoview(driver,chkboxDepartFlt,test);
		SeleniumUtility.ForceClick(driver, chkboxDepartFlt, "Depart Flight Radio",test);
		SeleniumUtility.scrooltoview(driver,chkboxReturnFlt,test);
		SeleniumUtility.ForceClick(driver, chkboxReturnFlt, "Return Flight Radio",test);
		SeleniumUtility.Click(driver, btnBook, "BOOK button",test);
	}
	
	public String GetTotalFare()
	{
		return lblTotalFare.getText().toString();
	}
	
	
	

}
