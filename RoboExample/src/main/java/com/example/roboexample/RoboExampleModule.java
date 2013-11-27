package com.example.roboexample;

import android.location.Location;

import com.example.roboexample.provider.LocationProvider;
import com.google.inject.AbstractModule;

public class RoboExampleModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(Location.class).toProvider(LocationProvider.class);
    }
}
