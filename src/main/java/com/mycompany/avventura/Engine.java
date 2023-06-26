/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.avventura;

import com.mycompany.gioco.Avventura;
import com.mycompany.parser.Parser;
import com.mycompany.parser.ParserOutput;
import com.mycompany.tipi.TipoComando;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.lang.IllegalArgumentException;
import com.mycompany.avventura.LoaderPrinterText;

/**
 *
 * @author gabri
 * Da questa classe partirà l'esecuzione del gioco ero e proprio. Essa è fatta in modo che possa
 * eseguire qualsiasi gioco che estende StrutturaGioco, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 */
public class Engine
{

    private final StrutturaGioco gioco;

    private Parser parser;

    //Costruttore
    /*Il costruttore fa principalmente 2 cose:
        1)caricamento dei dati del gioco
        2)crezione del parser
        3)caricamento delle stopwords da file
    */
    public Engine(StrutturaGioco game) 
    {
        this.gioco = game;
        
        try
        {
            this.gioco.init();//Questa chiamata fa partire il caricamento dei dati del gioco
        }
        catch (Exception ex)
        {
            System.err.println(ex);
        }
        
        try
        {
            //caricamento delle stopwords in un set di stringhe.
            Set<String> stopwords = Utils.caricaStopwords(new File("./resources/stopwords"));
            parser = new Parser(stopwords);//creazione del parser con le relative stopwords
        }
        catch (IOException ex)
        {
            System.err.println(ex);
        }
    }
    //
    
    //Questo metodo fa partire il gioco, rendendolo giocabile all'utente
    public void esegui()
    {
        //caricamento frasi introduttive e dialoghi iniziali
        try
        {
            BufferedReader fileIn = new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//(introduzione_al_gioco)Soggiorno.txt"));
            LoaderPrinterText frasiIntro = new LoaderPrinterText();
            frasiIntro.carica(fileIn);
            
            frasiIntro.stampaAdIntervallo(System.out, 2);
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Errore nel caricamento dati. File non trovato.");
        } 
        catch (InterruptedException | IllegalArgumentException ex) 
        {
            System.out.println("Errore nel caricamento dati.");
        }
        
        
        /*
        Viene preso un comando in input, viene analizzato dal parser.
        In base all'output del parser si procede con il gioco.
        */    
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) 
        {
            String command = scanner.nextLine();//comando preso in input dall'utente
            
            //E' presente l'output del parser dopo aver processato il comando dell'utente
            ParserOutput p = parser.parse(command, gioco.getComandi(), gioco.getStanzaCorrente().getOggetti(), gioco.getInventario());
            
            if (p == null || p.getComando() == null)
            {
                System.out.println("Non capisco quello che mi vuoi dire.");
                System.out.println();
            }
            else if (p.getComando() != null && p.getComando().getTipo() == TipoComando.FINE)
            {
                System.out.println("Partita terminata");
                break;
            }
            else
            {
                gioco.prossimaMossa(p, System.out);//avanzo con il gioco
            }
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        Engine engine = new Engine(new Avventura());
        engine.esegui();//partenza del gioco
    }

}
