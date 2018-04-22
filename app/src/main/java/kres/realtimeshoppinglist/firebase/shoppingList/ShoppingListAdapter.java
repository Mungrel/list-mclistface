package kres.realtimeshoppinglist.firebase.shoppingList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.model.ShoppingList;

public class ShoppingListAdapter {

    private LinearLayout listLayout;
    private LayoutInflater inflater;

    public ShoppingListAdapter(LinearLayout listLayout, Context context) {
        this.listLayout = listLayout;
        this.inflater = LayoutInflater.from(context);
    }

    public void appendItem(ShoppingList shoppingList) {
        RelativeLayout newItemLayout = (RelativeLayout) inflater.inflate(R.layout.shopping_list_item_layout, null, false);

        TextView listName = newItemLayout.findViewById(R.id.list_select_list_name);
        ImageButton deleteButton = newItemLayout.findViewById(R.id.delete_list_button);

        listName.setText(shoppingList.getName());

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete button clicked
            }
        });

        listLayout.addView(newItemLayout);
    }

    public void deleteItem(int index) {
        if (index < listLayout.getChildCount()) {
            listLayout.removeViewAt(index);
        }
    }

    public void updateName(int index, String newName) {
        RelativeLayout layout = (RelativeLayout) listLayout.getChildAt(index);

        TextView listName = layout.findViewById(R.id.list_select_list_name);
        listName.setText(newName);
    }
}
