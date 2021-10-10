package com.example.androidlabs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    private static final String ACTIVITY_NAME = "ProfileActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // When the picture button is clicked, start the camera intent
        ((ImageButton) findViewById(R.id.profile_picture)).setOnClickListener(clk -> {
            dispatchTakePictureIntent();
        });
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            ((ImageButton) findViewById(R.id.profile_picture)).setImageBitmap(imageBitmap);
        }
        Log.e(ACTIVITY_NAME, "in function" + "onActivityResult();");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME, "in function" + "onStart();");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(ACTIVITY_NAME, "in function" + "onPause();");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "in function" + "onResume();");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME, "in function" + "onStop;");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "in function" + "onDestroy();");
    }
}





/**public class ProfileActivity extends AppCompatActivity {

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

        Button button1 = (Button) findViewById(R.id.chatbutton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChatRoomActivity();
            }
        });
    }
    
    public void ChatRoomActivity(){
        Intent intent = new Intent(this, ChatRoomActivity.class);
        startActivity(intent);
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




}**/
