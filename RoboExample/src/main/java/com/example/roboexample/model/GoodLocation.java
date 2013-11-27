package com.example.roboexample.model;

import android.location.Location;

public class GoodLocation {
    private String description;
    private double latitude;
    private double longitude;

    public GoodLocation(String description, Location location) {
        this.description = description;
        this.latitude =  location != null ? location.getLatitude() : 0;
        this.longitude =  location != null ? location.getLongitude() : 0;
    }

    public String getDescription() {
        return description;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
