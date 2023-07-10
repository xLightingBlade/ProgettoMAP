/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.parser;

import com.mycompany.tipi.Oggetto;
import com.mycompany.tipi.Comando;
import java.io.Serializable;

/**
 *
 * @author gabri
 */
public class ParserOutput implements Serializable {

    private Comando comando;

    private Oggetto oggetto;
    
    private Oggetto oggettoInventario;

    
    public ParserOutput(Comando comando, Oggetto oggetto) {
        this.comando = comando;
        this.oggetto = oggetto;
    }

    public ParserOutput(Comando comando, Oggetto oggetto, Oggetto oggettoInv) {
        this.comando = comando;
        this.oggetto = oggetto;
        this.oggettoInventario = oggettoInv;
    }


    
    public Comando getComando() {
        return comando;
    }

    public void setComando(Comando comando) {
        this.comando = comando;
    }

    public Oggetto getOggetto() {
        return oggetto;
    }

    public void setOggetto(Oggetto oggetto) {
        this.oggetto = oggetto;
    }

    public Oggetto getOggettoInventario() {
        return oggettoInventario;
    }

    public void setOggettoInventario(Oggetto oggettoInventario) {
        this.oggettoInventario = oggettoInventario;
    }

}
