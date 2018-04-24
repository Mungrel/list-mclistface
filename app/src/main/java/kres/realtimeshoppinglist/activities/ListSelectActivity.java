package kres.realtimeshoppinglist.activities;

import android.app.DialogFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Set;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.dialog.joinCreate.JoinCreateDialog;
import kres.realtimeshoppinglist.dialog.joinCreate.ShoppingListUtil;
import kres.realtimeshoppinglist.firebase.shoppingList.ListExistsListener;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListAdapter;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListManager;
import kres.realtimeshoppinglist.model.ShoppingList;
import kres.realtimeshoppinglist.persistence.PersistenceManager;

public class ListSelectActivity extends AppCompatActivity implements ShoppingListUtil {

    private ShoppingListAdapter shoppingListAdapter;
    private PersistenceManager persistenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_select);

        LinearLayout listLayout = findViewById(R.id.list_select_list_layout);

        TextView titleBarTitle = findViewById(R.id.title_bar_title_text);
        TextView titleBarJoinCode = findViewById(R.id.title_bar_join_code);

        titleBarTitle.setText(R.string.app_name);
        titleBarJoinCode.setVisibility(View.INVISIBLE);

        shoppingListAdapter = new ShoppingListAdapter(listLayout, ListSelectActivity.this);
        persistenceManager = PersistenceManager.getInstance(ListSelectActivity.this);

        Set<String> knownIDs = persistenceManager.retrieveKnownIDs();

        for (final String id : knownIDs) {
            Log.d("PERSISTENCE_MANAGER", "Found ID: " + id);
            ShoppingListManager.getShoppingList(id, new ListExistsListener() {
                @Override
                public void onListFound(ShoppingList list) {
                    shoppingListAdapter.appendItem(list);
                }

                @Override
                public void onListNotFound() {
                    persistenceManager.removeKnownID(id);
                }
            });
        }

        FloatingActionButton fab = findViewById(R.id.list_select_list_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // fab clicked
                Log.d("LIST_SELECT_ACTIVITY", "FAB clicked");
                DialogFragment fragment = new JoinCreateDialog();
                fragment.show(getFragmentManager(), "Join/Create dialog");
            }
        });
    }

    @Override
    public ShoppingListAdapter getAdapter() {
        return shoppingListAdapter;
    }

    @Override
    public PersistenceManager getPersistenceManger() {
        return persistenceManager;
    }
}
