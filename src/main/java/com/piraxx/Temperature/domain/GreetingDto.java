package com.piraxx.Temperature.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GreetingDto {

    private String client_ip;

    private String location;

    private String greeting;
}
