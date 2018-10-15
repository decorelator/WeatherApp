package com.test.weather.model;

public class WeatherData extends BaseData {

    private String content;
    private String details;
    private ItemHeader header;

    public WeatherData(String id, String content, String details) {
        this.id = id;
        this.content = content;
        this.details = details;
    }

    public WeatherData(String id, ItemHeader itemHeader, String details) {
        this.id = id;
        this.header = itemHeader;
        this.details = details;

    }

    public ItemHeader getHeader() {
        return header;
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


