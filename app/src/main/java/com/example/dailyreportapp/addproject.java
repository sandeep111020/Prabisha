package com.example.dailyreportapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.dailyreportapp.Models.profileinfo;
import com.example.dailyreportapp.Models.projectmodel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import de.hdodenhof.circleimageview.CircleImageView;

public class addproject extends AppCompatActivity {
    EditText title,desc,link;
    CircleImageView image;
    Button submit;
    private Uri ImageUri;

    String Stitle,Sdesc,Slink;
    public static final int GalleryPick = 1;
    ProgressDialog loadingbar;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addproject);
        title=findViewById(R.id.projecttitleedt);
        desc=findViewById(R.id.projectdesedt);
        link=findViewById(R.id.projectlinkedt);
        image=findViewById(R.id.project_image);
        loadingbar=new ProgressDialog(this);
        submit=findViewById(R.id.addprojectbutton);

        storageReference = FirebaseStorage.getInstance().getReference("Images");
        databaseReference = FirebaseDatabase.getInstance().getReference("Projects");
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenGallery();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidateProductData();
            }
        });
    }

    private void ValidateProductData()
    {
        Stitle=title.getText().toString();
        Sdesc=desc.getText().toString();
        Slink=link.getText().toString();


        if (ImageUri==null)
        {
            Toast.makeText(this, "Project Image is Required", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Stitle))
        {
            Toast.makeText(this, "Title is required", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Sdesc)){
            Toast.makeText(this, "Description is required", Toast.LENGTH_SHORT).show();
        }
        else if (TextUtils.isEmpty(Slink))
        {
            Toast.makeText(this, "Link is required", Toast.LENGTH_SHORT).show();
        }

        else
        {
            StoreProductInformation();
        }
    }
    private void StoreProductInformation()
    {
        loadingbar.setMessage("Please Wait");
        loadingbar.setTitle("Adding New Product");
        loadingbar.setCancelable(false);
        loadingbar.show();
        UploadImage();


    }

    public void UploadImage() {

        if (ImageUri != null) {

            loadingbar.setTitle("Image is Uploading...");
            loadingbar.show();
            StorageReference storageReference2 = storageReference.child(System.currentTimeMillis() + "." + GetFileExtension(ImageUri));
            storageReference2.putFile(ImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            String Temptitle = title.getText().toString().trim();

                            String Templink = link.getText().toString().trim();
                            String Tempdesc =desc.getText().toString().trim();


                            loadingbar.dismiss();
                            Toast.makeText(getApplicationContext(), "Image Uploaded Successfully ", Toast.LENGTH_LONG).show();
                            storageReference2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    String url = uri.toString();
                                    projectmodel userProfileInfo = new projectmodel(Temptitle,Templink,Tempdesc, url);
                                    String ImageUploadId = databaseReference.push().getKey();
                                    databaseReference.child(ImageUploadId).setValue(userProfileInfo);
                                }
                            });

//                            @SuppressWarnings("VisibleForTests")
//
//                            uploadinfo imageUploadInfo = new uploadinfo(TempImageName,TempImageDescription,TempImagePrice, taskSnapshot.getUploadSessionUri().toString());
//                            String ImageUploadId = databaseReference.push().getKey();
//                            databaseReference.child(ImageUploadId).setValue(imageUploadInfo);
                        }
                    });
        }
        else {

            Toast.makeText(addproject.this, "Please Select Image or Add Image Name", Toast.LENGTH_LONG).show();

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==GalleryPick && resultCode==RESULT_OK && data!=null)
        {
            ImageUri=data.getData();
            image.setImageURI(ImageUri);
        }
    }


    public String GetFileExtension(Uri uri) {

        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;

    }
    private void OpenGallery()
    {
        Intent galleryintent=new Intent();
        galleryintent.setAction(Intent.ACTION_GET_CONTENT);
        galleryintent.setType("application/pdf");
        startActivityForResult(galleryintent,GalleryPick);
    }
}