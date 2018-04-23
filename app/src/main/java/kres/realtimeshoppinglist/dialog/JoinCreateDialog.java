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

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListManager;
import kres.realtimeshoppinglist.model.ShoppingList;

public class JoinCreateDialog extends DialogFragment {

    private static final String TAB_CREATE = "TAB_CREATE";
    private static final String TAB_JOIN = "TAB_JOIN";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.join_create_dialog, null);

        TabHost host = dialogView.findViewById(R.id.tab_host);
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
}
