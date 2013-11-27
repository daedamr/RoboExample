package com.example.roboexample.activity;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.roboexample.fragment.PlaceholderFragment;
import com.example.roboexample.R;
import com.example.roboexample.util.Constants;

public class MainActivity extends ActionBarActivity {

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
