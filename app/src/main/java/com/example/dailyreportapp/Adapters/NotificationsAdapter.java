package com.example.dailyreportapp.Adapters;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dailyreportapp.Models.Meets;
import com.example.dailyreportapp.Models.notifcations;
import com.example.dailyreportapp.R;
import com.example.dailyreportapp.User;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class NotificationsAdapter extends FirebaseRecyclerAdapter<notifcations, com.example.dailyreportapp.Adapters.NotificationsAdapter.myviewholder>{


    Context context;


    public NotificationsAdapter(@NonNull FirebaseRecyclerOptions<notifcations> options, Context context) {
        super(options);
        this.context = context;


    }




    @NonNull
    @Override
    public com.example.dailyreportapp.Adapters.NotificationsAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notificationlayout, parent, false);

        return new com.example.dailyreportapp.Adapters.NotificationsAdapter.myviewholder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull notifcations model) {

        holder.message.setText(model.getMessage());
        holder.title.setText( model.getTitle());
        holder.time.setText(model.getCurrenttime());
    }

    class myviewholder extends RecyclerView.ViewHolder {


        TextView title,message,time;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.notititle);
            message = (TextView) itemView.findViewById(R.id.notimessage);

            time = (TextView) itemView.findViewById(R.id.notitime);




        }
    }



}