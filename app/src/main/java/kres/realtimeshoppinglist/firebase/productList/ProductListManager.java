package kres.realtimeshoppinglist.firebase.productList;

import com.google.firebase.database.DatabaseReference;

import kres.realtimeshoppinglist.firebase.FirebaseRefs;
import kres.realtimeshoppinglist.model.Product;

public class ProductListManager {

    public static void addItem(String listID, Product product) {
        DatabaseReference ref = FirebaseRefs.getShoppingListRef(listID);
        DatabaseReference newProductRef = ref.push();
        product.setID(newProductRef.getKey());
        newProductRef.setValue(product);
    }

    public static void editItem(String listID, Product updatedProduct) {
        if (updatedProduct.getID() == null || updatedProduct.getID().isEmpty()) {
            return;
        }

        DatabaseReference productRef = FirebaseRefs.getProductRef(listID, updatedProduct.getID());
        productRef.setValue(updatedProduct);
    }

    public static void removeItem(String listID, String productID) {
        if (productID == null || productID.isEmpty()) {
            return;
        }

        DatabaseReference productRef = FirebaseRefs.getProductRef(listID, productID);
        productRef.removeValue();
    }
}
