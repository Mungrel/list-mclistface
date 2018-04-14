package kres.realtimeshoppinglist.firebase.shoppingList;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ShoppingListNameChangeListener implements ValueEventListener {
    @Override
    public void onDataChange(DataSnapshot dataSnapshot) {
        Log.d("LIST_NAME_CHANGE", "List name changed");
    }

    @Override
    public void onCancelled(DatabaseError databaseError) {
        Log.d("LIST_NAME_ERROR", "" + databaseError.getMessage());
    }
}
