package pageobjectmanager;

import org.openqa.selenium.remote.RemoteWebDriver;
import pages.HomePage;

public class PageObjectManager {
    private HomePage homePage = null;
    public HomePage getHomePage(RemoteWebDriver driver) {
        return (homePage == null) ? homePage = new HomePage(driver) : homePage;
    }

}
