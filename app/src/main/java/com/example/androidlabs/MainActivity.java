package com.example.androidlabs;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText email;
    String uEmail;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email = findViewById(R.id.textbox1);
        button = findViewById(R.id.loginbutton);
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        uEmail = sharedPreferences.getString("email", "");
        email.setText(uEmail);


        Button button = (Button) findViewById(R.id.loginbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ProfileActivity();
            }
        });
    }

    public void ProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
    }

        protected void onPause(){
            super.onPause();
            uEmail = email.getText().toString();
            SharedPreferences sharedPreferences1 = getSharedPreferences("MyPreferences1", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences1.edit();
            editor.putString("email", uEmail);
            editor.apply();

            Toast.makeText(MainActivity.this, "Email address saved", Toast.LENGTH_LONG).show();
        }




}