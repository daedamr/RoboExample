package com.example.roboexample.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.roboexample.model.GoodLocation;

import java.util.List;

public class LocationsAdapter extends BaseAdapter {

    private final List<GoodLocation> locations;

    public LocationsAdapter(List<GoodLocation> locations) {
        this.locations = locations;
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public Object getItem(int position) {
        return locations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
