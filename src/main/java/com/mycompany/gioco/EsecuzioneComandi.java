/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;

import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.Stanza;
import com.mycompany.tipi.TipoComando;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author gabri
 */

public class EsecuzioneComandi implements Serializable{
    Avventura a;
    ControlloSpostamenti controller = new ControlloSpostamenti();
    
    
    public EsecuzioneComandi(Avventura a) {
        this.a = a;
    }
    
    void chiudiPartita() {
        System.out.println("Partita terminata");
        System.exit(0);
    }
    
    void checkNordAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore){
        if (stanzacorrente.getNord() != null) {
            if(controller.checkAccessoStanza(a.getStanze().get(stanzacorrente.getNord().getId()), inventarioGiocatore)){
                //setta la nuova stanzaCorrente a quella a nord della stanza corrente attuale
                a.setStanzaCorrente(stanzacorrente.getNord());
                    a.haAccessoAllaStanza = true;
                }else{  
                    System.out.println("Non puoi accedere alla stanza.");
                }    
        }else {
            a.assenzaStanza = true;
        }
    }
    
    void checkSudAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore){
        if (stanzacorrente.getSud() != null) {
             if(controller.checkAccessoStanza(a.getStanze().get(stanzacorrente.getSud().getId()), inventarioGiocatore)) {
                a.setStanzaCorrente(stanzacorrente.getSud());
                a.haAccessoAllaStanza = true;
            }else {
                System.out.println("Non puoi accedere alla stanza.");
            }

        }else {
            a.assenzaStanza = true;
        }
    }
    
    void checkEstAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore){
        if (stanzacorrente.getEst() != null) {
            if(controller.checkAccessoStanza(a.getStanze().get(stanzacorrente.getEst().getId()), inventarioGiocatore)) {
                a.setStanzaCorrente(stanzacorrente.getEst());
                a.haAccessoAllaStanza = true;
            }else {
                System.out.println("Non puoi accedere alla stanza.");
            }

        }else {
            a.assenzaStanza = true;
        }
    }
    
    void checkWestAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore){
        if (stanzacorrente.getOvest() != null) {
            if(controller.checkAccessoStanza(a.getStanze().get(stanzacorrente.getOvest().getId()), inventarioGiocatore)) {
                a.setStanzaCorrente(stanzacorrente.getOvest());
                a.haAccessoAllaStanza = true;
            }else {
                System.out.println("Non puoi accedere alla stanza.");
            }

        } else {
            a.assenzaStanza = true;
        }
    }
}
