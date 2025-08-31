package com.timelog.Journal.App.service;

import com.timelog.Journal.App.ApiResponse.WeatherApiResponse;
import com.timelog.Journal.App.Cache.AppCache;
import com.timelog.Journal.App.repository.TimeLogConfigRepo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    @Value("${weather.api.key}")
    private String apikey;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private AppCache appCache;

    public WeatherApiResponse getWeather(String city){
        String finalApi = appCache.APP_CACHE.get("weather_api").replace("<city>", city).replace("<api_key>", apikey);
        ResponseEntity<WeatherApiResponse> response = restTemplate.exchange(finalApi, HttpMethod.GET, null, WeatherApiResponse.class);
        WeatherApiResponse body = response.getBody();
        return body;
    }


}
