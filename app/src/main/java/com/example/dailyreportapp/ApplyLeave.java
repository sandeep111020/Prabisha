package com.example.dailyreportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.dailyreportapp.Models.Leave;
import com.google.android.material.appbar.AppBarLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ApplyLeave extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    Spinner leavetype;
    Button apply;
    EditText comment;
    String date,Sleavetype,Scomment,employeeid;
    String[] arr = { "Personal Leave", "Official Leave","Health Issue",};
    CalendarView calendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apply_leave);
        employeeid = getIntent().getStringExtra("name");
        calendarView=findViewById(R.id.calender);
        apply=findViewById(R.id.leeavestatus);
        comment=findViewById(R.id.comment);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date= String.valueOf(dayOfMonth+""+month+""+year);

            }
        });

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validatemeetdetails();
                Intent i =new Intent(ApplyLeave.this,LeaveStatus.class);
                startActivity(i);
            }
        });
        leavetype = (Spinner)
                findViewById(R.id.autoCompleteTextView1);

        leavetype.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arr);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        leavetype.setAdapter(aa);
    }

    private void validatemeetdetails() {
        Sleavetype=leavetype.getSelectedItem().toString();
        Scomment=comment.getText().toString();
        if(TextUtils.isEmpty(date)){
            Toast.makeText(this,"Please pick date ",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Sleavetype)){
            Toast.makeText(this,"Please enter the leave type ",Toast.LENGTH_SHORT).show();
        }
        else if(TextUtils.isEmpty(Scomment)){
            Toast.makeText(this,"Please enter the comment ",Toast.LENGTH_SHORT).show();
        }
        else{
            FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
            DatabaseReference reference = rootNode.getReference("leaves");


            Leave addnewUser = new Leave(date,employeeid,"Sandeep",Scomment,Sleavetype);
            reference.child("111").child(date).setValue(addnewUser);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}