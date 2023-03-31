package api.service;

public class WhiskApiService {

    private WhiskApiService() {}

    public static String createShoppingList() {
        return "/list/v2";
    }

    public static String getShoppingListById() {
        return "/list/v2/{id}";
    }
}
