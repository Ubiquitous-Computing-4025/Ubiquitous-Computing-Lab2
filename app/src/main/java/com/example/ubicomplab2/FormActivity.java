package com.example.ubicomplab2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Form
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form);

        EditText nameInput = findViewById(R.id.nameInput);
        EditText passwordInput = findViewById(R.id.passwordInput);
        EditText phoneInput = findViewById(R.id.phoneInput);
        EditText emailInput = findViewById(R.id.emailInput);

        Button submitButton = findViewById(R.id.submitButton);

        submitButton.setOnClickListener(v -> {

            String name = nameInput.getText().toString();
            String password = passwordInput.getText().toString();
            String phone = phoneInput.getText().toString();
            String email = emailInput.getText().toString();

            if (!name.matches("[a-zA-Z ]+")) { //Validation checks
                nameInput.setError("Name must contain letters only");
                return;
            }

            if (!phone.matches("[0-9]+")) {
                phoneInput.setError("Telephone number must contain digits only");
                return;
            }

            if (password.isEmpty()) {
                passwordInput.setError("Password is required");
                return;
            }

            //From Stack Overflow
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                emailInput.setError("Enter a valid email address");
                return;
            }

            Toast.makeText(
                    FormActivity.this,
                    "Thank you " + name + ", your request is being processed",
                    Toast.LENGTH_SHORT).show();
        });
    }



}
