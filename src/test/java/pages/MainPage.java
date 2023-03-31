package pages;

import com.codeborne.selenide.SelenideElement;

import java.util.function.Function;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage extends BasePage {
    private final SelenideElement signInPlusIcon = $x("//*[@data-testid='plus']/parent::button");

    private final Function<String, SelenideElement> getNavMenuByName =
            navMenuName -> $x(String.format("//*[@data-testid='%s']", navMenuName));

    public boolean isTabActive(String tabName) {
        return getNavMenuByName.apply(tabName).getAttribute("data-activetab").equals("1");
    }

    public void clickOnNavMenu(String tabName) {
        getNavMenuByName.apply(tabName).click();
    }

    public void clickOnSignInPlusIcon() {
        signInPlusIcon.click();
    }
}
