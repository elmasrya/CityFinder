package com.example.cityfinder.controllers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.example.cityfinder.R;
import com.example.cityfinder.models.City;

import java.util.ArrayList;

/**
 * Created by Andrew El-Masry April 17th, 2019
 *
 * This is the list adapter for the City Recycler View
 */
//TODO: Implement city list adapter in Activity
public class CityListAdapter extends RecyclerView.Adapter<CityListAdapter.MyViewHolder> {
    private ArrayList<City> cityArrayList;
    Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public RelativeLayout cityListItemLayout;
        public MyViewHolder(RelativeLayout cityListItemLayout) {
            super(cityListItemLayout);
            this.cityListItemLayout = cityListItemLayout;
        }
    }

    //TODO: Implement constructor in Activity
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
    }

    @Override
    public int getItemCount() {
        return cityArrayList.size();
    }
}
