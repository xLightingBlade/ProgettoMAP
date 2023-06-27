/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.avventura;

import com.mycompany.parser.ParserOutput;
import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.Comando;
import com.mycompany.tipi.Stanza;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gabri
 * Questa classe astratta modella la struttura fondamentale 
 * che una qualunque avventura testuale deve avere.
 */
public abstract class StrutturaGioco {

    private List<Stanza> stanze = new ArrayList<>();//la lista delle stanze del gioco

    private List<Comando> comandi = new ArrayList<>();//la lista dei comandi del gioco

    private final List<Oggetto> inventario = new ArrayList<>();//la lista degli oggetti del gioco

    private Stanza stanzaCorrente;//so sempre in che stanza mi trovo in qualunque momento

    public List<Stanza> getStanze() {
        return stanze;
    }
    
    public void setStanze(List<Stanza> stanze) {
        this.stanze = stanze;
    }

    public List<Comando> getComandi() {
        return comandi;
    }
    
    public void setComandi(List<Comando> comandi) {
        this.comandi = comandi;
    }

    public Stanza getStanzaCorrente() {
        return stanzaCorrente;
    }

    public void setStanzaCorrente(Stanza stanzaCorrente) {
        this.stanzaCorrente = stanzaCorrente;
    }

    public List<Oggetto> getInventario() {
        return inventario;
    }

    public abstract void init() throws Exception;//Questo metodo (implementato da una classe che erediterà da questa)
    //inizializzerà tutti i dati del gioco, come stanze, oggetti ecc...

     
    public abstract void prossimaMossa(ParserOutput p);//In questo metodo è racchiusa la storia dell'avventura testuale

}
