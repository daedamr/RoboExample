package com.example.roboexample.activity;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;

import com.example.roboexample.fragment.RoboExampleFragment;
import com.example.roboexample.R;
import com.example.roboexample.util.Constants;

public class RoboExampleActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        RoboExampleFragment fragment = (RoboExampleFragment) getSupportFragmentManager().findFragmentByTag(Constants.PLACEHOLDER_FRAGMENT);

        if (fragment == null) {
            fragment = new RoboExampleFragment();

            transaction.add(R.id.container, fragment, Constants.PLACEHOLDER_FRAGMENT);
        } else {
            transaction.show(fragment);
        }

        transaction.commit();
    }

}
