package com.test.weather.Logic;

import android.util.Log;
import android.util.LongSparseArray;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.Volley;
import com.test.weather.View.WeatherApp;
import com.test.weather.model.DetailedInfo;
import com.test.weather.model.HeaderInfo;
import com.test.weather.model.WeatherData;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class WeatherHandler {
    private static final int OK_STATUS = 200;
    /**
     * api key for open weather requests
     */
    private final static String APP_ID = "ae9c45a9ca652b8b03d0f6d6bd039908";
    private final static String FORECAST_API = "http://api.openweathermap.org/data/2.5/";
    private final static String GET_FORECAST_BY_LOC = FORECAST_API + "forecast?lat=%s&lon=%s&cnt=%s&units=metric&appid=" + APP_ID;
    private static final int DAY_COUNT = 10;

    private ArrayList<WeatherData> weatherData = new ArrayList<>();
    private LongSparseArray<WeatherData> map = new LongSparseArray<>();

    public ArrayList<WeatherData> getWeatherData() {
        return weatherData;
    }

    public interface WeatherListener {
        void onReceive();

        void onError(String error);
    }

    private static final WeatherHandler thiz = new WeatherHandler();
    private static final String TAG = WeatherHandler.class.getCanonicalName();
    private RequestQueue queue;
    private String city;


    public static WeatherHandler getInstance() {
        return thiz;
    }

    private WeatherHandler() {
        queue = Volley.newRequestQueue(WeatherApp.getContext());

    }

    public void getForecast(String lat, String lon, final WeatherListener weatherListener) {
        String url = String.format(GET_FORECAST_BY_LOC, lat, lon, DAY_COUNT);
        CacheRequest cacheRequest = new CacheRequest(0, url, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse response) {
                try {
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    JSONObject jsonObject = new JSONObject(jsonString);
                    if (jsonObject.getInt("cod") == OK_STATUS) {
                        parse(jsonObject);
                        if (weatherListener != null) {
                            weatherListener.onReceive();
                        }
                    }

                } catch (UnsupportedEncodingException | JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, error.toString());
                if (weatherListener != null) {
                    weatherListener.onError(error.getMessage());
                }
            }
        });

        // Add the request to the RequestQueue.
        queue.add(cacheRequest);


    }

    private void parse(JSONObject response) throws JSONException {
        for (int i = 0; i < response.getJSONArray("list").length(); i++) {
            JSONObject item = response.getJSONArray("list").getJSONObject(i);
            long dt = item.getLong("dt");

            HeaderInfo headerInfo = new HeaderInfo(item);
            DetailedInfo detailedInfo = new DetailedInfo(item);
            weatherData.add(new WeatherData(dt, headerInfo, detailedInfo));

            map.put(dt, weatherData.get(weatherData.size() - 1));
            city = response.getJSONObject("city").getString("name");
        }
    }

    public WeatherData getWeatherDetails(long id) {
        return map.get(id);
    }

    public String getCity() {
        return city;
    }
}
