package kres.realtimeshoppinglist.firebase.productList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.dialog.removeProduct.RemoveProductDialog;
import kres.realtimeshoppinglist.model.Product;

public class ProductListAdapter {

    private LinearLayout productListLayout;
    private LayoutInflater inflater;
    private Context context;

    private String listID;

    public ProductListAdapter(LinearLayout productListLayout, Context context, String listID) {
        this.productListLayout = productListLayout;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
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
        LinearLayout listItem = (LinearLayout) inflater.inflate(R.layout.product_list_item_layout, null, false);
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

        itemCheckBox.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                RemoveProductDialog dialog = new RemoveProductDialog(context, listID, item.getID());
                dialog.show();
                return false;
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

        itemCheckBox.setText(newItem.getName());
        itemCheckBox.setChecked(newItem.isBought());
    }
}
