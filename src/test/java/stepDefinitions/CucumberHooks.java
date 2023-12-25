package stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import tests.BaseTest;

public class CucumberHooks {
    public BaseTest baseTest;

    public CucumberHooks(BaseTest baseTest) {
        this.baseTest = baseTest;
    }

    @Before
    public void beforeScenario() {
        baseTest.openBrowser();
    }

    @After
    public void afterScenario() {
        baseTest.closeSite();
    }

}
