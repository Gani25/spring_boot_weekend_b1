package com.sprk.weather_app.controller;

import com.sprk.weather_app.model.Root;
import com.sprk.weather_app.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @GetMapping("/current_weather")
    public Root currentWeather(@RequestParam(required = false)String latitude, @RequestParam(required = false)String longitude, @RequestParam(required = false, defaultValue = "Mumbai") String location) {
        if(latitude != null && longitude != null) {
            location = latitude + "," + longitude;
        }
        System.out.println(location);
        return weatherService.getCurrentWeather(location);
    }
    @GetMapping("/weather_forecast")
    public Root currentWeatherForecast(@RequestParam(required = false)String latitude,
                                       @RequestParam(required = false)String longitude,
                                       @RequestParam(required = false, defaultValue = "Mumbai") String location,
                                       @RequestParam(required = false, defaultValue = "1") int days) {
        if(latitude != null && longitude != null) {
            location = latitude + "," + longitude;
        }
        System.out.println(location);
        return weatherService.getWeatherForecast(location, days);
    }

}
