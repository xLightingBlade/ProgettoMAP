/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avventura;

import java.io.BufferedReader;
import java.io.Serializable;

/**
 *
 * @author Antonio
 * Questa classe vuole velocizzare le operazioni di input/output per il caricamento dei dati 
 * del gioco da un dispositivo di input e per la stampa a schermo, rendendole eseguibili in parallelo grazie all'utilizzo dei thread .
 */
public class CaricamentoDati extends Thread implements Serializable 
{
    private final BufferedReader in;
    
    public CaricamentoDati(BufferedReader in)
    {
        this.in = in;
    }
    
    @Override
    public void run() 
    {
        Integer secondiAttesa = 1;
        LoaderPrinterCharacterStream frasiIntro = new LoaderPrinterCharacterStream();
        try 
        {
            frasiIntro.carica(in);//caricamento effettivo dei dialoghi da file
            frasiIntro.stampaAdIntervallo(System.out, secondiAttesa);///stampa a schermo ad intervallo regolare di intro e dialoghi iniziali
        } 
        catch (InterruptedException ex) 
        {
            System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
            System.exit(0);
        } 
        catch (IllegalArgumentException ex) 
        {
            try 
            {
                frasiIntro.stampaAdIntervallo(System.out, 0);
            } 
            catch (InterruptedException | IllegalArgumentException ex1) 
            {
                System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
                System.exit(0);
            }
        }
    }
    
}
