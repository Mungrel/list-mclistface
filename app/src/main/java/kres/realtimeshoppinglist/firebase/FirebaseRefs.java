package kres.realtimeshoppinglist.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRefs {

    private static final DatabaseReference ROOT = FirebaseDatabase.getInstance().getReference();

    public static DatabaseReference getShoppingListRootRef() {
        return ROOT.child("lists");
    }

    public static DatabaseReference getShoppingListRef(String listID) {
        return getShoppingListRootRef().child(listID);
    }

    public static DatabaseReference getProductRef(String listID, String productID) {
        return getShoppingListRef(listID).child("products").child(productID);
    }
}
