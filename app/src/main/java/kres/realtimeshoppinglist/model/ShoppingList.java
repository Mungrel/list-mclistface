package kres.realtimeshoppinglist.model;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {

    private String id;
    private String name;
    private List<Product> products;

    public ShoppingList(String id, String name) {
        this.id = id;
        this.name = name;
        this.products = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }
}
