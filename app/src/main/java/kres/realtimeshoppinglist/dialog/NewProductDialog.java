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
import android.widget.NumberPicker;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.model.Product;

public class NewProductDialog extends DialogFragment {

    private NewProductDialogListener listener;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View dialogView = inflater.inflate(R.layout.dialog_new_product, null);
        final NumberPicker numberPicker = dialogView.findViewById(R.id.product_quantity_number_picker);
        final EditText editText = dialogView.findViewById(R.id.product_name_edit_text);

        builder.setView(dialogView);

        builder.setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("NEW_PRODUCT_DIALOG", "Positive button clicked");

                int quantity = numberPicker.getValue();
                String productName = editText.getText().toString();
                Product product = new Product(productName, quantity);

                listener.onProductAdded(product);
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Log.d("NEW_PRODUCT_DIALOG", "Negative button clicked");
                listener.onCancel();
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
            listener = (NewProductDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString() + " must implement NewProductDialogListener");
        }
    }
}
