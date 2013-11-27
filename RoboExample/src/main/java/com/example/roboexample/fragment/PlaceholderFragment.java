package com.example.roboexample.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.roboexample.R;
import com.example.roboexample.adapter.LocationsAdapter;
import com.example.roboexample.model.GoodLocation;
import com.example.roboexample.util.Constants;
import com.example.roboexample.util.IntentFactory;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import roboguice.fragment.RoboFragment;
import roboguice.inject.InjectView;
import roboguice.util.Strings;

public class PlaceholderFragment extends RoboFragment {

    @InjectView(R.id.location_description_input) private EditText locationDescription;
    @InjectView(R.id.save_last_location) private Button locationSave;
    @InjectView(R.id.locations_history_list) private ListView locationsList;
    @InjectView(android.R.id.empty) private View emptyView;

    @Inject protected Context context;
    @Inject protected SharedPreferences prefs;
    @Inject protected Location location;
    @Inject protected InputMethodManager imm;
    @Inject protected IntentFactory intentFactory;

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView();
    }

    private void initView() {
        locationsList.setEmptyView(emptyView);

        final List<GoodLocation> locations = new ArrayList<GoodLocation>();
        final LocationsAdapter locationAdapter = new LocationsAdapter(locations, context);
        locationsList.setAdapter(locationAdapter);

        locationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final GoodLocation location = locationAdapter.getItem(position);
                startActivity(intentFactory.newDescriptionIntent(location));
            }
        });

        locationSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Editable description = locationDescription.getText();
                final String descriptionString = description != null ? description.toString() : null;
                final String lastLocation =  prefs.getString(Constants.LAST_LOCATION_DESCRIPTION, null);
                if (Strings.notEmpty(descriptionString) && !Strings.equals(descriptionString, lastLocation)) {
                    prefs.edit().putString(Constants.LAST_LOCATION_DESCRIPTION, descriptionString).commit();

                    locations.add(new GoodLocation(descriptionString, location));
                    locationAdapter.notifyDataSetChanged();
                    imm.hideSoftInputFromWindow(locationDescription.getWindowToken(), 0);
                    locationDescription.setText(null);
                }
            }
        });
    }
}