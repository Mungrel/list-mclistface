package kres.realtimeshoppinglist.firebase.shoppingList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.activities.ProductListActivity;
import kres.realtimeshoppinglist.dialog.remove.DeleteListDialog;
import kres.realtimeshoppinglist.model.ShoppingList;
import kres.realtimeshoppinglist.util.Constants;

public class ShoppingListAdapter {

    private LinearLayout listLayout;
    private LayoutInflater inflater;
    private Context context;

    private List<String> listIDs;

    public ShoppingListAdapter(LinearLayout listLayout, Context context) {
        this.listLayout = listLayout;
        this.inflater = LayoutInflater.from(context);
        this.context = context;

        this.listIDs = new ArrayList<>();
    }

    public void appendItem(final ShoppingList shoppingList) {
        RelativeLayout newItemLayout = (RelativeLayout) inflater.inflate(R.layout.shopping_list_item_layout, null, false);

        TextView listName = newItemLayout.findViewById(R.id.list_select_list_name);
        ImageButton deleteButton = newItemLayout.findViewById(R.id.delete_list_button);

        listName.setText(shoppingList.getName());

        final ShoppingListAdapter that = this;

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Delete button clicked
                DeleteListDialog dialog = new DeleteListDialog(context, shoppingList.getId(), that);
                dialog.show();
            }
        });

        newItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ProductListActivity.class);
                String shoppingListJSON = new Gson().toJson(shoppingList);
                intent.putExtra(Constants.SHOPPING_LIST_INTENT_KEY, shoppingListJSON);
                context.startActivity(intent);
            }
        });

        listLayout.addView(newItemLayout);
        listIDs.add(shoppingList.getId());
    }

    public void deleteItem(String listID) {
        int index = listIDs.indexOf(listID);
        if (index != -1 && index < listLayout.getChildCount()) {
            listLayout.removeViewAt(index);
            listIDs.remove(index);
        }
    }

    public void updateName(int index, String newName) {
        RelativeLayout layout = (RelativeLayout) listLayout.getChildAt(index);

        TextView listName = layout.findViewById(R.id.list_select_list_name);
        listName.setText(newName);
    }
}
