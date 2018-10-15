package com.test.weather.model;

public class ItemHeader {
    private String Date;
    private String temp;

    public ItemHeader(String date, String temp) {
        Date = date;
        this.temp = temp;
    }

    public String getDate() {
        return Date;
    }

    public String getTemp() {
        return temp;
    }

    @Override
    public String toString() {
        return getDate() + " " + getTemp();
    }

}
