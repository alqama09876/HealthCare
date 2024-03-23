package com.example.healthcare.DashboardAdapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {
    ArrayList<Doctor_Model> arrayList;
    Context context;
    DatabaseHelper databaseHelper;
    boolean isDeleted;

    public FavoriteAdapter(ArrayList<Doctor_Model> arrayList, Context context, DatabaseHelper dbHelper) {
        this.arrayList = arrayList;
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
    }

    @NonNull
    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_fav_layout, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull FavoriteAdapter.ViewHolder holder, int position) {
        holder.tv_name.setText("Doctor Name: "+arrayList.get(position).getName());
        holder.tv_spec.setText("Specialization: "+arrayList.get(position).getSpecification());
        holder.tv_hospital.setText("Hospital Name: "+arrayList.get(position).getAffiliated_Hospital_Name());
        holder.tv_timmings.setText("Consultant Timings: "+arrayList.get(position).getOffice_Hours());
        holder.btn_remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Remove Confirmation");
                builder.setMessage("Are you sure you want to remove this favorite doctor?");

                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        if (position >= 0 && position < arrayList.size()) {
                            String name = arrayList.get(position).getName();
                            isDeleted = databaseHelper.removeFavorite(name);
                        }
                        if (isDeleted) {
                            Toast.makeText(context, "Favorite Removed", Toast.LENGTH_SHORT).show();
                            arrayList.remove(position);
                            notifyItemRemoved(position);
                        } else {
                            Toast.makeText(context, "Not Removed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name, tv_spec, tv_hospital, tv_timmings;
        Button btn_remove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_spec = itemView.findViewById(R.id.tv_spec);
            tv_hospital = itemView.findViewById(R.id.tv_hospital);
            tv_timmings = itemView.findViewById(R.id.tv_timmmings);
            btn_remove = itemView.findViewById(R.id.btn_remove);
        }
    }
}
