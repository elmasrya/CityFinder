package com.example.cityfinder.models;

import org.json.JSONException;
import org.json.JSONObject;

public class City {

    String city, county;
    int id, latitude, longitude;

    public City () {
    }

    public City (JSONObject cityObject) {
        try {
            city = cityObject.getString("name");
            county = cityObject.getString("country");
            id = cityObject.getInt("_id");
            longitude = cityObject.getJSONObject("coord").getInt("lon");
            latitude = cityObject.getJSONObject("coord").getInt("lat");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCounty() {
        return county;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public int getLatitude() {
        return latitude;
    }

    public void setLatitude(int latitude) {
        this.latitude = latitude;
    }

    public int getLongitude() {
        return longitude;
    }

    public void setLongitude(int longitude) {
        this.longitude = longitude;
    }

}
