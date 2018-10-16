package com.test.weather.presenter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.test.weather.Logic.WeatherHandler;
import com.test.weather.model.WeatherData;

import java.io.InputStream;

public class WeatherDetailsPresenter {
    private static final String IMG_URL = "http://openweathermap.org/img/w/";
    private View view;

    private static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {

        View view;

        DownloadImageTask(View view) {
            this.view = view;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(url).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            view.updateImage(result);
        }
    }

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
        if (data != null)
            new DownloadImageTask(view).execute(IMG_URL + data.getDetails().getWeather().getIco() + ".png");
    }

    public void destroy() {
        view = null;
    }

    public interface View extends BasePresenterView {
        void updateData(WeatherData weatherData);

        void updateImage(Bitmap result);

    }
}
