package api.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CsvReaderUtil {

    public static Object[][] readItemsFromCsv(String filePath) throws Exception {
        List<Object[]> data = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;

        reader.readLine(); // Пропускаме header реда

        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            String name = parts[0];
            String currency = parts[1];
            Float price = Float.parseFloat(parts[2]);
            Integer pfq = Integer.parseInt(parts[3]);
            String quantityUnit = parts[4];

            data.add(new Object[]{name, currency, price, pfq, quantityUnit});
        }

        reader.close();
        return data.toArray(new Object[0][]);
    }

}
