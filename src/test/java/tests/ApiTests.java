package tests;

import api.models.response.CreateShoppingListResponse;
import api.models.response.DeletedShoppingListResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;
import steps.api.WhiskApiServiceSteps;
import utilities.Environment;
import utilities.Fake;

public class ApiTests {
    private final String shoppingListName = Fake.shoppingList();
    private final String authToken = Environment.getBearerToken();

    @Test
    public void testCreateAndGetShoppingList() {
        String shoppingListId = createShoppingListAndGetId();
        CreateShoppingListResponse getShoppingListById =
                WhiskApiServiceSteps.getShoppingListById(shoppingListId, authToken, HttpStatus.SC_OK);
        Assert.assertEquals(getShoppingListById.getList().getId(), shoppingListId, "Wrong shopping list id");
        Assert.assertEquals(getShoppingListById.getList().getName(), shoppingListName, "Wrong shopping list name");
        Assert.assertTrue(ObjectUtils.isEmpty(getShoppingListById.getContent().getData()));
    }

    @Test
    public void testDeleteAndGetShoppingList() {
        String shoppingListId = createShoppingListAndGetId();
        WhiskApiServiceSteps.deleteShoppingListById(shoppingListId, authToken, HttpStatus.SC_OK);
        DeletedShoppingListResponse deletedShoppingListResponse =
                WhiskApiServiceSteps.getDeletedShoppingListById(shoppingListId, authToken, HttpStatus.SC_BAD_REQUEST);
        Assert.assertEquals(deletedShoppingListResponse.getCode(), "shoppingList.notFound", "Wrong code message");
    }

    private String createShoppingListAndGetId() {
        CreateShoppingListResponse createShoppingListResponse =
                WhiskApiServiceSteps.createShoppingList(shoppingListName, authToken, HttpStatus.SC_OK);
        return createShoppingListResponse.getList().getId();
    }
}
