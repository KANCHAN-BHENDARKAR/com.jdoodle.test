package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import utilities.TestUtility;
import utilities.logs.Log;

public class HomePage {
    public RemoteWebDriver driver;
    public TestUtility utility = new TestUtility();

    public String HelloJDoodleCode = "public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(\"Hello, Doodle!\");\n\t}\n}";
    public String SumOfTwoNumberJavaCode = "public class MyClass {\\n\n" +
            "\\tpublic static void main(String args[]) {\\n\n" +
            "\\t\\tint x=10;\\n\n" +
            "\\t\\tint y=25;\\n\n" +
            "\\t\\tint z=x+y;\\n\\n\n" +
            "\\t\\tSystem.out.println(\"Sum of x+y = \" + z);\\n\n" +
            "\\t}\\n\n" +
            "}\n";
    public String InvalidHelloJDoodleCode = "public class HelloWorld {\n\tpublic static void main(String[] args) {\n\t\tSystem.out.println(\"Hello, Doodle!\")\n\t}\n}";


    /**
     * Constructor
     */
    public HomePage(RemoteWebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * Web Elements
     */
    @FindBy(xpath = "//span[text()='Compiler ']")
    public WebElement compilerTitle;

    @FindBy(xpath = "(//button[@class = 'btn link-primary flex w-full justify-center gap-4 p-1 align-middle hover:justify-start justify-center'])[1]")
    public WebElement sidePanel;

    @FindBy(xpath = "//span[text()='New Project']")
    public WebElement newProjectButton;

    @FindBy(xpath = "(//div[@class='flex w-full gap-5'])[1]/button[normalize-space()='Yes']")
    public WebElement clearYes;

    @FindBy(xpath = "//div[@id='ideCodeEditor']//div[@id='code']")
    public WebElement whiteBoard;


    @FindBy(xpath = "(//div[@class='ace_layer ace_text-layer']//div[@class='ace_line'])[1]")
    public WebElement divContent;

    @FindBy(xpath = "(//a[@href='https://jdoodle.com/docs'])[1]")
    public WebElement fAQicon;

    @FindBy(xpath = "//button[text()=' Execute ']")
    public WebElement executeButton;

    @FindBy(xpath = "(//div[@class='ace_content'])[2]//div[@class='ace_line'][1]")
    public WebElement outputResult;

    @FindBy(xpath = "(//div[@class='ace_content'])[2]//div[@class='ace_line'][2]")
    public WebElement outputResultForErrorLine2;
    @FindBy(xpath = "(//div[@class='ace_content'])[2]//div[@class='ace_line'][3]")
    public WebElement outputResultForErrorLine3;

    @FindBy(xpath = "//div[@class='hidden w-auto justify-end py-3 pr-2 lg:flex lg:w-[162px] xl:w-auto']//input[@id='searchBar']")
    public WebElement searchLanguage;

    @FindBy(xpath = "//div[@id='codeSearchDropdown']")
    public WebElement codeSearchDropdown;

    @FindBy(xpath = "(//div[@class='ace_layer ace_text-layer'])[2]/div[text()='JDoodle in Action.... Running the program...']")
    public WebElement runningProgram;


    @Step("Opening JDoodle Online Compiler Page")
    public HomePage goToJDoodleOnlineCompilerPage() {
        Log.info("Opening JDoodle Online Compiler");
        utility.launchSite(driver);
        return this;
    }

    public HomePage verifyJDoodleOnlineCompilerPage() {
        utility.waitVisibility(driver, compilerTitle);
        String title = driver.getTitle();
        Assert.assertEquals(title, "Online Java Compiler - Online Java Editor - Java Code Online");
        return this;
    }


    public HomePage clearCurrentProject() {
        Actions actions = new Actions(driver);
        try {
            utility.waitforClickable(driver, newProjectButton);
            newProjectButton.click();
        } catch (Exception e) {
            actions.moveToElement(sidePanel).build().perform();
            newProjectButton.click();
        }
        utility.waitVisibility(driver, clearYes);
        utility.wait(driver, 10);
        clearYes.click();
        return this;
    }

    public HomePage enterValidCode() {
        utility.wait(driver, 20);
        utility.waitforClickable(driver, whiteBoard);
        whiteBoard.click();
        ((JavascriptExecutor) driver).executeScript("ace.edit(document.getElementById('code')).session.setValue(arguments[0]);", HelloJDoodleCode);
        return this;
    }

    public HomePage clickOnExecuteButton() {
        utility.scrollToSpecificWebElement(driver, fAQicon);
        try {
            Thread.sleep(5000);
        } catch (Exception e) {
            e.getMessage();
        }
        executeButton.click();
        return this;
    }

    public HomePage validateOutputResult() {
        utility.waitForInvisibility(driver, runningProgram);
        Assert.assertEquals(outputResult.getText(), "Hello, Doodle!");
        return this;
    }

    public HomePage changeLanguageOfJavaCode() {

        utility.waitforClickable(driver, searchLanguage);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", searchLanguage);
        utility.waitforClickable(driver, codeSearchDropdown);

        Actions actions = new Actions(driver);
        actions.click(codeSearchDropdown).build().perform();
        actions.sendKeys("c#").build().perform();
        return this;
    }

    public HomePage validateOutputResultWithChangeLanguage() {
        try {
            Thread.sleep(8000);
        } catch (Exception e) {
            e.getMessage();
        }
        String newChangeLanguageSyntax = whiteBoard.getText();
        Assert.assertNotEquals(newChangeLanguageSyntax, SumOfTwoNumberJavaCode);
        Assert.assertTrue(whiteBoard.getText().contains("using System;"));
        return this;
    }

    public HomePage enterInvalidCode() {
        utility.wait(driver, 16);
        utility.waitforClickable(driver, whiteBoard);
        whiteBoard.click();
        ((JavascriptExecutor) driver).executeScript("ace.edit(document.getElementById('code')).session.setValue(arguments[0]);", InvalidHelloJDoodleCode);
        return this;
    }

    public HomePage validateInvalidErrorOutputResult() {
        utility.waitForInvisibility(driver, runningProgram);
        Assert.assertEquals(outputResultForErrorLine2.getText(), "HelloWorld.java:3: error: ';' expected");
        Assert.assertEquals(outputResultForErrorLine3.getText().trim(), "        System.out.println(\"Hello, Doodle!\")".trim());
        Assert.assertTrue(outputResultForErrorLine2.getText().contains("error"));
        return this;
    }

    public HomePage validateInputAreaIsEmpty() {
        utility.wait(driver, 10);
        Assert.assertEquals(whiteBoard.getText(), "1");
        return this;
    }

}
