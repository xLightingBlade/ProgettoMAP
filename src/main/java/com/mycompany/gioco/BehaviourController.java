/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;

import com.mycompany.avventura.CaricamentoDati;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.Stanza;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 *
 * @author gabri
 */

public class BehaviourController {
    //Questo è un metodo bello generico, può controllare in ogni stanza cosa serve avere per entrarci
    public static boolean checkAccessoStanza(Stanza stanza, List<Oggetto> inventarioGiocatore) {
        int counterOggettiStanza = stanza.getOggettiNecessari().size();
        int counterOggettiTrovati = 0;
        for(Oggetto oggettoStanza : stanza.getOggettiNecessari()) {
            for(Oggetto oggettoInventario : inventarioGiocatore) {
                if(oggettoStanza.equals(oggettoInventario)){
                    counterOggettiTrovati+= 1;
                }
            }
        }
        return counterOggettiStanza == counterOggettiTrovati;
    }

    public static boolean controllaInventarioPerCura(List<Oggetto> inventarioGiocatore) {
        return (checkOggettoInventario(inventarioGiocatore, "garza")
                && checkOggettoInventario(inventarioGiocatore, "alcol")
                && checkOggettoInventario(inventarioGiocatore, "forbici"));
    }
    
    private static boolean checkOggettoInventario(List<Oggetto> inventarioGiocatore, String nomeOgg) {
        return inventarioGiocatore.stream().anyMatch(obj -> obj.getNome().equalsIgnoreCase(nomeOgg));
    }

    public static void checkDialoghi(Stanza stanzaCorrente) throws UnsupportedEncodingException{
        if(!stanzaCorrente.accessoFatto()) {
            selezioneDialogoIngressoStanza(stanzaCorrente);
            stanzaCorrente.setPrimoAccessoFatto(true);
        }
    }

    public static void selezioneDialogoIngressoStanza(Stanza stanzaCorrente) throws UnsupportedEncodingException{
        String filePath = null;
        
        switch(stanzaCorrente.getNome()) {
            
            case "Corridoio passaggio segreto" -> {
                filePath = ".//the_last_of_us(storia)//Dialoghi//Passaggio_segreto.txt";
                mostraDialogoStanza(filePath);
            }
            case "Uscita Passaggio" -> {
                //Questa stanza andrebbe creata al fine di visualizzare questo dialogo e poi passare direttamente alla prossima stanza
                filePath = ".//the_last_of_us(storia)//Dialoghi//Uscita_passaggio_segreto.txt";
                mostraDialogoStanza(filePath);
            }
            case "Ingresso Metro" -> {
                filePath = ".//the_last_of_us(storia)//Dialoghi//Metropolitana_allagata.txt";
                mostraDialogoStanza(filePath);
            }
            case "Stanza Zattera" -> {
                filePath = ".//the_last_of_us(storia)//Dialoghi//Stanza_della_zattera.txt";
                mostraDialogoStanza(filePath);
            }
            //anche questa stanza andrebbe creata per il dialogo
            case "Uscita Metro" -> {
                filePath = ".//the_last_of_us(storia)//Dialoghi//Uscita_metropolitana.txt";
                mostraDialogoStanza(filePath);
            }
            case "Ingresso Ospedale" -> {
                filePath = ".//the_last_of_us(storia)//Dialoghi//Arrivo_all_ospedale.txt";
                mostraDialogoStanza(filePath);
            }
            case "Sala d'attesa ospedale" -> {
                filePath = ".//the_last_of_us(storia)//Dialoghi//Dentro_ospedale.txt";
                mostraDialogoStanza(filePath);
            }
            case "Piano Sala" -> {
                filePath = ".//the_last_of_us(storia)//Dialoghi//Piano_della_sala_operatoria.txt";
                mostraDialogoStanza(filePath);
            }
            case "Sala Operatoria" -> {
                filePath = ".//the_last_of_us(storia)//Dialoghi//Sala_operatoria.txt";
                mostraDialogoStanza(filePath);
            }
            //creare
            case "Salt Lake City, Utah" -> {
                filePath = ".//the_last_of_us(storia)//Dialoghi//Dialoghi_finali.txt";
                mostraDialogoStanza(filePath);
            }
        }
    }

    public static void mostraDialogoStanza(String filePath) throws UnsupportedEncodingException
    {    
        try 
        {
            BufferedReader fileIn = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "UTF-8"));
            CaricamentoDati loader_introduzione = new CaricamentoDati(fileIn);
            loader_introduzione.start();
            loader_introduzione.join();
        } 
        catch(FileNotFoundException ex)
        {
            System.out.println("""
                               Errore nel caricamento dati. 
                               Riavvia il gioco.""");
            System.exit(0);
        } 
        catch (InterruptedException | IllegalArgumentException ex) 
        {
            System.out.println("Errore nel caricamento dati. Riavvia il gioco.");
            System.exit(0);
        } 
    }
}
