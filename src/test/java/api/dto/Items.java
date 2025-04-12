package api.dto;

import lombok.Data;

import java.util.List;

@Data
public class Items {

    public List<Item> getItems() {
        return items;
    }

    public Integer getTotal() {
        return total;
    }

    private Integer total;
    private List<Item> items;
}
