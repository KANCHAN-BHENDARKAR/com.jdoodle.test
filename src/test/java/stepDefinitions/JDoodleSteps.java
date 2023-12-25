package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.remote.RemoteWebDriver;
import pageobjectmanager.PageObjectManager;
import tests.BaseTest;

public class JDoodleSteps {

    public RemoteWebDriver driver;
    PageObjectManager pageObjectManager = new PageObjectManager();

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

    @And("User clicks on the execute button")
    public void userClicksOnTheExecuteButton() {
        pageObjectManager.getHomePage(driver).clickOnExecuteButton();
    }

    @Then("Expected output from the executed Java code should be displayed")
    public void expectedOutputFromTheExecutedJavaCodeShouldBeDisplayed() {
        pageObjectManager.getHomePage(driver).validateOutputResult();
    }

    @When("User selects a different programming language")
    public void userSelectsADifferentProgrammingLanguage() {
        pageObjectManager.getHomePage(driver).changeLanguageOfJavaCode();
    }

    @Then("Code editor should display the selected programming language code")
    public void codeEditorShouldDisplayTheSelectedProgrammingLanguageCode() {
        pageObjectManager.getHomePage(driver).validateOutputResultWithChangeLanguage();
    }

    @When("User enters invalid Java code")
    public void userEntersInvalidJavaCode() {
        pageObjectManager.getHomePage(driver).clearCurrentProject();
        pageObjectManager.getHomePage(driver).enterInvalidCode();
    }

    @Then("Error message for invalid code should be displayed in Output Result")
    public void errorMessageForInvalidCodeShouldBeDisplayedInOutputResult() {
        pageObjectManager.getHomePage(driver).validateInvalidErrorOutputResult();
    }

    @And("User clear Existing code with selection of New Project")
    public void userClearExistingCodeWithSelectionOfNewProject() {
        pageObjectManager.getHomePage(driver).clearCurrentProject();
    }

    @Then("the code input area should be empty")
    public void theCodeInputAreaShouldBeEmpty() {
        pageObjectManager.getHomePage(driver).validateInputAreaIsEmpty();
    }
}
