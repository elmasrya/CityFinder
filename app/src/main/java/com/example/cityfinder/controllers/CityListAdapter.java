package com.example.cityfinder.controllers;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cityfinder.R;
import com.example.cityfinder.models.City;

import java.util.ArrayList;

/**
 * Created by Andrew El-Masry April 17th, 2019
 *
 * This is the list adapter for the City Recycler View
 */
public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.MyViewHolder> {
    Context context;
    private ArrayList<City> cityArrayList;

    public CityListAdapter(ArrayList cityArrayList, Context context) {
        this.cityArrayList = cityArrayList;
        this.context = context;
    }

    @Override
    public CityListAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                           int viewType) {
        RelativeLayout cityListItemLayout = (RelativeLayout) LayoutInflater.from(parent
            .getContext()).inflate(R.layout.city_list_item, parent, false);

        MyViewHolder vh = new MyViewHolder(cityListItemLayout);
        return vh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TextView tvCity = (TextView) holder.cityListItemLayout.getChildAt(0);
        final City city = cityArrayList.get(position);
        tvCity.setText(city.getCity() + ", " + city.getCounty());
        holder.cityListItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("geo:"
                    + city.getLatitude() + "," + city.getLongitude()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return cityArrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout cityListItemLayout;

        public MyViewHolder(RelativeLayout cityListItemLayout) {
            super(cityListItemLayout);
            this.cityListItemLayout = cityListItemLayout;
        }
    }
}
