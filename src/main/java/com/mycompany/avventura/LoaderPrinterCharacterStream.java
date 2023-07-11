/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avventura;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Antonio
 * Questa classe esegue varie funzionalità:
 *      1)Permette di leggere da un qualunque flusso di caratteri e salvare ogni riga del flusso su una lista di stringhe.
 *      2)Permette di stampare il contenuto del flusso di caratteri su un qualunque output stream
 *      3)Permette di stampare il contenuto del flusso di caratteri su un qualunque output stream,
 *        stampando ogni elemento della lista di stringhe ad un intervallo di tempo regolare
 *      4)Permette di ottenere il contenuto della lista di stringhe su una singola stringa
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
                Thread.sleep(secondiAttesa*1000);
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
    
    
    public ArrayList<String> getFrasi() 
    {
        return new ArrayList<>(frasi);  // ritorno una copia
    }
    
    
    public String ottieniComeTesto(String pathName) 
    {
        BufferedReader buff = null;
        
        try 
        {
            buff = new BufferedReader(new FileReader(new File(pathName)));
        }  
        catch (FileNotFoundException ex) 
        {
            System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
            System.exit(0);
        }
        
        carica(buff);
        List<String> list = getFrasi();
        StringBuilder sBuilder = new StringBuilder();
        
        for(String s : list) 
        {
            sBuilder.append(s).append("\n");
        }
        
        String testo = sBuilder.toString();
        
        return testo;
    }
}
