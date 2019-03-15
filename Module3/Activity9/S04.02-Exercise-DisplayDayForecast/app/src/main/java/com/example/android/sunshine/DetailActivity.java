package com.example.android.sunshine;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    private static final String FORECAST_SHARE_HASHTAG = " #SunshineApp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView wData;
        wData = (TextView) findViewById(R.id.tv_display_weather);
        // TODO (2) Display the weather forecast that was passed from MainActivity
        Intent intentstarted = getIntent();
        if (intentstarted.hasExtra(Intent.EXTRA_TEXT)) {
            String weatherForecast = intentstarted.getStringExtra(Intent.EXTRA_TEXT);
            wData.setText(weatherForecast);
        }

    }
}