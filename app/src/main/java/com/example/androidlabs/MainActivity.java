package com.example.androidlabs;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;


public class MainActivity extends AppCompatActivity {
    // Add a comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main_relative);

        // Set onClickListener for the button to display a toast
        // This is using a lambda that was covered in Module 2
        findViewById(R.id.button).setOnClickListener((listener) -> Toast.makeText(this, getResources().getString(R.string.toast), Toast.LENGTH_LONG).show());

        // Set onCheckedChangeListener for switch and checkbox to display a snackbar
        // This is using a seperate class instead of a lambda. Both approaches were valid.
        CheckedChangeListener ccListener = new CheckedChangeListener(this);
        ((CheckBox) findViewById(R.id.checkbox)).setOnCheckedChangeListener(ccListener);
        ((Switch) findViewById(R.id.switchbox)).setOnCheckedChangeListener(ccListener);

    }}
class CheckedChangeListener implements CompoundButton.OnCheckedChangeListener {
    private final MainActivity act;

    public CheckedChangeListener(MainActivity act) {
        this.act = act;
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        Snackbar.make(buttonView, act.getResources().getString((isChecked) ? R.string.snackbar_on : R.string.snackbar_off), Snackbar.LENGTH_LONG)
                .setAction(act.getResources().getString(R.string.snackbar_undo), click -> buttonView.setChecked(!isChecked))
                .show();
    }
}
