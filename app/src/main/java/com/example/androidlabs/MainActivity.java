package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    // Add a comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main_linear);

        TextView firstText = (TextView) findViewById(R.id.firstText);

        Button button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener( (click) -> {
            button1.setText("Here is more information");
        });

        CheckBox checkBox = findViewById(R.id.checkbox);
        checkBox.setChecked(false);
        checkBox.setOnCheckedChangeListener((cb, b) ->
        {
            Snackbar.make(checkBox, "The switch is now " + b, Snackbar.LENGTH_LONG)
                    .setAction( "Undo", click -> checkBox.setChecked( !b))
            .show();
        });

        @SuppressLint("UseSwitchCompatOrMaterialCode") Switch simpleSwitch = findViewById(R.id.simpleSwitch);

        ImageButton FlagImage = (ImageButton) findViewById(R.id.FlagImage);
        FlagImage.setImageResource(R.drawable.flag);
    }
}