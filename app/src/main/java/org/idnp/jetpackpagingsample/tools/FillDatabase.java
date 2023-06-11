package org.idnp.jetpackpagingsample.tools;

import android.content.Context;

import org.idnp.jetpackpagingsample.entities.Country;
import org.idnp.jetpackpagingsample.model.CountryDao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FillDatabase {

    public String filltable(Context context) {
        StringBuilder sb = new StringBuilder();

        try{
            InputStream inputStream = context.getAssets().open("countries.txt");

            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String read;

            while ((read = br.readLine()) != null){
                sb.append(read);
            }

            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return  sb.toString();
    }

    public List<Country> parseCountriesList(String countriesList) {
        List<Country> countries = new ArrayList<>();
        String[] lines = countriesList.split("\n");

        for (String line : lines) {
            String[] properties = line.split(",");
            int id = 0, val = 0;
            if (properties.length >= 3) {
                Country country = new Country(id, properties[1], properties[2],
                        properties[3], properties[4], properties[5],
                        properties[6], properties[7], properties[8],
                        properties[9], properties[10], val);
                countries.add(country);
            }
        }

        return countries;
    }
}
