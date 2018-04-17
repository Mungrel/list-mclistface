package kres.realtimeshoppinglist.model;

public class Product {

    private String ID;
    private String name;
    private boolean bought;

    public Product() {
    }

    public Product(String name) {
        this.name = name;
        this.bought = false;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public boolean isBought() {
        return bought;
    }

    public void setBought(boolean bought) {
        this.bought = bought;
    }
}
