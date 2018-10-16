package com.test.weather.View;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.test.weather.R;
import com.test.weather.model.WeatherData;
import com.test.weather.presenter.WeatherDetailsPresenter;

import java.util.Objects;

/**
 * A fragment representing a single Weather detail screen.
 * This fragment is either contained in a {@link WeatherListActivity}
 * in two-pane mode (on tablets) or a {@link WeatherDetailActivity}
 * on handsets.
 */
public class WeatherDetailFragment extends Fragment implements WeatherDetailsPresenter.View {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";
    private WeatherDetailsPresenter presenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public WeatherDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.weather_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        presenter = new WeatherDetailsPresenter(this);
        assert getArguments() != null;
        presenter.showInfo(getArguments().getLong(ARG_ITEM_ID));

        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void updateData(WeatherData weatherData) {
        CollapsingToolbarLayout appBarLayout = Objects.requireNonNull(getActivity()).findViewById(R.id.toolbar_layout);
        if (appBarLayout != null) {
            appBarLayout.setTitle(weatherData.getHeader().toString());
        }

        ((TextView) Objects.requireNonNull(getView()).findViewById(R.id.weather_detail)).setText(weatherData.getDetails().print(getActivity()));
    }

    @Override
    public void showError(String error) {
        Toast.makeText(getActivity(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}
