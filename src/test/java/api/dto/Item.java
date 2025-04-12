package api.dto;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

// DTO
@Data
public class Item {
    private Integer id;
    private String name;
    private String currency;
    private Float price;

    @SerializedName("price_for_quantity")
    private Integer priceForQuantity;

    @SerializedName("quantity_unit")
    private String quantityUnit;
}
