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
    public void prossimaMossa(ParserOutput p, PrintStream out) {
        if (p.getComando() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean nienteStanza = false;
            boolean movimento = false;
            Stanza stanzacorrente = getStanzaCorrente();
            List<Oggetto> inventarioGiocatore = getInventario();
            
            //se vado a nord
            if (p.getComando().getTipo() == TipoComando.NORD) //se vado a nord
            {
                if (getStanzaCorrente().getNord() != null) {
                    if(controller.checkAccessoStanza(getStanze().get(stanzacorrente.getNord().getId()), inventarioGiocatore)){
                        //setta la nuova stanzaCorrente a quella a nord della stanza corrente attuale
                            setStanzaCorrente(getStanzaCorrente().getNord());
                            movimento = true;
                    }else{
                        out.println("Non puoi accedere alla stanza.");
                    }    
                } else {
                   nienteStanza = true;
                }
            }
            else if (p.getComando().getTipo() == TipoComando.SUD) //se vado a sud
            {
                //setta la nuova stanzaCorrente a quella a sud della stanza corrente attuale
                if (getStanzaCorrente().getSud() != null) {
                    setStanzaCorrente(getStanzaCorrente().getSud());
                    movimento = true;
                } else {
                    nienteStanza = true;
                }
            } else if (p.getComando().getTipo() == TipoComando.EST) //se vado ad est
            {
                //setta la nuova stanzaCorrente a quella a est della stanza corrente attuale
                if (getStanzaCorrente().getEst() != null) {
                    setStanzaCorrente(getStanzaCorrente().getEst());
                    movimento = true;
                } else {
                    nienteStanza = true;
                }
            } else if (p.getComando().getTipo() == TipoComando.OVEST) //se vado ad ovest
            {
                //setta la nuova stanzaCorrente a quella ad ovest della stanza corrente attuale
                if (getStanzaCorrente().getOvest() != null) {
                    setStanzaCorrente(getStanzaCorrente().getOvest());
                    movimento = true;
                } else {
                    nienteStanza = true;
                }
            } //Motra inventario
            else if (p.getComando().getTipo() == TipoComando.INVENTARIO) {
                out.println("Nel tuo inventario ci sono:");
                for (Oggetto o : getInventario()) {
                    if(!o.isInvisibile()) {
                        out.println(o.getNome() + ": " + o.getDescrizione());
                    }
                }
                
            } else if (p.getComando().getTipo() == TipoComando.GUARDA) {
                if (getStanzaCorrente().getOsservazione() != null) {
                    out.println(getStanzaCorrente().getOsservazione());
                } else {
                    out.println("Non c'è niente di interessante da osserva qui.");
                }
            } else if (p.getComando().getTipo() == TipoComando.PRENDI) {
                if (p.getOggetto() != null) {
                    if (p.getOggetto().isPrendibile()) {
                        getInventario().add(p.getOggetto());
                        getStanzaCorrente().getOggetti().remove(p.getOggetto());
                        if (!p.getOggetto().isInvisibile()) {
                            out.println("Hai raccolto: " + p.getOggetto().getDescrizione());
                        }
                    } else {
                        out.println("Non puoi raccogliere questo oggetto.");
                    }
                } else {
                    out.println("\nQuesto oggetto non è presente in questa stanza,\no forse non c'è niente da raccogliere qui.");
                }
            } else if (p.getComando().getTipo() == TipoComando.APRI) {
                /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                * Potrebbe non esssere la soluzione ottimale.
                 */
                if (p.getOggetto() == null && p.getOggettoInventario() == null) {
                    out.println("Non c'è niente da aprire qui.");
                } else {
                    if (p.getOggetto() != null) {
                        if (p.getOggetto().isApribile() && p.getOggetto().isAperto() == false) {
                            if (p.getOggetto() instanceof ContenitoreOggetti) {
                                out.println("Hai aperto: " + p.getOggetto().getNome());
                                ContenitoreOggetti c = (ContenitoreOggetti) p.getOggetto();
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getNome() + " contiene:");
                                    Iterator<Oggetto> it = c.getList().iterator();
                                    
                                    while (it.hasNext()) {
                                        Oggetto next = it.next();
                                        getStanzaCorrente().getOggetti().add(next);
                                        out.print(" " + next.getNome());
                                        it.remove();
                                    }
                                    out.println();
                                }
                                p.getOggetto().setAperto(true);
                            } else {
                                out.println("Hai aperto: " + p.getOggetto().getNome());
                                p.getOggetto().setAperto(true);
                            }
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                    
                    if (p.getOggettoInventario() != null) {
                        if (p.getOggettoInventario().isApribile() && p.getOggettoInventario().isAperto() == false) {
                            if (p.getOggettoInventario() instanceof ContenitoreOggetti) {
                                ContenitoreOggetti c = (ContenitoreOggetti) p.getOggettoInventario();
                                
                                if (!c.getList().isEmpty()) {
                                    out.print(c.getNome() + " contiene:");
                                    Iterator<Oggetto> it = c.getList().iterator();
                                    
                                    while (it.hasNext()) {
                                        Oggetto next = it.next();
                                        getInventario().add(next);
                                        out.print(" " + next.getNome());
                                        it.remove();
                                    }
                                    out.println();
                                }
                                
                                p.getOggettoInventario().setAperto(true);
                            } else {
                                p.getOggettoInventario().setAperto(true);
                            }
                            
                            out.println("Hai aperto nel tuo inventario: " + p.getOggettoInventario().getNome());
                        } else {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } else if (p.getComando().getTipo() == TipoComando.SPINGI) {
                //ricerca oggetti pushabili
                if (p.getOggetto() != null && p.getOggetto().isSpingibile()) {
                    out.println("Hai premuto: " + p.getOggetto().getNome());
                    //Creare metodo più generico qui.
                    //questo mi serve solo per ''vedere'' se ho ''premuto'' la leva(in realtà me la metto nell'inventario, invisibile
                    if (p.getOggetto().getId() == 14) {
                        getInventario().add(p.getOggetto());
                        getStanzaCorrente().getOggetti().remove(p.getOggetto());
                    }
                } else if (p.getOggettoInventario() != null && p.getOggettoInventario().isSpingibile()) {
                    out.println("Hai premuto: " + p.getOggettoInventario().getNome());
                } else {
                    out.println("Non ci sono oggetti che puoi premere qui.");
                }
            } else if (p.getComando().getTipo() == TipoComando.LEGGI) {
                if (p.getOggetto() != null && p.getOggetto().isLeggibile()) {
                    out.print(p.getOggetto().getContenuto());
                } else if (p.getOggettoInventario() != null && p.getOggettoInventario().isLeggibile()) {
                    out.print(p.getOggettoInventario().getContenuto());
                } else {
                    out.println("Non ci sono oggetti che puoi leggere qui.");
                }
            }
            
            if (nienteStanza) {
                out.println("Da quella parte non si può andare c'è un muro!\n");
            } else if (movimento) {
                out.println(getStanzaCorrente().getNome());
                out.println("================================================");
                out.println(getStanzaCorrente().getDescrizione());
            }
        }
    }
}
