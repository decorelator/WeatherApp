package com.test.weather.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.weather.R;
import com.test.weather.View.WeatherDetailActivity;
import com.test.weather.View.WeatherDetailFragment;
import com.test.weather.View.WeatherListActivity;
import com.test.weather.model.WeatherData;

import java.util.List;

public class SimpleItemRecyclerViewAdapter
        extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

    private final WeatherListActivity mParentActivity;
    private final List<WeatherData> mValues;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            WeatherData item = (WeatherData) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putLong(WeatherDetailFragment.ARG_ITEM_ID, item.getId());
                WeatherDetailFragment fragment = new WeatherDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.weather_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, WeatherDetailActivity.class);
                intent.putExtra(WeatherDetailFragment.ARG_ITEM_ID, item.getId());

                context.startActivity(intent);
            }
        }
    };

    public SimpleItemRecyclerViewAdapter(WeatherListActivity parent,
                                         List<WeatherData> items,
                                         boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).getHeader().toString());

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;

        ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id_text);
        }
    }
}
