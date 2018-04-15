package kres.realtimeshoppinglist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListManager;
import kres.realtimeshoppinglist.model.ShoppingList;
import kres.realtimeshoppinglist.util.Constants;

public class CreateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);

        final EditText listNameEditText = findViewById(R.id.list_name_edit_text);
        Button createButton = findViewById(R.id.create_button);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String listName = listNameEditText.getText().toString();
                if (listName.isEmpty()) {
                    return;
                }

                ShoppingList createdShoppingList = ShoppingListManager.createShoppingList(listName);
                String json = new Gson().toJson(createdShoppingList);

                Intent intent = new Intent(CreateActivity.this, ProductListActivity.class);
                intent.putExtra(Constants.SHOPPING_LIST_INTENT_KEY, json);
                startActivity(intent);
            }
        });
    }
}
