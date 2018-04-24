package kres.listyMcListFace.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShoppingList {

    private String id;
    private String name;
    private Map<String, Product> products;

    public ShoppingList() {
    }

    public ShoppingList(String id, String name) {
        this.id = id;
        this.name = name;
        this.products = new HashMap<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products.values());
    }
}
