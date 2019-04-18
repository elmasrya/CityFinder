package com.example.cityfinder;

import android.app.Activity;
import android.util.Log;

import com.example.cityfinder.models.City;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;


public class Util {
    private static final String TAG = "Util";

    public String readJSONFromAsset(Activity activity, String fileName) {
        String json = null;
        try {
            InputStream is = activity.getAssets().open(fileName);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void sortCities(ArrayList<City> cityList) {
        Collections.sort(cityList, new Comparator<City>() {
            @Override
            public int compare(final City object1, final City object2) {
                return object1.getCity().compareTo(object2.getCity());
            }
        });
    }
}
