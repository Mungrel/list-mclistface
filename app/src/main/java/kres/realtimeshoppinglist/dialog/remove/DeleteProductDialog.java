package kres.realtimeshoppinglist.dialog.remove;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import kres.realtimeshoppinglist.firebase.productList.ProductListManager;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListAdapter;

public class DeleteProductDialog {

    private Context context;
    private String listID;
    private String productID;

    private ShoppingListAdapter adapter;

    private AlertDialog dialog;

    public DeleteProductDialog(Context context, String listID, String productID, ShoppingListAdapter adapter) {
        this.context = context;
        this.listID = listID;
        this.productID = productID;

        this.adapter = adapter;

        this.dialog = build();
    }

    private AlertDialog build() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Delete Product");
        builder.setMessage("Delete this product for everyone?");

        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProductListManager.removeItem(listID, productID);
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
