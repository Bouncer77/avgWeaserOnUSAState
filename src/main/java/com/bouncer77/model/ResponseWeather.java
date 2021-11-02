package com.bouncer77.model;

import lombok.*;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
public class ResponseWeather {

    /*private Coord coord;
    private Weather weather;
    private String base;*/
    private Main main;
}
