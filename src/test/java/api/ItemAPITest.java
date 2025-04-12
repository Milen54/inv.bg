package api;

import api.base.BaseApiTest;
import api.dataprovider.ItemDataProvider;
import api.dto.Item;
import api.dto.Items;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.testng.annotations.Test;

public class ItemAPITest extends BaseApiTest {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Test
    @Tag("api")
    @Tag("items")
    @DisplayName("Can get all items")
    public void canGetAllItems(){

        Response response = api.getItemAPI().getItems();
        Assertions.assertEquals(200, response.statusCode());
        Items items = gson.fromJson(response.getBody().asString(), Items.class);
        Assertions.assertEquals(10, items.getTotal());
    }

    @Test
    @Tag("api")
    @Tag("items")
    @DisplayName("Can get a single item by ID")
    public void canGetItemById() {

        Response response = api.getItemAPI().getItem(3); //ID на съществуващ item
        Assertions.assertEquals(200, response.statusCode());

        Item item = gson.fromJson(response.getBody().asString(), Item.class);
        Assertions.assertEquals(3, item.getId());
    }

    @Test
    @Tag("api")
    @Tag("items")
    @DisplayName("Can create a new item")
    public void canCreateNewItem() {

        Item newItem = new Item();
        newItem.setName("Test Item v3");
        newItem.setCurrency("BGN");
        newItem.setPrice(9.99f);  // f за да е float
        newItem.setPriceForQuantity(5);
        newItem.setQuantityUnit("бр.");

        Response response = api.getItemAPI().createItem(newItem);
        Assertions.assertEquals(201, response.statusCode());

        // Очакваме само id в response body
        Item created = gson.fromJson(response.getBody().asString(), Item.class);
        Assertions.assertNotNull(created.getId());
        Assertions.assertTrue(created.getId() > 0);
    }

    @Test(dataProvider = "csvItems", dataProviderClass = ItemDataProvider.class)
    @Tag("api")
    @Tag("items")
    @DisplayName("Can create item from CSV")
    public void canCreateItemFromCsv(String name, String currency, Float price, Integer pfq, String quantityUnit) {

        Item item = new Item();
        item.setName(name);
        item.setCurrency(currency);
        item.setPrice(price);
        item.setPriceForQuantity(pfq);
        item.setQuantityUnit(quantityUnit);

        Response response = api.getItemAPI().createItem(item);
        Assertions.assertEquals(201, response.statusCode());

        Item created = gson.fromJson(response.getBody().asString(), Item.class);
        Assertions.assertNotNull(created.getId());
        Assertions.assertTrue(created.getId()>0);
    }

    @Test
    @DisplayName("Clean up test items")
    @Tag("cleanup")
    public void deleteTestItems() {

        Response response = api.getItemAPI().getItems();
        Items items = gson.fromJson(response.getBody().asString(), Items.class);

        for(Item item : items.getItems()) {
            if (item.getName().startsWith("Test_")) {
                api.getItemAPI().deleteItem(item.getId());
            }
        }

    }

}
