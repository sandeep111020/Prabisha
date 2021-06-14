package com.example.dailyreportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class PortalsScreen extends AppCompatActivity {
    LinearLayout drive,prabisha,startup,amw,ndchrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portals_screen);
        drive=findViewById(R.id.drivelayout);
        prabisha=findViewById(R.id.prabishalayout);
        startup=findViewById(R.id.startuplayout);
        amw=findViewById(R.id.amwtoyslayout);

        ndchrc=findViewById(R.id.ndchrclayout);
        drive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://drive.google.com/drive/u/0/folders/1ClWAvaL1OXC2_Zhpg-noeuDNEsA0gZIo"));
                startActivity(intent);
            }
        });
        prabisha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.prabisha.com"));
                startActivity(intent);
            }
        });
        amw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.amwtoys.com"));
                startActivity(intent);
            }
        });
        startup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.prabishastartup.com"));
                startActivity(intent);
            }
        });

        ndchrc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.ndchrc.com"));
                startActivity(intent);
            }
        });
    }
}