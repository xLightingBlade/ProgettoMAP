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
 * @author pierpaolo
 */
public abstract class StrutturaGioco {

    private final List<Stanza> stanze = new ArrayList<>();

    private final List<Comando> comandi = new ArrayList<>();

    private final List<Oggetto> inventario = new ArrayList<>();

    private Stanza stanzaCorrente;

    public List<Stanza> getStanze() {
        return stanze;
    }

    public List<Comando> getComandi() {
        return comandi;
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

    public abstract void init() throws Exception;

    public abstract void prossimaMossa(ParserOutput p, PrintStream out);

}
