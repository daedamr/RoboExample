package com.example.roboexample.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.roboexample.R;
import com.example.roboexample.adapter.LocationsAdapter;
import com.example.roboexample.model.GoodLocation;
import com.example.roboexample.util.Constants;

import java.util.ArrayList;
import java.util.List;

public class PlaceholderFragment extends Fragment {

    private EditText locationDescription;
    private Button locationSave;
    private ListView locationsList;
    private View emptyView;
    private List<GoodLocation> locations;
    private LocationsAdapter locationAdapter;

    public PlaceholderFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        locationDescription = (EditText) rootView.findViewById(R.id.location_description_input);
        locationSave = (Button) rootView.findViewById(R.id.save_last_location);
        locationsList = (ListView) rootView.findViewById(R.id.locations_history_list);
        emptyView = rootView.findViewById(android.R.id.empty);

        initView();

        return rootView;
    }

    private void initView() {
        locationsList.setEmptyView(emptyView);

        locations = new ArrayList<GoodLocation>();
        locationAdapter = new LocationsAdapter(locations);

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        locationSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Editable description = locationDescription.getText();
                final String descriptionString = description != null ? description.toString() : null;
                prefs.edit().putString(Constants.LAST_LOCATION_DESCRIPTION, descriptionString).commit();

                final Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

                locations.add(new GoodLocation(descriptionString, location != null ? location.getLatitude() : 0,
                        location != null ? location.getLongitude() : 0));
                locationAdapter.notifyDataSetChanged();
            }
        });
    }
}