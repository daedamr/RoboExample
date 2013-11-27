package com.example.roboexample.activity;

import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.example.roboexample.fragment.PlaceholderFragment;
import com.example.roboexample.R;
import com.example.roboexample.util.Constants;

import roboguice.activity.RoboFragmentActivity;

public class MainActivity extends RoboFragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        PlaceholderFragment fragment = (PlaceholderFragment) getSupportFragmentManager().findFragmentByTag(Constants.PLACEHOLDER_FRAGMENT);

        if (fragment == null) {
            fragment = new PlaceholderFragment();

            transaction.add(R.id.container, fragment, Constants.PLACEHOLDER_FRAGMENT);
        } else {
            transaction.show(fragment);
        }

        transaction.commit();
    }

}
