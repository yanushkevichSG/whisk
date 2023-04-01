package utilities;

import constants.Items;

public class DataProvider {

    private DataProvider() {}

    @org.testng.annotations.DataProvider(name = "allItems")
    public static Object[][] getTestData() {
        return Items.allDataProviderItems;
    }
}
