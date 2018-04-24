package kres.listyMcListFace.firebase.shoppingList;

import kres.listyMcListFace.model.ShoppingList;

public interface ListExistsListener {

    void onListFound(ShoppingList list);

    void onListNotFound();

}
