package kres.realtimeshoppinglist.firebase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseRefs {

    private static final DatabaseReference ROOT = FirebaseDatabase.getInstance().getReference();

    public static DatabaseReference getShoppingListRef(String listID) {
        return ROOT.child(listID);
    }
}
