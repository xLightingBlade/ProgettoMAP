/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gioco;

import com.mycompany.avventura.StrutturaGioco;
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
        //Comandi
        Comando nord = new Comando(TipoComando.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getComandi().add(nord);
        Comando inventario = new Comando(TipoComando.INVENTARIO, "inventario");
        inventario.setAlias(new String[]{"inv"});
        getComandi().add(inventario);
        Comando sud = new Comando(TipoComando.SUD, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getComandi().add(sud);
        Comando est = new Comando(TipoComando.EST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getComandi().add(est);
        Comando ovest = new Comando(TipoComando.OVEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getComandi().add(ovest);
        Comando fine = new Comando(TipoComando.FINE, "end");
        fine.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getComandi().add(fine);
        Comando osserva = new Comando(TipoComando.GUARDA, "osserva");
        osserva.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getComandi().add(osserva);
        Comando prendi = new Comando(TipoComando.PRENDI, "raccogli");
        prendi.setAlias(new String[]{"prendi"});
        getComandi().add(prendi);
        Comando apri = new Comando(TipoComando.APRI, "apri");
        apri.setAlias(new String[]{});
        getComandi().add(apri);
        Comando spingi = new Comando(TipoComando.SPINGI, "premi");
        spingi.setAlias(new String[]{"spingi", "attiva"});
        getComandi().add(spingi);
        
        //Stanze
        Stanza soggiornoCasa = new Stanza(0, "Soggiorno", "Il soggiorno della casa di Joel. Uno dei pochi posti ancora 'sicuri'");
        soggiornoCasa.setOsservazione("Nel tavolo del soggiorno puoi vedere, come buttati a caso lì sopra, una pistola, un coltello, "
                + "una bottiglia di vetro e delle scatolette di cibo \n"
                + "Ad est c'è la porta del bagno, a sud quella del ripostiglio. A nord c'è la porta principale." );
        Stanza bagnoCasa = new Stanza(1, "Bagno", "Il bagno. Non in gran condizioni, ma potrebbe esserci qualcosa di utile");
        bagnoCasa.setOsservazione("\nSotto lo specchio sporco c'è un mobiletto."
                + "\n Andando ad ovest torneresti nel soggiorno.");
        Stanza ripostiglioCasa = new Stanza(2, "Ripostiglio", "Un ripostiglio impolverato.");
        ripostiglioCasa.setOsservazione("\nCi sono due scaffali, sopra di essi dei pacchetti di munizioni per la pistola"
                + "e una confezione di batterie. \n Andando a nord torneresti in soggiorno");
        
        //Mappa
        soggiornoCasa.setEst(bagnoCasa);
        soggiornoCasa.setSud(ripostiglioCasa);
        bagnoCasa.setOvest(soggiornoCasa);
        ripostiglioCasa.setNord(soggiornoCasa);
        getStanze().add(soggiornoCasa);
        getStanze().add(bagnoCasa);
        getStanze().add(ripostiglioCasa);
        
        //Oggetti
        Oggetto pistola = new Oggetto(0, "pistola", "Una pistola 9mm");
        pistola.setAlias(new String[] {"arma"});
        soggiornoCasa.getOggetti().add(pistola);
        Oggetto coltello = new Oggetto(1, "coltello", "Un coltello da caccia");
        coltello.setAlias(new String[]{"lama"});
        soggiornoCasa.getOggetti().add(coltello);
        Oggetto bottigliaVuota = new Oggetto(2, "bottiglia", "Una bottiglia di vetro vuota");
        soggiornoCasa.getOggetti().add(bottigliaVuota);
        Oggetto scatolettaCibo = new Oggetto(3, "cibo", "Una scatoletta di cibo, ancora buono(forse)");
        scatolettaCibo.setAlias(new String[] {"scatoletta", "lattina"});
        soggiornoCasa.getOggetti().add(scatolettaCibo);
        ContenitoreOggetti mobileBagno = new ContenitoreOggetti(4, "mobile", "Un mobiletto da bagno. Chissà cosa c'è dentro..");
        mobileBagno.setAlias(new String[]{"mobiletto"});
        mobileBagno.setApribile(true);
        mobileBagno.setPrendibile(false);
        mobileBagno.setAperto(false);
        bagnoCasa.getOggetti().add(mobileBagno);
        Oggetto garza = new Oggetto(5, "garza", "Una garza sterile(piu o meno)");
        Oggetto alcol = new Oggetto(6, "alcol", "Una bottiglia di alcol etilico");  
        Oggetto forbici = new Oggetto(7, "forbici", "Un paio di forbici dalla punta decisamente non arrotondata");  
        mobileBagno.add(garza);
        mobileBagno.add(alcol);
        mobileBagno.add(forbici);
        Oggetto munizioni = new Oggetto(8, "munizioni", "Un pacco di munizioni 9mm per la pistola. Io le prenderei..");
        munizioni.setAlias(new String[]{"colpi", "pacco", "pacchetto"});
        ripostiglioCasa.getOggetti().add(munizioni);
        Oggetto batterie = new Oggetto(9, "batterie", "Un pacco di batterie, forse per una torcia");
        batterie.setAlias(new String[]{"batteria"});
        ripostiglioCasa.getOggetti().add(batterie);
        
        //Stanza attuale
        setStanzaCorrente(soggiornoCasa);
    }

    @Override
    public void prossimaMossa(ParserOutput p, PrintStream out) {
        if (p.getComando() == null) {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } else {
            //move
            boolean nienteStanza = false;
            boolean movimento = false;
            if (p.getComando().getTipo() == TipoComando.NORD) {
                if (getStanzaCorrente().getNord() != null) {
                 setStanzaCorrente(getStanzaCorrente().getNord());
                    movimento = true;
                } else {
                    nienteStanza = true;
                }
            } else if (p.getComando().getTipo() == TipoComando.SUD) {
                if (getStanzaCorrente().getSud() != null) {
           setStanzaCorrente(getStanzaCorrente().getSud());
                    movimento = true;
                } else {
                    nienteStanza = true;
                }
            } else if (p.getComando().getTipo() == TipoComando.EST) {
                if (getStanzaCorrente().getEst() != null) {
     setStanzaCorrente(getStanzaCorrente().getEst());
                    movimento = true;
                } else {
                    nienteStanza = true;
                }
            } else if (p.getComando().getTipo() == TipoComando.OVEST) {
                if (getStanzaCorrente().getOvest() != null) {setStanzaCorrente(getStanzaCorrente().getOvest());
                    movimento = true;
                } else {
                    nienteStanza = true;
                }
            } else if (p.getComando().getTipo() == TipoComando.INVENTARIO) {
                out.println("Nel tuo inventario ci sono:");
                for (Oggetto o : getInventario()) {
                    out.println(o.getNome() + ": " + o.getDescrizione());
                }
            } else if (p.getComando().getTipo() == TipoComando.GUARDA) {
                if (getStanzaCorrente().getOsservazione() != null) {
                    out.println(getStanzaCorrente().getOsservazione());
                } else {
                    out.println("Non c'è niente di interessante qui.");
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
                    out.println("Non c'è niente da raccogliere qui.");
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
            }
            if (nienteStanza) {
                out.println("Da quella parte non si può andare c'è un muro!\nNon hai ancora acquisito i poteri per oltrepassare i muri...");
            } else if (movimento) {
                out.println(getStanzaCorrente().getNome());
                out.println("================================================");
                out.println(getStanzaCorrente().getDescrizione());
            }
        }
    }
}
