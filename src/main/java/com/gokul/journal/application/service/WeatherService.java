package com.gokul.journal.application.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gokul.journal.application.api.response.WeatherResponse;
import com.gokul.journal.application.cache.AppCache;

@Service
public class WeatherService {

    @Value("${weather_api_key}")
    private String apiKey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private RedisService redisService;

    @Autowired
    private AppCache appCache;

    public WeatherResponse getWeather(String city) {
        WeatherResponse weatherResponse = redisService.get("Weather of " + city, WeatherResponse.class);
        if (weatherResponse != null ) {
            return weatherResponse;
        } else {
            String finalAPI = appCache.appCacheMap.get("weather_api").replace("<city>", city).replace("<apiKey>", apiKey);
            ResponseEntity<WeatherResponse> response = restTemplate.exchange(finalAPI, HttpMethod.GET, null, WeatherResponse.class);
            WeatherResponse body = response.getBody();
            if (body != null) {
                redisService.set("Weather of " + city, body, 300l);
            }
            return body;
        }
    }
    
}
