package CommonFunLibrary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Utilities.PropertyFileUtil;

public class FunctionLibrary {
	
	WebDriver driver;
	
	public static WebDriver startBrowser(WebDriver driver) throws Throwable
	{
		if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Firefox"))
     	{
		  driver=new FirefoxDriver();
	    }else
	    	if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("Chrome"))
 	        {
 	           System.setProperty("webdriver.chrome.driver","F:\\Downloads\\Project File of Qedge\\Mohanch(Automation)\\StockAccounting\\DriverFiles\\chromedriver.exe");
	           driver=new ChromeDriver();
            }else	
            	if (PropertyFileUtil.getValueForKey("Browser").equalsIgnoreCase("IE"))
 	           {
 	           System.setProperty("webdriver.ie.driver", "F:\\Downloads\\Project File of Qedge\\Mohanch(Automation)\\StockAccounting\\DriverFiles\\IEDriverServer.exe");
	           driver=new InternetExplorerDriver();
               }
		return driver;
	}

	//openApplication
	public static void openApplication(WebDriver driver) throws Throwable
	{
		driver.manage().window().maximize();		
		driver.get(PropertyFileUtil.getValueForKey("URL"));		
	}
	
	//click Action
	public static void ClickAction(WebDriver driver, String locatorType,String locatorValue)
	{
		if (locatorType.equalsIgnoreCase("id"))
		{
		  driver.findElement(By.id(locatorValue)).click();
		}else
			if (locatorType.equalsIgnoreCase("name"))
			{
			  driver.findElement(By.id(locatorValue)).click();
			}else
				if (locatorType.equalsIgnoreCase("xpath"))
				{
				  driver.findElement(By.xpath(locatorValue)).click();
				}
				
	}
	
	//type Action
	public static void typeAction(WebDriver driver, String locatorType,String locatorValue,String data)
	{
		if (locatorType.equalsIgnoreCase("id"))
		{
		  driver.findElement(By.id(locatorValue)).clear();
		  driver.findElement(By.id(locatorValue)).sendKeys(data);
		}else
			if (locatorType.equalsIgnoreCase("name"))
			{
				driver.findElement(By.name(locatorValue)).clear();
				driver.findElement(By.name(locatorValue)).sendKeys(data);
			}else
				if (locatorType.equalsIgnoreCase("xpath"))
				{
					driver.findElement(By.xpath(locatorValue)).clear();
					driver.findElement(By.xpath(locatorValue)).sendKeys(data);
				}
				
	}
	
	//CloseBrowser
	public static void closeBrowser(WebDriver driver)
	{
	  driver.close();	
	}
	
	public static void waitForElement(WebDriver driver, String locatorType,String locatorValue,String waitTime)
	{
	  WebDriverWait myWait = new WebDriverWait(driver, Integer.parseInt(waitTime));
	  if (locatorType.equalsIgnoreCase("id"))
	  {
		  myWait.until(ExpectedConditions.visibilityOfElementLocated(By.id(locatorValue))); 
	  }else
		  if (locatorType.equalsIgnoreCase("name"))
		  {
			  myWait.until(ExpectedConditions.visibilityOfElementLocated(By.name(locatorValue))); 
		  }else
			  if (locatorType.equalsIgnoreCase("xpath"))
			  {
				  myWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorValue))); 
			  }
	}
	public static void mauseAction1(WebDriver driver)throws Throwable
	{
		Actions action = new Actions(driver);
		action.click();
	}
	
	public static void mouseAction(WebDriver driver) throws Throwable
	{
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
		action.sendKeys(Keys.ARROW_DOWN+ "" +Keys.ENTER).build().perform();
		Thread.sleep(2000);
	}
	public static void mouseActionTab(WebDriver driver) throws Throwable
	{
		Actions action = new Actions(driver);
		action.sendKeys(Keys.TAB).build().perform();
	}
	public static void selectAction(WebDriver driver, String locatorType,String locatorValue,String text) throws Throwable
	{
		if(locatorType.equalsIgnoreCase("xpath"))
		{
			 new Select(driver.findElement(By.xpath(locatorValue))).selectByVisibleText(text);
		}else
			if(locatorType.equalsIgnoreCase("xpath"))
			{
				new Select(driver.findElement(By.xpath(locatorValue))).selectByValue(text);
			}
	}
	public static void keyDown(WebDriver driver, String locatorType,String locatorValue,String keydown)
	{
		if(locatorType.equalsIgnoreCase("id"))
		{
			driver.findElement(By.id(locatorValue)).sendKeys(Keys.DOWN);
		}else
			if(locatorType.equalsIgnoreCase("name"))
		{
			driver.findElement(By.name(locatorValue)).sendKeys(Keys.DOWN);
		}else
			if(locatorType.equalsIgnoreCase("xpath"))
			{
				driver.findElement(By.xpath(locatorValue)).sendKeys(Keys.DOWN);
			}
	}
	public static void captureData(WebDriver driver,String locatorType,String locatorValue) throws Throwable
	{
		String data="";
		if(locatorType.equalsIgnoreCase("id"))
		{
			data=driver.findElement(By.id(locatorValue)).getAttribute("value");
		}else
			if(locatorType.equalsIgnoreCase("name"))
			{
				data=driver.findElement(By.name(locatorValue)).getAttribute("value");
			}else
				if(locatorType.equalsIgnoreCase("xpath"))
				{
					data=driver.findElement(By.xpath(locatorValue)).getAttribute("value");
				}
		
		FileWriter fw=new FileWriter("E:\\selenium programs\\MavenProjectOrangeHrm\\CaptureData\\data.txt");
		BufferedWriter bw=new BufferedWriter(fw);
		bw.write(data);
		bw.flush();
		bw.close();
	}
	public static void tableValidation(WebDriver driver,String colNum) throws Exception
	{
	FileReader fr = new FileReader("E:\\selenium programs\\MavenProjectOrangeHrm\\CaptureData\\data.txt");
	BufferedReader br = new BufferedReader(fr);
	String exp_data = br.readLine();
	
	/*
		List<WebElement> table = driver.findElements(By.xpath("//table[@id='resultTable']//tbody//tr"));
		int rowCount = table.size();
		System.out.println("The no of rows are in the table :"+rowCount);
		//*[@id="resultTable"]/tbody/tr[1]/td[2]
		//*[@id="resultTable"]/tbody/tr[1]/td[2]
		//*[@id="resultTable"]/tbody/tr[3]/td[2]
		String beforeXpath ="//*[@id='resultTable']/tbody/tr[";
		String afterXpath = "]/td[+colNum+]";
		for(int i=1;i<=rowCount; i++)
		{
			String actualXpath =beforeXpath+i+afterXpath;
			String ele = driver.findElement(By.xpath(actualXpath)).getText();
			System.out.println(ele);
			try
			{
				if(ele.contains("1999dhoni"))
					{
		//				driver.findElement(By.xpath("//*[@id=\"resultTable\"]/tbody/tr[+i+]/td[1]/a")).click();
						driver.findElement(By.xpath("//a[contains(text(),'1999dhoni')]")).click();
						
						//click on edit
						WebElement edit = driver.findElement(By.xpath("//input[@id='btnSave']"));
						edit.click();
						
						//select user role
						WebElement userRole = driver.findElement(By.xpath("//select[@id='systemUser_userType']"));
						Select select = new Select(userRole);
						select.selectByVisibleText("Admin");
						
						//change password
						WebElement changePassword = driver.findElement(By.xpath("//input[@id='systemUser_chkChangePassword']"));
						changePassword.click();
						if(driver.findElement(By.xpath("//input[@id='systemUser_password']")).isDisplayed())
							{
								WebElement newPassword = driver.findElement(By.xpath("//input[@id='systemUser_password']"));
								newPassword.sendKeys("Virat@123IJKLabcd123");
								
								WebElement connewPassword = driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']"));
								connewPassword.sendKeys("Virat@123IJKLabcd123");
								
							}else
							{
								System.out.println("Change password is not displayed");
							}
						
						//click on save button
						WebElement saveButtton = driver.findElement(By.xpath("//input[@id='btnSave'][@name='btnSave']"));
						Thread.sleep(5000);
						saveButtton.click();
			
					}
			}
			catch(Exception e)
			{
				System.out.println("Exception is raised ");
			}
			
			
	}*/
	//table validation
			List<WebElement> table = driver.findElements(By.xpath("//table[@id='recordsListTable']//tr"));
			
			//count the row
			int rowCount = table.size();
			System.out.println("The no of rows are :"+rowCount);
			//*[@id="recordsListTable"]/tbody/tr[1]/td[2]
			//*[@id="recordsListTable"]/tbody/tr[2]/td[2];
			
			String beforexapth = "//*[@id='recordsListTable']/tbody/tr[";
			String afterxpath ="]/td[2]";
			for(int i=2; i<=rowCount;i++)
			{
				String actualxapth =beforexapth+i+afterxpath;
				WebElement ele = driver.findElement(By.xpath(actualxapth));
				System.out.println(ele.getText());
				if(ele.getText().contains("Chetan Varma"))
				{
					driver.findElement(By.xpath("//a[contains(text(),'Chetan Varma')]")).click();
				}
			}
	}

	
	public static void clickStockCategories(WebDriver driver) throws Throwable
	{
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_items']/a"))).build().perform();
		Thread.sleep(2000);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']/a"))).click().build().perform();
		Thread.sleep(2000);
	}	
	public static void fileUploading(String passport) throws Throwable
	{
		Runtime.getRuntime().exec(passport);

	}
	public static void main(String[] args) throws Throwable {
		
		WebDriver MyDriver;		
		MyDriver = FunctionLibrary.startBrowser(new FirefoxDriver());
	}

	public static String generateDate() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY_MM_dd_ss");
		return sdf.format(date);
	}

}
