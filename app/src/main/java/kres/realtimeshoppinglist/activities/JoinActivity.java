package kres.realtimeshoppinglist.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import kres.realtimeshoppinglist.R;

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
                String code = listCodeEditText.getText().toString();
                if (code.isEmpty()) {
                    return;
                }

                // TODO: Make some Firebase call to check list's existence
            }
        });
    }
}
