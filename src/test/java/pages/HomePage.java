package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.TestUtility;
import utilities.logs.Log;

import java.util.concurrent.ExecutionException;

public class HomePage {
    public RemoteWebDriver driver;
    public TestUtility utility = new TestUtility();

    /**
     * Web Elements
     */
    @FindBy(xpath = "//span[text()='Compiler ']")
    public WebElement compilerTitle;

    @FindBy(xpath = "(//button[@class = 'btn link-primary flex w-full justify-center gap-4 p-1 align-middle hover:justify-start justify-center'])[1]")
    public WebElement sidePanel;


//    @FindBy(xpath = "div[contains(@class, 'section-quinary')]//button/*[local-name()='svg' and @data-icon='file']/parent::button")
//    public WebElement sidePanel;
    @FindBy(xpath = "//span[text()='New Project']")
    public WebElement newProjectButton;

    @FindBy(xpath = "(//div[@class='flex w-full gap-5'])[1]/button[normalize-space()='Yes']")
    public WebElement clearYes;


    @FindBy(xpath = "//div[@id='code']//div[@class='ace_content']")
    public WebElement whiteBoard;

    @FindBy(xpath = "//div[contains(@style, '552px;')]")
    public WebElement divContent;







//    @FindBy(xpath = "//a[text()='All listings']")
//    public WebElement allListingPageButton;
//
//    @FindBy(how = How.XPATH,using = "//h1[@class='sc-gKAaRy cMptV'][text()=\"Search flats and houses \"]")
//    public WebElement searchFlatAndHouseBigTitle;

    /**
     * Constructor
     */
    public HomePage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

//    @Step("Click On Search Button")
//    public void clickSearchButton(){
//        utility.waitVisibility(driver, searchButton);
//        searchButton.click();
//    }

//    @Step("Click On All List Page Button")
//    public HomePage clickOnAllListPageButton(){
//        utility.waitVisibility(driver, allListingPageButton);
//        allListingPageButton.click();
//        return  this;
//    }

    @Step("Opening JDoodle Online Compiler Page")
    public HomePage goToJDoodleOnlineCompilerPage(){
        Log.info("Opening JDoodle Online Compiler");
        utility.launchSite(driver);
        return this;
    }

    public HomePage verifyJDoodleOnlineCompilerPage(){
        utility.waitVisibility(driver, compilerTitle);
        String title =  driver.getTitle();
        Assert.assertEquals(title,"Online Java Compiler - Online Java Editor - Java Code Online");
        return this;
    }


    public HomePage clearCurrentProject(){
//        utility.wait(driver,30);
        Actions actions = new Actions(driver);
        try{
            Thread.sleep(10000);
            newProjectButton.click();
        }
        catch(Exception e) {
            actions.moveToElement(sidePanel).build().perform();
            newProjectButton.click();
        }
        utility.waitVisibility(driver,clearYes);
        utility.wait(driver,10);
        clearYes.click();
        return this;
    }

    public HomePage enterValidCode(){
//        utility.waitforClickable(driver,whiteBoard);
        utility.wait(driver,10);
        utility.waitforClickable(driver,whiteBoard);
        whiteBoard.click();
//        driver.switchTo().frame("tooltip-container-wrapper");
        whiteBoard.sendKeys("public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(\"Hello, World!\");\n\t}\n}");
        return this;
    }

//    @Step("Opening Search Flats And House ResultPage")
//    public SearchPage goToSearchFlatsAndHousesResultsPage(){
//        Log.info("Going to Search Flats And House Result Tab");
//        clickSearchButton();
//        return new SearchPage(driver);
//    }
//
//    @Step("Opening All Listing Page")
//    public AllListingPropertiesPage goToAllListingPage(){
//        Log.info("Going to All Listing Properties Tab");
//        clickOnAllListPageButton();
//        return new AllListingPropertiesPage(driver);
//    }



}
