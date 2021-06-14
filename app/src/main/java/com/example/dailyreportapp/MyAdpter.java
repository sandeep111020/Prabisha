package com.example.dailyreportapp;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class MyAdpter extends FirebaseRecyclerAdapter<User, com.example.dailyreportapp.MyAdpter.myviewholder>{


    Context context;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    String Sdate;
    private DatabaseReference databaseRef,databaseRef4;

    public MyAdpter(@NonNull FirebaseRecyclerOptions<User> options, Context context) {
        super(options);
        this.context = context;


    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull User model) {


        holder.name.setText("Name: "+model.getName());
        holder.empid.setText("EmployeeId :"+model.getEmpid());
        holder.tasktype.setText("Task Type: "+model.getTasktype());
        holder.taskdetails.setText("Task Details: "+model.getTaskdetails());
        holder.status.setText("Status: "+model.getStatus());






    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.details_layout, parent, false);

        return new myviewholder(view);
    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView name, empid,date,tasktype,taskdetails,timetaken,status,remarks;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.user_name);
            empid = (TextView) itemView.findViewById(R.id.user_empid);

            tasktype = (TextView) itemView.findViewById(R.id.user_task_type);
            taskdetails = (TextView) itemView.findViewById(R.id.user_task_description);

            status = (TextView) itemView.findViewById(R.id.user_status);
           /* calendar = Calendar.getInstance();
            dateFormat = new SimpleDateFormat("ddMMyyyy");
            Sdate = dateFormat.format(calendar.getTime());

            databaseRef = FirebaseDatabase.getInstance().getReference().child("Employees");
            databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    for(DataSnapshot snapshot1 : snapshot.getChildren()) {

                        String tempid= snapshot1.child("userEmpid").getValue(String.class);

                        databaseRef4 = FirebaseDatabase.getInstance().getReference().child("eod").child(Sdate).child(tempid);
                        databaseRef4.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {

                                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                                    name.setText(snapshot1.child("name").getValue(String.class));
                                    empid.setText(snapshot1.child("empid").getValue(String.class));
                                    tasktype.setText(snapshot1.child("tasktype").getValue(String.class));
                                    taskdetails.setText(snapshot1.child("taskdetails").getValue(String.class));
                                    status.setText(snapshot1.child("status").getValue(String.class));


                                }


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {

                            }
                        });

                    }




                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });*/



        }
    }



}