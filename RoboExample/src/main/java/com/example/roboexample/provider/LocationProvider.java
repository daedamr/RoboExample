package com.example.roboexample.provider;

import android.location.Location;
import android.location.LocationManager;

import javax.inject.Inject;
import javax.inject.Provider;

public class LocationProvider implements Provider<Location> {
    @Inject protected LocationManager locationManager;

    @Override
    public Location get() {
        return locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
    }
}
