package com.MMT.PageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.MMT.SeleniumUtility.SeleniumUtility;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;


public class MMTLoginpage {
	
	public static WebDriver driver;
	
	public MMTLoginpage(WebDriver driver)
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//input[@id='hp-widget__sfrom']")
	public WebElement imgMMTLogo;
	
	@FindBy(xpath="//a[@id='ch_login_icon']")
	public WebElement lnlogin;
	
	@FindBy(xpath="//input[@id='ch_login_email']")
	public WebElement txtloginEmail;
	
	@FindBy(xpath="//input[@id='ch_login_password']")
	public WebElement txtloginpw;
	
	@FindBy(xpath="//button[@id='ch_login_btn']")
	public WebElement btnlogin;
	
	@FindBy(xpath="//a[@id='ch_logged-in']")
	public WebElement lnkHeyUser;
	
	@FindBy(xpath="//input[@id='hp-widget__sfrom']")
	public WebElement txtFromlocation;
	
	@FindBy(xpath="//input[@id='hp-widget__sTo']")
	public WebElement txtTolocation;
	
	@FindBy(xpath="//input[@class='checkSpecialCharacters']")
	public WebElement txtDatefrom; 
	
	public boolean checkMMTLogoPresence(String nameofelement,ExtentTest test)
	{
		if(SeleniumUtility.Verify_visibility_of_Element(driver,imgMMTLogo))
		{
			System.out.println("Element "+nameofelement+" is visible");
			test.log(LogStatus.PASS, "Element "+nameofelement+" is visible");
			return true;
		}
		else
		{
			System.out.println("Element "+nameofelement+" is not visible");
			test.log(LogStatus.FAIL, "Element "+nameofelement+" is not visible");
			return false;
		}
	}
	
	public void ClickLoginlink(String nameofelement,ExtentTest test)
	{
		try
		{
			SeleniumUtility.Click(driver,lnlogin,nameofelement,test);
		}
		catch(Exception E)
		{
			System.out.println("Couldnt click on "+nameofelement);
			System.out.println(E.getMessage().toString());
		}
		
	}
	
	public void Enteremail(String useremail,String nameofelement,ExtentTest test)
	{
		try
		{
			SeleniumUtility.EnterText(driver, txtloginEmail, nameofelement,useremail,test);
		}
		catch(Exception E)
		{
			System.out.println(E.getMessage().toString());
		}
		
	}
	
	public void Enterpassword(String userpassword,String nameofelement,ExtentTest test)
	{
		try
		{
			SeleniumUtility.EnterText(driver, txtloginpw, nameofelement,userpassword,test);
		}
		catch(Exception E)
		{
			System.out.println(E.getMessage().toString());
		}
		
	}
	
	public void ClickLoginButton(String nameofelement,ExtentTest test)
	{
		try
		{
			SeleniumUtility.Click(driver,btnlogin,nameofelement,test);
		}
		catch(Exception E)
		{
			System.out.println("Couldn't click on "+nameofelement);
			System.out.println(E.getMessage().toString());
		}
		
	}
	
	public boolean VerifyLogin(String nameofelement,ExtentTest test)
	{
		try
		{
		
			if(SeleniumUtility.Verify_visibility_of_Element(driver,lnkHeyUser))
			{
				System.out.println("Element "+nameofelement+" is visible");
				test.log(LogStatus.PASS, "Element "+nameofelement+" is visible");
				return true;
			}
			else
			{
				System.out.println("Element "+nameofelement+" is not visible");
				test.log(LogStatus.FAIL, "Element "+nameofelement+" is not visible");
				return false;
			}
		
		}	
		catch(Exception E)
		{
			System.out.println("The "+nameofelement+" not visible");
			System.out.println(E.getMessage().toString());
			test.log(LogStatus.FAIL, E.getMessage().toString());
			return false;
		}
	}
	
	
}
