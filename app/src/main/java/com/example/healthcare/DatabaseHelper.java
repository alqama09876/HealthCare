package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//import com.developer.translate.Model.UserModel;
import com.example.healthcare.PojoClasses.Doctor_Model;
import com.example.healthcare.PojoClasses.Login_Model;

import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "HEALTHCARE";
    private static final int DB_VERSION = 12;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createUserTable = "CREATE TABLE USERS(id INTEGER PRIMARY KEY, Name TEXT, Email TEXT, Phone TEXT, Password TEXT)";
        String createDoctorTable = "CREATE TABLE DOCTOR(id INTEGER PRIMARY KEY, Name TEXT, Specialities TEXT, Affiliated_Hospital_Name TEXT, Office_Hours TEXT)";
        String createAppointmentTable = "CREATE TABLE FAVORITE(id INTEGER PRIMARY KEY, Name TEXT, Specialities TEXT, Affiliated_Hospital_Name TEXT, Office_Hours TEXT)";
        db.execSQL(createUserTable);
        db.execSQL(createDoctorTable);
        db.execSQL(createAppointmentTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS USERS");
        db.execSQL("DROP TABLE IF EXISTS DOCTOR");
        db.execSQL("DROP TABLE IF EXISTS FAVORITE");
        onCreate(db);
    }

    public boolean userRegisteration(String name, String email, String phone, String pass) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Email", email);
        cv.put("Phone", phone);
        cv.put("Password", pass);
        Long l = sqLiteDatabase.insert("USERS", null, cv);
        sqLiteDatabase.close();
        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean userLoginValidation(String email, String pass) {
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM USERS WHERE Email='" + email + "' AND Password='" + pass + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            // User is LoggedIn
            return true;

        } else {
            // User is  Not LoggedIn
            return false;
        }
    }


    public boolean addUserData(String name, String email, String phone) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Email", email);
        cv.put("Phone", phone);
        Long l = sqLiteDatabase.insert("USERS", null, cv);
        sqLiteDatabase.close();
        if (l > 0) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addDoctorData(String name, String specialities, String affiliated_hospital, String office_hours) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Specialities", specialities);
        cv.put("Affiliated_Hospital_Name", affiliated_hospital);
        cv.put("Office_Hours", office_hours);
        Long l = sqLiteDatabase.insert("DOCTOR", null, cv);
        sqLiteDatabase.close();
        return  l > 0;
    }

    public boolean addFavorite(String name, String specialities, String affiliated_hospital, String office_hours) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("Name", name);
        cv.put("Specialities", specialities);
        cv.put("Affiliated_Hospital_Name", affiliated_hospital);
        cv.put("Office_Hours", office_hours);
        Long l = sqLiteDatabase.insert("FAVORITE", null, cv);
        sqLiteDatabase.close();
        return l > 0;
    }

    public Cursor displayUserData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM USERS ";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    public Cursor displayfavData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM FAVORITE";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    public Cursor displayDoctorData() {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "SELECT * FROM DOCTOR";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        return cursor;
    }

    public ArrayList<Login_Model> getLoggedInUserDetails(String email) {
        ArrayList<Login_Model> loginModels = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String query = "SELECT * FROM USERS WHERE Email='" + email + "'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            String name = cursor.getString(1);
            String user_email = cursor.getString(2);
            String phone = cursor.getString(3);
            loginModels.add(new Login_Model(name, user_email, phone));
        }
        return loginModels;
    }

    public boolean updateUserDetails(String name, String email, String phone) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Name", name);
        values.put("Email", email);
        values.put("Phone", phone);
        String selection = "email = ?";
        String[] selectionArgs = {email};
        int count = db.update("USERS", values, selection, selectionArgs);
        db.close();
        return count > 0;
    }

    @SuppressLint("Range")
    public ArrayList<Doctor_Model> searchDoctorsBySpecialty(String name) {
        ArrayList<Doctor_Model> doctors = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        String selection = "Name LIKE ?";
        String[] selectionArgs = {"%" + name + "%"};

        try {
            Cursor cursor = db.query(
                    "DOCTOR",
                    null,
                    selection,
                    selectionArgs,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                do {
                    Doctor_Model doctor = new Doctor_Model();
                    doctor.setId(cursor.getString(0));
                    doctor.setName(cursor.getString(1));
                    doctor.setSpecification(cursor.getString(2));
                    doctor.setAffiliated_Hospital_Name(cursor.getString(3));
                    doctor.setOffice_Hours(cursor.getString(4));
                    doctors.add(doctor);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return doctors;
    }

    public boolean removeFavorite(String name) {
        SQLiteDatabase db = getWritableDatabase();
        long l = db.delete("FAVORITE", "name=?", new String[]{String.valueOf(name)});
        db.close();
        return l > 0;
    }
}

