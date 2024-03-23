package com.example.healthcare;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcare.DashboardAdapter.DoctorAdapter;
import com.example.healthcare.PojoClasses.Doctor_Model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomeScreen extends AppCompatActivity {
    ImageView iv_userProfile;
    Button btn_seeAppointments;
    EditText edt_search_doctor;
    RecyclerView rv_displayDoctors;
    String search;
    TextView tv_healthInfo;
    FloatingActionButton fab_addDoctor;
    ArrayList<Doctor_Model> doctorModels = new ArrayList<>();
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        tv_healthInfo = findViewById(R.id.tv_healthInfo);
        iv_userProfile = findViewById(R.id.iv_userProfile);
        edt_search_doctor = findViewById(R.id.edt_search_doctor);
        rv_displayDoctors = findViewById(R.id.rv_displayDoctors);
        btn_seeAppointments = findViewById(R.id.btn_seeAppointments);
        fab_addDoctor = findViewById(R.id.fab_addDoctor);

        displayAllDoctors();
               edt_search_doctor.addTextChangedListener(new TextWatcher() {
                   @Override
                   public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                       
                   }

                   @Override
                   public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                       search = edt_search_doctor.getText().toString();
                       if(!search.isEmpty()){
                           doctorModels.clear();
                           doctorModels = databaseHelper.searchDoctorsBySpecialty(search);
                           DoctorAdapter adapter = new DoctorAdapter(doctorModels, HomeScreen.this, databaseHelper);
                           rv_displayDoctors.setLayoutManager(new LinearLayoutManager(HomeScreen.this));
                           rv_displayDoctors.setAdapter(adapter);
                           adapter.notifyDataSetChanged();
                       }
                   }
                   @Override
                   public void afterTextChanged(Editable editable) {
                   }
               });

        btn_seeAppointments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MyFavoriteHistory.class));
            }
        });
        fab_addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Add_Doctor.class));

            }
        });
        iv_userProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProfileScreen.class));
            }
        });
        tv_healthInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), HealthInformation.class));
            }
        });

    }

    public void displayAllDoctors() {
        Cursor cursor = databaseHelper.displayDoctorData();
        while (cursor.moveToNext()){
            Doctor_Model doctorModel = new Doctor_Model(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            doctorModels.add(doctorModel);
        }
        DoctorAdapter adapter = new DoctorAdapter(doctorModels, this, databaseHelper);
        rv_displayDoctors.setLayoutManager(new LinearLayoutManager(this));
        rv_displayDoctors.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }
}