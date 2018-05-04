package kres.listyMcListFace.dialog.editProduct;

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

import com.google.gson.Gson;

import kres.listyMcListFace.R;
import kres.listyMcListFace.dialog.DialogShowListener;
import kres.listyMcListFace.model.Product;
import kres.listyMcListFace.util.Constants;

public class EditProductDialog extends DialogFragment {

    private EditProductDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_edit_product, null);

        final EditText editText = dialogView.findViewById(R.id.product_name_edit_text);
        String existingProductNameJSON = getArguments().getString(Constants.EDIT_DIALOG_EXISTING_PRODUCT_JSON);
        final Product existingProduct = new Gson().fromJson(existingProductNameJSON, Product.class);
        editText.setText(existingProduct.getName());

        builder.setView(dialogView);

        builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("EDIT_PRODUCT_DIALOG", "Positive button clicked");

                String productName = editText.getText().toString();
                existingProduct.setName(productName);

                listener.onProductNameUpdated(existingProduct);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("EDIT_PRODUCT_DIALOG", "Negative button clicked");
                listener.onCancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogShowListener(getActivity(), alertDialog));

        return alertDialog;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            listener = (EditProductDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement EditProductDialogListener");
        }
    }
}
