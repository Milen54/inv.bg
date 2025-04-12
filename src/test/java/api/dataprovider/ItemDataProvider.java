package api.dataprovider;

import api.utils.CsvReaderUtil;
import org.testng.annotations.DataProvider;

public class ItemDataProvider {

    @DataProvider(name = "csvItems")
    public Object[][] provideItemsFromCsv() throws Exception {
        return CsvReaderUtil.readItemsFromCsv("src/test/resources/items.csv");
    }

}
