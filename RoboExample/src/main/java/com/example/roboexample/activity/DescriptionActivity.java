package com.example.roboexample.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.roboexample.R;
import com.example.roboexample.util.Constants;

import roboguice.activity.RoboActivity;
import roboguice.inject.InjectExtra;
import roboguice.inject.InjectView;

public class DescriptionActivity extends RoboActivity {

    @InjectView(R.id.description) protected TextView descriptionView;
    @InjectView(R.id.latitude) protected TextView latitudeView;
    @InjectView(R.id.longitude) protected TextView longitudeView;

    @InjectExtra(value = Constants.Extras.DESCRIPTION) protected String description;
    @InjectExtra(value = Constants.Extras.LATITUDE) protected double latitude;
    @InjectExtra(value = Constants.Extras.LONGITUDE) protected double longitude;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        descriptionView.setText(description);
        latitudeView.setText(String.format(getString(R.string.latitude), latitude));
        longitudeView.setText(String.format(getString(R.string.longitude), longitude));
    }
}