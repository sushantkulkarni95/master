import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.*;

import static org.junit.Assert.*;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import testlink.api.java.client.TestLinkAPIClient;
import testlink.api.java.client.TestLinkAPIException;
import testlink.api.java.client.TestLinkAPIResults;


public class Issue53 {
  private WebDriver driver;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();
  
  public static String DEVKEY="4b445fb6247afa4b6baa59505039bdd7";

  public static String URL="http://localhost:5050/testlink/lib/api/xmlrpc/v1/xmlrpc.php";
  
  public static void reportResult(String TestProject,String TestPlan,String Testcase,String Build,String Notes,String Result) throws TestLinkAPIException
  {

	  TestLinkAPIClient api=new TestLinkAPIClient(DEVKEY, URL);

	  api.reportTestCaseResult(TestProject, TestPlan, Testcase, Build, Notes, Result);

  }

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.get("http://localhost/sipl_webmanager_old/Login-Logout/Login.aspx"); 
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void testIssue53() throws Exception {
	  
      Issue53 c = new  Issue53();  
	  
	  String testProject="PACS Manager 2.0";

	  String testPlan="PACS Manager Test Plan";

	  String testCase="Storage: Multiple Institutes shown for multiple edits";

	  String build="PACS Manager 2.0 Release";

	  String notes=null;

	  String result=null;    
	  
	  try
	  {
		
		    driver.findElement(By.id("txtUserName")).clear();
		    driver.findElement(By.id("txtUserName")).sendKeys("rahul");
		    driver.findElement(By.id("txtPassword")).clear();
		    driver.findElement(By.id("txtPassword")).sendKeys("rahul");
		    driver.findElement(By.id("btnLogin")).click();
		    driver.findElement(By.id("hlnkMIS_Reports_header")).click();
		    driver.findElement(By.id("hlnkMIS_Reports_content_hlnkStorage")).click(); 
		    Thread.sleep(2000);
		    
		    driver.findElement(By.id("cphBody_btnAddStorageSystem")).click();
		    List<WebElement> before=new Select(driver.findElement(By.id("cphBody_ddlStorageSystemInstitutePopup"))).getAllSelectedOptions();
		    int count1=before.size();
		    driver.findElement(By.id("cphBody_btnStorageSystemCancelPopup")).click();
		    Thread.sleep(2000);
		    
		    driver.findElement(By.id("cphBody_SubsystemPanel_1_btnLock")).click();
		    driver.findElement(By.xpath("//tr[@id='SubsystemPanel_1_RowNo3']/td[1]")).click();
		    driver.findElement(By.id("cphBody_SubsystemPanel_1_btnGridEdit")).click();
		    driver.findElement(By.id("cphBody_SubsystemPanel_1_btnStorageComponentCancelPopupModified")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.id("cphBody_SubsystemPanel_1_btnLock")).click();
		    Thread.sleep(2000);
		    
		    driver.findElement(By.id("cphBody_SubsystemPanel_2_btnLock")).click();
		    driver.findElement(By.xpath("//tr[@id='SubsystemPanel_2_RowNo1002']/td[3]")).click();
		    driver.findElement(By.id("cphBody_SubsystemPanel_2_btnGridEdit")).click();
		    driver.findElement(By.id("cphBody_SubsystemPanel_2_btnStorageComponentCancelPopupModified")).click();
		    Thread.sleep(2000);
		    driver.findElement(By.id("cphBody_SubsystemPanel_2_btnLock")).click();
		    Thread.sleep(5000);
		    
		    driver.findElement(By.id("cphBody_btnAddStorageSystem")).click();
		    List<WebElement> after=new Select(driver.findElement(By.id("cphBody_ddlStorageSystemInstitutePopup"))).getAllSelectedOptions();
		    int count2=after.size();
		    
		    
		   if(count1==count2)
		   {   
			   result= TestLinkAPIResults.TEST_PASSED;

		       notes="Executed successfully"; 
		        
			   System.out.println("Test Passed!!"); 
		   }
		   else
		   {
			    result=TestLinkAPIResults.TEST_FAILED;

			    notes="Execution failed";
			   
			   System.out.println("Test Failed!!"); 
		   }
		 
		    Thread.sleep(5000);
		  
		  }
	    catch(Exception e)
	    {
	         result=TestLinkAPIResults.TEST_FAILED;

		     notes="Execution failed";
			    
			 System.out.println("Test Failed in catch!!");
	    }
	  finally
	  {

	   	 c.reportResult(testProject, testPlan, testCase, build, notes, result);
         
	     driver.quit();
	    	
	     System.out.println("Result reported!!");
	  }
}
    
    

  @After
  public void tearDown() throws Exception {
    driver.quit();
    String verificationErrorString = verificationErrors.toString();
    if (!"".equals(verificationErrorString)) {
      fail(verificationErrorString);
    }
  }

  private boolean isElementPresent(By by) {
    try {
      driver.findElement(by);
      return true;
    } catch (NoSuchElementException e) {
      return false;
    }
  }

  private boolean isAlertPresent() {
    try {
      driver.switchTo().alert();
      return true;
    } catch (NoAlertPresentException e) {
      return false;
    }
  }

  private String closeAlertAndGetItsText() {
    try {
      Alert alert = driver.switchTo().alert();
      String alertText = alert.getText();
      if (acceptNextAlert) {
        alert.accept();
      } else {
        alert.dismiss();
      }
      return alertText;
    } finally {
      acceptNextAlert = true;
    }
  }
}




