package com.test.weather.container;

import com.test.weather.model.ItemHeader;
import com.test.weather.model.WeatherData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample content for user interfaces created by
 * Android template wizards.
 * <p>
 * TODO: Replace all uses of this class before publishing your app.
 */
public class Items {

    /**
     * An array of sample (dummy) items.
     */
    public static final List<WeatherData> ITEMS = new ArrayList<WeatherData>();

    /**
     * A map of sample (dummy) items, by ID.
     */
    public static final Map<String, WeatherData> ITEM_MAP = new HashMap<String, WeatherData>();

    private static final int COUNT = 25;

    static {
        // Add some sample items.
        for (int i = 1; i <= COUNT; i++) {
            addItem(createWeatherData(String.valueOf(i), "date " + i, " info"));
        }
    }

    private static void addItem(WeatherData item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.getId(), item);
    }

    private static WeatherData createWeatherData(String id, String date, String temp) {
        return new WeatherData(id, new ItemHeader(date, temp), " data");
    }

    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Details about Item: ").append(position);
        for (int i = 0; i < position; i++) {
            builder.append("\nMore details information here.");
        }
        return builder.toString();
    }

}
