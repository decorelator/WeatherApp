package com.test.weather.presenter;

import com.test.weather.Logic.WeatherHandler;
import com.test.weather.Logic.LocationHelper;
import com.test.weather.model.WeatherData;

import java.util.ArrayList;

public class WeatherListPresenter {

    private View view;

    public WeatherListPresenter(View view) {
        this.view = view;
    }

    private void updateWeatherList(String lat, String lon) {

        WeatherHandler.getInstance().getForecast(lat, lon, new WeatherHandler.WeatherListener() {
            @Override
            public void onReceive() {
                view.updateList(WeatherHandler.getInstance().getWeatherData());
                view.updateCity(WeatherHandler.getInstance().getCity());
            }

            @Override
            public void onError(String error) {
                view.showError(error);
            }
        });
    }

    public void start() {
        LocationHelper locationHelper = LocationHelper.getInstance();
        locationHelper.setOnLocChanged(new LocationHelper.onLocChanged() {
            @Override
            public void onChanged(String latitude, String longitude) {
                updateWeatherList(latitude, longitude);

            }
        });
        updateWeatherList(locationHelper.getLatitude(), locationHelper.getLongitude());
    }

    public void destroy() {
        view = null;
    }

    public interface View extends BasePresenterView {
        void updateList(ArrayList<WeatherData> WeatherData);

        void updateCity(String city);
    }


}
