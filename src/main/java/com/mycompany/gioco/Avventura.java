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
public class Avventura extends StrutturaGioco 
{

    @Override
    public void init() throws Exception 
    {
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
        Comando leggi = new Comando(TipoComando.LEGGI, "leggi");
        leggi.setAlias(new String[]{"sfoglia"});
        getComandi().add(leggi);
        
        //Stanze
        Stanza soggiornoCasa = new Stanza(0, "Soggiorno", "Il soggiorno della casa di Joel. Uno dei pochi posti ancora 'sicuri'");
        soggiornoCasa.setOsservazione("Nel tavolo del soggiorno puoi vedere, come buttati a caso lì sopra, una pistola, un coltello, "
                + "una bottiglia di vetro e delle scatolette di cibo \n"
                + "Inoltre, non puoi fare a meno di notare una foto appoggiata su un mobile vicino\n"
                + "Ad est c'è la porta del bagno, a sud quella del ripostiglio. A nord c'è la porta principale." );
        
        Stanza bagnoCasa = new Stanza(1, "Bagno", "Il bagno. Non in gran condizioni, ma potrebbe esserci qualcosa di utile");
        bagnoCasa.setOsservazione("\nSotto lo specchio sporco c'è un mobiletto."
                + "\n Andando ad ovest torneresti nel soggiorno.");
        
        Stanza ripostiglioCasa = new Stanza(2, "Ripostiglio", "Un ripostiglio impolverato.");
        ripostiglioCasa.setOsservazione("\nCi sono due scaffali, sopra di essi dei pacchetti di munizioni per la pistola"
                + "e una confezione di batterie. \n Andando a nord torneresti in soggiorno");
        
        Stanza corridoioPassaggio = new Stanza(3, "Corridoio", "Un corridodio del passaggio segreto");
        corridoioPassaggio.setOsservazione("Noti che nel corridoio c'è una guarda che pattuglia la zona."
                + "Attorno a te c'è convenientemente un grande masso dove nascondersi."
                + "\nNoti dritto davanti a te, alla fine del corridoio, un cancello");
        
        Stanza cancello = new Stanza(4, "Cancello", "La stanza del cancello, l'uscita dalla ZQ");
        cancello.setOsservazione("Affianco al cancello c'è un tastierino numerico, sembra avrai bisogno di un qualche codice."
                + "\nGuardando ad est vedi una porta aperta verso una stanza."
                + "\nGuardandoti dietro vedi il corridoio");
        
        Stanza stanzaQuadroElettrico = new Stanza(5, "QuadroElettrico", "Una stanza con un quadro elettrico ed una leva abbassata");
        stanzaQuadroElettrico.setOsservazione("Osservi che forse qua potresti far tornare la corrente al cancello."
                + "\nMa non è tutto, perchè guardando bene noti che attaccato alla parete superiore del quadro, quasi nascosto, c'è un"
                + "foglietto con una scritta."
                + "\nAndando ad ovest ritorni nel corridoio");
        
        Stanza ingressoMetro = new Stanza(6, "IngressoMetro", "L'ingresso della metropolitana");
        ingressoMetro.setOsservazione("Noti il corpo esanime di una guardia, ormai consumato dal tempo. Sembra avere qualcosa addosso.."
                + "\nGuardando bene, noti che addosso ha una torcia con delle pile."
                + "\nAndando avanti a nord si scende giù");
        
        Stanza binariMetro = new Stanza(7, "BinariMetro", "I binari della metropolitana, c'è un problema però...");
        binariMetro.setOsservazione("La metropolitana è completamente allagata e l'acqua ti arriva ad altezza petto."
                + "\nAd ovest sembra esserci una stanzetta");
        
        Stanza stanzaZattera = new Stanza(8, "stanzaZattera", "Uno stanzino della metropolitana, completamente buio");
        stanzaZattera.setVisibile(false); 
        stanzaZattera.setOsservazione("Non si vede niente!");
        
        Stanza ingressoOspedale = new Stanza(9, "ingressoOspedale", "L'ingresso del Saint Mary's Hospital, QG delle Luci");
        //arrivato all'ingresso non puoi osservare, avviene direttamente una scena e ti portano dentro
        ingressoOspedale.setOsservazione("");
        
        Stanza dentroOspedale = new Stanza(10, "dentroOspedale", "Una stanza dentro l'ospedale..");
        //suppongo avvenga prima tutta la scena prima di poter osservare qualcosa
        dentroOspedale.setOsservazione("Il corpo morto di Marlene giace per terra. Nello scontro ha lasciato cadere una chiave."
                + "\nSia ad est che ad ovest ci sono delle stanze");
        
        Stanza magazzino = new Stanza(11, "Magazzino", "Una stanza usata come magazzino.");
        magazzino.setOsservazione("Noti un armadietto chiuso appoggiato ad una parete"
                + "\nAndando ad est torneresti nella stanza di prima");
        
        Stanza infermeria = new Stanza(12, "Infermeria", "Un'infermeria un po' spoglia");
        infermeria.setOsservazione("Ci sono due tavoli e uno scaffale, ma sono praticamente vuoti."
                + "\nCi trovi solamente una bottiglia d'alcol e delle garze"
                + "\nAndando ad ovest torneresti nella stanza di prima");
        
        Stanza pianoSalaOperatoria = new Stanza(13, "pianoSala", "Il piano dell'ospedale dove c'è la sala operatoria");
        pianoSalaOperatoria.setOsservazione("Davanti a te c'è un corridoio con una guardia ben armata, non c'è modo di attraversarlo senza farsi vedere."
                + "\nPerò ad est c'è qualcosa di interessante, mentre ad ovest una stanzina aperta."
                + "\nInoltre, per terra noti un documento medico");
        
        Stanza condotto = new Stanza(14, "condotto", "C'è quella che sembra una grata di un condotto dell'aria molto largo");
        condotto.setOsservazione("Ma la grata è fermamente salda, ci sono delle viti."
                + "\nAndando ad ovest torneresti nella stanza precedente");
        
        Stanza stanzaCacciavite = new Stanza(15, "stanzaCacciavite", "Uno stanzino buio");
        stanzaCacciavite.setVisibile(false);
        stanzaCacciavite.setOsservazione("Non si vede niente");
        
        Stanza salaOperatoria = new Stanza(16, "salaOperatoria", "La sala operatoria, c'è un tavolo operatorio e dei dottori al lavoro");
        salaOperatoria.setOsservazione("Guardi bene il tavolo e.... è Ellie!");
        
        //Mappa
        /*Alcune stanze non hanno settato i collegamenti alle stanze successive.
        *Questo perchè in alcune stanze bisogna sbloccarne l'entrata/uscita
        */
        soggiornoCasa.setEst(bagnoCasa);
        soggiornoCasa.setSud(ripostiglioCasa);
        bagnoCasa.setOvest(soggiornoCasa);
        ripostiglioCasa.setNord(soggiornoCasa);
        soggiornoCasa.setNord(corridoioPassaggio);
        corridoioPassaggio.setNord(cancello);
        cancello.setSud(corridoioPassaggio);
        cancello.setEst(stanzaQuadroElettrico);
        stanzaQuadroElettrico.setOvest(cancello);
        ingressoMetro.setNord(binariMetro);
        binariMetro.setSud(ingressoMetro);
        binariMetro.setOvest(stanzaZattera);
        stanzaZattera.setEst(binariMetro);
        dentroOspedale.setOvest(magazzino);
        magazzino.setEst(dentroOspedale);
        infermeria.setOvest(dentroOspedale);
        pianoSalaOperatoria.setEst(condotto);
        condotto.setOvest(pianoSalaOperatoria);
        pianoSalaOperatoria.setOvest(stanzaCacciavite);
        stanzaCacciavite.setEst(pianoSalaOperatoria);
        getStanze().add(soggiornoCasa);
        getStanze().add(bagnoCasa);
        getStanze().add(ripostiglioCasa);
        getStanze().add(corridoioPassaggio);
        getStanze().add(cancello);
        getStanze().add(stanzaQuadroElettrico);
        getStanze().add(ingressoMetro);
        getStanze().add(binariMetro);
        getStanze().add(stanzaZattera);
        getStanze().add(ingressoOspedale);
        getStanze().add(dentroOspedale);
        getStanze().add(magazzino);
        getStanze().add(infermeria);
        getStanze().add(pianoSalaOperatoria);
        getStanze().add(condotto);
        getStanze().add(stanzaCacciavite);
        getStanze().add(salaOperatoria);  
        
        //Oggetti
        //aggiungere un comando per leggere i documenti e un attributo a tali documenti dove metterne il contenuto
        Oggetto pistola = new Oggetto(0, "pistola", "Una pistola 9mm");
        pistola.setAlias(new String[] {"arma"});
        soggiornoCasa.getOggetti().add(pistola);
        Oggetto coltello = new Oggetto(1, "coltello", "Un coltello da caccia");
        coltello.setAlias(new String[]{"lama"});
        soggiornoCasa.getOggetti().add(coltello);
        Oggetto bottigliaVuota = new Oggetto(2, "bottiglia", "Una bottiglia di vetro vuota");
        bottigliaVuota.setAlias(new String[]{});
        soggiornoCasa.getOggetti().add(bottigliaVuota);
        Oggetto scatolettaCibo = new Oggetto(3, "cibo", "Una scatoletta di cibo, ancora buono(forse)");
        scatolettaCibo.setAlias(new String[] {"scatoletta", "lattina"});
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
        
        //Stanza attuale
        setStanzaCorrente(soggiornoCasa);
    }

    @Override
    //Aggiungere comando per leggere
    //In questo metodo è racchiuso il cuore del gioco e il suo avanzare.
    public void prossimaMossa(ParserOutput p, PrintStream out) 
    {
        if (p.getComando() == null) 
        {
            out.println("Non ho capito cosa devo fare! Prova con un altro comando.");
        } 
        else 
        {
            //move
            boolean nienteStanza = false;
            boolean movimento = false;
            
            if (p.getComando().getTipo() == TipoComando.NORD) 
            {
                if (getStanzaCorrente().getNord() != null)
                {
                 setStanzaCorrente(getStanzaCorrente().getNord());
                    movimento = true;
                }
                else
                {
                    nienteStanza = true;
                }
            }
            else if (p.getComando().getTipo() == TipoComando.SUD) 
            {
                if (getStanzaCorrente().getSud() != null) 
                {
                    setStanzaCorrente(getStanzaCorrente().getSud());
                    movimento = true;
                }
                else 
                {
                    nienteStanza = true;
                }
            } 
            else if (p.getComando().getTipo() == TipoComando.EST) 
            {
                if (getStanzaCorrente().getEst() != null) 
                {
                    setStanzaCorrente(getStanzaCorrente().getEst());
                    movimento = true;
                } 
                else 
                {
                    nienteStanza = true;
                }
            } 
            else if (p.getComando().getTipo() == TipoComando.OVEST) 
            {
                if (getStanzaCorrente().getOvest() != null) {setStanzaCorrente(getStanzaCorrente().getOvest());
                    movimento = true;
                } 
                else 
                {
                    nienteStanza = true;
                }
            } 
            else if (p.getComando().getTipo() == TipoComando.INVENTARIO) 
            {
                out.println("Nel tuo inventario ci sono:");
                
                for (Oggetto o : getInventario()) 
                {
                    out.println(o.getNome() + ": " + o.getDescrizione());
                }
                
            } 
            else if (p.getComando().getTipo() == TipoComando.GUARDA) 
            {
                if (getStanzaCorrente().getOsservazione() != null) {
                    out.println(getStanzaCorrente().getOsservazione());
                } 
                else 
                {
                    out.println("Non c'è niente di interessante qui.");
                }
            } 
            else if (p.getComando().getTipo() == TipoComando.PRENDI) 
            {
                if (p.getOggetto() != null) 
                {
                    if (p.getOggetto().isPrendibile()) 
                    {
                        getInventario().add(p.getOggetto());
                        getStanzaCorrente().getOggetti().remove(p.getOggetto());
                        out.println("Hai raccolto: " + p.getOggetto().getDescrizione());
                    } 
                    else 
                    {
                        out.println("Non puoi raccogliere questo oggetto.");
                    }
                } 
                else 
                {
                    out.println("Non c'è niente da raccogliere qui.");
                }
            } 
            else if (p.getComando().getTipo() == TipoComando.APRI) 
            {
                /*ATTENZIONE: quando un oggetto contenitore viene aperto, tutti gli oggetti contenuti
                * vengongo inseriti nella stanza o nell'inventario a seconda di dove si trova l'oggetto contenitore.
                * Potrebbe non esssere la soluzione ottimale.
                 */
                if (p.getOggetto() == null && p.getOggettoInventario() == null) 
                {
                    out.println("Non c'è niente da aprire qui.");
                } 
                else 
                {
                    if (p.getOggetto() != null) 
                    {
                        if (p.getOggetto().isApribile() && p.getOggetto().isAperto() == false) 
                        {
                            if (p.getOggetto() instanceof ContenitoreOggetti) 
                            {
                                out.println("Hai aperto: " + p.getOggetto().getNome());
                                ContenitoreOggetti c = (ContenitoreOggetti) p.getOggetto();
                                if (!c.getList().isEmpty()) 
                                {
                                    out.print(c.getNome() + " contiene:");
                                    Iterator<Oggetto> it = c.getList().iterator();
                                    
                                    while (it.hasNext()) 
                                    {
                                        Oggetto next = it.next();
                                        getStanzaCorrente().getOggetti().add(next);
                                        out.print(" " + next.getNome());
                                        it.remove();
                                    }
                                    out.println();
                                }
                                p.getOggetto().setAperto(true);
                            } 
                            else 
                            {
                                out.println("Hai aperto: " + p.getOggetto().getNome());
                                p.getOggetto().setAperto(true);
                            }
                        } 
                        else 
                        {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                    
                    if (p.getOggettoInventario() != null) 
                    {
                        if (p.getOggettoInventario().isApribile() && p.getOggettoInventario().isAperto() == false) 
                        {
                            if (p.getOggettoInventario() instanceof ContenitoreOggetti) 
                            {
                                ContenitoreOggetti c = (ContenitoreOggetti) p.getOggettoInventario();
                                
                                if (!c.getList().isEmpty()) 
                                {
                                    out.print(c.getNome() + " contiene:");
                                    Iterator<Oggetto> it = c.getList().iterator();
                                    
                                    while (it.hasNext()) 
                                    {
                                        Oggetto next = it.next();
                                        getInventario().add(next);
                                        out.print(" " + next.getNome());
                                        it.remove();
                                    }
                                    out.println();
                                }
                                
                                p.getOggettoInventario().setAperto(true);
                            } 
                            else 
                            {
                                p.getOggettoInventario().setAperto(true);
                            }
                            
                            out.println("Hai aperto nel tuo inventario: " + p.getOggettoInventario().getNome());
                        }
                        else 
                        {
                            out.println("Non puoi aprire questo oggetto.");
                        }
                    }
                }
            } 
            else if (p.getComando().getTipo() == TipoComando.SPINGI) 
            {
                //ricerca oggetti pushabili
                if (p.getOggetto() != null && p.getOggetto().isSpingibile()) 
                {
                    out.println("Hai premuto: " + p.getOggetto().getNome());
                } 
                else if (p.getOggettoInventario() != null && p.getOggettoInventario().isSpingibile()) 
                {
                    out.println("Hai premuto: " + p.getOggettoInventario().getNome());
                } 
                else 
                {
                    out.println("Non ci sono oggetti che puoi premere qui.");
                }
            } 
            else if (p.getComando().getTipo() == TipoComando.LEGGI) 
            {
                if (p.getOggetto() != null && p.getOggetto().isLeggibile()) 
                {
                    out.print(p.getOggetto().getContenuto());
                } 
                else if (p.getOggettoInventario() != null && p.getOggettoInventario().isLeggibile()) 
                {
                    out.print(p.getOggetto().getContenuto());
                } else 
                {
                    out.println("Non ci sono oggetti che puoi leggere qui.");
                }
            }
            
            if (nienteStanza) 
            {
                out.println("Da quella parte non si può andare c'è un muro!\n");
            } 
            else if (movimento) 
            {
                out.println(getStanzaCorrente().getNome());
                out.println("================================================");
                out.println(getStanzaCorrente().getDescrizione());
            }
        }
    }
}
