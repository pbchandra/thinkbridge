import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Problem2_Siginup_test_case {

    private WebDriver driver;
    private String baseUrl;

    @BeforeEach
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "http://jt-dev.azurewebsites.net/#/SignUp";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testMethod() throws Exception {

        //un comment below two lines if settings tab is opening in chrome browser
       /* ArrayList <String> newTb = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(newTb.get(1)); */

        driver.get(baseUrl);
        driver.findElement(By.xpath("//div[@id='language']/div[1]/span")).click();
      List<WebElement> li=  driver.findElements(By.xpath("//div[@id='language']/ul/li/div/a"));
      List<String> expected =new ArrayList<String>();
      expected.add("English");
      expected.add("Dutch");
      for(int i=0;i<li.toArray().length;i++){
          System.out.println(li.get(i).getText());
          Assertions.assertEquals(expected.get(i),li.get(i).getText());

      }
      driver.findElement(By.id("name")).sendKeys("tester");
      driver.findElement(By.id("orgName")).sendKeys("orgName");
      driver.findElement(By.id("singUpEmail")).sendKeys("singUpEmail@email.com");
      driver.findElement(By.xpath("//form/fieldset/div[4]/label/span")).click();
      driver.findElement(By.xpath("//button[@type='submit']")).click();
      String expected_msg = "A welcome email has been sent. Please check your email.";
      WebDriverWait wait = new WebDriverWait(driver,5);
      WebElement email= wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//form/div/span")));
      String email_msg = driver.findElement(By.xpath("//form/div/span")).getText();
      Assertions.assertEquals(expected_msg,email_msg);

    }


    @AfterEach
    public void tearDown()  {
        driver.quit();
    }
}
