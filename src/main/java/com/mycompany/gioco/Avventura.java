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
        //Commands
        /*
        Comando nord = new Comando(TipoComando.NORD, "nord");
        nord.setAlias(new String[]{"n", "N", "Nord", "NORD"});
        getComandi().add(nord);
        Comando iventory = new Comando(TipoComando.INVENTARIO, "inventario");
        iventory.setAlias(new String[]{"inv"});
        getComandi().add(iventory);
        Comando sud = new Comando(TipoComando.SUD, "sud");
        sud.setAlias(new String[]{"s", "S", "Sud", "SUD"});
        getComandi().add(sud);
        Comando est = new Comando(TipoComando.EST, "est");
        est.setAlias(new String[]{"e", "E", "Est", "EST"});
        getComandi().add(est);
        Comando ovest = new Comando(TipoComando.OVEST, "ovest");
        ovest.setAlias(new String[]{"o", "O", "Ovest", "OVEST"});
        getComandi().add(ovest);
        Comando end = new Comando(TipoComando.FINE, "end");
        end.setAlias(new String[]{"end", "fine", "esci", "muori", "ammazzati", "ucciditi", "suicidati", "exit"});
        getComandi().add(end);
        Comando look = new Comando(TipoComando.GUARDA, "osserva");
        look.setAlias(new String[]{"guarda", "vedi", "trova", "cerca", "descrivi"});
        getComandi().add(look);
        Comando pickup = new Comando(TipoComando.PRENDI, "raccogli");
        pickup.setAlias(new String[]{"prendi"});
        getComandi().add(pickup);
        Comando open = new Comando(TipoComando.APRI, "apri");
        open.setAlias(new String[]{});
        getComandi().add(open);
        Comando push = new Comando(TipoComando.SPINGI, "premi");
        push.setAlias(new String[]{"spingi", "attiva"});
        getComandi().add(push);
        //Rooms
        Stanza hall = new Stanza(0, "Corridoio", "Sei appena tornato a casa e non sai cosa fare.\nTi ricordi che non hai ancora aperto quel fantastico regalo di tua zia Lina.\n"
                + " Sarà il caso di cercarlo e di giocarci!");
        hall.setLook("Sei nel corridoio, a nord vedi il bagno, a sud il soggiorno e ad ovest la tua cameretta, forse il gioco sarà lì?");
        Stanza livingRoom = new Stanza(1, "Soggiorno", "Ti trovi nel soggiorno.\nCi sono quei mobili marrone scuro che hai sempre odiato e delle orribili sedie.");
        livingRoom.setLook("Non c'è nulla di interessante qui.");
        Stanza kitchen = new Stanza(2, "Cucina", "Ti trovi nella solita cucina.\nMobili bianchi, maniglie azzurre, quello strano lampadario che adoravi tanto quando eri piccolo.\n"
                + "C'è un tavolo con un bel portafrutta e una finestra.");
        kitchen.setLook("La solita cucina, ma noti una chiave vicino al portafrutta.");
        Stanza bathroom = new Stanza(3, "Bagno", "Sei nel bagno.\nQuanto tempo passato qui dentro...meglio non pensarci...");
        bathroom.setLook("Vedo delle batterie sul mobile alla destra del lavandino.");
        Stanza yourRoom = new Stanza(4, "La tua cameratta", "Finalmente la tua cameretta!\nQuesto luogo ti è così famigliare...ma non ricordi dove hai messo il nuovo regalo di zia Lina.");
        yourRoom.setLook("C'è un armadio bianco, di solito ci conservi i tuoi giochi.");
        //map
        kitchen.setEast(livingRoom);
        livingRoom.setNorth(hall);
        livingRoom.setWest(kitchen);
        hall.setSouth(livingRoom);
        hall.setWest(yourRoom);
        hall.setNorth(bathroom);
        bathroom.setSouth(hall);
        yourRoom.setEast(hall);
        getStanze().add(kitchen);
        getStanze().add(livingRoom);
        getStanze().add(hall);
        getStanze().add(bathroom);
        getStanze().add(yourRoom);
        //obejcts
        Oggetto battery = new Oggetto(1, "batteria", "Un pacco di batterie, chissà se sono cariche.");
        battery.setAlias(new String[]{"batterie", "pile", "pila"});
        bathroom.getObjects().add(battery);
        ContenitoreOggetti wardrobe = new ContenitoreOggetti(2, "armadio", "Un semplice armadio.");
        wardrobe.setAlias(new String[]{"guardaroba", "vestiario"});
        wardrobe.setOpenable(true);
        wardrobe.setPickupable(false);
        wardrobe.setOpen(false);
        yourRoom.getObjects().add(wardrobe);
        Oggetto toy = new Oggetto(3, "giocattolo", "Il gioco che ti ha regalato zia Lina.");
        toy.setAlias(new String[]{"gioco", "robot"});
        toy.setPushable(true);
        toy.setPush(false);
        wardrobe.add(toy);
        Oggetto kkey = new Oggetto(4, "chiave", "Usa semplice chiave come tante altre.");
        toy.setAlias(new String[]{"key"});
        toy.setPushable(false);
        toy.setPush(false);
        kitchen.getObjects().add(kkey);
        //set starting room
        setCurrentRoom(hall);
        */
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
                    if (p.getOggetto().getId() == 3) {
                        end(out);
                    }
                } else if (p.getOggettoInventario() != null && p.getOggettoInventario().isSpingibile()) {
                    out.println("Hai premuto: " + p.getOggettoInventario().getNome());
                    if (p.getOggettoInventario().getId() == 3) {
                        end(out);
                    }
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

    private void end(PrintStream out) {
        out.println("Premi il pulsante del giocattolo e in seguito ad una forte esplosione la tua casa prende fuoco...\ntu e tuoi famigliari cercate invano di salvarvi e venite avvolti dalle fiamme...\nè stata una morte CALOROSA...addio!");
        System.exit(0);
    }
}
