package com.example.dailyreportapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class UsersList extends AppCompatActivity {
    RecyclerView recyclerView;

    String date;
    MyAdpter adapter;
    TextView datedisplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        recyclerView=findViewById(R.id.recycler_manu);
        datedisplay=findViewById(R.id.date);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        /*date = getIntent().getStringExtra("data");
        datedisplay.setText(date);*/



        FirebaseRecyclerOptions<User> options =
                new FirebaseRecyclerOptions.Builder<User>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("eod").child("24052021").child("111"), User.class)
                        .build();

        adapter = new MyAdpter(options,getApplicationContext());
        recyclerView.setAdapter(adapter);
        ;


    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}