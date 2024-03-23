package com.example.healthcare.DashboardAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.healthcare.DatabaseHelper;
import com.example.healthcare.PojoClasses.Doctor_Model;
import com.example.healthcare.R;

import java.util.ArrayList;

public class DoctorAdapter extends RecyclerView.Adapter<DoctorAdapter.ViewHolder> {
    ArrayList<Doctor_Model> arrayList = new ArrayList<>();
    Context context;
    DatabaseHelper databaseHelper;
    boolean isAppointment;

    public DoctorAdapter(ArrayList<Doctor_Model> arrayList, Context context, DatabaseHelper databaseHelper) {
        this.arrayList = arrayList;
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public DoctorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_doctor_layout, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull DoctorAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText("Doctor Name: "+arrayList.get(position).getName());
        holder.tv_spec.setText("Specialization: "+arrayList.get(position).getSpecification());
        holder.tv_hospital.setText("Hospital Name: "+arrayList.get(position).getAffiliated_Hospital_Name());
        holder.tv_timmings.setText("Consultant Timings: "+arrayList.get(position).getOffice_Hours());
        holder.btn_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String id = arrayList.get(position).getId();
                String name = arrayList.get(position).getName();
                String specification = arrayList.get(position).getSpecification();
                String hospitalName = arrayList.get(position).getAffiliated_Hospital_Name();
                String officeHours = arrayList.get(position).getOffice_Hours();
                isAppointment = databaseHelper.addFavorite(name, specification, hospitalName, officeHours);
                if(isAppointment){
                    Toast.makeText(context, "Added in favorite Granted", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(context, "Not add", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_spec, tv_hospital, tv_timmings;
        Button btn_favorite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_spec = itemView.findViewById(R.id.tv_spec);
            tv_hospital = itemView.findViewById(R.id.tv_hospital);
            tv_timmings = itemView.findViewById(R.id.tv_timmmings);
            btn_favorite = itemView.findViewById(R.id.btn_favorite);
        }
    }
}
