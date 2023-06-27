/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;

import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.Stanza;
import java.util.List;

/**
 *
 * @author gabri
 */

public class ControlloSpostamenti {
    //Questo è un metodo bello generico, può controllare in ogni stanza cosa serve avere per entrarci
    public boolean checkAccessoStanza(Stanza stanza, List<Oggetto> inventarioGiocatore) {
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
    
}
