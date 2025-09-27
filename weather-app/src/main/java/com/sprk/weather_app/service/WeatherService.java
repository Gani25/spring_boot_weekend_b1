package com.sprk.weather_app.service;

import com.sprk.weather_app.model.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final RestTemplate restTemplate;

    @Value("${weather.base.currentUrl}")
    private String baseCurrentUrl;

    @Value("${weather.base.forecastUrl}")
    private String baseForecastUrl;


    @Value("${weather.api.key}")
    private String apiKey;

    public Root getCurrentWeather(String location){
        String url = baseCurrentUrl+"?key="+apiKey+"&q="+location;

        return restTemplate.getForObject(url, Root.class);
    }

    public Root getWeatherForecast(String location, int numOfDays) {
        String url = baseForecastUrl+"?key="+apiKey+"&q="+location+"&days="+numOfDays;

        return restTemplate.getForObject(url, Root.class);
    }
}
