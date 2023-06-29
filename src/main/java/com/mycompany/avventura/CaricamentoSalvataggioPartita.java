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
    //questo attributo statico conterrà una partita salvata, oppure null se non è stata salvata nessuna partita
    private static Engine partitaSalvata;
            
    //permette di salvare una partita
    public static void salva(Engine partita)
    {
        try
        {
            partitaSalvata = partita;
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
            //ex.printStackTrace();
        }
    }
    
    
    /*
      Questo metodo carica una partita salvata precedentemente, se presente.
      Il metodo restituisce :
        1 se il file user.dat non esiste
        2 se il file user.dat è vuoto
        3 se c'è un errore di I/O o un errore durante il casting per inserire l'oggetto letto nell'oggetto partita
        4 se la partita salvata è stata caricata correttamente
    */
    public static int carica()
    {   
        try
        {
            FileInputStream inFile = new FileInputStream("user.dat");
            ObjectInputStream inStream = new ObjectInputStream(inFile);
            partitaSalvata = (Engine) inStream.readObject();//recupera la partita creata
            inStream.close();
            
            if(partitaSalvata == null)
            {
                throw new FileVuotoException();
            }
            
            System.out.println("Partita caricata, riprendi a giocare!\n");
        }
        catch(FileNotFoundException fnf)
        {
            //fnf.printStackTrace();
            return 1;
        }
        catch(FileVuotoException ex)
        {
            //ex.printStackTrace();
            return 2;
        }
        catch(IOException | ClassNotFoundException ex) 
        {
            //ex.printStackTrace();
            return 3;
        }
 
        return 4;
    }
    
    
    //Avvia la partita salvata
    public static void avviaPartitaSalvata()
    {
        partitaSalvata.esegui();
    }
    
    
    
}
