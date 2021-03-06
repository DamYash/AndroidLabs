package com.example.androidlabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private static final String SHARED_PREFS = "lab3";
    private static final String EMAIL_PREF_KEY = "email";

    private EditText emailText;
    private Button login;
    private SharedPreferences prefs;
    private String savedEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Check the shared preferences for a saved email and display it in the email box, otherwise display an empty string.
        SharedPreferences pref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String savedEmail = pref.getString(EMAIL_PREF_KEY, "");
        ((EditText) findViewById(R.id.email)).setText(savedEmail);
        // When the login button is clicked, transition to the Profile activity.
        ((Button) findViewById(R.id.login)).setOnClickListener(clk -> {
            // Saving the email to the shared preferences while clicking the button wasn't required, but it's helpful.
            saveEmailToPreferences();
            startActivity(new Intent(this, ProfileActivity.class));
        });
    }
    private void saveEmailToPreferences() {
        SharedPreferences pref = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String enteredEmail = ((EditText) findViewById(R.id.email)).getText().toString();
        pref.edit().putString(EMAIL_PREF_KEY, enteredEmail).commit();
    }
    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Ensure the contents of the email box are saved in the shared preferences
        saveEmailToPreferences();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }
    protected void onDestroy() {
        super.onDestroy();
    }
}







/**EditText email;
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
    }**/




