import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;


public class Issue_AEInt {
  private WebDriver driver;
  private String baseUrl;
  private boolean acceptNextAlert = true;
  private StringBuffer verificationErrors = new StringBuffer();

  @Before
  public void setUp() throws Exception {
    driver = new FirefoxDriver();
    driver.get("http://localhost/pacs-manager/Login-Logout/Login.aspx");
    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    driver.manage().window().maximize();
  }

  @Test
  public void testIssueAEInt() throws Exception {
    
    driver.findElement(By.id("txtUserName")).clear();
    
    driver.findElement(By.id("txtUserName")).sendKeys("rahul");
    
    driver.findElement(By.id("txtPassword")).clear();
    driver.findElement(By.id("txtPassword")).sendKeys("rahul");
    
    driver.findElement(By.id("btnLogin")).click();
    
    driver.findElement(By.id("hlnkDICOM_Nodes_header")).click();
    
    driver.findElement(By.id("hlnkDICOM_Nodes_content_hlnkPACS_AET_In")).click();
    
    driver.findElement(By.xpath("//table[@id='tblGrid']/tbody/tr[10]/td[2]")).click();
    
    driver.findElement(By.id("cphBody_btnEdit")).click();
    
    if( driver.findElement(By.id("cphBody_chklstServiceRolesSCP_0")).isSelected())
    {
    	driver.findElement(By.id("cphBody_chklstServiceRolesSCP_0")).click();	
    }
    
    driver.findElement(By.id("cphBody_chklstServiceRolesSCP_1")).click();
    
    driver.findElement(By.id("cphBody_btnAdvSettings")).click();
    
    driver.findElement(By.id("cphBody_AEInternalAdvancedSettingsTab_tpnlMisc_btnMiscCancelSettings")).click();
    
    driver.findElement(By.id("cphBody_chklstServiceRolesSCP_0")).click();
    
    driver.findElement(By.id("cphBody_btnAdvSettings")).click();
    Thread.sleep(5000);
    
    driver.findElement(By.id("__tab_cphBody_AEInternalAdvancedSettingsTab_tpnlImport")).click();
    Thread.sleep(5000);
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

