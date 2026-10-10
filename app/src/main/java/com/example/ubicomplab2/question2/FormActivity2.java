package com.example.ubicomplab2.question2;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ubicomplab2.R;

import java.util.Random;

public class FormActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { //Form
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_form2);

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

            //Generate a random int
            Random random = new Random();
            int verificationCode = 100000 + random.nextInt(900000);

            //Implicit intent tells what to do but not where
            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
            emailIntent.setData(Uri.parse("mailto:"));
            //An external email application receives the extras.
            emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{email});
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Account Verification");
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Your verification code is: " + verificationCode);

            //Send the email
            Intent verifyIntent = new Intent(this, ImplicitIntentActivity.class);
            verifyIntent.putExtra("verificationCode", verificationCode);

            try {
                startActivity(verifyIntent);
                startActivity(emailIntent);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "No email app found", Toast.LENGTH_SHORT).show();
            }

            Toast.makeText(
                    FormActivity2.this,
                    "Thank you " + name + ", your request is being processed",
                    Toast.LENGTH_SHORT).show();
        });
    }



}
