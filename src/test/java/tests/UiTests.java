package tests;

import constants.Items;
import constants.NavMenu;
import models.user.User;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ShoppingListPage;
import steps.login.LogInSteps;
import utilities.DataProvider;
import utilities.Fake;

public class UiTests extends BaseTest {
    private final User defaultUser = User.defaultUser();
    private final String shoppingListName = Fake.shoppingList();
    private final String secondShoppingListName = Fake.shoppingList();
    private final MainPage mainPage = new MainPage();
    private final ShoppingListPage shoppingListPage = new ShoppingListPage();

    @Test(priority = 1)
    public void testAddItemsToShoppintList() {
        mainPage.isPageDisplayed();
        mainPage.clickOnSignInPlusIcon();
        LogInSteps.logIn(defaultUser);
        mainPage.clickOnNavMenu(NavMenu.SHOPPING.getNavMenuTestId());
        mainPage.isTabActive(NavMenu.SHOPPING.getNavMenuTestId());
        createShoppingList(shoppingListName);
        shoppingListPage.searchAndSelectItem(Items.MILK);
        shoppingListPage.searchAndSelectItem(Items.EGGS);
        shoppingListPage.searchAndSelectItem(Items.BREAD);
        shoppingListPage.searchAndSelectItem(Items.BUTTER);
        shoppingListPage.searchAndSelectItem(Items.CHEESE);
    }

    @Test(priority = 2, dataProvider = "allItems", dataProviderClass = DataProvider.class)
    public void testCheckAllAddedItems(String item) {
        shoppingListPage.isIteamAdded(item);
    }

    @Test(priority = 3)
    public void testCreateShoppingListAndDelete() {
        createShoppingList(secondShoppingListName);
        shoppingListPage.clickOnThreeDotsForShoppingList(secondShoppingListName);
        shoppingListPage.clickOnDeleteList();
        shoppingListPage.getDeleteListForm().isFormDisplayed();
        shoppingListPage.getDeleteListForm().confirmDelete();
        shoppingListPage.isShoppingListNotDisplayed(secondShoppingListName);
    }

    private void createShoppingList(String shoppingListName) {
        shoppingListPage.isPageDisplayed();
        shoppingListPage.createNewList();
        shoppingListPage.getCreateNewListForm().isFormDisplayed();
        shoppingListPage.getCreateNewListForm().renameShoppingList(shoppingListName);
        shoppingListPage.getCreateNewListForm().create();
        shoppingListPage.isShoppingListNameCorrect(shoppingListName);
    }
}
