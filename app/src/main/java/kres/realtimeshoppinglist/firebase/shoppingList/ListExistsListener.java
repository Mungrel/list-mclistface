package kres.realtimeshoppinglist.firebase.shoppingList;

import kres.realtimeshoppinglist.model.ShoppingList;

public interface ListExistsListener {

    void onListFound(ShoppingList list);

    void onListNotFound();

}
