package kres.realtimeshoppinglist.activities;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.firebase.util.FirebaseRefs;
import kres.realtimeshoppinglist.firebase.productList.ProductListChangeListener;
import kres.realtimeshoppinglist.firebase.productList.ProductListManager;
import kres.realtimeshoppinglist.model.ShoppingList;
import kres.realtimeshoppinglist.util.Constants;
import kres.realtimeshoppinglist.firebase.productList.ProductListAdapter;

import kres.realtimeshoppinglist.dialog.newProduct.NewProductDialog;
import kres.realtimeshoppinglist.dialog.newProduct.NewProductDialogListener;
import kres.realtimeshoppinglist.model.Product;

public class ProductListActivity extends AppCompatActivity implements NewProductDialogListener {

    private String listID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);

        String shoppingListJSON = getIntent().getStringExtra(Constants.SHOPPING_LIST_INTENT_KEY);
        ShoppingList shoppingList = new Gson().fromJson(shoppingListJSON, ShoppingList.class);

        listID = shoppingList.getId();

        TextView titleBarTitle = findViewById(R.id.title_bar_title_text);
        TextView titleBarJoinCode = findViewById(R.id.title_bar_join_code);

        titleBarTitle.setText(shoppingList.getName());
        titleBarJoinCode.setText(String.format("Join - %s", shoppingList.getId()));

        LinearLayout productListLayout = findViewById(R.id.product_list_layout);
        ProductListAdapter adapter = new ProductListAdapter(productListLayout, ProductListActivity.this, listID);
        ProductListChangeListener changeListener = new ProductListChangeListener(adapter);

        DatabaseReference listRef = FirebaseRefs.getShoppingListProductsRef(listID);
        listRef.addChildEventListener(changeListener); 

        FloatingActionButton fab = findViewById(R.id.product_list_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("PRODUCT_LIST_ACTIVITY", "FAB clicked");
                DialogFragment fragment = new NewProductDialog();
                fragment.show(getFragmentManager(), "New Product");
            }
        });
    }

    @Override
    public void onProductAdded(Product product) {
        Log.d("PRODUCT_ADDED", "" + product.getName());
        ProductListManager.addItem(listID, product);
    }

    @Override
    public void onCancel() {
    }
}
