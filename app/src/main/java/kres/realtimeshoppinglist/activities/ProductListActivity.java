package kres.realtimeshoppinglist.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.dialog.NewProductDialog;
import kres.realtimeshoppinglist.dialog.NewProductDialogListener;
import kres.realtimeshoppinglist.model.Product;

public class ProductListActivity extends AppCompatActivity implements NewProductDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        FloatingActionButton fab = findViewById(R.id.product_list_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment fragment = new NewProductDialog();
                fragment.show(getFragmentManager(), "New Product");
            }
        });
    }

    @Override
    public void onProductAdded(Product product) {
        Log.d("PRODUCT_ADDED", "" + product.getName());
    }

    @Override
    public void onCancel() {

    }
}
