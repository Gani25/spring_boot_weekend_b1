package com.sprk.weather_app.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Forecastday{
    public String date;
    public int date_epoch;
    public Day day;
    public Astro astro;
    public ArrayList<Hour> hour;
}
