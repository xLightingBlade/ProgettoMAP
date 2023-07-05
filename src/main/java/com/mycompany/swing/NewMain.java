/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.swing;

/**
 *
 * @author santo
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TastierinoJFrame t = new TastierinoJFrame(4, 8, 0);
        t.setVisible(true);
        
        DocumentFrame d = new DocumentFrame("hola", "Un lucchetto ti ostacola, la combinazione è sconosciuta."
                + "Tre cifre dovrai trovare per aprire questa porta."
                + "Ascolta attentamente.Questi tre indizi ti diranno la sequenza corretta che dovrai indovinare:"
                + "1) La somma delle tre cifre è 12."
                + "2) Il primo numero è la metà del secondo."
                + "3) La differenza tra il primo numero ed il terzo è 4."
                + "(sono cifre comprese tra 0 e 9)");
        d.setVisible(true);
    }
    
}
