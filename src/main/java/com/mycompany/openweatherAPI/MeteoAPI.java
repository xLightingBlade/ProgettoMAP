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
//Chiamate API utili allo scopo del progetto.
public class MeteoAPI 
{
    
    public static int getMeteoID(String citta) throws InterruptedException, IOException 
    {
        int meteoId = 0;
        
        try
        {
            meteoId = MeteoAPIController.getMeteoCitta(citta).weather.get(0).id;
        }
        catch(Exception ex) 
        {
            System.err.print(ex.getMessage());
        }
        
        return meteoId;
    }

    
    public static void mostraTemperaturaCitta(String citta) throws IOException, InterruptedException
    {
        //in questo esempio stampa la temperatura. Chiaramente posso chiedergli altro
        System.out.println(MeteoAPIController.getMeteoCitta(citta).main.temp);
    }
    
    
    public static void mostraTemperaturaConCoordinate(String latitudine, String longitudine) throws IOException, InterruptedException 
    {
        //in questo esempio stampa la temperatura. Chiaramente posso chiedergli altro
        System.out.println(MeteoAPIController.getMeteoConCoordinate(latitudine, longitudine).main.temp);
    }
}
