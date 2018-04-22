package kres.realtimeshoppinglist.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.Set;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.firebase.shoppingList.ListExistsListener;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListAdapter;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListManager;
import kres.realtimeshoppinglist.model.ShoppingList;
import kres.realtimeshoppinglist.persistence.PersistenceManager;

public class ListSelectActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_select);

        LinearLayout listLayout = findViewById(R.id.list_select_list_layout);

        final ShoppingListAdapter adapter = new ShoppingListAdapter(listLayout, ListSelectActivity.this);

        final PersistenceManager manager = PersistenceManager.getInstance(ListSelectActivity.this);
        Set<String> knownIDs = manager.retrieveKnownIDs();

        for (final String id : knownIDs) {
            ShoppingListManager.getShoppingList(id, new ListExistsListener() {
                @Override
                public void onListFound(ShoppingList list) {
                    adapter.appendItem(list);
                }

                @Override
                public void onListNotFound() {
                    manager.removeKnownID(id);
                }
            });
        }
    }
}
