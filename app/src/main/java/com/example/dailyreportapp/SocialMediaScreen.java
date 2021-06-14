package com.example.dailyreportapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class SocialMediaScreen extends AppCompatActivity {
    ImageView fb,instra,twit,ln,youtube, watsapp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_screen);
        fb=findViewById(R.id.prabishafacebook);
        instra=findViewById(R.id.prabishainstra);
        twit=findViewById(R.id.prabishatwitter);
        ln=findViewById(R.id.prabishaln);
        youtube=findViewById(R.id.prabishayoutube);
        watsapp=findViewById(R.id.prabishawatsapp);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCUdX_mkqI2KSpn6veGLVpIA"));
                startActivity(intent);
            }
        });
        watsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://api.whatsapp.com/send?phone=919599824600"));
                startActivity(intent);
            }
        });
        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/prabishac"));
                startActivity(intent);
            }
        });
        instra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/prabishadesignstudio/"));
                startActivity(intent);
            }
        });
        twit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/PrabishaC"));
                startActivity(intent);
            }
        });
        ln.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/pratyushk/"));
                startActivity(intent);
            }
        });
    }
}