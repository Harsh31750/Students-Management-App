package com.example.studentmanagement;

import android.telephony.SubscriptionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class StudentAdapter extends RecyclerView.Adapter<StudentAdapter.StudentViewHolder> {

    ArrayList<StudentData> arrayList;
    OnStudentItemsClickListener clickListener;

    public StudentAdapter(ArrayList<StudentData> arrayList, OnStudentItemsClickListener clickListener) {
        this.arrayList = arrayList;
        this.clickListener = clickListener;
    }

    public interface OnStudentItemsClickListener{
        void onDeleteClickListener(int position);
        void onEditClickListener(int position);
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
      View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items,parent,false);
     return new StudentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentViewHolder holder, int position) {
     StudentData data = arrayList.get(position);
     if (data==null){
         return;
     }

     String fullName = data.getName() + " "+ data.getLastName();
     holder.fullName.setText(fullName);

     holder.stuClass.setText(String.valueOf(data.getStudentClass()));

     holder.delete.setOnClickListener(v -> {
           clickListener.onDeleteClickListener(position);
     });

     holder.edit.setOnClickListener(v -> {
          clickListener.onEditClickListener(position);
     });



    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder{

        TextView fullName,stuClass;
        ImageView delete,edit;


        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);

            fullName = itemView.findViewById(R.id.full_name);
            stuClass = itemView.findViewById(R.id.stu_class);
            delete = itemView.findViewById(R.id.delete);
            edit = itemView.findViewById(R.id.edit);

        }
    }
}
