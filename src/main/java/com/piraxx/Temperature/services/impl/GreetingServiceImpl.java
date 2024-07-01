package com.piraxx.Temperature.services.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.piraxx.Temperature.domain.GreetingDto;
import com.piraxx.Temperature.domain.WeatherDto;
import com.piraxx.Temperature.services.GeolocationService;
import com.piraxx.Temperature.services.GreetingService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GreetingServiceImpl implements GreetingService {

    private final GeolocationService geolocationService;

    @Autowired
    public GreetingServiceImpl(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @Override
    public Optional<GreetingDto> greet(HttpServletRequest request) throws JsonProcessingException {

            String ipAddress = getIpAddress(request);
            WeatherDto weatherDetail = geolocationService.getWeather(ipAddress);
            String name = getName(request);
            double tempInDegreeCelcius = weatherDetail.getCurrent().getTemp_c();
            String location = weatherDetail.getLocation().getName();

            var greeting = GreetingDto.builder()
                    .client_ip(ipAddress)
                    .location(location)
                    .greeting("Hello, " + name + "!, the temperature is " + tempInDegreeCelcius + " degrees Celcius " + location)
                    .build();
            return Optional.of(greeting);
    }

    private static String getName(HttpServletRequest request){
        String name = request.getParameter("visitor_name");
        if(name.startsWith("\"")){
            name = name.substring(1, name.length() - 1);
        }
        return name;
    }

    private static String getIpAddress(HttpServletRequest request){
        String ipAddress = request.getRemoteAddr();
        String xForwardedForHeader = request.getHeader("X-Forwarded-For");

        if (xForwardedForHeader != null && !xForwardedForHeader.isEmpty()) {
            ipAddress = xForwardedForHeader.split(",")[0];
        }
        return ipAddress;
    }
}

