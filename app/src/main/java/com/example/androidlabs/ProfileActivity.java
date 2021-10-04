package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    public static final String ACTIVITY_NAME = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        int e = Log.e(ACTIVITY_NAME, "In OnCreate");
        TextView t1;

        t1 = findViewById(R.id.emailtext);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("MyPreferences1", Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", "");

        t1.setText(email);

        ImageButton button = (ImageButton) findViewById(R.id.imgbutton);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, REQUEST_IMAGE_CAPTURE);
            }
        });
    }
    protected void onStart(){
        super.onStart();
        Log.e(ACTIVITY_NAME, "In onStart");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE  && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ImageButton button = (ImageButton) findViewById(R.id.imgbutton);
            button.setImageBitmap(imageBitmap);
        }
    }

    protected void onPause(){
        super.onPause();
        Log.e(ACTIVITY_NAME, "In onPause");
    }

    protected void onResume(){
        super.onResume();
        Log.e(ACTIVITY_NAME, "In onResume");
    }

    protected void onStop(){
        super.onStop();
        Log.e(ACTIVITY_NAME, "In onStop");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "In onDestroy");
    }




}
