package com.sprk.weather_app.model;

import lombok.Data;

@Data
public class Condition{
    public String text;
    public String icon;
    public int code;
}