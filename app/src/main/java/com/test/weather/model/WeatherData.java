package com.test.weather.model;

public class WeatherData extends BaseData {

    private DetailedInfo details;
    private HeaderInfo header;

    public WeatherData(long id, HeaderInfo itemHeader, DetailedInfo details) {
        super(id);
        this.header = itemHeader;
        this.details = details;
    }

    public HeaderInfo getHeader() {
        return header;
    }

    public DetailedInfo getDetails() {
        return details;
    }


}


