package com.example.roboexample.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.roboexample.R;
import com.example.roboexample.util.Constants;

public class DescriptionActivity extends Activity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        final TextView descriptionView = (TextView) findViewById(R.id.description);
        final TextView latitudeView = (TextView) findViewById(R.id.latitude);
        final TextView longitudeView = (TextView) findViewById(R.id.longitude);

        final Intent intent = getIntent();

        final String description = intent.getStringExtra(Constants.Extras.DESCRIPTION);
        final double latitude = intent.getDoubleExtra(Constants.Extras.LATITUDE, 0);
        final double longitude = intent.getDoubleExtra(Constants.Extras.LONGITUDE, 0);

        descriptionView.setText(description);
        latitudeView.setText(String.format(getString(R.string.latitude), latitude));
        longitudeView.setText(String.format(getString(R.string.longitude), longitude));
    }
}