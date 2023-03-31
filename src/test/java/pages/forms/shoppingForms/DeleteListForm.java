package pages.forms.shoppingForms;

import com.codeborne.selenide.SelenideElement;
import pages.forms.BaseForm;

import static com.codeborne.selenide.Selenide.$x;

public class DeleteListForm extends BaseForm {
    private final SelenideElement deleteBtn = $x("//*[@data-testid='confirm-delete-button']");

    public DeleteListForm() {
        super($x("//*[@data-testid='confirm-modal']"));
    }

    public void confirmDelete() {
        deleteBtn.click();
    }
}
