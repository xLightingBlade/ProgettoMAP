/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avventura;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio
 */
public class LoaderPrinterCharacterStream implements Serializable 
{
    private List<String> frasi;

    //Costruttore
    public LoaderPrinterCharacterStream() 
    {
        this.frasi = new ArrayList<>();
    }
    
    //caricamento stream di caratteri da un generico dispositivo di input
    public void carica(BufferedReader in)
    {
        String l = null;
        try
        {
            l = in.readLine();

            while (l  != null) 
            {
                frasi.add(l);
                l = in.readLine();      
            }
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Errore nel caricamento dati. File non trovato.");
            System.exit(0);
        }
        catch(IOException ex)
        {
            System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
            System.exit(0);
        }
        finally
        {
            try
            {
                in.close();
            }
            catch(IOException ex2)
            {
                System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
                System.exit(0);
            }
        }     
    }
    
    //salvataggio stream di caratteri in un generico dispositivo di output
    public void stampaAdIntervallo(PrintStream out, int secondiAttesa) throws InterruptedException, IllegalArgumentException
    {
        if(secondiAttesa >= 0 && secondiAttesa <= 3)
        {
            for(String s : frasi)
            {
                out.println(s);
                Thread.sleep(secondiAttesa*0);
            }
        }
        else
        {
            throw new IllegalArgumentException(); 
        }       
    }
    
    public void stampa(PrintStream out)
    {
        for(String s : frasi)
        {
            out.println(s);
        }     
    }
}
