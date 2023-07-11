/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.openweatherAPI;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

/**
 *
 * Plain Old Java Object, contiene tutti i campi del json restituito dalla chiamata API
 * @author gabri
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MeteoForecastDTO 
{
    public Coord coord;
    public ArrayList<Weather> weather;
    public String base;
    public Main main;
    public int visibility;
    public Wind wind;
    public Clouds clouds;
    public int dt;
    public Sys sys;
    public int timezone;
    public int id;
    public String name;
    public int cod;
    
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Clouds
    {
        public int all;
    }
    
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Coord
    {
        public double lon;
        public double lat;
    }
    
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Main
    {
        public double temp;
        public double feels_like;
        public double temp_min;
        public double temp_max;
        public int pressure;
        public int humidity;
        public int sea_level;
        public int grnd_level;
    }
    
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Sys
    {
        public int type;
        public int id;
        public String country;
        public int sunrise;
        public int sunset;
    }
    
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Weather
    {
        public int id;
        public String main;
        public String description;
        public String icon;
    }
    
    
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Wind
    {
        public double speed;
        public int deg;
        public double gust;
    }
    
    public static MeteoForecastDTO parseJson(String json) throws MalformedURLException, IOException 
    {
        ObjectMapper mapper = new ObjectMapper();
        return (MeteoForecastDTO) mapper.readValue(json, MeteoForecastDTO.class);
    }
}
