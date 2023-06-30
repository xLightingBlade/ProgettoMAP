/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.openweatherAPI;

import java.io.IOException;
/**
 *
 * @author gabri
 */
//Andrebbe migliorato un po', però non credo che l'utente mentre giochi voglia sapere che tempo fa.
//Semplicemente possiamo scrivere alcune chiamate di nostro gradimento e usarle per mostrare dialoghi diversi in base alle condizioni meteo.
public class MeteoAPI {

    public static void mostraTemperaturaCitta(String citta) throws IOException, InterruptedException {
        //in questo esempio stampa la temperatura. Chiaramente posso chiedergli altro
        System.out.println(MeteoAPIController.getWeatherByCity(citta).main.temp);
    }
    
    public static void mostraTemperaturaConCoordinate(String latitudine, String longitudine) throws IOException, InterruptedException {
        //in questo esempio stampa la temperatura. Chiaramente posso chiedergli altro
        System.out.println(MeteoAPIController.getWeatherByCoordinates(latitudine, longitudine).main.temp);
    }
}