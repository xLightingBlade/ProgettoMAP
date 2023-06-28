/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.avventura;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Antonio
 * Questa classe viene utilizzata per il salvataggio e caricamento della partita di gioco salvata precedentemente,
 * ma più in generale salva e carica oggetti da file.
 */
public class CaricamentoSalvataggioPartita implements Serializable
{
    //permette di salvare una partita
    public static void salva(Engine partita)
    {
        try
        {
            FileOutputStream outFile = new FileOutputStream("user.dat");
            ObjectOutputStream outStream = new ObjectOutputStream(outFile);
            outStream.writeObject(partita);
            outStream.close();
            System.out.println("Salvataggio completato");
        }
        catch(NotSerializableException ex)
        {
            System.out.println("Errore durante il processo di caricamento. Riprova più tardi");
        }
        catch(IOException ex) 
        {
            System.out.println("Impossibile salvare la partita ora. Riprova più tardi o riavvia il gioco.");
            ex.printStackTrace();
        }
    }
    
    //da eseguire quando l'utente schiaccia sul bottone "carica partita salvata"
    public static boolean carica()
    {
        System.out.println("Caricamento partita salvata");
        Engine partita = null;
        
        try
        {
            FileInputStream inFile = new FileInputStream("user.dat");
            ObjectInputStream inStream = new ObjectInputStream(inFile);
            partita = (Engine) inStream.readObject();//recupera la partita creata
            inStream.close();
            /*
            System.out.println(partita.getGioco().getStanzaCorrente().getNome());
            System.out.println(partita.getGioco().getStanzaCorrente().getDescrizione());
            */
            
            if(partita == null)
            {
                throw new FileVuotoException();
            }
            
            System.out.println("Partita caricata, riprendi a giocare!\n");
        }
        catch(FileNotFoundException fnf)
        {
            System.out.println("Impossibile caricare la partita. Non vi è nessuna partita salvata.");
            fnf.printStackTrace();
            return false;
        }
        catch(FileVuotoException ex)
        {
            System.out.println("Impossibile caricare la partita. Ne hai salvata una ma è vuota.");
            ex.printStackTrace();
            return false;
        }
        catch(IOException | ClassNotFoundException ex) 
        {
            System.out.println("Impossibile caricare la partita salvata. Riprova più tardi.");
            ex.printStackTrace();
            return false;
        }
        
        avviaPartita(partita);
        return true;
    }
    
    //Avvia una qualunque partita
    public static void avviaPartita(Engine partita)
    {
        partita.esegui();
    }
}
