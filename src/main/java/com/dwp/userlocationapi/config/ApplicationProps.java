package com.dwp.userlocationapi.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@ConfigurationProperties
@Getter
@Setter
public class ApplicationProps {

    private Map<String,Coordinate> cityList;

    @Getter
    @Setter
    public static class Coordinate {
        private double latitude;
        private double longitude;
    }
}
