package kres.realtimeshoppinglist.firebase.productList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.dialog.remove.DeleteProductDialog;
import kres.realtimeshoppinglist.model.Product;

public class ProductListAdapter {

    private LinearLayout productListLayout;
    private LayoutInflater inflater;
    private Context context;

    private String listID;
    private List<String> productIDs;

    public ProductListAdapter(LinearLayout productListLayout, Context context, String listID) {
        this.productListLayout = productListLayout;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
        this.listID = listID;

        this.productIDs = new ArrayList<>();
    }

    public void removeItem(String productID) {
        int index = productIDs.indexOf(productID);
        if (index != -1 && index < productListLayout.getChildCount()) {
            productListLayout.removeViewAt(index);
            productIDs.remove(index);
        }
    }

    public void moveItem(int oldIndex, int newIndex) {
        Log.d("MOVING_ITEM", "From: " + oldIndex + " To: " + newIndex);
        if (oldIndex == newIndex) {
            return;
        }

        LinearLayout item = (LinearLayout) productListLayout.getChildAt(oldIndex);
        productListLayout.removeViewAt(oldIndex);
        String id = productIDs.remove(oldIndex);
        productListLayout.addView(item, newIndex);
        productIDs.add(newIndex, id);
    }

    public void insertItem(int index, final Product item) {
        RelativeLayout listItem = (RelativeLayout) inflater.inflate(R.layout.product_list_item_layout, null, false);
        CheckBox itemCheckBox = listItem.findViewById(R.id.list_item_check_box);

        itemCheckBox.setText(item.getName());
        itemCheckBox.setChecked(item.isBought());

        itemCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                item.setBought(isChecked);
                ProductListManager.editItem(listID, item);
            }
        });

        ImageButton deleteButton = listItem.findViewById(R.id.delete_product_button);

        final ProductListAdapter that = this;

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteProductDialog dialog = new DeleteProductDialog(context, listID, item.getID(), that);
                dialog.show();
            }
        });

        if (index >= productListLayout.getChildCount()) {
            productListLayout.addView(listItem);
        } else {
            productListLayout.addView(listItem, index);
        }

        productIDs.add(item.getID());
    }

    public void updateItem(int index, Product newItem) {
        LinearLayout listItem = (LinearLayout) productListLayout.getChildAt(index);
        CheckBox itemCheckBox = listItem.findViewById(R.id.list_item_check_box);

        itemCheckBox.setText(newItem.getName());
        itemCheckBox.setChecked(newItem.isBought());
    }
}
