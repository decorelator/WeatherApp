package com.test.weather;

public class Core {
    private final static String APP_ID = "ae9c45a9ca652b8b03d0f6d6bd039908";
    private final static String FORECAST_API = "api.openweathermap.org/data/2.5/forecast/";
    public final static String GET_FORECAST_BY_LOC = FORECAST_API + "daily?lat=%s&lon=%s&cnt=%s&appid=" + APP_ID;


}
