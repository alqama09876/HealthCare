package com.example.healthcare;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText emailEditText, passwordEditText;
    TextView signUpTextView;
    Button loginButton;
    boolean isUserLoggedIn;

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);

        emailEditText = findViewById(R.id.edtEmail);
        passwordEditText = findViewById(R.id.edtPassword);
        signUpTextView = findViewById(R.id.tvVisitSignup);
        loginButton = findViewById(R.id.btnLogin);

        databaseHelper = new DatabaseHelper(LoginActivity.this);

        signUpTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), SignupScreen.class));
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = emailEditText.getText().toString().trim();
                String userPassword = passwordEditText.getText().toString().trim();

                if (userEmail.isEmpty() || userPassword.isEmpty()) {
                    showError("Email and Password are required");
                } else if (userPassword.length() <= 3) {
                    showError("Password must be more than 3 characters");
                } else {
                    isUserLoggedIn = databaseHelper.userLoginValidation(userEmail, userPassword);
                    if (isUserLoggedIn) {
                        saveUserEmailToSharedPreferences(userEmail);
                        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                    } else {
                        showError("Wrong Credentials. Please Try Again.");
                    }
                }
            }
        });
    }

    private void saveUserEmailToSharedPreferences(String email) {
        SharedPreferences userPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = userPreferences.edit();
        editor.putString("email", email);
        editor.apply();
    }

    private void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
