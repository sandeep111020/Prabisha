package com.example.dailyreportapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class AdminHome extends FragmentActivity {
    EditText mEdit;

    DatePicker simpleDatePicker;
    Button submit;
    String day,month,year;
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);


        simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        submit = (Button) findViewById(R.id.submitButton);
        // perform click event on submit button
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // get the values for day of month , month and year from a date picker
                 day = String.valueOf(simpleDatePicker.getDayOfMonth());
                 month = String.valueOf((simpleDatePicker.getMonth() + 1));
                 year = String.valueOf(simpleDatePicker.getYear());
                // display the values by using a toast
                Toast.makeText(getApplicationContext(), day + "0" + month + year, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(AdminHome.this,UsersList.class);
                intent.putExtra("data",day+"0"+month+year);
                startActivity(intent);
            }
        });




    }


}