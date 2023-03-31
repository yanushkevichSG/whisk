package pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage extends BasePage {
    private final SelenideElement emailInput = $x("//*[@name='username']");
    private final SelenideElement passwordInput = $x("//*[@name='password']");
    private final SelenideElement continueButton = $x("//*[@data-testid='auth-continue-button']");
    private final SelenideElement logInButton = $x("//*[@data-testid='auth-login-button']");

    public LoginPage() {
        super($x("//*[@data-testid='authentication-modal']"));
    }

    public void fillEmail(String email) {
        emailInput.sendKeys(email);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public void fillPassword(String password) {
        passwordInput.sendKeys(password);
    }

    public void clickLogIn() {
        logInButton.click();
    }
}
