package com.MMT.SeleniumUtility;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.beust.jcommander.Strings;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import com.sun.jna.platform.FileUtils;

public class SeleniumUtility extends com.MMT.Utility.Driver {
	
	public static void Wait_For_Element(WebDriver driver,WebElement e,ExtentTest test)
	{
		try
		{
		WebDriverWait wait=new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(e));
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage().toString());
			try {
				String screenShotPath = capture(driver);
				test.log(LogStatus.FAIL,Ex.getMessage().toString()+test.addScreenCapture(screenShotPath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			report.endTest(test);
			report.flush();
			throw new AssertionError("A clear description of the failure", Ex);
		}
	}
	
	public static void Wait_For_Element_wo_terminate(WebDriver driver,WebElement e)
	{
		try
		{
		WebDriverWait wait=new WebDriverWait(driver, 60);
		wait.until(ExpectedConditions.visibilityOf(e));
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage().toString());
		}
	}
	
	public static boolean Check_Presence_of_Element(WebDriver driver,String locator)
	{
		boolean status=false;
		try
		{
			WebDriverWait wait=new WebDriverWait(driver, 5);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
			status=true;
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage().toString());
			status=false;
			return status;
		}
		
		return status;
	}
	
	
	public static boolean Verify_visibility_of_Element(WebDriver driver,WebElement e)
	{
		Wait_For_Element_wo_terminate(driver,e);
		if(e.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static void Click(WebDriver driver,WebElement e,String nameofelement,ExtentTest test)
	{
		try
		{
			Wait_For_Element(driver,e,test);
			if(e.isDisplayed())
			{
				e.click();
				System.out.println("Clicked on "+ nameofelement);
				test.log(LogStatus.PASS, "Clicked on "+ nameofelement);
			}
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage().toString());
			try {
				String screenShotPath = capture(driver);
				test.log(LogStatus.FAIL,"Failed to click element "+nameofelement+test.addScreenCapture(screenShotPath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void ForceClick(WebDriver driver,WebElement e,String nameofelement,ExtentTest test)
	{
		try
		{
			
			Wait_For_Element(driver,e,test);
			
			if(e.isDisplayed())
			{
				
				Actions Action=new Actions(driver);
				Action.moveToElement(e).click().perform();
				System.out.println("Clicked on "+ nameofelement);
				test.log(LogStatus.PASS, "Clicked on "+ nameofelement);
				
			}
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
			try {
				String screenShotPath = capture(driver);
				test.log(LogStatus.FAIL,"Failed to click on element "+nameofelement+test.addScreenCapture(screenShotPath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void scrooltoview(WebDriver driver,WebElement e,ExtentTest test)
	{
		Wait_For_Element(driver,e,test);
		try
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(true);", e);
			Thread.sleep(100);
			test.log(LogStatus.PASS, "Scrolled element "+ e.getTagName() +" to view");
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
			try {
				String screenShotPath = capture(driver);
				test.log(LogStatus.FAIL,Ex.getMessage().toString()+test.addScreenCapture(screenShotPath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void scrooltoviewup(WebDriver driver,WebElement e,ExtentTest test)
	{
		Wait_For_Element(driver,e,test);
		try
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("arguments[0].scrollIntoView(false);", e);
			Thread.sleep(100);
			test.log(LogStatus.PASS, "Scrolled element "+ e.getTagName() +" to view");
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
			test.log(LogStatus.FAIL,Ex.getMessage().toString());
		}
	}
	
	public static void scrooltobottom(WebDriver driver,ExtentTest test)
	{
		try
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
			Thread.sleep(100);
			test.log(LogStatus.PASS, "Scrolled to bottom of the page");
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
			test.log(LogStatus.FAIL,Ex.getMessage().toString());
		}
	}
	

	public static void scrollby(WebDriver driver)
	{
		try
		{
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0, 2000)");
			Thread.sleep(100);
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
		}
	}
	
	public static void EnterText(WebDriver driver,WebElement e,String nameofelement,String texttoenter,ExtentTest test)
	{
		try
		{
			Wait_For_Element(driver,e,test);
			if(e.isDisplayed())
			{
				e.clear();
				e.sendKeys(texttoenter);
				System.out.println("Entered text "+texttoenter+" in "+nameofelement+" text box.");
				test.log(LogStatus.PASS, "Entered text "+texttoenter+" in "+nameofelement+" text box.");
			}
		}
		catch(Exception Ex)
		{
			System.out.println("Could not enter text "+texttoenter+" in "+nameofelement+" text box.");
			System.out.println(Ex.getMessage());
			try {
				String screenShotPath = capture(driver);
				test.log(LogStatus.FAIL,"Failed to enter text on element "+nameofelement+test.addScreenCapture(screenShotPath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
	public static void EnterTextusingKeys(WebDriver driver,WebElement e,String nameofelement,String texttoenter,ExtentTest test)
	{
		try
		{
			Wait_For_Element(driver,e,test);
			if(e.isDisplayed())
			{
				e.click();
				e.sendKeys(Keys.DELETE);
				Thread.sleep(200);
				e.sendKeys(texttoenter);
				System.out.println("Entered text "+texttoenter+" in "+nameofelement+" text box.");
				test.log(LogStatus.PASS, "Entered text "+texttoenter+" in "+nameofelement+" text box.");
			}
		}
		catch(Exception Ex)
		{
			System.out.println("Could not enter text "+texttoenter+" in "+nameofelement+" text box.");
			System.out.println(Ex.getMessage());
			try {
				String screenShotPath = capture(driver);
				test.log(LogStatus.FAIL,"Failed to enter text on element "+nameofelement+test.addScreenCapture(screenShotPath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	public static String getText(WebDriver driver,WebElement e,String nameofelement,ExtentTest test)
	{	
		String ErrorText="";
		try
		{
			Wait_For_Element(driver,e,test);
			if(e.isDisplayed())
			{
				return e.getText().toString();
			}
			else
			{
				ErrorText="Could not find element "+nameofelement;
				return ErrorText;
			}
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
			ErrorText="Could not find element "+nameofelement;
			try {
				String screenShotPath = capture(driver);
				test.log(LogStatus.FAIL,"Could not find element "+nameofelement+test.addScreenCapture(screenShotPath));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			return ErrorText;
		}
		
	}
	
	
	public static void DatePicker(WebDriver driver,String Date,ExtentTest test,String datetype)
	{
		String xpathfromdate="";
		try
		{
			String[] parttext=Date.split("-");
			xpathfromdate="//span[@class='ui-datepicker-month' and text()='"+parttext[1]+"']//ancestor::div[contains(@class,'ui-datepicker-group ui-datepicker-group-')]//child::table[@class='ui-datepicker-calendar']//a[text()='"+parttext[0]+"']";
			List<WebElement> lstDepartDate=driver.findElements(By.xpath(xpathfromdate));
			for(WebElement e:lstDepartDate)
			{
				Dimension d=e.getSize();
				if(d.getHeight()!=0 & d.getWidth()!=0)
				{
					e.click();
					test.log(LogStatus.PASS, "Selected "+datetype+" date: "+Date+" successfully");
					break;
				}
			}
		}
		catch(Exception Ex)
		{
			//System.out.println("Could not enter text "+texttoenter+" in "+nameofelement+" text box.");
			System.out.println(Ex.getMessage());
			try {
				String screenShotPath = capture(driver);
				test.log(LogStatus.FAIL,Ex.getMessage().toString()+test.addScreenCapture(screenShotPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void PressTabKey(WebDriver driver,String KeyToHit)
	{
		
		try
		{
			Actions action=new Actions(driver);
			action.sendKeys(Keys.TAB).perform();
			Thread.sleep(500);
			}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
		}
	}
	
	public static void ClickonScreen(WebDriver driver)
	{
		
		try
		{
			Thread.sleep(1000);
			Actions action=new Actions(driver);
			action.moveByOffset(350,100).click().perform();
			Thread.sleep(500);
			}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
		}
	}
	
	public static Set GetWindowHandles(WebDriver driver,ExtentTest test)
	{
		Set<String> WindowHandles;
		try
		{
			WindowHandles=driver.getWindowHandles();
			
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
			WindowHandles=null;
			test.log(LogStatus.FAIL,Ex.getMessage().toString());
			return WindowHandles;
		}
		return WindowHandles;
	}
	
	public static WebDriver SwitchtoNewWindow(Set<String> currentwindowHandles,Set<String> afterClickwindowHandles,WebDriver driver,ExtentTest test)
	{
		try
		{
			for(String windowhandle:afterClickwindowHandles)
			{
				if(!currentwindowHandles.contains(windowhandle))
				{
					driver.switchTo().window(windowhandle);
					break;
				}
			}
			return driver;
			
		}
		catch(Exception Ex) {
			System.out.println(Ex.getMessage());
			try {
				String screenShotPath = capture(driver);
				test.log(LogStatus.FAIL,Ex.getMessage().toString()+test.addScreenCapture(screenShotPath));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return driver;
		}
	}
	
	public static String getattributevalue(WebDriver driver,WebElement e,String attributename,String nameofelement)
	{
		String attributevalue=null;
		try
		{
			Wait_For_Element_wo_terminate(driver,e);
			if(e.isDisplayed())
			{
				attributevalue=e.getAttribute(attributename);
				
			}
		}
		catch(Exception Ex)
		{
			System.out.println(Ex.getMessage());
			return attributevalue=null;
		}
		return attributevalue;
	}
	
	public static String capture(WebDriver driver) throws IOException
    {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        String dest = "C:\\Java_Projects\\Selenium\\Extent Reports\\ErrorScreenshots\\"+System.currentTimeMillis()+".png";
        File destination = new File(dest);
        FileHandler.copy(source, destination);
        return dest;
    }

}
