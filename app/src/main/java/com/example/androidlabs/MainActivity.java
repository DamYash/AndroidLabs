package com.example.androidlabs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.CompoundButton;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    // Add a comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main_linear);

        TextView firstText = findViewById(R.id.firstText);

        final Button btn = findViewById(R.id.button);
        btn.setText("New Strings");
        btn.setOnClickListener( (click) -> {btn.setText("CLICK HERE");} );

        CheckBox cb = findViewById(R.id.checkbox);
        /** cb.setOnClickListener( (compoundButton, b) ->
        {
            Snackbar.make(theEdit, text:"Checkbox is " + b, Snackbar.LENGTH_LONG)
            .setAction( text: "Undo", click -> compoundButton( !b))
            .show();
        });  **/
    }
}