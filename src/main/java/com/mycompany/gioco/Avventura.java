/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gioco;

import com.mycompany.avventura.StrutturaGioco;
import com.mycompany.database.OperazioniDatabase;
import com.mycompany.parser.ParserOutput;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.ContenitoreOggetti;
import com.mycompany.tipi.Comando;
import com.mycompany.tipi.TipoComando;
import com.mycompany.tipi.Stanza;
import static com.mycompany.tipi.TipoComando.APRI;
import static com.mycompany.tipi.TipoComando.EST;
import static com.mycompany.tipi.TipoComando.GUARDA;
import static com.mycompany.tipi.TipoComando.INVENTARIO;
import static com.mycompany.tipi.TipoComando.LEGGI;
import static com.mycompany.tipi.TipoComando.NORD;
import static com.mycompany.tipi.TipoComando.OVEST;
import static com.mycompany.tipi.TipoComando.PRENDI;
import static com.mycompany.tipi.TipoComando.SPINGI;
import static com.mycompany.tipi.TipoComando.SUD;
import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;

/**
 * ATTENZIONE: La descrizione del gioco è fatta in modo che qualsiasi gioco
 * debba estendere la classe StrutturaGioco. L'Engine è fatto in modo che possa
 * eseguire qualsiasi gioco che estende StrutturaGioco, in questo modo si
 * possono creare più gioci utilizzando lo stesso Engine.
 *
 * Diverse migliorie possono essere applicate: - la descrizione del gioco
 * potrebbe essere caricate da file o da DBMS in modo da non modificare il
 * codice sorgente - l'utilizzo di file e DBMS non è semplice poiché all'interno
 * del file o del DBMS dovrebbe anche essere codificata la logica del gioco
 * (prossimaMossa) oltre alla descrizione di stanze, oggetti, ecc...
 *
 * @author gabri
 */
public class Avventura extends StrutturaGioco {
    ControlloSpostamenti controller = new ControlloSpostamenti();
    boolean haAccessoAllaStanza = false;
    boolean assenzaStanza = false;
    @Override
    public void init() throws Exception {
        OperazioniDatabase.connettiDatabase();
        OperazioniDatabase.resetDatabase();
        OperazioniDatabase.creaTabelle();
        OperazioniDatabase.caricaDati();
        OperazioniDatabase.creaStanze();
        
        setComandi(OperazioniDatabase.creaComandi());
        setStanze(OperazioniDatabase.creaOggetti());
        //Stanza attuale
        setStanzaCorrente(getStanze().get(0));
    }
    
    @Override
    //Aggiungere comando per leggere
    //In questo metodo è racchiuso il cuore del gioco e il suo avanzare.
    public void prossimaMossa(ParserOutput p) {
        if (p.getComando() == null) {
            System.out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            this.haAccessoAllaStanza = false;
            this.assenzaStanza = false;
            Stanza stanzacorrente = getStanzaCorrente();
            List<Oggetto> inventarioGiocatore = getInventario();
            Oggetto oggetto = p.getOggetto();
            Oggetto oggettoInventario = p.getOggettoInventario();
            TipoComando comando = p.getComando().getTipo();
            
            switch(comando){
                case NORD -> checkNordAccess(stanzacorrente,inventarioGiocatore);
                case SUD -> checkSudAccess( stanzacorrente,inventarioGiocatore);
                case EST -> checkEstAccess(stanzacorrente, inventarioGiocatore);
                case OVEST -> checkWestAccess(stanzacorrente,inventarioGiocatore);
                case INVENTARIO -> printInventarioContent(inventarioGiocatore);
                case GUARDA -> printOsservazione(stanzacorrente);
                case PRENDI -> prendiOggetto( oggetto,inventarioGiocatore, stanzacorrente);
                case APRI -> apriOggetto(oggetto, oggettoInventario, stanzacorrente, inventarioGiocatore );
                case SPINGI -> {
                    if(oggetto!= null){
                        spingiOggetto(oggetto, inventarioGiocatore, stanzacorrente);
                    }else{
                        spingiOggetto(oggettoInventario, inventarioGiocatore, stanzacorrente);
                    }
                }
                case LEGGI -> {
                    if(oggetto!= null){
                        leggiOggetto(oggetto);
                    }else{
                        leggiOggetto(oggettoInventario);
                    }
                }
                default -> {
                    return;
                }
            }
            
            if (this.haAccessoAllaStanza) {
                System.out.println(getStanzaCorrente().getNome());
                System.out.println("================================================");
                System.out.println(getStanzaCorrente().getDescrizione());
            }
            if (this.assenzaStanza) {
                System.out.println("Da quella parte non si può andare c'è un muro!\n");
            }
        }
    }
    
    private void checkNordAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore){
        if (stanzacorrente.getNord() != null) {
            if(controller.checkAccessoStanza(getStanze().get(stanzacorrente.getNord().getId()), inventarioGiocatore)){
                //setta la nuova stanzaCorrente a quella a nord della stanza corrente attuale
                setStanzaCorrente(stanzacorrente.getNord());
                    this.haAccessoAllaStanza = true;
                }else{  
                    System.out.println("Non puoi accedere alla stanza.");
                }    
        }else {
            assenzaStanza = true;
        }
    }
    
    private void checkSudAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore){
        if (stanzacorrente.getSud() != null) {
             if(controller.checkAccessoStanza(getStanze().get(stanzacorrente.getSud().getId()), inventarioGiocatore)) {
                setStanzaCorrente(stanzacorrente.getSud());
                this.haAccessoAllaStanza = true;
            }else {
                System.out.println("Non puoi accedere alla stanza.");
            }

        }else {
            assenzaStanza = true;
        }
    }
    
    private void checkEstAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore){
        if (stanzacorrente.getEst() != null) {
            if(controller.checkAccessoStanza(getStanze().get(stanzacorrente.getEst().getId()), inventarioGiocatore)) {
                setStanzaCorrente(stanzacorrente.getEst());
                this.haAccessoAllaStanza = true;
            }else {
                System.out.println("Non puoi accedere alla stanza.");
            }

        }else {
            assenzaStanza = true;
        }
    }
    
    private void checkWestAccess(Stanza stanzacorrente, List<Oggetto> inventarioGiocatore){
        if (stanzacorrente.getOvest() != null) {
            if(controller.checkAccessoStanza(getStanze().get(stanzacorrente.getOvest().getId()), inventarioGiocatore)) {
                setStanzaCorrente(stanzacorrente.getOvest());
                this.haAccessoAllaStanza = true;
            }else {
                System.out.println("Non puoi accedere alla stanza.");
            }

        } else {
            assenzaStanza = true;
        }
    }
    
    private void printInventarioContent(List<Oggetto> inventarioGiocatore){
        System.out.println("Nel tuo inventario ci sono:");
                for (Oggetto o : inventarioGiocatore) {
                    if(!o.isInvisibile()) {
                        System.out.println(o.getNome() + ": " + o.getDescrizione());
                    }
                }
    }
    
    private void printOsservazione(Stanza stanzaCorrente){
    
         if (stanzaCorrente.getOsservazione() != null) {
                    System.out.println(stanzaCorrente.getOsservazione());
                } else {
                    System.out.println("Non c'è niente di interessante da osserva qui.");
                }
    }
    /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
    * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
    * Potrebbe non esssere la soluzione ottimale.
     */
    private void apriOggetto(Oggetto oggetto, Oggetto oggettoInventario, Stanza stanzaCorrente, List<Oggetto> inventarioGiocatore ){
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
    
    private void prendiOggetto(Oggetto oggetto, List<Oggetto> inventarioGiocatore, Stanza stanzaCorrente){
         if (oggetto != null) {
                    if (oggetto.isPrendibile()) {
                        getInventario().add(oggetto);
                        stanzaCorrente.getOggetti().remove(oggetto);
                        if (!oggetto.isInvisibile()) {
                            System.out.println("Hai raccolto: " + oggetto.getDescrizione());
                        }
                    } else {
                        System.out.println("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    System.out.println("\nQuesto oggetto non è presente in questa stanza,\no forse non c'è niente da raccogliere qui.");
                }
    }
    
    private void spingiOggetto(Oggetto oggetto, List<Oggetto> inventarioGiocatore, Stanza stanzaCorrente){
        if(oggetto != null){
            if (oggetto.isSpingibile()) {
                       System.out.println("Hai premuto: " + oggetto.getNome());
                       //Creare metodo più generico qui.
                       //questo mi serve solo per ''vedere'' se ho ''premuto'' la leva(in realtà me la metto nell'inventario, invisibile
                       if (oggetto.getId() == 14) {
                           getInventario().add(oggetto);
                           stanzaCorrente.getOggetti().remove(oggetto);
                       }
            } else {
                System.out.println("Non ci sono oggetti che puoi premere qui.");
            }
        }else{
            System.out.println("Nessun Oggetto da spingere.");
        }
    }
    
    private void leggiOggetto(Oggetto oggetto){
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
    
    
}
