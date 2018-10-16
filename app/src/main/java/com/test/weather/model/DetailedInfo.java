package com.test.weather.model;

import android.content.Context;

import com.test.weather.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;


public class DetailedInfo implements PrintableInfo {

    private double temp;
    private double temp_min;
    private double temp_max;
    private double pressure;
    private double sea_level;
    private double grnd_level;
    private double humidity;
    private Weather weather;

    public Weather getWeather() {
        return weather;
    }

    public class Weather {
        private String status;
        private String desc;
        private String ico;

        public String getStatus() {
            return status;
        }

        public String getDesc() {
            return desc;
        }

        public String getIco() {
            return ico;
        }

        public Weather(JSONObject jsonObject) throws JSONException {
            status = jsonObject.getString("main");
            desc = jsonObject.getString("description");
            ico = jsonObject.getString("icon");

        }

        @Override
        public String toString() {
            return getDesc();
        }
    }

    public DetailedInfo(JSONObject jsonObject) throws JSONException {

        JSONObject main = jsonObject.getJSONObject("main");

        temp = main.getDouble("temp");
        temp_min = main.getDouble("temp_min");
        temp_max = main.getDouble("temp_max");
        pressure = main.getDouble("pressure");
        sea_level = main.getDouble("sea_level");
        grnd_level = main.getDouble("grnd_level");
        humidity = main.getDouble("temp_kf");

        weather = new Weather(jsonObject.getJSONArray("weather").getJSONObject(0));
    }


    public double getTemp() {
        return temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public double getPressure() {
        return pressure;
    }

    public double getSea_level() {
        return sea_level;
    }

    public double getGrnd_level() {
        return grnd_level;
    }

    public double getHumidity() {
        return humidity;
    }


    @Override
    public String print(Context context) {
        StringBuilder info = new StringBuilder();
        info.append(String.format(Locale.getDefault(), context.getString(R.string.detailTemperature), getTemp())).append(System.getProperty("line.separator"));
        info.append(String.format(Locale.getDefault(), context.getString(R.string.detailTemperatureMin), getTemp_min())).append(System.getProperty("line.separator"));
        info.append(String.format(Locale.getDefault(), context.getString(R.string.detailTemperatureMax), getTemp_max())).append(System.getProperty("line.separator"));
        info.append(String.format(Locale.getDefault(), context.getString(R.string.detailPressure), getPressure())).append(System.getProperty("line.separator"));
        info.append(String.format(Locale.getDefault(), context.getString(R.string.detailSeaLevel), getSea_level())).append(System.getProperty("line.separator"));
        info.append(String.format(Locale.getDefault(), context.getString(R.string.detailGrndLevel), getGrnd_level())).append(System.getProperty("line.separator"));
        info.append(String.format(Locale.getDefault(), context.getString(R.string.detailHumidity), getHumidity()));
        return info.toString();
    }
}
