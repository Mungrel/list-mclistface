package kres.realtimeshoppinglist.firebase.shoppingList;

import com.google.firebase.database.DatabaseReference;

import kres.realtimeshoppinglist.firebase.FirebaseRefs;
import kres.realtimeshoppinglist.model.ShoppingList;

public class ShoppingListManager {

    public static ShoppingList createShoppingList(String listName) {
        DatabaseReference newListRef = FirebaseRefs.getShoppingListRootRef().push();
        String listID = newListRef.getKey();

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
}
