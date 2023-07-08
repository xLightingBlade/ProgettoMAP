/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gioco;

import com.mycompany.avventura.CaricamentoDati;
import static com.mycompany.gioco.BehaviourController.mostraDialogoStanza;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
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
    public void run () 
    {
        try {
            mostraDialogoStanza(fileFrasi);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TimerGioco.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {
            //ripartono i dialoghi
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(dialoghiTimerScaduto), "UTF-8"));
            CaricamentoDati dialoghi = new CaricamentoDati(in);
            dialoghi.start();
            dialoghi.join();
        } 
        catch (FileNotFoundException | InterruptedException ex) 
        {
            System.out.println("Errore nel caricamento dati. File non trovato.");
            System.exit(0);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(TimerGioco.class.getName()).log(Level.SEVERE, null, ex);
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
