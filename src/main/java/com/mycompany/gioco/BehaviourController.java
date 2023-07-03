/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;

import com.mycompany.avventura.LoaderPrinterCharacterStream;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.Stanza;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
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

    public static void checkDialoghi(Stanza stanzaCorrente) throws FileNotFoundException {
        if(!stanzaCorrente.accessoFatto()) {
            selezioneDialogoIngressoStanza(stanzaCorrente);
            stanzaCorrente.setPrimoAccessoFatto(true);
        }
    }

    public static void selezioneDialogoIngressoStanza(Stanza stanzaCorrente) throws FileNotFoundException {
        String filePath = null;
        switch(stanzaCorrente.getId()) {
            case 3 -> {
                filePath = ".//the_last_of_us(storia)//Dialoghi//Passaggio_segreto.txt";
                mostraDialogoStanza(filePath);
            }
        }
    }

    public static void mostraDialogoStanza(String filePath) throws FileNotFoundException {
        LoaderPrinterCharacterStream streamFrasi = new LoaderPrinterCharacterStream();
        BufferedReader fileIn = new BufferedReader(new FileReader(filePath));
        streamFrasi.carica(fileIn);
        streamFrasi.stampa(System.out);
    }
}
