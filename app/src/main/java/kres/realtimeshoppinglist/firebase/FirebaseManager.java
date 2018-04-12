package kres.realtimeshoppinglist.firebase;

import com.google.firebase.database.DatabaseReference;

import kres.realtimeshoppinglist.model.Product;

public class FirebaseManager {

    public void addItem(String listID, Product product) {
        DatabaseReference ref = FirebaseRefs.getShoppingListRef(listID);
        DatabaseReference newProductRef = ref.push();
        product.setID(newProductRef.getKey());
        newProductRef.setValue(product);
    }

    public void editItem(String listID, Product updatedProduct) {
        if (updatedProduct.getID() == null || updatedProduct.getID().isEmpty()) {
            return;
        }

        DatabaseReference productRef = FirebaseRefs.getProductRef(listID, updatedProduct.getID());
        productRef.setValue(updatedProduct);
    }

    public void removeItem(String listID, String productID) {
        if (productID == null || productID.isEmpty()) {
            return;
        }

        DatabaseReference productRef = FirebaseRefs.getProductRef(listID, productID);
        productRef.removeValue();
    }
}
