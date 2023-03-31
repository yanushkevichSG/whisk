package api.models.response;

import api.models.response.inner.Content;
import api.models.response.inner.ShoppingList;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreateShoppingListResponse {
    private ShoppingList list;
    private Content content;

    public ShoppingList getList() {
        return list;
    }

    public Content getContent() {
        return content;
    }
}
