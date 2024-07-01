package com.piraxx.Temperature.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.piraxx.Temperature.domain.GreetingDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface GreetingService {

    Optional<GreetingDto> greet(HttpServletRequest request) throws JsonProcessingException;
}
