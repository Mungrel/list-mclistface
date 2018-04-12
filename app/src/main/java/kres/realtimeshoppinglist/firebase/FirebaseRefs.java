package kres.realtimeshoppinglist.firebase;

import android.provider.ContactsContract;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRefs {

    private static final DatabaseReference ROOT = FirebaseDatabase.getInstance().getReference();

    public static DatabaseReference getShoppingListRef(String listID) {
        return ROOT.child(listID);
    }

    public static DatabaseReference getProductRef(String listID, String productID) {
        return getShoppingListRef(listID).child("products").child(productID);
    }
}
