package com.example.cityfinder.views;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.cityfinder.R;
import com.example.cityfinder.controllers.CityListAdapter;
import com.example.cityfinder.models.City;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CityFinderActivity extends AppCompatActivity {
    ArrayList<City> cityArrayList;
    ProgressBar pbLoadingCities;
    TextView tvCityHeader, tvLoadingMessage;
    EditText edSearchCity;
    RecyclerView rvDisplayCity;
    CityListAdapter cityListAdapter;
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        activity = this;
        pbLoadingCities = findViewById(R.id.pb_loading_cities);
        tvLoadingMessage = findViewById(R.id.tv_loading_message);
        edSearchCity = findViewById(R.id.ed_search_city);
        tvCityHeader = findViewById(R.id.tv_city_header);
        rvDisplayCity = findViewById(R.id.rv_city_list);

        LoadCitiesAsyncTask task = new LoadCitiesAsyncTask();
        task.execute(10);
    }

    public class LoadCitiesAsyncTask extends AsyncTask<Integer, Integer, ArrayList<City> > {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(ArrayList<City> cityArrayList) {
            super.onPostExecute(cityArrayList);

            edSearchCity.setVisibility(View.VISIBLE);
            tvCityHeader.setVisibility(View.VISIBLE);
            rvDisplayCity.setVisibility(View.VISIBLE);
            pbLoadingCities.setVisibility(View.GONE);
            tvLoadingMessage.setVisibility(View.GONE);
            cityListAdapter = new CityListAdapter(cityArrayList, activity);
            rvDisplayCity.setAdapter(cityListAdapter);
            rvDisplayCity.setLayoutManager(new LinearLayoutManager(activity));
        }

        @Override
        protected ArrayList<City> doInBackground(Integer... integers) {
            return createList();
        }
    }

    public void getCityListFromJSON () {
        try {
            JSONArray array = new JSONArray(readJSONFromAsset());
            cityArrayList = new ArrayList<City>();
            City city = null;

            for (int i = 0; i < array.length(); i++) {
                city = new City(array.getJSONObject(i));
                cityArrayList.add(city);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String readJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("cities.json");
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

    public void sortCities() {
        Collections.sort(cityArrayList, new Comparator<City>() {
            @Override
            public int compare(final City object1, final City object2) {
                return object1.getCity().compareTo(object2.getCity());
            }
        });
    }

    public ArrayList<City> createList() {
        getCityListFromJSON();
        sortCities();
        return cityArrayList;
    }

}
