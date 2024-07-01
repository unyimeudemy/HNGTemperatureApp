package com.piraxx.Temperature.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.piraxx.Temperature.domain.ErrorDto;
import com.piraxx.Temperature.domain.GreetingDto;
import com.piraxx.Temperature.services.GreetingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GreetingController {

    private final GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }


    @GetMapping("/hello")
    public ResponseEntity<?> greet(
            @NonNull HttpServletRequest request,
            @RequestParam(value = "visitor_name", required = false) String visitorName
    ) throws JsonProcessingException {
        if (visitorName == null || visitorName.isEmpty()) {
            ErrorDto error = ErrorDto.builder()
                    .errorMessage("Missing or invalid 'visitor_name' parameter. Please provide a valid 'visitor_name' as a query parameter.")
                    .build();
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        Optional<GreetingDto> res = greetingService.greet(request);

        return new ResponseEntity<>(res.get(), HttpStatus.OK);
    }


}
