package kres.listyMcListFace.dialog.remove;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import kres.listyMcListFace.dialog.DialogShowListener;
import kres.listyMcListFace.firebase.productList.ProductListAdapter;
import kres.listyMcListFace.firebase.productList.ProductListManager;
import kres.listyMcListFace.firebase.shoppingList.ShoppingListAdapter;

public class DeleteProductDialog {

    private Context context;
    private String listID;
    private String productID;

    private ProductListAdapter adapter;

    private AlertDialog dialog;

    public DeleteProductDialog(Context context, String listID, String productID, ProductListAdapter adapter) {
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
                adapter.removeItem(productID);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogShowListener(context, alertDialog));

        return alertDialog;
    }

    public void show() {
        dialog.show();
    }
}
