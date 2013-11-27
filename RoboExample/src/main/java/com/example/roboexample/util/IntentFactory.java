package com.example.roboexample.util;

import android.content.Context;
import android.content.Intent;

import com.example.roboexample.activity.DescriptionActivity;
import com.example.roboexample.model.GoodLocation;

import javax.inject.Inject;

public class IntentFactory {

    @Inject private Context context;

    public Intent newDescriptionIntent() {
        return new Intent(context, DescriptionActivity.class);
    }

    public Intent newDescriptionIntent(GoodLocation location) {
        return newDescriptionIntent()
                .putExtra(Constants.Extras.DESCRIPTION, location.getDescription())
                .putExtra(Constants.Extras.LATITUDE, location.getLatitude())
                .putExtra(Constants.Extras.LONGITUDE, location.getLongitude());
    }
}
