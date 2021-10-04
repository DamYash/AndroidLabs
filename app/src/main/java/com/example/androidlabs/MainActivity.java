package com.example.androidlabs;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(v -> openProfile());
    }

        public void openProfile()
        {
            Intent nextPage = new Intent(this, ProfileActivity.class);
            startActivity(nextPage);
        }


        /**SharedPreference with onPause
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("email", null);
        editor.commit();**/




}