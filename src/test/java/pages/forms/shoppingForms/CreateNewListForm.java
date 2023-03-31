package pages.forms.shoppingForms;

import com.codeborne.selenide.SelenideElement;
import elements.ElementsAction;
import pages.forms.BaseForm;

import static com.codeborne.selenide.Selenide.$x;

public class CreateNewListForm extends BaseForm {
    private final SelenideElement createNewShoppingInput =
            $x("//*[@data-testid='create-new-shopping-list-name-input']//input");
    private final SelenideElement createBtn = $x("//*[@data-testid='create-new-shopping-list-create-button']");

    public CreateNewListForm() {
        super($x("//*[@data-testid='list-form-modal']"));
    }

    public void renameShoppingList(String listName) {
        ElementsAction.clearInput(createNewShoppingInput);
        createNewShoppingInput.sendKeys(listName);
    }

    public void create() {
        createBtn.click();
    }
}
