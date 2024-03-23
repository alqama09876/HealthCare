package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.healthcare.PojoClasses.Login_Model;

import java.util.ArrayList;

public class ProfileScreen extends AppCompatActivity {
    ImageView back;
    Button btn_logout, btn_update;
    EditText edt_name, edt_email, edt_phone;
    String user_name, user_email, user_phone;
    Boolean isupdated;
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    ArrayList<Login_Model> userDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        back = findViewById(R.id.back);
        edt_name = findViewById(R.id.tv_name);
        edt_email = findViewById(R.id.tv_email);
        edt_phone = findViewById(R.id.tv_phone);
        btn_update = findViewById(R.id.btn_update);
        btn_logout = findViewById(R.id.btn_logout);
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        String email = sharedPreferences.getString("email", null); // -1 is a default value in case the ID doesn't exist
        if(email!=null){
            userDetails = databaseHelper.getLoggedInUserDetails(email);
            if(userDetails.size()>0){
                edt_name.setText(userDetails.get(0).getName());
                edt_email.setText(userDetails.get(0).getEmail());
                edt_phone.setText(userDetails.get(0).getPhone());
            }
            else{
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
        }
        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user_name = edt_name.getText().toString();
                user_email = edt_email.getText().toString();
                user_phone = edt_phone.getText().toString();
                isupdated = databaseHelper.updateUserDetails(user_name, user_email, user_phone);
                if(isupdated){
                    Toast.makeText(ProfileScreen.this, "Updated Successfully", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(ProfileScreen.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}