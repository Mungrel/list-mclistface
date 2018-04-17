package kres.realtimeshoppinglist.util;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.firebase.productList.ProductListManager;
import kres.realtimeshoppinglist.model.Product;

public class ProductListAdapter {

    private LinearLayout productListLayout;
    private LayoutInflater inflater;

    private String listID;

    public ProductListAdapter(LinearLayout productListLayout, Context context, String listID) {
        this.productListLayout = productListLayout;
        this.inflater = LayoutInflater.from(context);
        this.listID = listID;
    }

    public void removeItem(int index) {
        productListLayout.removeViewAt(index);
    }

    public void moveItem(int oldIndex, int newIndex) {
        Log.d("MOVING_ITEM", "From: " + oldIndex + " To: " + newIndex);
        if (oldIndex == newIndex) {
            return;
        }

        LinearLayout item = (LinearLayout) productListLayout.getChildAt(oldIndex);
        productListLayout.removeViewAt(oldIndex);
        productListLayout.addView(item, newIndex);
    }

    public void insertItem(int index, final Product item) {
        LinearLayout listItem = (LinearLayout) inflater.inflate(R.layout.shopping_list_item_layout, null, false);
        CheckBox itemCheckBox = listItem.findViewById(R.id.list_item_check_box);

        itemCheckBox.setText(String.format("%dx %s", item.getQuantity(), item.getName()));
        itemCheckBox.setChecked(item.isBought());

        itemCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                item.setBought(isChecked);
                ProductListManager.editItem(listID, item);
            }
        });

        if (index >= productListLayout.getChildCount()) {
            productListLayout.addView(listItem);
        } else {
            productListLayout.addView(listItem, index);
        }

    }

    public void updateItem(int index, Product newItem) {
        LinearLayout listItem = (LinearLayout) productListLayout.getChildAt(index);
        CheckBox itemCheckBox = listItem.findViewById(R.id.list_item_check_box);

        itemCheckBox.setText(String.format("%dx %s", newItem.getQuantity(), newItem.getName()));
        itemCheckBox.setChecked(newItem.isBought());
    }
}
