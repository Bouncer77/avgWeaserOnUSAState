package com.bouncer77;

import com.bouncer77.model.ResponseWeather;

import java.io.IOException;

public class App {
    public static void main(String[] args) {
        String req = "";
        try {
            req = HttpsGet.connect("https://api.openweathermap.org/data/2.5/weather?q=Moscow&appid=10a1023146c007ade8bcb3da7c95df0a&mode=json&units=metric&lang=ru");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(req);

        ResponseWeather responseWeather = JsonReader.getWeather(req);

        System.out.println("Temp = " + responseWeather.getMain().getTemp());
    }
}
