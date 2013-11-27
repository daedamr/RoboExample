package com.example.roboexample.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.roboexample.R;
import com.example.roboexample.model.GoodLocation;

import java.util.List;

public class LocationsAdapter extends BaseAdapter {

    private final List<GoodLocation> locations;
    private final Context context;
    private ViewHolder viewHolder;

    public LocationsAdapter(List<GoodLocation> locations, Context context) {
        this.locations = locations;
        this.context = context;
    }

    @Override
    public int getCount() {
        return locations.size();
    }

    @Override
    public GoodLocation getItem(int position) {
        return locations.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_locations, null);
            viewHolder = new ViewHolder();
            viewHolder.description = (TextView) convertView.findViewById(R.id.location_description);
            viewHolder.latitude = (TextView) convertView.findViewById(R.id.location_lat);
            viewHolder.longitude = (TextView) convertView.findViewById(R.id.location_lng);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final GoodLocation location = getItem(position);
        viewHolder.description.setText(location.getDescription());
        viewHolder.latitude.setText(String.format(context.getString(R.string.latitude), location.getLatitude()));
        viewHolder.longitude.setText(String.format(context.getString(R.string.longitude), location.getLongitude()));

        return convertView;
    }

    static class ViewHolder {
        TextView description;
        TextView latitude;
        TextView longitude;
    }
}
