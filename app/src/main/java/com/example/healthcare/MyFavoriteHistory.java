package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.healthcare.DashboardAdapter.FavoriteAdapter;
import com.example.healthcare.PojoClasses.Doctor_Model;

import java.util.ArrayList;

public class MyFavoriteHistory extends AppCompatActivity {
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    ImageView btn_back;
    ArrayList<Doctor_Model> doctorModels = new ArrayList<>();
    RecyclerView rv_yourAppointment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_history);
        btn_back = findViewById(R.id.btn_back);
        rv_yourAppointment = findViewById(R.id.rv_yourAppointment);
//
        doctorModels.clear();
        Cursor cursor = databaseHelper.displayfavData();
        while (cursor.moveToNext()){
            Doctor_Model doctorModel = new Doctor_Model(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4));
            doctorModels.add(doctorModel);
        }
        FavoriteAdapter adapter = new FavoriteAdapter(doctorModels, this, databaseHelper);
        rv_yourAppointment.setLayoutManager(new LinearLayoutManager(this));
        rv_yourAppointment.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}