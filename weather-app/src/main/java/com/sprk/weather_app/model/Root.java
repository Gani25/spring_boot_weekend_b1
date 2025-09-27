package com.sprk.weather_app.model;

import lombok.Data;

@Data
public class Root{
    public Location location;
    public Current current;
    public Forecast forecast;
}
