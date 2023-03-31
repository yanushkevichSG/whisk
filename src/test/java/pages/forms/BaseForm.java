package pages.forms;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

public abstract class BaseForm {
    private SelenideElement formLabel;

    protected BaseForm(SelenideElement element) {
        this.formLabel = element;
    }

    public void isFormDisplayed() {
        formLabel.shouldBe(Condition.visible);
    }
}
