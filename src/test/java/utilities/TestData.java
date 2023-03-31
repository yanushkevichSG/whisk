package utilities;


import api.models.request.CreateShoppingListRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import framework.settings.JsonSettingsFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestData {
    private static final JsonSettingsFile TESTDATA_API_FILE;
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        String environment = System.getProperty("environment", "dev");
        TESTDATA_API_FILE = new JsonSettingsFile(String.format("api/testdata/api_testdata_%s.json", environment.toLowerCase()));
    }

    public static CreateShoppingListRequest getCreateShoppingListRequestWithName(String shoppingListName) {
        String jsonValue = TESTDATA_API_FILE.getValue("/createShoppingList").toString();
        try {
            CreateShoppingListRequest createShoppingListRequest = mapper.readValue(jsonValue, CreateShoppingListRequest.class);
            createShoppingListRequest.setName(shoppingListName);
            return createShoppingListRequest;
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
