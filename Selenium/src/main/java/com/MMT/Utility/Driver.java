package com.MMT.Utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.TestNG;
import org.testng.annotations.Test;
import org.testng.xml.XmlClass;
import org.testng.xml.XmlInclude;
import org.testng.xml.XmlSuite;
import org.testng.xml.XmlTest;

import com.EOC.CancelReservation.CancelReservation;
import com.EOC.ChangeReservation.ChangeReservation;
import com.EOC.NewReservation.NewReservation;
import com.relevantcodes.extentreports.ExtentReports;

public class Driver {
	
 protected static ArrayList<HashMap<String,String>> TestCaseDetailsList=null;
 protected static ExtentReports report=null;
  @Test
  public void TestDriver() {
	  
	  try
	  {			
		  	int cntr=0;
		  	TestCaseDetailsList=excelreader();
		  	String CurrentClassName="";
		  	///Create an instance on TestNG 
		     TestNG myTestNG = new TestNG();  
		     
		     //Create an instance of XML Suite and assign a name for it. 
		      XmlSuite mySuite = new XmlSuite(); 
		      mySuite.setName("MySuite"); 
		      
		      //Create a list of XmlTests and add the Xmltest you created earlier to it.
		      List<XmlTest> myTests = new ArrayList<XmlTest>(); 
		  while(cntr<(TestCaseDetailsList.size()))
		  {
			  if((TestCaseDetailsList.get(cntr).get("Execute")).equalsIgnoreCase("y"))
			  {
				  XmlTest myTest = new XmlTest(mySuite); 
				  myTest.setName((TestCaseDetailsList.get(cntr).get("TestCaseName")));
				  
				  //Set Class name
				  if((TestCaseDetailsList.get(cntr).get("TestCaseName")).startsWith("New"))
				  {
					  CurrentClassName="com.EOC.NewReservation.NewReservation"; 
				  }
				  
				  else if((TestCaseDetailsList.get(cntr).get("TestCaseName")).startsWith("Change"))
				  {
					  CurrentClassName="com.EOC.ChangeReservation.ChangeReservation"; 
				  }
				  
				  else if((TestCaseDetailsList.get(cntr).get("TestCaseName")).startsWith("Cancel"))
				  {
					  CurrentClassName="com.EOC.CancelReservation.CancelReservation"; 
				  }
				  
				  else
				  {
					  System.out.println("No Class Name found.Stopping Execution");
				  }
				  
				 //Create a list which can contain the classes that you want to run.
			     List<XmlClass> myClasses = new ArrayList<XmlClass>();
			     ArrayList<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
			    
			     XmlClass testClass = new XmlClass(CurrentClassName);

			     methodsToRun.add(new XmlInclude((TestCaseDetailsList.get(cntr).get("TestCaseName"))));
			     testClass.setIncludedMethods(methodsToRun);
			     myClasses.add(testClass);   

			     //Assign that to the XmlTest Object created earlier. 
			     myTest.setXmlClasses(myClasses); 
			     myTests.add(myTest);
			     
			     
			   }
			  cntr++;
		  }
		  mySuite.setTests(myTests); 
		  List<XmlSuite> mySuites = new ArrayList<XmlSuite>(); 
		  mySuites.add(mySuite);
		  myTestNG.setXmlSuites(mySuites);
		  myTestNG.run();
		  System.out.println("Testng.xml executed");
	  }
	  catch(Exception e)
	  {
		  System.out.println("Exception:"+e.getMessage());
	  }
	  
  }
  
  
  public static ArrayList<HashMap<String,String>> excelreader()
  {
	 
	  ArrayList<HashMap<String,String>> TestList=new ArrayList<HashMap<String,String>>();
	  //HashMap<String,HashMap<String,String>> TestList=new HashMap<String,HashMap<String,String>>();
	 // HashMap<String,String> hmTestSet=new HashMap<String,String>();
	  HashMap<String,String> hmTestSet=null;
	  ArrayList<String> colHeaders=new ArrayList<String>();
	  String C="";
	  String D="";
	  Row row;
	  String TestCaseName="";
	  String ExecuteIndex="";
	  int iterationcntr=0;
	  int headercounter=0;
	  try
	  {
		  File excelFile = new File("C:\\Java_Projects\\Selenium\\src\\test\\resources\\MMTData.xlsx");
		  FileInputStream fis = new FileInputStream(excelFile);
		  XSSFWorkbook workbook = new XSSFWorkbook(fis);
		  XSSFSheet sheet = workbook.getSheetAt(0);
		  Iterator<Row> rowIt = sheet.iterator();
		  
		    while(rowIt.hasNext()) {
		      row = rowIt.next();
		      Iterator<Cell> cellIterator = row.cellIterator();
		      hmTestSet=new HashMap<String,String>();

			      while (cellIterator.hasNext()) {
			        Cell cell = cellIterator.next();
			        if(iterationcntr==0)
			        {
			        	colHeaders.add(cell.getStringCellValue());
			        }
			        else 
			        {
			        	C=colHeaders.get(headercounter);
			        	D=cell.getStringCellValue();
			        	//hmTestSet.put((colHeaders.get(headercounter)).toString(),(cell.getStringCellValue()).toString());
			        	hmTestSet.put(C,D);
			        	headercounter++;
			        	
			        }
			        
			    }
			      
			      
			      headercounter=0;
			      if(iterationcntr!=0)
			      {
			    	  //TestList.put(hmTestSet.get("TestCaseName"),hmTestSet);
			    	  TestList.add(hmTestSet);
			      }
			      iterationcntr++;
			      hmTestSet=null;
			      
		    }
	 
	  	  
	  }
	  
	  catch(Exception e)
	  {
		  System.out.println("Exception:"+e.getStackTrace().toString());
	  }
	
	 return TestList;
  }
}
