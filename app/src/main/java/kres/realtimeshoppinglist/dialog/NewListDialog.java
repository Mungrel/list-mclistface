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

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.firebase.productList.ProductListManager;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListManager;
import kres.realtimeshoppinglist.model.Product;
import kres.realtimeshoppinglist.model.ShoppingList;

public class NewListDialog extends DialogFragment {

    private ShoppingListUtil utils;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_new_list, null);

        final EditText editText = dialogView.findViewById(R.id.list_name_edit_text);

        builder.setView(dialogView);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("NEW_LIST_DIALOG", "Positive button clicked");

                String listName = editText.getText().toString();
                ShoppingList newShoppingList = ShoppingListManager.createShoppingList(listName);
                utils.getAdapter().appendItem(newShoppingList);
                utils.getPersistenceManger().persistKnownID(newShoppingList.getId());
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