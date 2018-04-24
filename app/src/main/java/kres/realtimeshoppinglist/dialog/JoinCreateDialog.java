package kres.realtimeshoppinglist.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TabHost;

import com.google.gson.Gson;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.firebase.shoppingList.ListExistsListener;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListManager;
import kres.realtimeshoppinglist.model.ShoppingList;
import kres.realtimeshoppinglist.persistence.PersistenceManager;

public class JoinCreateDialog extends DialogFragment {

    private ShoppingListUtil utils;

    private static final String TAB_CREATE = "TAB_CREATE";
    private static final String TAB_JOIN = "TAB_JOIN";
    private static final int TAB_CREATE_INDEX = 0;
    private static final int TAB_JOIN_INDEX = 1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.join_create_dialog, null);

        final TabHost host = dialogView.findViewById(R.id.tab_host);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec(TAB_CREATE);
        spec.setContent(R.id.tab_create_linear_layout);
        spec.setIndicator("Create");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec(TAB_JOIN);
        spec.setContent(R.id.tab_join_linear_layout);
        spec.setIndicator("Join");
        host.addTab(spec);

        final EditText joinCodeEditText = dialogView.findViewById(R.id.tab_join_join_code_edit_text);
        final EditText listNameEditText = dialogView.findViewById(R.id.tab_create_list_name_edit_text);

        builder.setView(dialogView);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("NEW_LIST_DIALOG", "Positive button clicked");
                switch(host.getCurrentTab()) {
                    case TAB_CREATE_INDEX:
                        String newListName = listNameEditText.getText().toString();
                        ShoppingListManager.createShoppingList(newListName);
                        break;
                    case TAB_JOIN_INDEX:
                        String joinCode = joinCodeEditText.getText().toString();
                        ShoppingListManager.getShoppingList(joinCode, new ListExistsListener() {
                            @Override
                            public void onListFound(ShoppingList list) {
                                utils.getPersistenceManger().persistKnownID(list.getId());
                                utils.getAdapter().appendItem(list);
                            }

                            @Override
                            public void onListNotFound() {
                                // TODO: Show some error
                            }
                        });
                        break;
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("NEW_LIST_DIALOG", "Negative button clicked");
            }
        });

        return builder.create();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            utils = (ShoppingListUtil) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement ShoppingListUtil");
        }
    }
}
