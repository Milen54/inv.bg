package api;

import api.base.BaseApiTest;
import api.dto.Item;
import api.dto.Items;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.json.Json;

import java.util.List;

public class ItemAPITest extends BaseApiTest {

        private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

        @Test
        @Tag("api")
        @Tag("items")
        @DisplayName("Can get all items")
        public void canGetAllItems() {
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
            Response response = api.getItemAPI().getItem(3);
            Assertions.assertEquals(200, response.statusCode());

            Item item = gson.fromJson(response.getBody().asString(), Item.class);
            Assertions.assertEquals(3, item.getId());
        }

        @Test
        @Tag("api")
        @Tag("items")
        @DisplayName("Can't get item by Invalid ID")
        public void getItemByInvalidId() {
            Response response = api.getItemAPI().getItem(-2);
            Assertions.assertEquals(404, response.statusCode());

            String errorMsg = JsonPath.from(response.getBody().asString()).get("error");
            Assertions.assertEquals("Item Not Found", errorMsg);
        }

        @Test
        @Tag("api")
        @Tag("items")
        @DisplayName("Can create a new item")
        public void canCreateNewItem() {
            Item newItem = new Item();
            newItem.setName("Test Item v3");
            newItem.setCurrency("BGN");
            newItem.setPrice(9.99f);
            newItem.setPriceForQuantity(5);
            newItem.setQuantityUnit("бр.");

            Response response = api.getItemAPI().createItem(newItem);
            Assertions.assertEquals(201, response.statusCode());

            Item created = gson.fromJson(response.getBody().asString(), Item.class);
            Assertions.assertNotNull(created.getId());
            Assertions.assertTrue(created.getId() > 0);
        }

        @ParameterizedTest
        @CsvFileSource(resources = "/items.csv", numLinesToSkip = 1)
        @Tag("api")
        @Tag("items")
        @DisplayName("Can create item from CSV")
        public void canCreateItemFromCsv(String name, String currency, float price, int pfq, String quantityUnit) {
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
            Assertions.assertTrue(created.getId() > 0);
        }

        @Test
        @DisplayName("Clean up test items")
        @Tag("cleanup")
        public void deleteTestItems() {
            Response response = api.getItemAPI().getItems();
            Items items = gson.fromJson(response.getBody().asString(), Items.class);

            for (Item item : items.getItems()) {
                if (item.getName().startsWith("Test_")) {
                    api.getItemAPI().deleteItem(item.getId());
                }
            }
        }

        @Test
        @Disabled("API не позволява изтриване чрез токена - връща 403 Forbidden")
        @DisplayName("Can delete item by ID (disabled - lacks permission)")
        public void canDeleteItemById() {
            Item item = new Item();
            item.setName("Test_Delete2");
            item.setCurrency("BGN");
            item.setPrice(1.23f);
            item.setPriceForQuantity(1);
            item.setQuantityUnit("бр.");

            Response response = api.getItemAPI().createItem(item);
            Assertions.assertEquals(201, response.statusCode());

            Item created = gson.fromJson(response.getBody().asString(), Item.class);

            response = api.getItemAPI().deleteItem(created.getId());
            Assertions.assertEquals(204, response.statusCode());

            response = api.getItemAPI().getItem(created.getId());
            Assertions.assertEquals(404, response.statusCode());
        }

        @Test
        @Tag("api")
        @Tag("items")
        @Tag("negative")
        @DisplayName("Can't delete item with Invalid ID - returns 404")
        public void cannotDeleteInvalidItem() {
            Response response = api.getItemAPI().deleteItem(-51);
            Assertions.assertEquals(404, response.statusCode());
        }

        @Test
        @Tag("api")
        @Tag("items")
        @DisplayName("Can update item by ID")
        public void canUpdateItemById() {
            Item item = new Item();
            item.setName("ToUpdateTags6");
            item.setCurrency("BGN");
            item.setPrice(2.0f);
            item.setPriceForQuantity(1);
            item.setQuantityUnit("бр.");

            Response create = api.getItemAPI().createItem(item);
            Assertions.assertEquals(201, create.statusCode());

            Item created = gson.fromJson(create.getBody().asString(), Item.class);

            Response update = api.getItemAPI().updateTags(
                    created.getId(),
                    List.of("tag_auto_1", "tag_auto_2")
            );
            Assertions.assertEquals(204, update.statusCode());
        }
    }
