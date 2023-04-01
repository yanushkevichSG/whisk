package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import utilities.Environment;

public abstract class BaseTest {

    @BeforeSuite()
    public void setHeadlessModeIfNeeded() {
        if (System.getProperty("headless") != null && System.getProperty("headless").equals("on")) {
            setHeadless();
        }
    }

    private void setHeadless() {
        Configuration.headless = true;
    }

    @BeforeClass
    public void startTheApp() {
        SelenideLogger.addListener("AllureSelenide",
                new AllureSelenide().
                        screenshots(true).
                        savePageSource(true));
        Configuration.screenshots = false;
        Configuration.savePageSource = false;
        Configuration.webdriverLogsEnabled = false;
        Configuration.pageLoadTimeout = 30000;
        Configuration.timeout = 16000;
        Selenide.open(Environment.getBaseUrl());
    }
}
