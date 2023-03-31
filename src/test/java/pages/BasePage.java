package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public abstract class BasePage {
    private final SelenideElement pageLabel;

    protected BasePage() {
        this.pageLabel = $x("//*[@id='app']");
    }

    protected BasePage(SelenideElement element) {
        this.pageLabel = element;
    }

    public void isPageDisplayed() {
        pageLabel.shouldBe(Condition.visible);
    }
}
