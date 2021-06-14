package com.example.dailyreportapp.Adapters;
import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dailyreportapp.R;
import com.example.dailyreportapp.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.sql.BatchUpdateException;


public class TodoAdapter extends FirebaseRecyclerAdapter<User, com.example.dailyreportapp.Adapters.TodoAdapter.myviewholder>{

    String date,taskid;

    Context context;

    public TodoAdapter(@NonNull FirebaseRecyclerOptions<User> options, Context context) {
        super(options);
        this.context = context;

    }



    @Override
    protected void onBindViewHolder(@NonNull com.example.dailyreportapp.Adapters.TodoAdapter.myviewholder holder, int position, @NonNull User model) {

        String chek;
        holder.taskdetails.setText("Task Details: "+model.getTaskdetails());
        date=model.getDate();
        chek=model.getStatus();
        taskid=model.getTaskid();
        if (chek.equals("completed")){
            holder.status.setChecked(true);
        }
        else {
            holder.status.setChecked(false);
        }









    }

    @NonNull
    @Override
    public com.example.dailyreportapp.Adapters.TodoAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todorecyclerlayout, parent, false);

        return new com.example.dailyreportapp.Adapters.TodoAdapter.myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView taskdetails;
        CheckBox status;
        private DatabaseReference databaseRef;
        Button sumit;
        String checj;


        public myviewholder(@NonNull View itemView) {
            super(itemView);



            taskdetails = (TextView) itemView.findViewById(R.id.taskdescript);
            status = (CheckBox) itemView.findViewById(R.id.taskstatus);


            status.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean checked = ((CheckBox) v).isChecked();
                    if (checked){
                        // Do your coding
                        checj="completed";
                        databaseRef = FirebaseDatabase.getInstance().getReference().child("eod").child(date).child("111");
                        databaseRef.child(taskid).child("status").setValue(checj);
                    }
                    else{
                        // Do your coding
                    }
                }
            });


        }
    }



}