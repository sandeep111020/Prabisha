package com.example.dailyreportapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.dailyreportapp.Adapters.ProjectsAdapter;
import com.example.dailyreportapp.Models.projectmodel;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;

public class Projects extends AppCompatActivity {
    Button btn;
    private ProjectsAdapter adapter1;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projects);
        recyclerView=findViewById(R.id.recycler_manu);
        btn=findViewById(R.id.addprojectscreenbutton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Projects.this,addproject.class);
                startActivity(i);
            }
        });

        FirebaseRecyclerOptions<projectmodel> options =
                new FirebaseRecyclerOptions.Builder<projectmodel>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Projects"), projectmodel.class)
                        .build();

        // .child("24052021130648")
        adapter1 = new ProjectsAdapter(options,getApplicationContext());
        recyclerView.setAdapter(adapter1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter1.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter1.stopListening();
    }

}