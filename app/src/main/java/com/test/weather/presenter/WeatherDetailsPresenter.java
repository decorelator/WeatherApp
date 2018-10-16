package com.test.weather.presenter;

import com.test.weather.Logic.WeatherHandler;
import com.test.weather.model.WeatherData;

public class WeatherDetailsPresenter {
    private View view;

    public WeatherDetailsPresenter(View view) {
        this.view = view;
    }

    public void showInfo(long dataId) {
        WeatherData data = WeatherHandler.getInstance().getWeatherDetails(dataId);
        if (data == null) {
            view.showError("no data");
        } else {
            view.updateData(data);
        }
    }

    public void destroy() {
        view = null;
    }

    public interface View extends BasePresenterView {
        void updateData(WeatherData weatherData);

    }
}
