package kres.realtimeshoppinglist.dialog.joinCreate;

import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListAdapter;
import kres.realtimeshoppinglist.persistence.PersistenceManager;

public interface ShoppingListUtil {

    ShoppingListAdapter getAdapter();
    PersistenceManager getPersistenceManger();

}
