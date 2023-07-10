/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.openweatherAPI;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Classe con metodi per differenti chiamate API ad OpenWeather
 * La chiave api rimane qui in chiaro, per lo scopo di questo progetto non ci si preoccupa dell'aspetto sicurezza
 * @author gabri
 */
public class MeteoAPIController {
    private static final String API_KEY = "a9d4563be1eacdd328807b358fb23966";
    
    public static MeteoForecastDTO getMeteoConCoordinate(String latitude, String longitude) throws IOException, InterruptedException{
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?lat="+latitude+"&lon="+longitude+"&units=metric&appid="+API_KEY);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest
                .newBuilder()
                .uri(uri)
                .header("accept", "application/json")
                .GET()
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
      
        return MeteoForecastDTO.parseJson(response.body());
    }
    
    public static MeteoForecastDTO getMeteoCitta(String city) throws IOException, InterruptedException{
        var uri = URI.create("https://api.openweathermap.org/data/2.5/weather?q="+city.replaceAll("\\s", "%20")+"&units=metric&appid="+API_KEY);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest
                .newBuilder()
                .uri(uri)
                .header("accept", "application/json")
                .GET()
                .build();
        var response = client.send(request, HttpResponse.BodyHandlers.ofString());
      
        return MeteoForecastDTO.parseJson(response.body());
    }
    
}
