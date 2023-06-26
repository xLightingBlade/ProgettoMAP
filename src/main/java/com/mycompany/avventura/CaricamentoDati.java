/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avventura;

import java.io.BufferedReader;

/**
 *
 * @author Antonio
 * Questa classe vuole velocizzare le operazioni di input/output per il caricamento dei dati 
 * del gioco da un dispositivo di input e per la stampa a schermo, rendendole eseguibili in parallelo grazie all'utilizzo dei thread .
 */
public class CaricamentoDati extends Thread 
{
    private final BufferedReader in;
    
    public CaricamentoDati(BufferedReader in)
    {
        this.in = in;
    }
    
    @Override
    public void run() 
    {
        Integer secondiAttesa = 2;
        LoaderPrinterText frasiIntro = new LoaderPrinterText();
        try 
        {
            frasiIntro.carica(in);//caricamento effettivo della intro e dialoghi iniziali da file
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
                frasiIntro.stampaAdIntervallo(System.out, secondiAttesa-1);
            } 
            catch (InterruptedException | IllegalArgumentException ex1) 
            {
                System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
                System.exit(0);
            }
        }
    }
    
}
