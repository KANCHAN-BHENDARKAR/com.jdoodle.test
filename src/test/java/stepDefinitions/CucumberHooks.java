package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeStep;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageobjectmanager.PageObjectManager;
import pages.HomePage;
import tests.BaseTest;

import java.security.PublicKey;

public class CucumberHooks  {
    public BaseTest baseTest;
    PageObjectManager pageObjectManager ;
    public CucumberHooks(BaseTest baseTest) {
        this.baseTest = baseTest;
    }

    @Before
    public void beforeScenario(){
      baseTest.openBrowser();
    }

//    @After
//    public void  afterScenario(){
//        baseTest.closeSite();
//    }

}
