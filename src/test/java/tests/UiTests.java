package tests;

import constants.Items;
import constants.NavMenu;
import models.user.User;
import org.testng.annotations.Test;
import pages.MainPage;
import pages.ShoppingListPage;
import steps.login.LogInSteps;
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

    @Test(priority = 2)
    public void testCheckFirstItem() {
        shoppingListPage.isIteamAdded(Items.MILK);
    }

    @Test(priority = 3)
    public void testCheckSecondItem() {
        shoppingListPage.isIteamAdded(Items.EGGS);
    }

    @Test(priority = 4)
    public void testCheckThirdItem() {
        shoppingListPage.isIteamAdded(Items.BREAD);
    }

    @Test(priority = 5)
    public void testCheckFourthItem() {
        shoppingListPage.isIteamAdded(Items.BUTTER);
    }

    @Test(priority = 6)
    public void testCheckFifthItem() {
        shoppingListPage.isIteamAdded(Items.CHEESE);
    }

    @Test(priority = 7)
    public void testDeleteShoppingListAndDelete() {
        createShoppingList(secondShoppingListName);
        shoppingListPage.clickOnThreeDotsForShoppingList(secondShoppingListName);
        shoppingListPage.clickOnDeleteList();
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
