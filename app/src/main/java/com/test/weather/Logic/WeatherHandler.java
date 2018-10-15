package com.test.weather.Logic;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.Volley;
import com.test.weather.Core;

import org.json.JSONObject;

public class WeatherHandler {
    private static final WeatherHandler thiz = new WeatherHandler();
    private static final String TAG = WeatherHandler.class.getCanonicalName();
    private RequestQueue queue;
    private Object DAY_COUNT = 10;

    public static WeatherHandler getInstance() {
        return thiz;
    }

    private WeatherHandler() {

    }

    public void init(Context context) {
        queue = Volley.newRequestQueue(context);

    }

    public void getForecast(String lat, String lon) {
        String url = String.format(Core.GET_FORECAST_BY_LOC, lat, lon, DAY_COUNT);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d(TAG, error.toString());

                    }
                });
        queue.add(jsonObjectRequest);

    }

}
