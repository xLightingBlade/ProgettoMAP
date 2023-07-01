/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.swing;

/**
 *
 * @author santo
 */
public class OpzioniMenu {
    Stato statoCorrente;
    
    public enum Stato {
        AVVIA,
        CARICA,
    }
    
    public OpzioniMenu() {
        statoCorrente = null;
    }
    
    public Stato getStatoCorrente() {
        return statoCorrente;
    }
    
    public void setStatoCorrente(Stato nuovoStato) {
        statoCorrente = nuovoStato;
    }
}
