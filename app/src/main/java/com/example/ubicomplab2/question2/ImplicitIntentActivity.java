package com.example.ubicomplab2.question2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.ubicomplab2.R;

public class ImplicitIntentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_implicit_intent);

        //Connections
        EditText codeInput = findViewById(R.id.codeInput);
        Button verifyButton = findViewById(R.id.verifyButton);

        //Retrieve the generated code
        int verificationCode = getIntent().getIntExtra("verificationCode", -1);

        //Check the code that the user input
        verifyButton.setOnClickListener(v -> {

            String enteredCode = codeInput.getText().toString();

            if (enteredCode.equals(String.valueOf(verificationCode))) {
                Toast.makeText(this, "Account Verified!", Toast.LENGTH_LONG).show();
            } else {
                codeInput.setError("Incorrect verification code");
            }

        });

    }
}