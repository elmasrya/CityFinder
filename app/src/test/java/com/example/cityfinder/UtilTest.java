package com.example.cityfinder;

import android.app.Activity;

import com.example.cityfinder.models.City;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class UtilTest {

    ArrayList<City> cities = new ArrayList<>();
    Util util = new Util();

    @Test
    public void sortCities() {
        util.sortCities(cities);
        assertEquals(cities, new ArrayList<City>());
    }

    @Test
    public void sortCitiesFunctionality() {
        City city1 = new City();
        City city2 = new City();

        city1.setCity("Rio");
        city1.setCounty("Brazil");
        city1.setId(2441245);
        city1.setLatitude("50.0908");
        city1.setLongitude("76.00604");

        city2.setCity("Atlanta");
        city2.setCounty("US");
        city2.setId(324234);
        city2.setLatitude("50.0908");
        city2.setLongitude("76.00604");
        cities.add(city1);
        cities.add(city2);

        // Test the correct order before the sorting
        assertEquals(cities.get(0).getCity(), "Rio");

        // Execute sorting
        util.sortCities(cities);

        // Test that the items have switched places alphabetically
        assertEquals("Atlanta", cities.get(0).getCity());
    }
}