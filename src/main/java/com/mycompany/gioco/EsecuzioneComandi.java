/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;

import com.mycompany.exception.ImgException;
import com.mycompany.swing.ImgJFrame;
import com.mycompany.tipi.ContenitoreOggetti;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.Stanza;
import java.io.Serializable;
import java.util.Iterator;
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
    
    //Per ora accende solo la torcia
    void accendiQualcosa(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore, Oggetto oggetto) {
        if(oggetto != null){
            if(oggetto.getNome().equalsIgnoreCase("torcia") && stanzacorrente.isVisibile() == false) {
                stanzacorrente.setVisibile(true);
                System.out.println("Hai acceso la tua torcia, adesso riesci a vedere cosa c'è nella stanza");
            } else if(stanzacorrente.isVisibile() == false) {
                System.out.println("La stanza è già abbastanza illuminata");
            }
        } else {
            System.out.println("Non c'è niente da accendere");
        }
    }
    
    //per ora si nasconde solo dietro la roccia
    void nasconditi(Stanza stanzacorrente) {
        if(stanzacorrente.getOggetti().contains(new Oggetto(11))) {
            System.out.println("Ti sei nascosto dietro la grande roccia.");
        } else {
            System.out.println("Non vedi un posto dove nasconderti.");
        }
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
    
    void printInventarioContent(List<Oggetto> inventarioGiocatore){
        System.out.println("Nel tuo inventario ci sono:");
                for (Oggetto o : inventarioGiocatore) {
                    if(!o.isInvisibile()) {
                        System.out.println(o.getNome() + ": " + o.getDescrizione());
                    }
                }
    }
    
    void printOsservazione(Stanza stanzaCorrente){
    
         if (stanzaCorrente.getOsservazione() != null) {
             if(stanzaCorrente.isVisibile() == true) {
                 System.out.println(stanzaCorrente.getOsservazione());
             } else {
                 System.out.println("Non si vede niente!");
             }    
                } else {
                    System.out.println("Non c'è niente di interessante da osserva qui.");
                }
    }
    /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
    * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
    * Potrebbe non esssere la soluzione ottimale.
     */
    void apriOggetto(Oggetto oggetto, Oggetto oggettoInventario, Stanza stanzaCorrente, List<Oggetto> inventarioGiocatore ){
        if (oggetto == null && oggettoInventario == null) {
                    System.out.println("Non c'è niente da aprire qui.");
                } else {
                    if (oggetto != null) {
                        if (oggetto.isApribile() && oggetto.isAperto() == false) {
                            if (oggetto instanceof ContenitoreOggetti) {
                                System.out.println("Hai aperto: " + oggetto.getNome());
                                ContenitoreOggetti c = (ContenitoreOggetti) oggetto;
                                if (!c.getList().isEmpty()) {
                                    System.out.print(c.getNome() + " contiene:");
                                    Iterator<Oggetto> it = c.getList().iterator();
                                    
                                    while (it.hasNext()) {
                                        Oggetto next = it.next();
                                        stanzaCorrente.getOggetti().add(next);
                                        System.out.print(" " + next.getNome());
                                        it.remove();
                                    }
                                    System.out.println();
                                }
                                oggetto.setAperto(true);
                            } else {
                                System.out.println("Hai aperto: " + oggetto.getNome());
                                oggetto.setAperto(true);
                            }
                        } else {
                            System.out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                    
                    if (oggettoInventario != null) {
                        if (oggettoInventario.isApribile() && oggettoInventario.isAperto() == false) {
                            if (oggettoInventario instanceof ContenitoreOggetti) {
                                ContenitoreOggetti c = (ContenitoreOggetti) oggettoInventario;
                                
                                if (!c.getList().isEmpty()) {
                                    System.out.print(c.getNome() + " contiene:");
                                    Iterator<Oggetto> it = c.getList().iterator();
                                    
                                    while (it.hasNext()) {
                                        Oggetto next = it.next();
                                        inventarioGiocatore.add(next);
                                        System.out.print(" " + next.getNome());
                                        it.remove();
                                    }
                                    System.out.println();
                                }
                                
                                oggettoInventario.setAperto(true);
                            } else {
                                oggettoInventario.setAperto(true);
                            }
                            
                            System.out.println("Hai aperto nel tuo inventario: " + oggettoInventario.getNome());
                        } else {
                            System.out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                }
    }
    
    void prendiOggetto(Oggetto oggetto, List<Oggetto> inventarioGiocatore, Stanza stanzaCorrente) {
        if (oggetto != null) 
        {
            if (oggetto.isPrendibile()) 
            {
                //da migliorare
                if(oggetto.getNome().equals("foto")) 
                {
                    try
                    {
                        new ImgJFrame(".//resources//img//fotoSoggiorno960x660.jpg","").setVisible(true);
                        System.out.println("Stai guardando: "+oggetto.getDescrizione());
                    }
                    catch(ImgException e)
                    {
                        System.out.println(e.getMessage());
                    }
                }
                else
                {
                    inventarioGiocatore.add(oggetto);
                    stanzaCorrente.getOggetti().remove(oggetto);
                    
                }
                
                if (!oggetto.isInvisibile() && !oggetto.getNome().equals("foto")) 
                {
                    System.out.println("Hai raccolto: " + oggetto.getDescrizione());
                }
            }
            else 
            {
                System.out.println("Non puoi raccogliere questo oggetto.");
            }
        }
        else 
        {
            System.out.println("\nQuesto oggetto non è presente in questa stanza,\no forse non c'è niente da raccogliere qui.");
        }
    }
    
    void spingiOggetto(Oggetto oggetto, List<Oggetto> inventarioGiocatore, Stanza stanzaCorrente){
        if(oggetto != null){
            if (oggetto.isSpingibile()) {
                       System.out.println("Hai premuto: " + oggetto.getNome());
                       //Creare metodo più generico qui.
                       //questo mi serve solo per ''vedere'' se ho ''premuto'' la leva(in realtà me la metto nell'inventario, invisibile
                       if (oggetto.getId() == 14) {
                           inventarioGiocatore.add(oggetto);
                           stanzaCorrente.getOggetti().remove(oggetto);
                       }
            } else {
                System.out.println("Non ci sono oggetti che puoi premere qui.");
            }
        }else{
            System.out.println("Nessun Oggetto da spingere.");
        }
    }
    
    void leggiOggetto(Oggetto oggetto){
        if(oggetto != null){
            if (oggetto.isLeggibile()) {
                    System.out.print(oggetto.getContenuto());

            } else {
                System.out.println("Non ci sono oggetti che puoi leggere qui.");
            }
        }else{
            System.out.println("Nessun Oggetto da Leggere.");
        }
    }

    void curati(List<Oggetto> inventarioGiocatore) {
        if (controller.controllaInventarioPerCura(inventarioGiocatore)) {
            System.out.println("Ti sei curato");
            inventarioGiocatore.remove(new Oggetto(6, "garza"));
            inventarioGiocatore.remove(new Oggetto(7, "alcol"));
            inventarioGiocatore.remove(new Oggetto(9, "forbici"));
        } else {
            System.out.println("Non possiedi ciò che ti serve per curarti, cerca meglio");
        }
    }
}
