package com.test.weather.View;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.test.weather.R;
import com.test.weather.adapters.SimpleItemRecyclerViewAdapter;
import com.test.weather.model.WeatherData;
import com.test.weather.presenter.WeatherListPresenter;

import java.util.ArrayList;

/**
 * An activity representing a list of Details. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link WeatherDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
public class WeatherListActivity extends AppCompatActivity implements WeatherListPresenter.View {

    private static final int PERMISSIONS_REQUEST_LOCATION = 101;
    //private static final String TAG = WeatherListActivity.class.getCanonicalName();
    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;
    private RecyclerView recyclerView;
    private WeatherListPresenter presenter = new WeatherListPresenter(this);
    private TextView cityTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.weather_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        cityTv = findViewById(R.id.cityTv);
        recyclerView = findViewById(R.id.weather_list);
        assert recyclerView != null;
        checkPermissions();

    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSIONS_REQUEST_LOCATION);
        } else {
            presenter.start();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                        presenter.start();
                    }
                } else {
                    Toast.makeText(this, "Need location permission", Toast.LENGTH_LONG).show();
                    finish();
                }
            }

        }
    }


    @Override
    public void updateList(ArrayList<WeatherData> WeatherData) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, WeatherData, mTwoPane));
    }

    @Override
    public void updateCity(String city) {
        cityTv.setText(city);
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}
