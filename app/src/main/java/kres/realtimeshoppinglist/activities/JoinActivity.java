package kres.realtimeshoppinglist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.gson.Gson;

import kres.realtimeshoppinglist.R;
import kres.realtimeshoppinglist.firebase.shoppingList.ListExistsListener;
import kres.realtimeshoppinglist.firebase.shoppingList.ShoppingListManager;
import kres.realtimeshoppinglist.model.ShoppingList;
import kres.realtimeshoppinglist.util.Constants;

public class JoinActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        final EditText listCodeEditText = findViewById(R.id.list_code_edit_text);
        Button submitButton = findViewById(R.id.submit_code_button);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String code = listCodeEditText.getText().toString();
                if (code.isEmpty()) {
                    return;
                }

                ShoppingListManager.getShoppingList(code, new ListExistsListener() {
                    @Override
                    public void onListFound(ShoppingList list) {
                        String json = new Gson().toJson(list);

                        Intent intent = new Intent(JoinActivity.this, ProductListActivity.class);
                        intent.putExtra(Constants.SHOPPING_LIST_INTENT_KEY, json);

                        startActivity(intent);
                    }

                    @Override
                    public void onListNotFound() {
                        Log.d("LIST_NOT_FOUND", "No list found with ID: " + code);
                    }
                });


            }
        });
    }
}
