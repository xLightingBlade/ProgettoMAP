/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;

import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.Stanza;
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
}
