package CommonFunLibrary;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class StockLibrary {
	
	String res;
	WebDriver driver;
	
	//App Launch
	public String appLaunch(String url)
	{
	  System.setProperty("webdriver.chrome.driver", "E:\\selenium programs\\MavenProjectOrangeHrm\\DriverFiles\\chromedriver.exe");
	  driver = new ChromeDriver();
	  driver.get(url);
	  driver.manage().window().maximize();
	  //validation
	  if (driver.findElement(By.id("txtUsername")).isDisplayed()) 
	  {
		res="Pass";		
	   }
	  else
	  {
		 res="Fail";
	  }
	  return res;	  		
	}
	
	/*public static void main(String[] args) throws Throwable
	{
	  StockLibrary app = new StockLibrary();
	  String results = app.appLaunch("http://webapp.qedge.com/login.php");
	  //System.out.println(results);
	  app.appLogin("admin","master");
	  Thread.sleep(2000);
	  //String result = app.supplierCreation("redmimc","ameerpet","Hyd","mc","india","1234","mc@12344","3324234","sb notes sb");
	  String result = app.createStockCategory("MyCat29jul20193");
	  System.out.println(result);
	  app.appLogout();
	  app.appClose();				
	} */
	
	public String appLogin(String username,String password)
	{
	  driver.findElement(By.id("txtUsername")).clear();
	  driver.findElement(By.id("txtUsername")).sendKeys(username);
	  driver.findElement(By.id("txtPassword")).clear();
	  driver.findElement(By.id("txtPassword")).sendKeys(password);
	  driver.findElement(By.id("btnLogin")).click();
	  if (driver.findElement(By.id("welcome")).isDisplayed())
	  {
		res="Pass";	
	  }
	  else
	  {
		res="Fail";
	  }
	  return res;
	}
	public String appLogout() throws Exception
	{
		//click on welcome user
				WebElement welcome = driver.findElement(By.xpath("//*[@id='welcome']"));
				welcome.click();
				WebElement about = driver.findElement(By.xpath("//a[@id='aboutDisplayLink']"));
				WebElement changePassword = driver.findElement(By.xpath("//a[contains(text(),'Change Password')]"));
				WebElement logOut = driver.findElement(By.xpath("//a[contains(text(),'Logout')]"));
				
				Actions action = new Actions(driver);
//				action.moveToElement(welcome).build().perform();
				action.moveToElement(about).build().perform();
				action.moveToElement(changePassword).build().perform();
				action.moveToElement(logOut).click().perform();
				if(driver.findElement(By.xpath("//input[@id='txtUsername']")).isDisplayed())
				{
					res="Pass";
				}else
				{
					res="Fail";
				}
		
		return res;		
	}
	
	public void appClose()
	{
	   driver.close();			
	}
	
	public String userCreation(String empName1,String text, String username1,String password1,String confmPassword1) throws Throwable
	{
		//Mouse move to admin,usermanagement, and user
				WebElement admin = driver.findElement(By.xpath("//b[contains(text(),'Admin')]"));
				WebElement usermanagement = driver.findElement(By.xpath("//a[@id='menu_admin_UserManagement']"));
				WebElement user = driver.findElement(By.xpath("//a[@id='menu_admin_viewSystemUsers']"));
				
				Actions action = new Actions(driver);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				action.moveToElement(admin).build().perform();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				action.moveToElement(usermanagement).build().perform();
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				action.moveToElement(user).click().perform();
				
				//click on add
				driver.findElement(By.xpath("//input[@id='btnAdd']")).click();;
				
				//select user role
				 new Select(driver.findElement(By.xpath("//select[@id='systemUser_userType']"))).selectByVisibleText(text);;
//						
				//enter employee name
				WebElement empName = driver.findElement(By.xpath("//input[@id='systemUser_employeeName_empName']"));
				empName.sendKeys(empName1);

				Actions action1 = new Actions(driver);				
				action1.sendKeys(Keys.ARROW_DOWN+""+Keys.ENTER).build().perform();				
				WebElement username = driver.findElement(By.xpath("//input[@id='systemUser_userName']"));
				username.sendKeys(username1);
				
				//enter password
				WebElement password = driver.findElement(By.xpath("//input[@id='systemUser_password']"));
				password.sendKeys(password1);
				WebElement conPassword = driver.findElement(By.xpath("//input[@id='systemUser_confirmPassword']"));
				conPassword.sendKeys(confmPassword1);
				conPassword.sendKeys(Keys.TAB);
				
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				//click on save button

				Thread.sleep(5000);
				
				//click on save button
				driver.findElement(By.xpath("//input[@name='btnSave'][@id='btnSave']")).click();
//				save.click();
				if(driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).isDisplayed())
				{
					driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).clear();
					driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys(username1);
					driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
				}else
				{
					driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).clear();
					driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys(username1);
					driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
				}
				
				//a[contains(text(),'2000Rajkumar')]
				String act_data = driver.findElement(By.xpath("//a[contains(text(),'2000Rajkumar')]")).getText();
				System.out.println(act_data);
				if(username1.equals(act_data))
				{
					res="Pass";
				}else
				{
					res="Fail";
				}
				return res;


	}
	

 	public String supplierCreation(String suppliername,String address,String city,String country,String contactperson,String phonenumber,String email,String mobilenumber,String notes) throws Exception
 
	{
		driver.findElement(By.xpath("//*[@id='mi_a_suppliers']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a/span")).click();
		Thread.sleep(2000);
		String exp_data = driver.findElement(By.xpath("//*[@id='x_Supplier_Number']")).getAttribute("value");	
		System.out.println(exp_data);
		driver.findElement(By.xpath("//*[@id='x_Supplier_Name']")).sendKeys(suppliername);
		//driver.findElement(By.xpath("//*[@id='x_Supplier_Name']")).sendKeys(suppliername);
		driver.findElement(By.xpath("//*[@id='x_Address']")).sendKeys(address);
		driver.findElement(By.xpath("//*[@id='x_City']")).sendKeys(city);
		driver.findElement(By.xpath("//*[@id='x_Country']")).sendKeys(country);
		driver.findElement(By.xpath("//*[@id='x_Contact_Person']")).sendKeys(contactperson);
		driver.findElement(By.xpath("//*[@id='x_Phone_Number']")).sendKeys(phonenumber);
		driver.findElement(By.xpath("//*[@id='x__Email']")).sendKeys(email);
		driver.findElement(By.xpath("//*[@id='x_Mobile_Number']")).sendKeys(mobilenumber);
	    driver.findElement(By.xpath("//*[@id='x_Notes']")).sendKeys(notes);	
	   Actions action = new Actions(driver);
	   action.sendKeys(Keys.PAGE_DOWN).build().perform();
	    Thread.sleep(2000);
	    driver.findElement(By.xpath("//*[@id='btnAction']")).click();	
	    Thread.sleep(2000);
        driver.findElement(By.xpath("(//*[text()='OK!'])[1]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();    
        Thread.sleep(2000);
        if (!driver.findElement(By.xpath("//*[@id='psearch']")).isDisplayed())
        {
        	driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button")).click();
            Thread.sleep(2000);	
        }        
        driver.findElement(By.xpath("//*[@id='psearch']")).clear();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(exp_data);
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
        Thread.sleep(2000);
      //*[@id="el1_a_suppliers_Supplier_Number"]/span
        String act_data = driver.findElement(By.xpath("//*[@id='el1_a_suppliers_Supplier_Number']/span")).getText();
        System.out.println(act_data);
       if (exp_data.equals(act_data))
       {
        res="Pass";      	   
       }
       else
       {
    	res="Fail";
       }       
       return res;                     
	}

	public String createStockCategory(String categoryName) throws Exception
	{
		/*driver.findElement(By.xpath("//*[@id='mi_a_stock_items']/a")).click();
		Thread.sleep(6000);
		driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']/a")).click();
		*/
		Actions action = new Actions(driver);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_items']/a"))).build().perform();
		Thread.sleep(2000);
		action.moveToElement(driver.findElement(By.xpath("//*[@id='mi_a_stock_categories']/a"))).click().build().perform();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[3]/div[1]/div[1]/div[1]/div/a/span")).click();		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='x_Category_Name']")).sendKeys(categoryName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='btnAction']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK!'])[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("(//*[text()='OK'])[6]")).click();
		Thread.sleep(2000);        
		driver.findElement(By.xpath("//*[@id='ewContentColumn']/div[2]/div[2]/div/button/span")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='psearch']")).sendKeys(categoryName);
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@id='btnsubmit']")).click();
		Thread.sleep(2000);
		String act_stockCategory = driver.findElement(By.xpath("//*[@id='el1_a_stock_categories_Category_Name']/span")).getText();
		System.out.println(categoryName);
		System.out.println(act_stockCategory);
		if (categoryName.equals(act_stockCategory)) {
		   res="Pass";
		}
		else
		{
			res="Fail";			
		}
				
		return res;
	  	
	}
	public String Qualification_Skills(String name1, String description1)
	{
		WebElement admin = driver.findElement(By.xpath("//b[contains(text(),'Admin')]"));
		WebElement qualification = driver.findElement(By.xpath("//a[@id='menu_admin_Qualifications']"));
		WebElement skills = driver.findElement(By.xpath("//a[@id='menu_admin_viewSkills']"));
		
		
		Actions action = new Actions(driver);
		action.moveToElement(admin).build().perform();
		action.moveToElement(qualification).click().perform();
		action.moveToElement(skills).click().perform();
		
		//click on add button
		WebElement add = driver.findElement(By.xpath("//input[@id='btnAdd']"));
		add.click();
		String exp_data = driver.findElement(By.xpath("//input[@id='skill_name']")).getAttribute("id");
		System.out.println(exp_data);
		
		//Enter name
		WebElement name = driver.findElement(By.xpath("//input[@id='skill_name']"));
		name.sendKeys(name1);
		
		//Enter description
		WebElement description = driver.findElement(By.xpath("//input[@id='skill_name']"));
		description.sendKeys(description1);
		
		//click on save button
		WebElement save = driver.findElement(By.xpath("//input[@id='btnSave']"));
		save.click();
		

		return res;
		
	}
	public String Qualification_Education(String level)
	{
		WebElement admin = driver.findElement(By.xpath("//b[contains(text(),'Admin')]"));
		WebElement qualification = driver.findElement(By.xpath("//a[@id='menu_admin_Qualifications']"));
		WebElement skills = driver.findElement(By.xpath("//a[@id='menu_admin_viewSkills']"));
		WebElement education = driver.findElement(By.xpath("//a[@id='menu_admin_viewEducation']"));
		
		Actions action = new Actions(driver);
		action.moveToElement(admin).build().perform();
		action.moveToElement(qualification).click().perform();
		action.moveToElement(skills).build().perform();
		action.moveToElement(education).click().perform();
		
		//click on add button
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		
		//Enter qualification level
		driver.findElement(By.xpath("//input[@id='education_name']")).sendKeys(level);
		
		//click on save button
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		if(driver.findElement(By.xpath("//input[@id='btnAdd']")).isDisplayed())
		{
			res="Pass";
		}else
		{
			res="Fail";
		}
		return res;
		
		
	}
	public String Qualifications_License(String name)
	{
		WebElement admin = driver.findElement(By.xpath("//b[contains(text(),'Admin')]"));
		WebElement qualification = driver.findElement(By.xpath("//a[@id='menu_admin_Qualifications']"));
		WebElement skills = driver.findElement(By.xpath("//a[@id='menu_admin_viewSkills']"));
		WebElement education = driver.findElement(By.xpath("//a[@id='menu_admin_viewEducation']"));
		WebElement license = driver.findElement(By.xpath("//a[@id='menu_admin_viewLicenses']"));
		
		Actions action = new Actions(driver);
		action.moveToElement(admin).build().perform();
		action.moveToElement(qualification).click().perform();
		action.moveToElement(skills).build().perform();
		action.moveToElement(education).build().perform();
		action.moveToElement(license).click().perform();
		
		//click on add button
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		//Enter name
		driver.findElement(By.xpath("//input[@id='license_name']")).sendKeys(name);
		
		//click on save button
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		if(driver.findElement(By.xpath("//input[@id='btnAdd']")).isDisplayed())
		{
			res="Pass";
		}else
		{
			res="False";
		}
		return res;
		

	}
	public String Qualifications_Languages(String languagName)
	{
		WebElement admin = driver.findElement(By.xpath("//b[contains(text(),'Admin')]"));
		WebElement qualification = driver.findElement(By.xpath("//a[@id='menu_admin_Qualifications']"));
		WebElement skills = driver.findElement(By.xpath("//a[@id='menu_admin_viewSkills']"));
		WebElement education = driver.findElement(By.xpath("//a[@id='menu_admin_viewEducation']"));
		WebElement license = driver.findElement(By.xpath("//a[@id='menu_admin_viewLicenses']"));
		WebElement languages = driver.findElement(By.xpath("//a[@id='menu_admin_viewLanguages']"));
		
		Actions action = new Actions(driver);
		action.moveToElement(admin).build().perform();
		action.moveToElement(qualification).click().perform();
		action.moveToElement(skills).build().perform();
		action.moveToElement(education).build().perform();
		action.moveToElement(license).build().perform();
		action.moveToElement(languages).click().perform();
		
		//click on add button
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		//Enter name
		driver.findElement(By.xpath("//input[@id='language_name']")).sendKeys(languagName);
		
		//click on save button
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		if(driver.findElement(By.xpath("//input[@id='btnAdd']")).isDisplayed())
		{
			res="Pass";
		}else
		{
			res="False";
		}
		return res;
		

	}
	public String Qualifications_Membership(String membership)
	{
		WebElement admin = driver.findElement(By.xpath("//b[contains(text(),'Admin')]"));
		WebElement qualification = driver.findElement(By.xpath("//a[@id='menu_admin_Qualifications']"));
		WebElement skills = driver.findElement(By.xpath("//a[@id='menu_admin_viewSkills']"));
		WebElement education = driver.findElement(By.xpath("//a[@id='menu_admin_viewEducation']"));
		WebElement license = driver.findElement(By.xpath("//a[@id='menu_admin_viewLicenses']"));
		WebElement languages = driver.findElement(By.xpath("//a[@id='menu_admin_viewLanguages']"));
		WebElement Membership = driver.findElement(By.xpath("//a[@id='menu_admin_membership']"));
		
		Actions action = new Actions(driver);
		action.moveToElement(admin).build().perform();
		action.moveToElement(qualification).click().perform();
		action.moveToElement(skills).build().perform();
		action.moveToElement(education).build().perform();
		action.moveToElement(license).build().perform();
		action.moveToElement(languages).build().perform();
		action.moveToElement(Membership).click().perform();
		
		//click on add button
		driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		//Enter name

		driver.findElement(By.xpath("//input[@id='membership_name']")).sendKeys(membership);
		
		//click on save button
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();
		if(driver.findElement(By.xpath("//input[@id='btnAdd']")).isDisplayed())
		{
			res="Pass";
		}else
		{
			res="False";
		}
		return res;
		

	}
	public String Admin_Nationalities(String nationalities)
	{
		WebElement admin = driver.findElement(By.xpath("//b[contains(text(),'Admin')]"));
		WebElement Nationlities = driver.findElement(By.xpath("//a[@id='menu_admin_nationality']"));
		
		Actions action = new Actions(driver);
		action.moveToElement(Nationlities).click().perform();
		
		//click on add button
				driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
				//Enter name

				driver.findElement(By.xpath("//input[@id='nationality_name']")).sendKeys(nationalities);
				
				//click on save button
				driver.findElement(By.xpath("//input[@id='btnSave']")).click();
				if(driver.findElement(By.xpath("//input[@id='btnAdd']")).isDisplayed())
				{
					res="Pass";
				}else
				{
					res="False";
				}
				return res;	
	}
	
	public String PIM_EmployeeList_Add(String firstName,String middleName,String lastName,String username,String password,String cfmpassword ) throws Throwable
	{
		//click on PIM
		driver.findElement(By.xpath("//b[contains(text(),'PIM')]")).click();
		
		//click on Employee list
		driver.findElement(By.xpath("//a[@id='menu_pim_viewEmployeeList']")).click();
		
		//click on addd button
		if(driver.findElement(By.xpath("//input[@id='btnAdd']")).isDisplayed())
		{
			driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		}else
		{
			driver.findElement(By.xpath("//input[@id='btnAdd']")).click();
		}
		
		//Enter full name
		if(driver.findElement(By.xpath("//input[@id='firstName']")).isDisplayed())
		{
			//enter first name
			driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(firstName);
			//enter middle name
			driver.findElement(By.xpath("//input[@id='middleName']")).sendKeys(middleName);
			//enter last name
			driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastName);
		}
		else
		{
			//enter first name
			driver.findElement(By.xpath("//input[@id='firstName']")).sendKeys(firstName);
			//enter middle name
			driver.findElement(By.xpath("//input[@id='middleName']")).sendKeys(middleName);
			//enter last name
			driver.findElement(By.xpath("//input[@id='lastName']")).sendKeys(lastName);
		}
		
		String exp_data = driver.findElement(By.xpath("//input[@id='employeeId']")).getAttribute("id");
		System.out.println(exp_data);
		
		//choose photograph
		WebElement chooseFile = driver.findElement(By.xpath("//input[@id='photofile']"));
		chooseFile.click();
		
		//click on check box
		driver.findElement(By.xpath("//input[@id='chkLogin']")).click();
		
		//enter username
		driver.findElement(By.xpath("//input[@id='user_name']")).sendKeys(username);
		
		//enter password
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(password);
		
		//confirm password
		driver.findElement(By.xpath("//input[@id='user_password']")).sendKeys(cfmpassword);
		
		//click on save button
		driver.findElement(By.xpath("//input[@id='btnSave']")).click();

				return res;
		
		
		
	}
}



