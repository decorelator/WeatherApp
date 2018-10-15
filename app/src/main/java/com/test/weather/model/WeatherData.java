package com.test.weather.model;

public class WeatherData extends BaseData {

    private final String content;
    private final String details;

    public WeatherData(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    public String getContent() {
        return content;
    }

    public String getDetails() {
        return details;
    }

    @Override
    public String toString() {
        return super.toString() + " WeatherData{" +
                "content='" + content + '\'' +
                ", details='" + details + '\'' +
                '}';
    }
}


