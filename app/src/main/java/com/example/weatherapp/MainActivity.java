package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.TextureView;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class MainActivity extends AppCompatActivity {

    TextView tempTextView;
    TextView dateTextView;
    TextView weatherdesctextView;
    ImageView weatherImageView;
    private TextView citytextView;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );

        TextView tempTextView = (TextView) findViewById(R.id.tempTextView);

        tempTextView = (TextView) findViewById(R.id.tempTextView);
        dateTextView = (TextView) findViewById(R.id.dateTextView);
        weatherdesctextView = (TextView) findViewById(R.id.weatherdesctextView);
        citytextView = (TextView) findViewById(R.id.cityTextView);



        String url = "https://api.openweathermap.org/data/2.5/weather?zip=30339,us&appid=60410d40dbeeb03f792eb993f563c7a9&units=Imperial";

        final TextView finalTempTextView = tempTextView;
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        finalTempTextView.setText("Response: " + response.toString());

                        try {
                            JSONObject mainJSONOject = response.getJSONObject("main");
                            JSONObject responseObject = new JSONObject();
                            JSONArray weatherArray;
                            weatherArray = responseObject.getJSONArray("weather");
                            JSONObject firstWeatherObject = ((JSONArray) weatherArray).getJSONObject(0);
                            
                            String temp = Integer.toString((int) Math.round(mainJSONOject.getDouble("temp"  )));
                            String weatherDescription = firstWeatherObject.getString("description");
                            String city = (String) responseObject.get("name");
                            
                            finalTempTextView.setText(temp);
                            AtomicIntegerArray weatherdescTextView;
                            weatherdescTextView("weatherdesc");
                            citytextView.setText(city);
                            
                        } catch (JSONException e) {
                            e.printStackTrace ();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error

                    }
                });

// Access the RequestQueue through your singleton class.
        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonObjectRequest);













    }

    private void weatherdescTextView(String weatherdesc){
    }
}
