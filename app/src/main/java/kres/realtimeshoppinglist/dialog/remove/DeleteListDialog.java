package kres.realtimeshoppinglist.dialog.remove;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListAdapter;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListManager;

public class DeleteListDialog {

    private Context context;
    private String listID;

    private ShoppingListAdapter adapter;

    private AlertDialog dialog;

    public DeleteListDialog(Context context, String listID, ShoppingListAdapter adapter) {
        this.context = context;
        this.listID = listID;

        this.adapter = adapter;

        this.dialog = build();
    }

    private AlertDialog build() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Delete List");
        builder.setMessage("Delete this list for everyone?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ShoppingListManager.deleteList(listID);
                adapter.deleteItem(listID);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });

        return builder.create();
    }

    public void show() {
        dialog.show();
    }
}
