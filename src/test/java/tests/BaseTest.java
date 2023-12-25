package tests;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utilities.PropertiesFileUtility;

public class BaseTest {

    public RemoteWebDriver driver;

    public  RemoteWebDriver openBrowser()
    {
        String browsername = PropertiesFileUtility.getValueFromConfigPropertiesFile("browsername");
        if(browsername.equalsIgnoreCase("chrome"))
        {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.setPageLoadStrategy(PageLoadStrategy.EAGER);
            driver = new ChromeDriver(chromeOptions);
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

    public void closeSite()
    {
        driver.quit();
    }

}
