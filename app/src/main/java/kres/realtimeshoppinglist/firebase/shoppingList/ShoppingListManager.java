package kres.realtimeshoppinglist.firebase.shoppingList;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import kres.realtimeshoppinglist.firebase.FirebaseRefs;
import kres.realtimeshoppinglist.firebase.ListID;
import kres.realtimeshoppinglist.model.ShoppingList;

public class ShoppingListManager {

    public static ShoppingList createShoppingList(String listName) {
        String listID = ListID.generate();
        DatabaseReference newListRef = FirebaseRefs.getShoppingListRef(listID);

        ShoppingList shoppingList = new ShoppingList(listID, listName);
        newListRef.setValue(shoppingList);

        return shoppingList;
    }

    public static void deleteShoppingList(String listID) {
        DatabaseReference listRef = FirebaseRefs.getShoppingListRef(listID);
        listRef.removeValue();
    }

    public static void editShoppingListName(String listID, String newListName) {
        DatabaseReference listRef = FirebaseRefs.getShoppingListRef(listID);
        DatabaseReference listNameRef = listRef.child("name");
        listNameRef.setValue(newListName);
    }

    public static void getShoppingList(String listID, final ListExistsListener listExistsListener) {
        DatabaseReference listRef = FirebaseRefs.getShoppingListRef(listID);
        listRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ShoppingList list = dataSnapshot.getValue(ShoppingList.class);
                    listExistsListener.onListFound(list);
                } else {
                    listExistsListener.onListNotFound();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("LIST_FIND", "Error finding list: " + databaseError.getMessage());
            }
        });
    }
}
