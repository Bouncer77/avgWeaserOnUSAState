package com.bouncer77;

import com.bouncer77.model.ResponseWeather;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonReader {

    public static ResponseWeather getWeather(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        ResponseWeather responseWeather = null;

        try {
            responseWeather = objectMapper.readValue(json, ResponseWeather.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return responseWeather;
    }
}
