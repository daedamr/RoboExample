package com.example.roboexample.model;

public class GoodLocation {
    private String description;
    private double latitude;
    private double longitude;

    public GoodLocation(String description, double latitude, double longitude) {
        this.description = description;
        this.latitude = latitude;
        this.longitude = longitude;
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
