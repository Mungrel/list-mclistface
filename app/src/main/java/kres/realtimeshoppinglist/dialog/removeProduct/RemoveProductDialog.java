package kres.realtimeshoppinglist.dialog.removeProduct;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import kres.realtimeshoppinglist.firebase.productList.ProductListManager;

public class RemoveProductDialog {

    private Context context;
    private String listID;
    private String productID;

    private AlertDialog dialog;

    public RemoveProductDialog(Context context, String listID, String productID) {
        this.context = context;
        this.listID = listID;
        this.productID = productID;

        this.dialog = build();
    }

    private AlertDialog build() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setTitle("Remove Product");
        builder.setMessage("Remove this product?");

        builder.setPositiveButton("Remove", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ProductListManager.removeItem(listID, productID);
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
