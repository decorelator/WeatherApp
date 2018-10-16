package com.test.weather.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.util.Locale;

public class HeaderInfo {

    private String date;
    private double temperature;

    private static final String SEPARATOR = " ";

    public HeaderInfo(JSONObject jsonObject) throws JSONException {

        long dt = jsonObject.getLong("dt");
        date = DateFormat.getDateInstance(DateFormat.MEDIUM).format(dt * 1000);
        temperature = jsonObject.getJSONObject("main").getDouble("temp");

    }

    public String getDate() {
        return date;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return getDate() + SEPARATOR + String.format(Locale.getDefault(), "%.2f", getTemperature());
    }
}
