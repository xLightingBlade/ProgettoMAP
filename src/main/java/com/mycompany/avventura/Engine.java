/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.avventura;

import com.mycompany.gioco.Avventura;
import com.mycompany.parser.Parser;
import com.mycompany.parser.ParserOutput;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.TipoComando;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.io.Serializable;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author gabri
 * Da questa classe parto che possa
 * eseguire qualsiasi gioco che estende StrutturaGioco, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 */
public class Engine implements Serializable 
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
        
        //caricamento frasi introduttive e dialoghi iniziali da file tramite thread 
        try
        {
            BufferedReader fileIn = new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//(introduzione_al_gioco)Soggiorno.txt"));
            CaricamentoDati loader_introduzione = new CaricamentoDati(fileIn);
            loader_introduzione.start();//caricamento e visualizzazione intro e dialoghi con thread       
            
            this.gioco.init();//Questa chiamata fa partire il caricamento dei dati del gioco
            loader_introduzione.join();
        }
        catch(FileNotFoundException ex)
        {
            System.out.println("Errore nel caricamento dati. File per i dialoghi iniziali non trovato. "
            + "\nRiavvia il gioco.");
            System.exit(0);
        } 
        catch (InterruptedException | IllegalArgumentException ex) 
        {
            System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
            System.exit(0);
        } 
        catch (Exception ex)
        {
            System.err.println(ex);
            System.exit(0);
        }
        
        try
        {
            //caricamento delle stopwords in un set di stringhe.
            Set<String> stopwords = Utils.caricaStopwords(new File("./resources/stopwords"));
            parser = new Parser(stopwords);//creazione del parser con le relative stopwords
        }
        catch (IOException ex)
        {
            System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
            System.exit(0);
        }
    }
    //
    
    //Questo metodo fa partire il gioco, rendendolo giocabile all'utente
    public void esegui()
    {           
        /*
        Viene preso un comando in input, viene analizzato dal parser.
        In base all'output del parser si procede con il gioco.
        */    
        
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) 
        {
            String command = scanner.nextLine();//comando preso in input dall'utente
            
            if(command.equals("salva"))
            {
                CaricamentoSalvataggioPartita.salva(this);
            }
            else
            {
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
    }


    public static void main(String[] args)           
    {
        //Avvia di una nuova partita. Da eseguire quando l'utente schiaccia il bottone nuova partita.
        Engine partita = new Engine(new Avventura());
        CaricamentoSalvataggioPartita.avviaPartita(partita);
        
        
        //CaricamentoSalvataggioPartita.carica();
    }
    
    
    public StrutturaGioco getGioco()
    {
        return this.gioco; 
    }
}
