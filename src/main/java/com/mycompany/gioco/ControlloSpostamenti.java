/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;

import com.mycompany.tipi.Oggetto;

/**
 *
 * @author gabri
 */
public class ControlloSpostamenti {
    public boolean checkZattera(Avventura a) {
        if(a.getStanzaCorrente().getNome().equals("BinariMetro")) {
            //Controlla di avere la zattera
            if(a.getInventario().contains(new Oggetto(17))){
                 return true;
            } else {
                 System.out.println("Non puoi attraversare i binari, Ellie non sa nuotare");
                 return false;
             }
        }
        return true;
    }
    
    public boolean checkOggettiCasa(Avventura a) {
        if(a.getStanzaCorrente().getNome().equals("Soggiorno")) {
            //Controlla di avere nell'inventario: pistola, coltello, cibo e munizioni
            if (a.getInventario().contains(new Oggetto(0)) && a.getInventario().contains(new Oggetto(1))
            && a.getInventario().contains(new Oggetto(3)) && a.getInventario().contains(new Oggetto(9))) {
                return true;
            } else {
                System.out.println("Senti di non essere preparato a sufficienza per uscire di casa");
                return false;
            }
        }
        return true;
    }
}
