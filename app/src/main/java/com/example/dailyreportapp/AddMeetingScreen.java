package com.example.dailyreportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.dailyreportapp.Models.Leave;
import com.example.dailyreportapp.Models.Meets;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddMeetingScreen extends AppCompatActivity {


    private DatabaseReference databaseRef;
    TimePicker simpleTimePicker;
    Spinner person1,person2;
    Button submit;
    String Smeetlink,Smeettitle,Sparticipant1,Sparticipant2,Stime,Sdate;
    EditText meetlink,meettitle;
    ArrayList<String> users = new ArrayList<String>();
    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    String employeeid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meeting_screen);
        meetlink=findViewById(R.id.meetlink);
        meettitle=findViewById(R.id.meettitle);
        person1=findViewById(R.id.participant2);
        person2=findViewById(R.id.participant3);
        submit=findViewById(R.id.submitbutton);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("ddMMyyyy");
        Sdate = dateFormat.format(calendar.getTime());
        employeeid = getIntent().getStringExtra("name");
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Smeetlink=meetlink.getText().toString();
                Smeettitle=meettitle.getText().toString();
                Sparticipant1=person1.getSelectedItem().toString();
                StringBuffer parid1=new StringBuffer();
                for (int i=0; i<Sparticipant1.length(); i++)
                {
                    if (Character.isDigit(Sparticipant1.charAt(i)))
                        parid1.append(Sparticipant1.charAt(i));

                }
                String participantid1= String.valueOf(parid1);
                Sparticipant2=person2.getSelectedItem().toString();
                StringBuffer parid2=new StringBuffer();
                for (int i=0; i<Sparticipant2.length(); i++)
                {
                    if (Character.isDigit(Sparticipant2.charAt(i)))
                        parid2.append(Sparticipant2.charAt(i));

                }
                String participantid2= String.valueOf(parid2);
                if(TextUtils.isEmpty(Smeettitle)){
                    Toast.makeText(AddMeetingScreen.this, "Meeet title is Required", Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(Smeetlink)){
                    Toast.makeText(AddMeetingScreen.this,"Please enter Meet link",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(Sparticipant1)){
                    Toast.makeText(AddMeetingScreen.this,"Please enter Meet Participant",Toast.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(Sparticipant2)){
                    Toast.makeText(AddMeetingScreen.this,"Please enter Meet Participant",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(Stime)){
                    Toast.makeText(AddMeetingScreen.this,"Please pick Meet time",Toast.LENGTH_SHORT).show();
                }else if(TextUtils.isEmpty(Sdate)){
                    Toast.makeText(AddMeetingScreen.this,"Date error",Toast.LENGTH_SHORT).show();
                }else{
                    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
                    DatabaseReference reference = rootNode.getReference("Meets");


                    Meets addnewUser = new Meets(Sdate,Stime,Smeettitle,Smeetlink,Sparticipant1,Sparticipant2,employeeid);
                    reference.child(Sdate).child(Sdate+Stime).setValue(addnewUser);
                }

                Toast.makeText(AddMeetingScreen.this,"Submited",Toast.LENGTH_SHORT).show();

            }

        });
        users.add("None");
        simpleTimePicker = (TimePicker) findViewById(R.id.simpleTimePicker);
        simpleTimePicker.setIs24HourView(false);
        simpleTimePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {
            @Override
            public void onTimeChanged(TimePicker view, int hourOfDay, int minute) {
                Stime=hourOfDay+":"+minute;

                Toast.makeText(getApplicationContext(), hourOfDay + "  " + minute, Toast.LENGTH_SHORT).show();

            }
        });

        databaseRef = FirebaseDatabase.getInstance().getReference().child("Employees");
        databaseRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for(DataSnapshot snapshot1 : snapshot.getChildren()) {
                    String tempname= snapshot1.child("userName").getValue(String.class);
                    String tempid= snapshot1.child("userEmpid").getValue(String.class);
                    users.add(tempid+" "+tempname);

                }




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,users);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        person1.setAdapter(aa);
        ArrayAdapter ab = new ArrayAdapter(this,android.R.layout.simple_spinner_item,users);
        ab.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        person2.setAdapter(ab);
    }
}