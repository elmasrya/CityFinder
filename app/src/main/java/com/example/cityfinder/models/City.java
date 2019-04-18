package com.example.cityfinder.models;

import org.json.JSONException;
import org.json.JSONObject;

public class City {

    String city, county, longitude, latitude;;
    int id;

    public City () {

    }

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

    public void setCity(String city) {
        this.city = city;
    }

    public void setCounty(String county) {
        this.county = county;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setId(int id) {
        this.id = id;
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
