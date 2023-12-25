package utilities;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver.Timeouts;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.logs.Log;

import java.io.FileOutputStream;
import java.time.Duration;
import java.util.Properties;

public class TestUtility {

    public RemoteWebDriver driver;
    private static FileOutputStream output;
    private static Properties prop;
    public WebDriverWait wait;

    public  RemoteWebDriver openBrowser()
    {
        String browsername = PropertiesFileUtility.getValueFromConfigPropertiesFile("browsername");
        if(browsername.equalsIgnoreCase("chrome"))
        {
            driver = new ChromeDriver();
        }
        else if(browsername.equalsIgnoreCase("firefox"))
        {
            driver=new FirefoxDriver();
        }
        else if(browsername.equalsIgnoreCase("edge"))
        {
            driver=new EdgeDriver();
        }
        else
        {
            //Set IE browser zoom level to 100% manually
            //System.setProperty("webdriver.ie.driver","path of iedriverserver.exe software");
            driver=new InternetExplorerDriver();
        }
        return(driver);
    }

    public FluentWait<RemoteWebDriver> defineWait(RemoteWebDriver driver) throws Exception
    {
        String temp1=PropertiesFileUtility.getValueFromConfigPropertiesFile("maxwait");
        int value1=Integer.parseInt(temp1);
        String temp2=PropertiesFileUtility.getValueFromConfigPropertiesFile("interval");
        int value2=Integer.parseInt(temp2);
        FluentWait<RemoteWebDriver> wait=new FluentWait<RemoteWebDriver>(driver);
        wait.withTimeout(Duration.ofSeconds(value1));
        wait.pollingEvery(Duration.ofMillis(value2));
        return(wait);
    }
    public void launchSite(RemoteWebDriver driver) {
        String url = PropertiesFileUtility.getValueFromConfigPropertiesFile("url");
        driver.get(url);
        driver.manage().window().maximize();
    }

    public void closeSite(RemoteWebDriver driver)
    {
        driver.quit();
    }

   public WebElement waitVisibility(RemoteWebDriver driver, WebElement element){
      wait =  new WebDriverWait(driver,Duration.ofSeconds(15));
     return  wait.until(ExpectedConditions.visibilityOf(element));
   }

    public WebElement waitforClickable(RemoteWebDriver driver, WebElement element){
        wait =  new WebDriverWait(driver,Duration.ofSeconds(15));
        return  wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public Timeouts wait(RemoteWebDriver driver, int time){
       return  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
    }

    public void scrollToBottom(RemoteWebDriver driver) {
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Log.info("Scroll to Bottom");
    }

    public void scrollToTop(RemoteWebDriver driver) {
        driver.executeScript("window.scrollTo(document.body.scrollHeight,0)");
        Log.info("Scroll to Top");
    }

    public void scrollToSpecificWebElement(RemoteWebDriver driver,WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
        Log.info("Scroll to Specific Element");
    }


    public TestUtility scrollToSpecificWebElement1(RemoteWebDriver driver, WebElement element) {
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();",element);
        Log.info("Scroll to Specific Element");
       return this;
    }

}
