package steps.api;

import api.models.response.CreateShoppingListResponse;
import api.models.response.DeletedShoppingListResponse;
import api.service.WhiskApiService;
import utilities.Environment;
import utilities.TestData;
import utilities.spec.Specifications;

import static io.restassured.RestAssured.given;

public class WhiskApiServiceSteps {

    private static final String url = Environment.getWhiskApiUrl();

    public static CreateShoppingListResponse createShoppingList(String shoppingListName, String authToken, int status) {
        CreateShoppingListResponse getCreateShoppingListResponse = given(Specifications.requestSpecWithAuth(url, authToken))
                .then().statusCode(status)
                .with()
                .and()
                .body(TestData.getCreateShoppingListRequestWithName(shoppingListName))
                .post(WhiskApiService.createShoppingList())
                .then()
                .extract().as(CreateShoppingListResponse.class);

        return getCreateShoppingListResponse;
    }

    public static CreateShoppingListResponse getShoppingListById(String shoppingListId,
                                                                 String authToken, int status) {
        CreateShoppingListResponse getCreateShoppingListResponse = given(Specifications.requestSpecWithAuth(url, authToken))
                .then().statusCode(status)
                .with()
                .and()
                .pathParam("id", shoppingListId)
                .get(WhiskApiService.getShoppingListById())
                .then()
                .extract().as(CreateShoppingListResponse.class);

        return getCreateShoppingListResponse;
    }

    public static void deleteShoppingListById(String shoppingListId,
                                                                 String authToken, int status) {
        given(Specifications.requestSpecWithAuth(url, authToken))
                .then().statusCode(status)
                .with()
                .and()
                .pathParam("id", shoppingListId)
                .delete(WhiskApiService.getShoppingListById());
    }

    public static DeletedShoppingListResponse getDeletedShoppingListById(String shoppingListId,
                                                                 String authToken, int status) {
        DeletedShoppingListResponse getDeletedShoppingListResponse = given(Specifications.requestSpecWithAuth(url, authToken))
                .then().statusCode(status)
                .with()
                .and()
                .pathParam("id", shoppingListId)
                .get(WhiskApiService.getShoppingListById())
                .then()
                .extract().as(DeletedShoppingListResponse.class);

        return getDeletedShoppingListResponse;
    }
}
