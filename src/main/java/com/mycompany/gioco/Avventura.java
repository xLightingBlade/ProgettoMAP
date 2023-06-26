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
    
    @Override
    public void init() throws Exception {
        OperazioniDatabase.connettiDatabase();
        OperazioniDatabase.resetDatabase();
        OperazioniDatabase.creaTabelle();
        OperazioniDatabase.caricaDati();
        OperazioniDatabase.creaStanze();
        
        
        setComandi(OperazioniDatabase.creaComandi());
        setStanze(OperazioniDatabase.creaOggetti());

        /*
        Questa parte credo possa essere eliminata in modo sicuro, ma la tengo commentata come misura di backup
        Oggetto pistola = new Oggetto(0, "pistola", "Una pistola 9mm");
        pistola.setAlias(new String[]{"arma"});
        soggiornoCasa.getOggetti().add(pistola);
        Oggetto coltello = new Oggetto(1, "coltello", "Un coltello da caccia");
        coltello.setAlias(new String[]{"lama"});
        soggiornoCasa.getOggetti().add(coltello);
        Oggetto bottigliaVuota = new Oggetto(2, "bottiglia", "Una bottiglia di vetro vuota");
        bottigliaVuota.setAlias(new String[]{});
        soggiornoCasa.getOggetti().add(bottigliaVuota);
        Oggetto scatolettaCibo = new Oggetto(3, "cibo", "Una scatoletta di cibo, ancora buono(forse)");
        scatolettaCibo.setAlias(new String[]{"scatoletta", "lattina"});
        soggiornoCasa.getOggetti().add(scatolettaCibo);
        Oggetto foto = new Oggetto(4, "foto", "Una foto di te con tua figlia. Un ricordo di ciò che non c'è più");
        foto.setAlias(new String[]{"immagine"});
        soggiornoCasa.getOggetti().add(foto);
        ContenitoreOggetti mobileBagno = new ContenitoreOggetti(5, "mobile", "Un mobiletto da bagno. Chissà cosa c'è dentro..");
        mobileBagno.setAlias(new String[]{"mobiletto"});
        mobileBagno.setApribile(true);
        mobileBagno.setPrendibile(false);
        mobileBagno.setAperto(false);
        bagnoCasa.getOggetti().add(mobileBagno);
        Oggetto garza = new Oggetto(6, "garza", "Una garza sterile(piu o meno)");
        garza.setAlias(new String[]{"garze"});        
        Oggetto alcol = new Oggetto(7, "alcol", "Una bottiglia di alcol etilico");
        alcol.setAlias(new String[]{"alcol etilico", "etilico", "alcool"});
        Oggetto forbici = new Oggetto(8, "forbici", "Un paio di forbici dalla punta decisamente non arrotondata");
        forbici.setAlias(new String[]{"forbice"});
        mobileBagno.add(garza);
        mobileBagno.add(alcol);
        mobileBagno.add(forbici);
        Oggetto munizioni = new Oggetto(9, "munizioni", "Un pacco di munizioni 9mm per la pistola. Io le prenderei..");
        munizioni.setAlias(new String[]{"colpi", "pacco", "pacchetto"});
        ripostiglioCasa.getOggetti().add(munizioni);
        Oggetto batterie = new Oggetto(10, "batterie", "Un pacco di batterie, forse per una torcia");
        batterie.setAlias(new String[]{"batteria"});
        ripostiglioCasa.getOggetti().add(batterie);
        Oggetto roccia = new Oggetto(11, "roccia", "Una grande roccia, più grande di te");
        roccia.setAlias(new String[]{"masso"});
        roccia.setPrendibile(false);
        corridoioPassaggio.getOggetti().add(roccia);
        Oggetto tastierino = new Oggetto(12, "tastierino", "Il tastierino numerico per aprire il cancello");
        tastierino.setAlias(new String[]{});        
        tastierino.setPrendibile(false);
        cancello.getOggetti().add(tastierino);
        Oggetto foglioQuadro = new Oggetto(13, "fogliettoQuadroElettrico", "Un foglio con sopra un enigma riguardante un codice");
        foglioQuadro.setLeggibile(true);
        foglioQuadro.setAlias(new String[]{"foglio enigma", "foglietto", "enigma"});
        foglioQuadro.setContenuto("CONTENUTO FOGLIO ENIGMA");
        stanzaQuadroElettrico.getOggetti().add(foglioQuadro);
        Oggetto levaCorrente = new Oggetto(14, "leva", "Una leva, forse per riattivare il quadro elettrico");
        levaCorrente.setAlias(new String[]{});
        levaCorrente.setPrendibile(false);
        stanzaQuadroElettrico.getOggetti().add(levaCorrente);
        Oggetto torcia = new Oggetto(15, "torcia", "Una torcia, tornerà utile prima o poi");
        torcia.setAlias(new String[]{});
        ingressoMetro.getOggetti().add(torcia);
        Oggetto documentoMetro = new Oggetto(16, "documentoMetro", "Un documento");
        documentoMetro.setAlias(new String[]{"documento", "documento metro", "lettera"});
        documentoMetro.setLeggibile(true);
        documentoMetro.setContenuto("CONTENUTO DOCUMENTO METROPOLITANA");
        binariMetro.getOggetti().add(documentoMetro);
        Oggetto zattera = new Oggetto(17, "zattera", "Assi di legno a mo' di zattera. Abbastanza da reggere una ragazzina");
        zattera.setAlias(new String[]{"legno"});
        stanzaZattera.getOggetti().add(zattera);
        Oggetto chiaveArmadietto = new Oggetto(18, "chiaveArmadietto", "Una chiave, non sai bene cosa apre");
        chiaveArmadietto.setAlias(new String[]{"chiave", "chiave armadietto"});
        dentroOspedale.getOggetti().add(chiaveArmadietto);
        ContenitoreOggetti armadietto = new ContenitoreOggetti(19, "armadietto", "Un armadietto chiuso a chiave");
        armadietto.setAlias(new String[]{});
        armadietto.setApribile(false);
        armadietto.setPrendibile(false);
        armadietto.setAperto(false);
        armadietto.add(forbici);
        Oggetto tesserino = new Oggetto(20, "tesserino", "Un tesserino con scritto 'Infermeria'");
        tesserino.setAlias(new String[]{"tessera"});
        armadietto.add(tesserino);
        magazzino.getOggetti().add(armadietto);
        infermeria.getOggetti().add(alcol);
        infermeria.getOggetti().add(garza);
        Oggetto cacciavite = new Oggetto(21, "cacciavite", "Un cacciavite");
        cacciavite.setAlias(new String[]{});
        stanzaCacciavite.getOggetti().add(cacciavite);
        Oggetto documentoMedico = new Oggetto(22, "documentoMedico", "Un documento medico");
        documentoMedico.setAlias(new String[]{"referto", "documento medico"});
        documentoMedico.setLeggibile(true);
        documentoMedico.setContenuto("CONTENUTO DOCUMENTO MEDICO OSPEDALE");
        pianoSalaOperatoria.getOggetti().add(documentoMedico);
        Oggetto grata = new Oggetto(23, "grata", "Una grata, chiusa con delle viti");
        grata.setAlias(new String[]{});
        grata.setPrendibile(false);
        grata.setSpingibile(false);
        grata.setApribile(false);
        condotto.getOggetti().add(grata);
        */
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

            //se vado a nord
            if (p.getComando().getTipo() == TipoComando.NORD) //se vado a nord
            {
                //setta la nuova stanzaCorrente a quella a nord della stanza corrente attuale
                if (getStanzaCorrente().getNord() != null) {
                    setStanzaCorrente(getStanzaCorrente().getNord());
                    movimento = true;
                } else {
                    nienteStanza = true;
                }
            } else if (p.getComando().getTipo() == TipoComando.SUD) //se vado a sud
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
                    out.println(o.getNome() + ": " + o.getDescrizione());
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
                        out.println("Hai raccolto: " + p.getOggetto().getDescrizione());
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
