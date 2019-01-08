package com.EOC.NewReservation;

import java.util.HashMap;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.MMT.BusinessFunctions.BusinessFunction;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import net.bytebuddy.jar.asm.commons.Method;

public class NewReservation extends com.MMT.Utility.Driver {
	
//public class NewReservation{
	
public static WebDriver driver;
	
@BeforeTest
public static void Setup()
{
	try {
		
	System.setProperty("webdriver.chrome.driver", "C:\\Java_Projects\\Selenium\\src\\test\\resources\\chromedriver.exe");
	driver=new ChromeDriver();
	driver.get("https://www.makemytrip.com/");
	driver.manage().window().maximize();
	
	}
	catch (Exception e) {
		
		e.printStackTrace();
	}
	
}
	
 	
  @Test
  public void NewReservation1() {
	  
	  System.out.println("I am inside NewReservation1");
	  report=new ExtentReports("C:\\Java_Projects\\Selenium\\Extent Reports\\NewReservation1"+System.currentTimeMillis()+".html",true);
	  ExtentTest test=report.startTest("New Flight Reservation", "I am inside NewReservation1");
	 
	  HashMap<String,String> hmtestdata=new  HashMap<String,String>();
	  
	 
	for (HashMap<String,String> hm : TestCaseDetailsList)
	  {
		  if(hm.get("TestCaseName").toString().equalsIgnoreCase("NewReservation1"))
		  {
			  hmtestdata=hm;
			  break;
		  }
	  }
	  
	  BusinessFunction.Login(driver,hmtestdata.get("UserEmail"),hmtestdata.get("Password"),test);
	  BusinessFunction.MakeFlightReservation(driver,hmtestdata,test);
	  report.endTest(test);
	  report.flush();
	  
  }


@Test
public void NewReservation2() {
	
		System.out.println("I am inside NewReservation2");
		 ExtentReports report=new ExtentReports("C:\\Java_Projects\\Selenium\\Extent Reports\\NewReservation2"+System.currentTimeMillis()+".html",true);
		  ExtentTest test=report.startTest("New Flight Reservation", "I am inside NewReservation2");
	  
		HashMap<String,String> hmtestdata=new  HashMap<String,String>();
	  
		  for (HashMap<String,String> hm : TestCaseDetailsList)
		  {
			  if(hm.get("TestCaseName").toString().equalsIgnoreCase("NewReservation2"))
			  {
				  hmtestdata=hm;
				  break;
			  }
		  } 
	BusinessFunction.Login(driver,hmtestdata.get("UserEmail"),hmtestdata.get("Password"),test);
	BusinessFunction.MakeHotelReservation(driver,hmtestdata,test);
	report.endTest(test);
	report.flush();

}


@Test
public void NewReservation3() {
	  
	  System.out.println("I am inside NewReservation3");
}

@AfterTest
public void cleanup()
{
	driver.quit();
	report=null;
}
}
