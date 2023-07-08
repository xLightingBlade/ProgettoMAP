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

/**
 *
 * @author Antonio
 */
public class TimerGioco extends TimerTask implements Serializable  
{
    private boolean tempoScaduto;
    private String fileFrasi;
    private String dialoghiTimerScaduto;
    
    public TimerGioco(String fileFrasi,String dialoghiTimerScaduto)
    {
        tempoScaduto = false;
        this.fileFrasi = fileFrasi;
        this.dialoghiTimerScaduto = dialoghiTimerScaduto;
    }
    
    //Questo metodo dovrà salvare la partita e terminarla poichè il tempo è scaduto e l'utente ha perso.
    @Override
    public void run() 
    {
        mostraDialogoStanza(fileFrasi);
        
        try 
        {
            //ripartono i dialoghi
            BufferedReader in = new BufferedReader(new FileReader(dialoghiTimerScaduto));
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
