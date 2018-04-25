package kres.listyMcListFace.firebase.productList;

import com.google.firebase.database.DatabaseReference;

import kres.listyMcListFace.firebase.util.FirebaseRefs;
import kres.listyMcListFace.model.Product;

public class ProductListManager {

    public static void addItem(String listID, Product product) {
        DatabaseReference ref = FirebaseRefs.getShoppingListProductsRef(listID);
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
