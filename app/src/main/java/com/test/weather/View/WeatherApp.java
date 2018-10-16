package com.test.weather.View;

import android.app.Application;

public class WeatherApp extends Application {

    private static Application thiz;

    @Override
    public void onCreate() {
        thiz = this;
        super.onCreate();
    }

    public static Application getContext(){
        return thiz;
    }
}
