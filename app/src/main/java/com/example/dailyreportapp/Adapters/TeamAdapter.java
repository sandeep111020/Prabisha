package com.example.dailyreportapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dailyreportapp.Models.profileinfo;
import com.example.dailyreportapp.MyAdpter;
import com.example.dailyreportapp.R;
import com.example.dailyreportapp.User;
import com.example.dailyreportapp.chatMain;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.common.graph.ImmutableNetwork;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TeamAdapter extends FirebaseRecyclerAdapter<profileinfo, com.example.dailyreportapp.Adapters.TeamAdapter.myviewholder> {


    Context context;

    public TeamAdapter(@NonNull FirebaseRecyclerOptions<profileinfo> options, Context context) {
        super(options);
        this.context = context;

    }
    @NonNull
    @Override
    public TeamAdapter.myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teammate_details, parent, false);

        return new TeamAdapter.myviewholder(view);
    }




    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull profileinfo model) {
        holder.Name.setText(model.getUserName());
        holder.Designation.setText(model.getUserDept());
        holder.Empid.setText(model.getUserEmpid());
        // we are using Picasso to load images
        // from URL inside our image view.
        Picasso.get().load(model.getImageURL()).into(holder.Profile);
    }


    public class myviewholder extends RecyclerView.ViewHolder {
        // creating variables for our
        // views of recycler items.
        private TextView Name,Designation,Empid;
        private ImageView Profile;
        Button chat;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            // initializing the views of recycler views.
            Name = itemView.findViewById(R.id.name);
            Designation=itemView.findViewById(R.id.designation);
            Empid=itemView.findViewById(R.id.empid);
            Profile = itemView.findViewById(R.id.profile);
            chat=itemView.findViewById(R.id.chatscreendisplay);
            chat.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent i = new Intent(context, chatMain.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(i);
                }
            });
        }
    }
}
