package com.example.dailyreportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class LeaveStatus extends AppCompatActivity {
    String employeeid;
    TextView status;
    private SimpleDateFormat dateFormat;
    private Calendar calendar;
    String Sdate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_status);
        status=findViewById(R.id.textstatus);
        employeeid = getIntent().getStringExtra("name");
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("ddMMyyyy");
        Sdate = dateFormat.format(calendar.getTime());
        Query checkuser = FirebaseDatabase.getInstance().getReference("leaveupdate").child(employeeid).child(Sdate);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String text = snapshot.child("update").getValue(String.class);
               if (text.equals("Approved")){
                   status.setText("Your leave is approved");
               }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {


            }
        });
    }
}