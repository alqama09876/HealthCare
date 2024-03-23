package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

public class SignupScreen extends AppCompatActivity {

    private EditText edName, edEmail, edPass, edPhone;
    private AppCompatButton btSignup;
    private DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_screen);

        db = new DatabaseHelper(SignupScreen.this);

        edName = findViewById(R.id.edName);
        edEmail = findViewById(R.id.edEmail);
        edPhone = findViewById(R.id.edPhone);
        edPass = findViewById(R.id.edPass);
        btSignup = findViewById(R.id.btSignup);

        btSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = edName.getText().toString().trim();
                String userEmail = edEmail.getText().toString().trim();
                String userPhone = edPhone.getText().toString().trim();
                String userPassword = edPass.getText().toString().trim();

                if (username.isEmpty() || userEmail.isEmpty() || userPhone.isEmpty() || userPassword.isEmpty()) {
                    showError("Please fill all fields");
                } else {
                    boolean userRegistered = db.userRegisteration(username, userEmail, userPhone, userPassword);
                    if (userRegistered) {
                        showSuccess("User registered successfully");
                        clearFields();
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    } else {
                        showError("Something went wrong");
                    }
                }
            }
        });
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void showSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void clearFields() {
        edName.setText("");
        edEmail.setText("");
        edPhone.setText("");
        edPass.setText("");
    }

    public void backLogin(View view) {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
        finish();
    }
}
