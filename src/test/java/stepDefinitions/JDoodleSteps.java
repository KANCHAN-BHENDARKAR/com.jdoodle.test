package stepDefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageobjectmanager.PageObjectManager;
import pages.HomePage;
import tests.BaseTest;

public class JDoodleSteps {

    public  RemoteWebDriver driver ;
    PageObjectManager  pageObjectManager = new PageObjectManager();

    public JDoodleSteps(BaseTest baseTest) {
        this.driver = baseTest.driver;
    }

    @Given("User navigates to the JDoodle Compiler website")
    public void userNavigatesToTheJDoodleCompilerWebsite() {
        pageObjectManager.getHomePage(driver).goToJDoodleOnlineCompilerPage();
    }

    @Then("JDoodle homepage should be displayed")
    public void jdoodleHomepageShouldBeDisplayed() {
        pageObjectManager.getHomePage(driver).verifyJDoodleOnlineCompilerPage();
    }

    @When("User enters valid Java code")
    public void userEntersValidJavaCode() {
        pageObjectManager.getHomePage(driver).clearCurrentProject();
        pageObjectManager.getHomePage(driver).enterValidCode();
    }
}
