/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;

import com.mycompany.avventura.CaricamentoDati;
import static com.mycompany.gioco.BehaviourController.mostraDialogoStanza;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Serializable;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Antonio
 */
public class TimerGioco extends TimerTask implements Serializable  
{
    private boolean tempoScaduto;
    
    public TimerGioco()
    {
        tempoScaduto = false;
    }
    
    //Questo metodo dovrà salvare la partita e terminarla poichè il tempo è scaduto e l'utente ha perso.
    @Override
    public void run() 
    {
        String filePath = ".//the_last_of_us(storia)//Dialoghi//guardie_prendono_Joel_e_Ellie.txt";
        mostraDialogoStanza(filePath);
        System.out.println( "Hai perso! Nasconditi prima che arrivino le guardie. Riprova!\nCaricamento, attendi.\n");
        
        try 
        {
            //ripartono i dialoghi
            BufferedReader in = new BufferedReader(new FileReader(".//the_last_of_us(storia)//Dialoghi//Passaggio_segreto.txt"));
            CaricamentoDati dialoghi = new CaricamentoDati(in);
            dialoghi.start();
            dialoghi.join();
        } 
        catch (FileNotFoundException | InterruptedException ex) 
        {
            System.out.println("Errore nel caricamento dati. File non trovato.");
            System.exit(0);
        }
        finally
        {
            tempoScaduto = true;
        }
    }

    public boolean isTempoScaduto() 
    {
        return tempoScaduto;
    }

    public void setTempoScaduto(boolean tempoScaduto) 
    {
        this.tempoScaduto = tempoScaduto;
    }
    
    
}
