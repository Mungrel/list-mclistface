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
        String existingProductName = getArguments().getString(Constants.EDIT_DIALOG_PRODUCT_NAME_KEY);
        editText.setText(existingProductName);

        builder.setView(dialogView);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("NEW_PRODUCT_DIALOG", "Positive button clicked");

                String productName = editText.getText().toString();
                Product product = new Product(productName);

                listener.onProductNameUpdated(product);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("NEW_PRODUCT_DIALOG", "Negative button clicked");
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
            throw new ClassCastException(activity.toString() + " must implement NewProductDialogListener");
        }
    }
}
