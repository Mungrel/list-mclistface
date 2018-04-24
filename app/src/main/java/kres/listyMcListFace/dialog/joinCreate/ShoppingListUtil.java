package kres.listyMcListFace.dialog.joinCreate;

import kres.listyMcListFace.firebase.shoppingList.ShoppingListAdapter;
import kres.listyMcListFace.persistence.PersistenceManager;

public interface ShoppingListUtil {

    ShoppingListAdapter getAdapter();
    PersistenceManager getPersistenceManger();

}
