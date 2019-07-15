package SeleniumAutomation;

import java.awt.AWTException;
import java.awt.FlowLayout;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import XMLReader.TestStep;

public class KeywordManager {	
	ReportFunctions report = null;
	String ParentWindowHandle=null;
	/*	Keyword Name: EnterValue
	Description: This method is used to enter the test data value in the application fields.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void EnterValue(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException
	{		
		if(report == null){
			report =  new ReportFunctions();
		}
		String result = "fail";
		String data1value=null;
			
		try{
			String[] refValues = step.getObjrefValue().split("\\->");			
			if (hmap.containsKey(step.getData1().trim())) 
			{
				data1value = hmap.get(step.getData1().trim());
			}
			else{
				data1value = step.getData1().trim();
			}
			if(refValues[0].equals("id"))
			{
				WebDriver.findElement(By.id(refValues[1])).sendKeys(data1value);
			}
			if(refValues[0].equals("xpath"))
			{
				WebDriver.findElement(By.xpath(refValues[1])).sendKeys(data1value);
			}
			if(refValues[0].equals("cssSelector"))
			{
				WebDriver.findElement(By.cssSelector(refValues[1])).sendKeys(data1value);
			}
			if(refValues[0].equals("className"))
			{
				WebDriver.findElement(By.className(refValues[1])).sendKeys(data1value);
			}
			
			result = "pass";
			System.out.println("Successfully entered"+" "+data1value);
			report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully entered"+" "+data1value);
			
		}
		catch(Exception ex)
		{
			System.out.println("unable to find the locator:"+" "+ex.getMessage());
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1);
		}			
	}
	/*	Keyword Name: Click
	Description: This method is used to click the application field.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void Click(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException {
		
		if(report == null){
			report =  new ReportFunctions();
		}
		
		String result = "fail";
		
		try{
			String[] refValues = step.getObjrefValue().split("\\->");
			String data1value = step.getData1().trim();
			WebElement element = null;
			if(refValues[0].equals("id"))
			{
				element = WebDriver.findElement(By.id(refValues[1]));
			}
			if(refValues[0].equals("xpath"))
			{
				element = WebDriver.findElement(By.xpath(refValues[1]));
			}
			if(refValues[0].equals("cssSelector"))
			{
				element = WebDriver.findElement(By.cssSelector(refValues[1]));
			}
			if(refValues[0].equals("className"))
			{
				element = WebDriver.findElement(By.className(refValues[1]));
			}
			
			if (element.isEnabled()){
				System.out.println("Succesfully clicked on"+" "+data1value);			
				element.click();	
				result = "pass";
				report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully clicked"+" "+data1value);
			}
		}
		catch(Exception ex)
		{

			System.out.println("unable to find the locator:"+" "+ex.getMessage());
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1);
		}		
	}
	/*	Keyword Name: SendKeys
	Description: This method is used to perform keyboard actions on the application such as "ENTER", "TAB", etc.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void SendKeys(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException, AWTException
	{
		if(report == null){
			report =  new ReportFunctions();
		}
		String result = "fail";
		Robot robot = new Robot();
			
		try{
			String data1value = step.getData1().trim();
			if (step.getData1().equals("ENTER"))			
			{
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
			}
			if (step.getData1().equals("TAB"))			
			{
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
			}
			if (step.getData1().equals("DELETE"))			
			{
				robot.keyPress(KeyEvent.VK_DELETE);
				robot.keyRelease(KeyEvent.VK_DELETE);
			}
			if (step.getData1().equals("ALTSHIFTT"))			
			{
				robot.keyPress(KeyEvent.VK_ALT);
				robot.keyPress(KeyEvent.VK_SHIFT);
				robot.keyPress(KeyEvent.VK_T);
				robot.keyRelease(KeyEvent.VK_ALT);
				robot.keyRelease(KeyEvent.VK_SHIFT);
				robot.keyRelease(KeyEvent.VK_T);
			}
			result = "pass";
			report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully sent data:"+" "+data1value);		
		}
		catch(Exception ex)
		{
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1);
		}		
	}
	/*	Keyword Name: CloseAllBrowsers
	Description: This method is used to close all the open browsers.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void CloseAllBrowsers(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException {
		 if(report == null){
             report =  new ReportFunctions();
		 }
		 String result = "fail";
		try{	
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEDriverServer.exe");
			//WebDriver.close();
			result = "pass";
			System.out.println("successfully closed all browsers");
			report.LogRepoter(hmap, result, step.getKeyword(), "successfully closed all browsers");
			
		} catch (Exception e) {

			System.out.println("unable to find the browser" + " " + e.getMessage());
			report.LogRepoter(hmap, result, step.getKeyword(), "unable to find the browser" + " " + e.getMessage());			
			System.exit(1);
		}
		
}
	/*	Keyword Name: GetText
	Description: This method is used to close all the open browsers.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void GetText(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap)  throws IOException
    {
           if(report == null){
                  report =  new ReportFunctions();
           }
           String result = "fail";
           try{
                  String[] refValues = step.getObjrefValue().split("\\->");
                  
                  String refInput = step.getData1().trim();
                  String ovalue = null;
                  
                  if(refValues[0].equals("id"))
                  {
                        ovalue = WebDriver.findElement(By.id(refValues[1])).getText();
                  }
                  if(refValues[0].equals("xpath"))
                  {
                        ovalue = WebDriver.findElement(By.xpath(refValues[1])).getText();
                  }
                  if(refValues[0].equals("cssSelector"))
                  {
                        ovalue = WebDriver.findElement(By.cssSelector(refValues[1])).getText();
                  }
                  if(refValues[0].equals("className"))
                  {
                        ovalue = WebDriver.findElement(By.className(refValues[1])).getText();
                  }
                  
                  
                  if(ovalue!=null)
                  {      
                        hmap.put(refInput, ovalue);
                        String GetTextElement = hmap.get(refInput);                          
                        System.out.println("Succesfully captured the value   "+GetTextElement);
                        result = "pass";
                        report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully capture the text :"+" "+GetTextElement);
                     
                  } else {
                        System.out.println("value is null" +" "+ovalue); 
                        report.LogRepoter(hmap, result, step.getKeyword(), "Unable to capture the text");
                  }      
           
           }
           catch(Exception ex)
           {
              report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
              System.exit(1);
           }        
    }
	/*	Keyword Name: VerifyValue
	Description: This method is used to fetch runtime value of the application field and compare with test data.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void VerifyValue(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException
	{		
		if(report == null){
			report =  new ReportFunctions();
		}
		String result = "fail";
		String ovalue=null;
		String data1value=null;
		try{
			String[] refValues = step.getObjrefValue().split("\\->");
			if (hmap.containsKey(step.getData1().trim())) 
			{
				data1value = hmap.get(step.getData1().trim());
			}
			else{
				data1value = step.getData1().trim();
			}
			data1value = data1value.toUpperCase().trim().replace(" ", "");				
			if(refValues[0].equals("id"))
			{
				ovalue=WebDriver.findElement(By.id(refValues[1])).getText();
				ovalue=ovalue.toUpperCase().trim().replace(" ", "");				
			}
			if(refValues[0].equals("xpath"))
			{
				ovalue=WebDriver.findElement(By.xpath(refValues[1])).getText();
				ovalue=ovalue.toUpperCase().trim().replace(" ", "");
			}
			if(refValues[0].equals("cssSelector"))
			{
				ovalue=WebDriver.findElement(By.cssSelector(refValues[1])).getText();
				ovalue=ovalue.toUpperCase().trim().replace(" ", "");
			}
			if(refValues[0].equals("className"))
			{
				ovalue=WebDriver.findElement(By.className(refValues[1])).getText();
				ovalue=ovalue.toUpperCase().trim().replace(" ", "");
			}
			if (ovalue.contains(data1value)){				 
				result = "pass";
				System.out.println("Both values matched");
				report.LogRepoter(hmap, result, step.getKeyword(), "Both values matched " +ovalue+ " and " +data1value);
			}
			else{
				System.out.println("Both values did not match");
				report.LogRepoter(hmap, result, step.getKeyword(), "Both values did not match " +ovalue+ " and " +data1value);
			}				
			
		}
		catch(Exception ex)
		{
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1);
		}		
	}
	/*	Keyword Name: GetValue
	Description: This method is used to fetch runtime value of the application field.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void GetValue(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException
	{
		if(report == null){
            report =  new ReportFunctions();
		}
		String result = "fail";
		String value=null;
		try{
            String[] refValues = step.getObjrefValue().split("\\->");            
            String refInput = step.getData1().trim();
            String ovalue = null;
            
            if(refValues[0].equals("id"))
            {
                  ovalue = WebDriver.findElement(By.id(refValues[1])).getAttribute("value");
            }
            if(refValues[0].equals("xpath"))
            {
                  ovalue = WebDriver.findElement(By.xpath(refValues[1])).getAttribute("value");
            }
            if(refValues[0].equals("cssSelector"))
            {
                  ovalue = WebDriver.findElement(By.cssSelector(refValues[1])).getAttribute("value");
            }
            if(refValues[0].equals("className"))
            {
                  ovalue = WebDriver.findElement(By.className(refValues[1])).getAttribute("value");
            }    
            
            if(ovalue!=null)
            {      
        	  hmap.put(refInput, ovalue);
        	  value = hmap.get(refInput);                          
              System.out.println("Succesfully captured the value   "+value);
              result = "pass";
              report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully capture the value :"+" "+value);
               
            } else {
            	System.out.println("Unable captured the value   "+value);
                report.LogRepoter(hmap, result, step.getKeyword(), "Unable to capture the value");
            }      
     
		}catch(Exception ex)
        {
            report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
            System.exit(1);
        } 
		
	}
	/*	Keyword Name: IsDisplayed
	Description: This method is used to verify whether the respective application field is available/displayed.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void IsDisplayed(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException  
	{
		if(report == null){
			report =  new ReportFunctions();
		}
		String result = "fail";
		try{
			String[] refValues = step.getObjrefValue().split("\\->");
			String object = step.getData1().trim();
			if(refValues[0].equals("id"))
			{
				WebDriver.findElement(By.id(refValues[1])).isDisplayed();
			}
			if(refValues[0].equals("xpath"))
			{
				WebDriver.findElement(By.xpath(refValues[1])).isDisplayed();
			}
			if(refValues[0].equals("cssSelector"))
			{
				WebDriver.findElement(By.cssSelector(refValues[1])).isDisplayed();
			}
			if(refValues[0].equals("className"))
			{
				WebDriver.findElement(By.className(refValues[1])).isDisplayed();
			}
			result = "pass";			
			System.out.println(object+": Object displayed on web page");
			report.LogRepoter(hmap, result, step.getKeyword(), object+": Object displayed on web page");
			
		}
		catch(Exception ex)
		{
			System.out.println("Object wasn't displayed on web page");
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1);
		}
	}
	/*	Keyword Name: RandomNumber
	Description: This method is used to generate random numbers(policy number) during Account Creation flow.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void RandomNumber(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap)  throws IOException
	{
		if(report == null){
			report =  new ReportFunctions();
		}
		String result = "fail";
		try{			
			String data1 = step.getData1().trim();
			Random rand = new Random(); 
			int Val = rand.nextInt(899999999) + 1000000000; 
			String Reqval = Integer.toString(Val); 
			hmap.put(data1, Reqval);
			data1 = hmap.get(data1);
			result = "pass";
			System.out.println("Succesfully captured the value   "+data1);
			report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully get the  value :"+" "+data1);							
		}
		catch(Exception ex)
		{
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1);
		}			
	}
	/*	Keyword Name: MsgBox
	Description: This method is used to generate and open message box popup during script execution.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void MsgBox(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException
    {
           if(report == null){
                  report =  new ReportFunctions();
           }
           String result = "fail";                  
           try{          
               
        	   String data1value = step.getData1().trim();
        	   String data2value = step.getData2().trim();
               int maxtimeout;                   
               JFrame frame = new JFrame("Message to user");
               JPanel panel = new JPanel();
               panel.setLayout(new FlowLayout());
               JLabel labeli = new JLabel(data2value);
               panel.add(labeli);
               JButton btnCancel = new JButton("Cancel");
               btnCancel.addActionListener(null);
               frame.add(panel);
               frame.setSize(300, 300);
               frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.setVisible(true);
               maxtimeout = Integer.parseInt(data1value);
               while (maxtimeout > 0) {
                     maxtimeout--;
                     frame.setTitle("Waiting...:" + maxtimeout + "]");
                     try {
                            Thread.sleep(1000);
                     }catch (InterruptedException exc) {
                    	 
                     }                    
               }
               result = "pass";              
               frame.setVisible(false);
           }catch(Exception ex)
           {
              report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
              System.exit(1); 
           }
    }
	/*	Keyword Name: IFrameClick
	Description: This method is used to click the application field comes under frame.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void IFrameClick(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException 
	{		
		if(report == null){
			report =  new ReportFunctions();
		}		
		String result = "fail";		
		try{
			String[] refValues = step.getObjrefValue().split("\\->");
			String data1value = step.getData1().trim();
			String data2value = step.getData2().trim();	
			WebDriver.switchTo().frame(data1value);
			WebElement element = null;
			if(refValues[0].equals("id"))
			{
				element = WebDriver.findElement(By.id(refValues[1]));
			}
			if(refValues[0].equals("xpath"))
			{
				element = WebDriver.findElement(By.xpath(refValues[1]));
			}
			if(refValues[0].equals("cssSelector"))
			{
				element = WebDriver.findElement(By.cssSelector(refValues[1]));
			}
			if(refValues[0].equals("className"))
			{
				element = WebDriver.findElement(By.className(refValues[1]));
			}
			if(refValues[0].equals("partialLinkText"))
			{
				element = WebDriver.findElement(By.partialLinkText(refValues[1]));
			}
			
			if (element.isEnabled()){
				element.click();
				System.out.println("Succesfully clicked the element");					
				result = "pass";
				report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully clicked element");				
			}
			WebDriver.switchTo().defaultContent();
		}
		catch(Exception ex)
		{
			System.out.println("unable to find the locator:"+" "+ex.getMessage());
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1); 
		}		
	}
	/*	Keyword Name: SwitchWindow
	Description: This method is used to switch the window
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void SwitchWindow(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws InterruptedException, IOException  
	{		
		if(report == null){
			report =  new ReportFunctions();
		}		
		String result = "fail";	
		try  
		{
			ParentWindowHandle = WebDriver.getWindowHandle();		
			Set<String> allWindowHandles = WebDriver.getWindowHandles();			
			for(String handle : allWindowHandles)	
			{
				if(!handle.equals(ParentWindowHandle))
				{
					WebDriver.switchTo().window(handle);	
					Thread.sleep(1000);
					WebDriver.manage().window().maximize();
					result = "pass";
					System.out.println("Successfully switch the window ");
					report.LogRepoter(hmap, result, step.getKeyword(), "Successfully Switch the window ");
					break;
				}									
			}			 
		}
		catch (Exception ex) 
		{
			System.out.println("Failed to switch the window ");
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1); 
		}

	}
	/*	Keyword Name: CloseSwitchWindow
	Description: This method is used to close the switched window
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void CloseSwitchWindow(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws InterruptedException, IOException  
	{
		if(report == null){
			report =  new ReportFunctions();
		}		
		String result = "fail";	
		try  
		{
			Set<String> allWindowHandles = WebDriver.getWindowHandles();			
			for(String handle : allWindowHandles)	
			{
				if(!handle.equals(ParentWindowHandle))
				{
					WebDriver.close();					
					WebDriver.switchTo().window(ParentWindowHandle);
					Thread.sleep(2000);
					result = "pass";
					System.out.println("Successfully closed the switch window");
					report.LogRepoter(hmap, result, step.getKeyword(), "Successfully closed the switch window");
					break;
				}				
			}			 
		}
		catch (Exception ex) 
		{
			System.out.println("Failed to close switch window ");
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1); 
		}	

	}
	/*	Keyword Name: ClickThroughIteration
	Description: This method is used to click the field using the index.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void ClickThroughIteration(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException 
	{
		if(report == null){
			report =  new ReportFunctions();
		}		
		String result = "fail";		
		try{
			String[] refValues = step.getObjrefValue().split("\\->");
			int index = Integer.parseInt(step.getData1().trim());
			String data2value=step.getData2().trim();
			List<WebElement> element = null;
			if(refValues[0].equals("id"))
			{
				element = WebDriver.findElements(By.id(refValues[1]));
			}
			if(refValues[0].equals("xpath"))
			{
				element = WebDriver.findElements(By.xpath(refValues[1]));
			}
			if(refValues[0].equals("cssSelector"))
			{
				element = WebDriver.findElements(By.cssSelector(refValues[1]));
			}
			if(refValues[0].equals("className"))
			{
				element = WebDriver.findElements(By.className(refValues[1]));
			}
			int count = 0;
			WebElement locator = null;
			for(WebElement w: element)
			{
				if(count == index)
				{
					locator = w;
					break;
				}				
				count = count + 1;
			}		
			if (locator.isEnabled()){						
				locator.click();
				System.out.println("Succesfully clicked on"+" "+data2value);	
				result = "pass";
				report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully clicked"+" "+data2value);
			}
		}
		catch(Exception ex)
		{

			System.out.println("unable to find the locator:"+" "+ex.getMessage());
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1); 
		}		
	}
	/*	Keyword Name: EnterThroughIteration
	Description: This method is used to enter value to the field using the index.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void EnterThroughIteration(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws IOException 
	{
		if(report == null){
			report =  new ReportFunctions();
		}		
		String result = "fail";		
		try{
			String[] refValues = step.getObjrefValue().split("\\->");
			int index = Integer.parseInt(step.getData1().trim());
			String data2value=step.getData2().trim();
			List<WebElement> element = null;
			if(refValues[0].equals("id"))
			{
				element = WebDriver.findElements(By.id(refValues[1]));
			}
			if(refValues[0].equals("xpath"))
			{
				element = WebDriver.findElements(By.xpath(refValues[1]));
			}
			if(refValues[0].equals("cssSelector"))
			{
				element = WebDriver.findElements(By.cssSelector(refValues[1]));
			}
			if(refValues[0].equals("className"))
			{
				element = WebDriver.findElements(By.className(refValues[1]));
			}
			int count = 0;
			WebElement locator = null;
			for(WebElement w: element)
			{
				if(count == index)
				{
					locator = w;
					break;
				}				
				count = count + 1;
			}		
			if (locator.isEnabled()){						
				locator.clear();
				locator.sendKeys(data2value);	
				result = "pass";
				System.out.println("Succesfully entered the value"+" "+data2value);	
				report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully entered the value"+" "+data2value);
			}
		}
		catch(Exception ex)
		{
			System.out.println("unable to find the locator:"+" "+ex.getMessage());
			report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
			System.exit(1); 
		}		
	}
	/*	Keyword Name: SelectDropDownValue
	Description: This method is used to select value from the dropdown field.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
	public void SelectDropDownValue(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap) throws Exception
    {
           if  (report == null){
                  report =  new ReportFunctions();
                  }             
                  String result = "fail";    
                  String var1= null;
                  
                  try{
                        String[] refValues = step.getObjrefValue().split("\\->");                   
                        if (hmap.containsKey(step.getData1().trim())) 
                        {
                               var1 = hmap.get(step.getData1().trim());
                        }
                        else{
                               var1 = step.getData1().trim();
                        }                          
                        
                        if(refValues[0].equals("id"))                                 
                        {
                           WebElement  identifier = WebDriver.findElement(By.id(refValues[1]));
                           Select oSelect = new Select(identifier);
                           List <WebElement> elementCount = oSelect.getOptions();
                           int iSize = elementCount.size();
                           for(int i =0; i<iSize ; i++){
                                  String sValue = elementCount.get(i).getText();
                                  if (sValue.equalsIgnoreCase(var1))
                                  {
                                         result = "true";
                                         if (ISNUMERIC(var1)) {
                                                if (var1.length() == 1) {
                                                       int oindex= Integer.parseInt(var1);
                                                       oSelect.selectByIndex(oindex );
                                                       System.out.println("Selected the element with index "+" "+oindex);
                                                       report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with index"+" "+var1);                                                            
                                                }else{
                                                       oSelect.selectByValue(var1);
                                                       System.out.println("Selected the value "+" "+var1);
                                                       report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with value"+" "+var1);                                                     
                                                
                                                }
                                         }else{
                                                oSelect.selectByVisibleText(var1);
                                                System.out.println("Selected the text"+" "+var1);
                                                report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with visibletext "+" "+var1);
                                                
                                         } 
                                         break;
                                  }
                               
                        }
                        
                        result = "pass";
                        System.out.println("Successfully entered"+" "+var1);
                        report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully entered"+" "+var1);
                        }
                        if(refValues[0].equals("xpath"))
                        {

	                       WebElement  identifier = WebDriver.findElement(By.xpath(refValues[1]));
	                       Select oSelect = new Select(identifier);
	                       List <WebElement> elementCount = oSelect.getOptions();
	                       int iSize = elementCount.size();
	                       for(int i =0; i<iSize ; i++){
	                              String sValue = elementCount.get(i).getText();
	                              if (sValue.equalsIgnoreCase(var1))
	                              {
	                                     result = "true";
	                                     if (ISNUMERIC(var1)) {
	                                            if (var1.length() == 1) {
	                                                   int oindex= Integer.parseInt(var1);
	                                                   oSelect.selectByIndex(oindex );
	                                                   System.out.println("Selected the element with index "+" "+oindex);
	                                                   report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with index"+" "+var1);                                                            
	                                            }else{
	                                                   oSelect.selectByValue(var1);
	                                                   System.out.println("Selected the value "+" "+var1);
	                                                   report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with value"+" "+var1);                                                     
	                                            
	                                            }
	                                     }else{
	                                            oSelect.selectByVisibleText(var1);
	                                            System.out.println("Selected the text"+" "+var1);
	                                            report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with visibletext "+" "+var1);
	                                            
	                                     } 
	                                     break;
	                              }
	                       
	                        }
                        
                        result = "pass";
                        System.out.println("Successfully entered"+" "+var1);
                        report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully entered"+" "+var1);
                        
                }
                if(refValues[0].equals("cssSelector"))
                {

                   WebElement  identifier = WebDriver.findElement(By.cssSelector(refValues[1]));
                   Select oSelect = new Select(identifier);
                   List <WebElement> elementCount = oSelect.getOptions();
                   int iSize = elementCount.size();
                   for(int i =0; i<iSize ; i++){
                          String sValue = elementCount.get(i).getText();
                          if (sValue.equalsIgnoreCase(var1))
                          {
                                 result = "true";
                                 if (ISNUMERIC(var1)) {
                                        if (var1.length() == 1) {
                                               int oindex= Integer.parseInt(var1);
                                               oSelect.selectByIndex(oindex );
                                               System.out.println("Selected the element with index "+" "+oindex);
                                               report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with index"+" "+var1);                                                            
                                        }else{
                                               oSelect.selectByValue(var1);
                                               System.out.println("Selected the value "+" "+var1);
                                               report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with value"+" "+var1);                                                     
                                        
                                        }
                                 }else{
                                        oSelect.selectByVisibleText(var1);
                                        System.out.println("Selected the text"+" "+var1);
                                        report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with visibletext "+" "+var1);
                                        
                                 } 
                                 break;
                          }
                               
                        }
                        
                        result = "pass";
                        System.out.println("Successfully entered"+" "+var1);
                        report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully entered"+" "+var1);
                        
                }
                if(refValues[0].equals("className"))
                {
                       WebElement  identifier = WebDriver.findElement(By.className(refValues[1]));
                       Select oSelect = new Select(identifier);
                       List <WebElement> elementCount = oSelect.getOptions();
                       int iSize = elementCount.size();
                       for(int i =0; i<iSize ; i++){
                              String sValue = elementCount.get(i).getText();
                              if (sValue.equalsIgnoreCase(var1))
                              {
                                     result = "true";
                                     if (ISNUMERIC(var1)) {
                                            if (var1.length() == 1) {
                                                   int oindex= Integer.parseInt(var1);
                                                   oSelect.selectByIndex(oindex );
                                                   System.out.println("Selected the element with index "+" "+oindex);
                                                   report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with index"+" "+var1);                                                            
                                            }else{
                                                   oSelect.selectByValue(var1);
                                                   System.out.println("Selected the value "+" "+var1);
                                                   report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with value"+" "+var1);                                                     
                                            
                                            }
                                     }else{
                                            oSelect.selectByVisibleText(var1);
                                            System.out.println("Selected the text"+" "+var1);
                                            report.LogRepoter(hmap, result, step.getKeyword(), "select value from drop down with visibletext "+" "+var1);
                                            
                                     } 
                                     break;
                              }                               
                       }
                
                    result = "pass";
                    System.out.println("Successfully entered"+" "+var1);
                    report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully entered"+" "+var1);                 
                	}                       
                        
                  }                          
                  
                  catch(Exception ex)
                  {
                        report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
                        System.exit(1); 
                  }
    }
    public static boolean ISNUMERIC(String str)  
	  {  
	        try  
	        {  
	               double d = Double.parseDouble(str);  
	        }  
	        catch(NumberFormatException nfe)  
	        {  
	               return false;	               
	        }  
	        return true;  
	  }
    /*	Keyword Name: CustomWait
	Description: This method is used to wait upto specified time.
	Created By: TGS
	Modified Date: 
	Modified By:	*/
    public void CustomWait(WebDriver WebDriver, TestStep step, HashMap<String, String> hmap)throws IOException
    {
       if(report == null){
              report =  new ReportFunctions();
       }             
       String result = "fail";           
       try{                 
              int sleepTime = Integer.valueOf(step.getData1())*1000;               
              Thread.sleep(sleepTime);                 
              result = "pass";
              System.out.println("Successfully waited for "+" "+sleepTime+" MilliSeconds");
              report.LogRepoter(hmap, result, step.getKeyword(), "Succesfully waited for time "+" "+sleepTime);                 
       }
       catch(Exception ex)
       {
           report.LogRepoter(hmap, result, step.getKeyword(), ex.getMessage());
           System.exit(1); 
       }           
    }

    
    
    
    
    //class
}     
