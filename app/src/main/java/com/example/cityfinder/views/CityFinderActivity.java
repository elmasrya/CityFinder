package com.example.cityfinder.views;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
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
    ArrayList<City> fullCityArrayList, previousCityArrayList;
    ProgressBar pbLoadingCities;
    TextView tvCityHeader, tvLoadingMessage;
    EditText etSearchCity;
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
        etSearchCity = findViewById(R.id.et_search_city);
        tvCityHeader = findViewById(R.id.tv_city_header);
        rvDisplayCity = findViewById(R.id.rv_city_list);

        LoadCitiesAsyncTask task = new LoadCitiesAsyncTask();
        task.execute();

        etSearchCity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!etSearchCity.getText().toString().isEmpty()) {
                    ArrayList<City> newCityArrayList = new ArrayList<City>();

                    for (City c : previousCityArrayList) {
                        if (c.getCity().toLowerCase().startsWith(s.toString().toLowerCase())) {
                            newCityArrayList.add(c);
                        }
                    }

                    if (s.length() == 1) {
                        previousCityArrayList = newCityArrayList;
                    }

                    createList(newCityArrayList);
                } else {
                    previousCityArrayList = fullCityArrayList;
                    createList(fullCityArrayList);
                }
            }
        });
    }

    public class LoadCitiesAsyncTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            initializeCityList();
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            etSearchCity.setVisibility(View.VISIBLE);
            etSearchCity.setEnabled(true);
            tvCityHeader.setVisibility(View.VISIBLE);
            rvDisplayCity.setVisibility(View.VISIBLE);
            pbLoadingCities.setVisibility(View.GONE);
            tvLoadingMessage.setVisibility(View.GONE);
            previousCityArrayList = fullCityArrayList;
            createList(fullCityArrayList);
        }
    }

    public void getCityListFromJSON () {
        try {
            JSONArray array = new JSONArray(readJSONFromAsset());
            fullCityArrayList = new ArrayList<City>();
            City city = null;

            for (int i = 0; i < array.length(); i++) {
                city = new City(array.getJSONObject(i));
                fullCityArrayList.add(city);
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

    public void sortCities(ArrayList<City> cityList) {
        Collections.sort(cityList, new Comparator<City>() {
            @Override
            public int compare(final City object1, final City object2) {
                return object1.getCity().compareTo(object2.getCity());
            }
        });
    }

    public void initializeCityList() {
        getCityListFromJSON();
        sortCities(fullCityArrayList);
    }

    public void createList(ArrayList<City> cityList) {
        cityListAdapter = new CityListAdapter(cityList, activity);
        rvDisplayCity.setAdapter(cityListAdapter);
        rvDisplayCity.setLayoutManager(new LinearLayoutManager(activity));
        cityListAdapter.notifyDataSetChanged();
    }
}