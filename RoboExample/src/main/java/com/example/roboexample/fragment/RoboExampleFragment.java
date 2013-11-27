package com.example.roboexample.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextUtils;
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

public class RoboExampleFragment extends Fragment {

    private EditText locationDescription;
    private Button locationSave;
    private ListView locationsList;
    private View emptyView;

    public RoboExampleFragment() {
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

        final List<GoodLocation> locations = new ArrayList<GoodLocation>();
        final LocationsAdapter locationAdapter = new LocationsAdapter(locations, getActivity());
        locationsList.setAdapter(locationAdapter);

        final IntentFactory intentFactory = new IntentFactory(getActivity());
        locationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final GoodLocation location = locationAdapter.getItem(position);
                startActivity(intentFactory.newDescriptionIntent(location));
            }
        });

        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final LocationManager locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);

        locationSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Editable description = locationDescription.getText();
                final String descriptionString = description != null ? description.toString() : null;
                final String lastLocation =  prefs.getString(Constants.LAST_LOCATION_DESCRIPTION, null);
                if (!TextUtils.isEmpty(descriptionString) && !descriptionString.equals(lastLocation)) {
                    prefs.edit().putString(Constants.LAST_LOCATION_DESCRIPTION, descriptionString).commit();

                    final Location location = locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);

                    locations.add(new GoodLocation(descriptionString, location));
                    locationAdapter.notifyDataSetChanged();
                    InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(
                            Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(locationDescription.getWindowToken(), 0);
                    locationDescription.setText(null);
                }
            }
        });
    }
}