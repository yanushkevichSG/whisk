package steps.login;

import models.user.User;
import pages.LoginPage;

public class LogInSteps {

    private LogInSteps() {}

    public static void logIn(User user) {
        LoginPage loginPage = new LoginPage();
        loginPage.isPageDisplayed();
        loginPage.fillEmail(user.getEmail());
        loginPage.clickContinue();
        loginPage.fillPassword(user.getPassword());
        loginPage.clickLogIn();
    }
}
