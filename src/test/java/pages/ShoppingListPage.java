package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import elements.ElementsAction;
import org.openqa.selenium.Keys;
import pages.forms.shoppingForms.CreateNewListForm;
import pages.forms.shoppingForms.DeleteListForm;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.$x;

public class ShoppingListPage extends BasePage {
    private final CreateNewListForm createNewListForm = new CreateNewListForm();
    private final DeleteListForm deleteListForm = new DeleteListForm();

    private final SelenideElement createNewListBtn = $x("//*[@data-testid='create-new-shopping-list-button']");
    private final SelenideElement shoppingListNameTextBox = $x("//*[@data-testid='shopping-list-name']/h3");
    private final SelenideElement addItemSearchBar = $x("//*[@data-testid='desktop-add-item-autocomplete']");
    private final SelenideElement deleteListBtn = $x("//*[@data-testid='shopping-list-delete-menu-button']");
    private final ElementsCollection shoppingListAddedItems = $$x("//*[@data-testid='shopping-list-item-name']");

    private final Function<String, SelenideElement> getThreeDotsShoppingListByName =
            shoppingListName -> $x(String.format("//*[@data-testid='shopping-lists-list-name' and text() = '%s']//following::*[@data-testid='3dotsHorizontal']/parent::button", shoppingListName));
    private final Function<String, SelenideElement> getShoppingListByName =
            shoppingListName -> $x(String.format("//*[@data-testid='shopping-lists-list-name' and text() = '%s']", shoppingListName));

    public void createNewList() {
        createNewListBtn.click();
    }

    public void isShoppingListNameCorrect(String shoppingListName) {
        shoppingListNameTextBox.shouldHave(Condition.text(shoppingListName));
    }

    public void searchAndSelectItem(String item) {
        ElementsAction.clearInput(addItemSearchBar);
        addItemSearchBar.setValue(item).pressEnter();
    }

    public void clickOnThreeDotsForShoppingList(String shoppingListName) {
        getThreeDotsShoppingListByName.apply(shoppingListName).click();
    }

    public void clickOnDeleteList() {
        deleteListBtn.click();
    }

    public void isShoppingListNotDisplayed(String shoppingListName) {
        getShoppingListByName.apply(shoppingListName).shouldNotBe(Condition.visible);
    }

    public void isIteamAdded(String itemName) {
        shoppingListAddedItems.stream().filter(item -> itemName.equals(item.getText()))
                .findAny()
                .orElse(null).shouldHave(Condition.text(itemName));
    }

    public CreateNewListForm getCreateNewListForm() {
        return createNewListForm;
    }

    public DeleteListForm getDeleteListForm() {
        return deleteListForm;
    }
}
