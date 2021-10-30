package com.example.androidlabs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForecast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        ProgressBar pb = (ProgressBar) findViewById(R.id.loading);
        pb.setVisibility(ProgressBar.VISIBLE);

        ForecastQuery forecastQuery = new ForecastQuery();
        forecastQuery.execute();
    }


    private class ForecastQuery extends AsyncTask<String, Integer, String> {
        String current, min, max, uv;
        String iconName;
        String imageURL;
        Bitmap image;

        ProgressBar pb = findViewById(R.id.loading);

        @Override
        protected String doInBackground(String... s) {

            try {
                URL url = new URL("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=xml&units=metric");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                InputStream input = urlConnection.getInputStream();

                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                factory.setNamespaceAware(false);
                XmlPullParser xpp = factory.newPullParser();
                xpp.setInput(input, "UTF-8");

                int eventType = xpp.getEventType();

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    if (eventType == XmlPullParser.START_TAG) {
                        if (xpp.getName().equals("temperature")) {
                            current = xpp.getAttributeValue(null, "value");
                            publishProgress(25);

                            min = xpp.getAttributeValue(null, "min");
                            publishProgress(50);

                            max = xpp.getAttributeValue(null, "max");
                            publishProgress(75);
                        } else if (xpp.getName().equals("weather")) {
                            iconName = xpp.getAttributeValue(null, "icon");

                            imageURL = "http://openweathermap.org/img/w/" + iconName + ".png";

                            Log.i("WeatherForecastActivity", "Looking for a file called " + iconName);
                            if (fileExistance(iconName)) {
                                FileInputStream fis = null;
                                try {
                                    fis = openFileInput(iconName);
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                                image = BitmapFactory.decodeStream(fis);
                                Log.i("WeatherForecastActivity", "This file is locally stored");
                            } else {
                                URL url1 = new URL(imageURL);
                                HttpURLConnection connection = (HttpURLConnection) url1.openConnection();
                                connection.connect();
                                int responseCode = connection.getResponseCode();
                                if (responseCode == 200) {
                                    image = BitmapFactory.decodeStream(connection.getInputStream());
                                }

                                FileOutputStream outputStream = openFileOutput(iconName + ".png", Context.MODE_PRIVATE);
                                image.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                                outputStream.flush();
                                outputStream.close();
                                Log.i("WeatherForecastActivity", "This file was needed to be downloaded.");
                            }
                            publishProgress(100);
                        }
                    }
                    eventType = xpp.next();
                }

                URL url1 = new URL("http://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat=45.348945&lon=-75.759389");
                HttpURLConnection urlConnection1 = (HttpURLConnection) url1.openConnection();
                InputStream input1 = urlConnection1.getInputStream();

                BufferedReader reader = new BufferedReader(new InputStreamReader(input1));
                StringBuilder sb = new StringBuilder();
                String line;
                try {
                    while ((line = reader.readLine()) != null) {
                        sb.append(line).append("\n");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        input1.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                JSONObject jsonObject = new JSONObject(sb.toString());
                uv = String.valueOf(jsonObject.getDouble("value"));
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
            }

            return "Done";
        }

        public boolean fileExistance(String fname) {
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

        @Override
        public void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            pb.setVisibility(View.VISIBLE);
            pb.setProgress(values[0]);
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onPostExecute(String s) {

            ImageView imageView = findViewById(R.id.imageView);
            imageView.setImageBitmap(image);

            TextView currentView = findViewById(R.id.current);
            currentView.setText("Current: " + current);

            TextView minView = findViewById(R.id.min);
            minView.setText("Min: " + min);

            TextView maxView = findViewById(R.id.max);
            maxView.setText("Max: " + max);

            TextView uvRating = findViewById(R.id.UV);
            uvRating.setText("The uvRating is: " + uv);

            pb.setVisibility(View.INVISIBLE);
        }

    }

}


