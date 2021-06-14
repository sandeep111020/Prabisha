package com.example.dailyreportapp;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Dashboard extends AppCompatActivity {
    LinearLayout profile,todo,chats,notify,leave,attendence,team,payslip,policy;

    CircleImageView photo;
    TextView username,empid,designation;
    private String employeeid;
    String  Sdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        payslip=findViewById(R.id.paysliplayout);
        profile=findViewById(R.id.profilelayout);
        todo=findViewById(R.id.todolayout);
        chats=findViewById(R.id.chatslayout);
        notify=findViewById(R.id.notficationlayout);
        leave=findViewById(R.id.leavelayout);
        attendence=findViewById(R.id.attendancelayout);
        team=findViewById(R.id.teamlayout);
        policy=findViewById(R.id.policylayout);
        empid=findViewById(R.id.empid);
        username=findViewById(R.id.name);
        designation=findViewById(R.id.designation);
        photo=findViewById(R.id.profile_photo);
        Sdate=getIntent().getStringExtra("date");
        employeeid = getIntent().getStringExtra("name");
        policy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard.this,PolicyScreen.class);
                startActivity(i);
            }
        });
        team.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard.this,TeamScreen.class);
                startActivity(i);
            }
        });
        attendence.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,TaskScreen.class);
                i.putExtra("name",employeeid);
                i.putExtra("date",Sdate);
                startActivity(i);
            }
        });
        leave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,LeaveScreen.class);
                i.putExtra("name",employeeid);
                startActivity(i);
            }
        });
        notify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,Notifications.class);
                startActivity(i);
            }
        });
        chats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,chatMain.class);
                i.putExtra("name",employeeid);
                startActivity(i);
            }
        });
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Dashboard.this,TodoScreen.class);
                i.putExtra("name",employeeid);
                startActivity(i);

            }
        });
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,Profile.class);
                i.putExtra("name",employeeid);
                startActivity(i);
            }
        });
        payslip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Dashboard.this,Projects.class);
                startActivity(i);
            }
        });

        Query checkuser = FirebaseDatabase.getInstance().getReference("Employees").child(employeeid);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String designation1 = snapshot.child("userDesignation").getValue(String.class);
                String name1 = snapshot.child("userName").getValue(String.class);
                String empid1 = snapshot.child("userEmpid").getValue(String.class);
                String image = snapshot.child("imageURL").getValue().toString();
                username.setText(name1);
                designation.setText(designation1);
                empid.setText(empid1);
                Picasso.get().load(image).placeholder(R.drawable.profile).into(photo);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}