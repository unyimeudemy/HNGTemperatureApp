package com.piraxx.Temperature.services;


import com.piraxx.Temperature.domain.WeatherDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class GeolocationService {

    private final RestTemplate restTemplate;

    @Autowired
    public GeolocationService( RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherDto getWeather(String ip) {
        String apiKey = System.getenv("WEATHER_API_KEY");
        String url = "http://api.weatherapi.com/v1/current.json?key=" + apiKey + "&q="  + ip;
        return restTemplate.getForObject(url, WeatherDto.class);
    }

}


