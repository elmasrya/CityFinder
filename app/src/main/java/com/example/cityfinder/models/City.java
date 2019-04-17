package com.example.cityfinder.models;

import org.json.JSONException;
import org.json.JSONObject;

public class City {

    String city, county, longitude, latitude;;
    int id;

    public City(JSONObject cityObject) {
        try {
            city = cityObject.getString("name");
            county = cityObject.getString("country");
            id = cityObject.getInt("_id");
            latitude = cityObject.getJSONObject("coord").getString("lat");
            longitude = cityObject.getJSONObject("coord").getString("lon");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }
}
