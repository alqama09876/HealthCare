package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Add_Doctor extends AppCompatActivity {
    ImageView btn_back;
    Boolean isAdded;
    String doctorName, doctorSpecifications, doctorHospital, doctorTimmings;
    EditText edt_name, edt_spec, edt_hospital, edt_timmmings;
    DatabaseHelper databaseHelper = new DatabaseHelper(Add_Doctor.this);
    Button btn_addDoctor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_doctor);
        btn_back = findViewById(R.id.btn_back);
        edt_name = findViewById(R.id.edt_name);
        edt_spec = findViewById(R.id.edt_spec);
        edt_hospital = findViewById(R.id.edt_hospital);
        edt_timmmings = findViewById(R.id.edt_timmmings);
        btn_addDoctor = findViewById(R.id.btn_addDoctor);

        btn_addDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doctorName = edt_name.getText().toString();
                doctorSpecifications = edt_spec.getText().toString();
                doctorHospital = edt_hospital.getText().toString();
                doctorTimmings = edt_timmmings.getText().toString();
                if(doctorName.isEmpty() || doctorSpecifications.isEmpty() || doctorHospital.isEmpty() || doctorTimmings.isEmpty()){
                    edt_name.setError("*Required");
                    edt_spec.setError("*Required");
                    edt_hospital.setError("*Required");
                    edt_timmmings.setError("*Required");
                }
                else{
                    isAdded = databaseHelper.addDoctorData(doctorName, doctorSpecifications, doctorHospital, doctorTimmings);
                    if(isAdded){
                        Toast.makeText(Add_Doctor.this, "Doctor Added", Toast.LENGTH_SHORT).show();
                        edt_name.setText("");
                        edt_spec.setText("");
                        edt_hospital.setText("");
                        edt_timmmings.setText("");
                    }
                    else{
                        Toast.makeText(Add_Doctor.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}