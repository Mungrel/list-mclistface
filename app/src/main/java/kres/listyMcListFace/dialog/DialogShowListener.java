package kres.listyMcListFace.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v4.content.ContextCompat;
import android.widget.Button;

import kres.listyMcListFace.R;

public class DialogShowListener implements DialogInterface.OnShowListener {

    private Context context;
    private AlertDialog alertDialog;

    public DialogShowListener(Context context, AlertDialog alertDialog) {
        this.context = context;
        this.alertDialog = alertDialog;
    }

    @Override
    public void onShow(DialogInterface dialogInterface) {
        int confirmColor = ContextCompat.getColor(context, R.color.colorPrimary);
        int cancelColor = ContextCompat.getColor(context, R.color.colorBodyText);

        Button positive = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
        Button negative = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);

        if (positive != null) {
            positive.setTextColor(confirmColor);
        }

        if (negative != null) {
            negative.setTextColor(cancelColor);
        }
    }
}
