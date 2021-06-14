package com.example.dailyreportapp.Adapters;

import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;


import com.example.dailyreportapp.Models.projectmodel;
import com.example.dailyreportapp.R;
import com.example.dailyreportapp.ViewPdfActivity;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import de.hdodenhof.circleimageview.CircleImageView;


public class ProjectsAdapter extends FirebaseRecyclerAdapter<projectmodel, com.example.dailyreportapp.Adapters.ProjectsAdapter.myviewholder>{


    Context context;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    String Sdate;
    private DatabaseReference databaseRef,databaseRef4;
    private DatabaseReference database;
    private String message;

    public ProjectsAdapter(@NonNull FirebaseRecyclerOptions<projectmodel> options, Context context) {
        super(options);
        this.context = context;


    }





    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.projectlayout, parent, false);

        return new myviewholder(view);
    }



    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull projectmodel model) {
        holder.title.setText("Name: "+model.getProjecttitle());
        holder.link.setText("ProjectLink "+model.getProjectlink());
        holder.desc.setText("ProjectDescription: "+model.getProjectdesc());
        message=model.getProjectimgurl();

        holder.img.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, ViewPdfActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("url", message);
                context.startActivity(intent);
               /* CharSequence options[] = new CharSequence[]{
                        "Download",
                        "View",
                        "Cancel"
                };
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Choose One");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // we will be downloading the pdf
                        if (which == 0) {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(message));
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                        }
                        // We will view the pdf
                        if (which == 1) {
                            Intent intent = new Intent(context, ViewPdfActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.putExtra("url", message);
                            context.startActivity(intent);
                        }
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();*/
            }
        });
       // Picasso.get().load(model.getProjectimgurl()).into(holder.img);


                // After clicking here alert box will come

    }

    class myviewholder extends RecyclerView.ViewHolder {

        TextView title,link,desc;
        TextView img;


        public myviewholder(@NonNull View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.proj_title);
            link = (TextView) itemView.findViewById(R.id.proj_link);

            desc = (TextView) itemView.findViewById(R.id.proj_desc);

            img=(TextView) itemView.findViewById(R.id.proj_image);




        }
    }



}