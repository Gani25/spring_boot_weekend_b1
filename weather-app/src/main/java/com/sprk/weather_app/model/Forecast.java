package com.sprk.weather_app.model;

import lombok.Data;

import java.util.ArrayList;

@Data

public class Forecast{
    public ArrayList<Forecastday> forecastday;
}