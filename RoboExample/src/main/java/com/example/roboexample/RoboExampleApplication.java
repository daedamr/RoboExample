package com.example.roboexample;

import android.app.Application;

import roboguice.RoboGuice;

public class RoboExampleApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        RoboGuice.setBaseApplicationInjector(this, RoboGuice.DEFAULT_STAGE, RoboGuice.newDefaultRoboModule(this), new RoboExampleModule());
    }
}
