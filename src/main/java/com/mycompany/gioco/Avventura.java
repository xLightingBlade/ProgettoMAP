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
import java.io.Serializable;
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
public class Avventura extends StrutturaGioco implements Serializable {
    private static final long serialVersionUID = -4185062833257302102L;
    
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
            EsecuzioneComandi esec = new EsecuzioneComandi(this);
            this.haAccessoAllaStanza = false;
            this.assenzaStanza = false;
            Stanza stanzacorrente = getStanzaCorrente();
            List<Oggetto> inventarioGiocatore = getInventario();
            List<Stanza> stanze = getStanze();
            Oggetto oggetto = p.getOggetto();
            Oggetto oggettoInventario = p.getOggettoInventario();
            TipoComando comando = p.getComando().getTipo();
            
            switch(comando){
                case NORD -> esec.checkNordAccess(stanzacorrente,inventarioGiocatore);
                case SUD -> esec.checkSudAccess( stanzacorrente,inventarioGiocatore);
                case EST -> esec.checkEstAccess(stanzacorrente, inventarioGiocatore);
                case OVEST -> esec.checkWestAccess(stanzacorrente,inventarioGiocatore);
                case INVENTARIO -> esec.printInventarioContent(inventarioGiocatore);
                case GUARDA -> esec.printOsservazione(stanzacorrente);
                case PRENDI -> esec.prendiOggetto( oggetto,inventarioGiocatore, stanzacorrente);
                case APRI -> esec.apriOggetto(oggetto, oggettoInventario, stanzacorrente, inventarioGiocatore );
                case SPINGI -> {
                    if(oggetto!= null){
                        esec.spingiOggetto(oggetto, inventarioGiocatore, stanzacorrente);
                    }else{
                        esec.spingiOggetto(oggettoInventario, inventarioGiocatore, stanzacorrente);
                    }
                }
                case LEGGI -> {
                    if(oggetto!= null){
                        esec.leggiOggetto(oggetto);
                    }else{
                        esec.leggiOggetto(oggettoInventario);
                    }
                }
                case FINE -> esec.chiudiPartita();
                
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
}
