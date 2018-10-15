package com.test.weather.model;

public class BaseData {

    protected String id;

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "id='" + id + '\'' +
                '}';
    }
}
