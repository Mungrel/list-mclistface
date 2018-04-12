package kres.realtimeshoppinglist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kres.realtimeshoppinglist.R;

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

                // TODO: Create the list in Firebase
            }
        });
    }
}
