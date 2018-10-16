package com.test.weather.model;

public class BaseData {

    protected long id;

    BaseData(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "id='" + id + '\'' +
                '}';
    }
}
