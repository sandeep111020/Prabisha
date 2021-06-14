package com.example.dailyreportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class HomeScreen extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView name,mail,date,taskid;
    Button submit,addother;
    EditText tasktype,taskdetails,timetaken,remarks,empid,Name;
    String Sname,Smail,Stasktype,Staskdetails,Stimetaken,Sstatus,Sremarks,Sempid;
    Spinner status;
    private GoogleSignInClient mGoogleSignInClient;
    private Calendar calendar;
    private SimpleDateFormat dateFormat,simpleDateFormat;
    private String Sdate,dateTime,employeeid;
    String[] stat={"completed","pending"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.email);
        tasktype=findViewById(R.id.tasktype);
        taskdetails=findViewById(R.id.tasks);
        taskid=findViewById(R.id.taskid);
        empid=findViewById(R.id.employeeid);

        status=findViewById(R.id.status);
        remarks=findViewById(R.id.remarks);
        addother=findViewById(R.id.add_another_task);
        submit= findViewById(R.id.submit);
        date = findViewById(R.id.date);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("ddMMyyyy");
        Sdate = dateFormat.format(calendar.getTime());
        simpleDateFormat = new SimpleDateFormat("ddMMyyyyHHmmss");
        dateTime = simpleDateFormat.format(calendar.getTime());
        date.setText(Sdate);
        taskid.setText("Taskid is  :"+dateTime);
        employeeid = getIntent().getStringExtra("name");
        status.setOnItemSelectedListener(this);
        empid.setText(employeeid);
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,stat);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        status.setAdapter(aa);
        addother.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeScreen.this,HomeScreen.class);
                startActivity(i);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sname= name.getText().toString();
                Smail= mail.getText().toString();
                Stasktype= tasktype.getText().toString();
                Staskdetails= taskdetails.getText().toString();
                Sempid=empid.getText().toString();
                Sname=name.getText().toString();
                Sstatus= status.getSelectedItem().toString().trim();
                Sremarks= remarks.getText().toString();
                storedatainDb();
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);
        if (acct != null) {
            String personName = acct.getDisplayName();
            String personGivenName = acct.getGivenName();
            String personFamilyName = acct.getFamilyName();
            String personEmail = acct.getEmail();
            String personId = acct.getId();
            Uri personPhoto = acct.getPhotoUrl();

            name.setText(personName);
            mail.setText(personEmail);
        }

    }

    private void storedatainDb() {
        FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
        DatabaseReference reference = rootNode.getReference("eod");


        User addnewUser = new User(Sempid,Sname,Sdate,Smail,Stasktype,Staskdetails,Sstatus,Sremarks,dateTime);
        reference.child(Sdate).child(Sempid).child(dateTime).setValue(addnewUser);
        Toast.makeText(HomeScreen.this, "Submission Completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}